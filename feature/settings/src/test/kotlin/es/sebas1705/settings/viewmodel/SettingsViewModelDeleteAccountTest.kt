package es.sebas1705.settings.viewmodel

import es.sebas1705.auth.AuthUsesCases
import es.sebas1705.auth.GetAccountProvider
import es.sebas1705.auth.ReauthResult
import es.sebas1705.auth.ReauthenticateWithGoogle
import es.sebas1705.auth.ReauthenticateWithPassword
import es.sebas1705.authentication.config.AccountProvider
import es.sebas1705.settings.ReadSettingsUseCase
import es.sebas1705.settings.UpdateSettingsUseCase
import es.sebas1705.user.UserUsesCases
import es.sebas1705.user.usescases.DeleteAccountResult
import es.sebas1705.user.usescases.DeleteCurrentUser
import es.sebas1705.user.usescases.RemoveUserListener
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

/**
 * The account deletion flow of [SettingsViewModel]: intents in, state out, with the use cases mocked.
 * The ViewModel works on the real `Dispatchers.IO`, so every assertion waits (wall clock) for the
 * state it expects.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class SettingsViewModelDeleteAccountTest {

    private val getAccountProvider: GetAccountProvider = mock()
    private val reauthenticateWithPassword: ReauthenticateWithPassword = mock()
    private val reauthenticateWithGoogle: ReauthenticateWithGoogle = mock()
    private val deleteCurrentUser: DeleteCurrentUser = mock()
    private val removeUserListener: RemoveUserListener = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun viewModel(provider: AccountProvider = AccountProvider.Password): SettingsViewModel {
        whenever(getAccountProvider()).thenReturn(provider)
        val authUsesCases = AuthUsesCases(
            getFirebaseUser = mock(),
            signInEmailUser = mock(),
            signUpEmailUser = mock(),
            sendForgotPassword = mock(),
            signGoogle = mock(),
            signOut = mock(),
            getAccountProvider = getAccountProvider,
            reauthenticateWithPassword = reauthenticateWithPassword,
            reauthenticateWithGoogle = reauthenticateWithGoogle
        )
        val userUsesCases = UserUsesCases(
            setUserListener = mock(),
            removeUserListener = removeUserListener,
            saveUser = mock(),
            setGroupToUser = mock(),
            removeGroupToUser = mock(),
            addCreditsToUser = mock(),
            addPointsToUser = mock(),
            changePhotoToUser = mock(),
            changeNicknameToUser = mock(),
            deleteDataUser = mock(),
            deleteCurrentUser = deleteCurrentUser,
            uploadProfilePhoto = mock(),
            getUser = mock(),
            containsUser = mock(),
            getUserRanking = mock(),
            getUserByNickname = mock()
        )
        return SettingsViewModel(
            readSettingsUseCase = mock<ReadSettingsUseCase>(),
            updateSettingsUseCase = mock<UpdateSettingsUseCase>(),
            authUsesCases = authUsesCases,
            userUsesCases = userUsesCases
        )
    }

    /** Waits (wall clock) until the state satisfies [predicate]; the ViewModel emits it from IO. */
    private suspend fun SettingsViewModel.awaitState(predicate: (SettingsState) -> Boolean): SettingsState =
        withContext(Dispatchers.Default) { withTimeout(WAIT_MS) { uiState.first(predicate) } }

    private suspend fun SettingsViewModel.awaitIdle() =
        withContext(Dispatchers.Default) { withTimeout(WAIT_MS) { loading.first { !it } } }

    private fun SettingsViewModel.send(intent: SettingsIntent) = sendIntent(intent)

    // region dialogs

    @Test
    fun `tapping delete account opens the confirmation and dismissing closes it`() = runTest {
        val viewModel = viewModel()

        viewModel.send(SettingsIntent.RequestDeleteAccount)
        assertEquals(DeleteAccountStep.Confirm, viewModel.uiState.value.deleteAccountStep)

        viewModel.send(SettingsIntent.DismissDeleteAccount)
        assertEquals(DeleteAccountStep.Hidden, viewModel.uiState.value.deleteAccountStep)
    }

    @Test
    fun `dismissing clears the error of the last attempt`() = runTest {
        val viewModel = viewModel(AccountProvider.Other)
        whenever(deleteCurrentUser()).thenReturn(DeleteAccountResult.Failed("x", dataDeleted = false))
        viewModel.send(SettingsIntent.ConfirmDeleteAccount)
        viewModel.awaitState { it.deleteAccountError == DeleteAccountError.Generic }

        viewModel.send(SettingsIntent.DismissDeleteAccount)

        assertEquals(DeleteAccountStep.Hidden, viewModel.uiState.value.deleteAccountStep)
        assertEquals(null, viewModel.uiState.value.deleteAccountError)
    }

    // endregion

    // region email and password

    @Test
    fun `an email user is asked for the password before anything is deleted`() = runTest {
        val viewModel = viewModel(AccountProvider.Password)

        viewModel.send(SettingsIntent.ConfirmDeleteAccount)

        val state = viewModel.awaitState { it.deleteAccountStep == DeleteAccountStep.ReauthPassword }
        assertEquals(null, state.deleteAccountError)
        verify(deleteCurrentUser, never()).invoke()
    }

    @Test
    fun `the right password deletes the account and sends the user to the auth screen`() = runTest {
        val viewModel = viewModel(AccountProvider.Password)
        whenever(reauthenticateWithPassword("secret")).thenReturn(ReauthResult.Success)
        whenever(deleteCurrentUser()).thenReturn(DeleteAccountResult.Success)

        viewModel.send(SettingsIntent.ReauthenticateWithPassword("secret"))

        val state = viewModel.awaitState { it.accountDeleted }
        assertEquals(DeleteAccountStep.Hidden, state.deleteAccountStep)
        assertEquals(null, state.deleteAccountError)
        viewModel.awaitIdle()
        verify(removeUserListener).invoke()
    }

    @Test
    fun `a wrong password keeps the password dialog open and deletes nothing`() = runTest {
        val viewModel = viewModel(AccountProvider.Password)
        whenever(reauthenticateWithPassword("nope")).thenReturn(ReauthResult.WrongCredentials)

        viewModel.send(SettingsIntent.ReauthenticateWithPassword("nope"))

        val state = viewModel.awaitState { it.deleteAccountError != null }
        assertEquals(DeleteAccountError.WrongCredentials, state.deleteAccountError)
        assertEquals(DeleteAccountStep.ReauthPassword, state.deleteAccountStep)
        assertFalse(state.accountDeleted)
        viewModel.awaitIdle()
        verify(deleteCurrentUser, never()).invoke()
    }

    @Test
    fun `a wrong password can be corrected and then deletes the account`() = runTest {
        val viewModel = viewModel(AccountProvider.Password)
        whenever(reauthenticateWithPassword("nope")).thenReturn(ReauthResult.WrongCredentials)
        whenever(reauthenticateWithPassword("secret")).thenReturn(ReauthResult.Success)
        whenever(deleteCurrentUser()).thenReturn(DeleteAccountResult.Success)

        viewModel.send(SettingsIntent.ReauthenticateWithPassword("nope"))
        viewModel.awaitState { it.deleteAccountError == DeleteAccountError.WrongCredentials }
        viewModel.awaitIdle()
        viewModel.send(SettingsIntent.ReauthenticateWithPassword("secret"))

        val state = viewModel.awaitState { it.accountDeleted }
        assertEquals(null, state.deleteAccountError)
    }

    @Test
    fun `a reauthentication that fails for another reason shows a generic error on the password dialog`() = runTest {
        val viewModel = viewModel(AccountProvider.Password)
        whenever(reauthenticateWithPassword("secret")).thenReturn(ReauthResult.Failed("network"))

        viewModel.send(SettingsIntent.ReauthenticateWithPassword("secret"))

        val state = viewModel.awaitState { it.deleteAccountError != null }
        assertEquals(DeleteAccountError.Generic, state.deleteAccountError)
        assertEquals(DeleteAccountStep.ReauthPassword, state.deleteAccountStep)
        viewModel.awaitIdle()
        verify(deleteCurrentUser, never()).invoke()
    }

    // endregion

    // region Google

    @Test
    fun `a Google user is reauthenticated with Google and then deleted`() = runTest {
        val viewModel = viewModel(AccountProvider.Google)
        whenever(reauthenticateWithGoogle()).thenReturn(ReauthResult.Success)
        whenever(deleteCurrentUser()).thenReturn(DeleteAccountResult.Success)

        viewModel.send(SettingsIntent.ConfirmDeleteAccount)

        viewModel.awaitState { it.accountDeleted }
        verify(reauthenticateWithPassword, never()).invoke(any())
    }

    @Test
    fun `choosing another Google account deletes nothing`() = runTest {
        val viewModel = viewModel(AccountProvider.Google)
        whenever(reauthenticateWithGoogle()).thenReturn(ReauthResult.WrongCredentials)

        viewModel.send(SettingsIntent.ConfirmDeleteAccount)

        val state = viewModel.awaitState { it.deleteAccountError != null }
        assertEquals(DeleteAccountError.WrongCredentials, state.deleteAccountError)
        assertEquals(DeleteAccountStep.Confirm, state.deleteAccountStep)
        viewModel.awaitIdle()
        verify(deleteCurrentUser, never()).invoke()
    }

    @Test
    fun `dismissing the Google sheet leaves the confirmation open with an error`() = runTest {
        val viewModel = viewModel(AccountProvider.Google)
        whenever(reauthenticateWithGoogle()).thenReturn(ReauthResult.Failed("cancelled"))

        viewModel.send(SettingsIntent.ConfirmDeleteAccount)

        val state = viewModel.awaitState { it.deleteAccountError != null }
        assertEquals(DeleteAccountError.Generic, state.deleteAccountError)
        assertEquals(DeleteAccountStep.Confirm, state.deleteAccountStep)
    }

    // endregion

    // region no provider

    @Test
    fun `a user with no provider to confirm is deleted straight away`() = runTest {
        val viewModel = viewModel(AccountProvider.Other)
        whenever(deleteCurrentUser()).thenReturn(DeleteAccountResult.Success)

        viewModel.send(SettingsIntent.ConfirmDeleteAccount)

        viewModel.awaitState { it.accountDeleted }
        verify(reauthenticateWithGoogle, never()).invoke()
        verify(reauthenticateWithPassword, never()).invoke(any())
    }

    // endregion

    // region failures and retries

    @Test
    fun `firebase asking for a recent login goes back to the confirmation, and confirming again finishes`() = runTest {
        val viewModel = viewModel(AccountProvider.Password)
        whenever(reauthenticateWithPassword("secret")).thenReturn(ReauthResult.Success)
        whenever(deleteCurrentUser())
            .thenReturn(DeleteAccountResult.RecentLoginRequired)
            .thenReturn(DeleteAccountResult.Success)

        viewModel.send(SettingsIntent.ReauthenticateWithPassword("secret"))
        val failed = viewModel.awaitState { it.deleteAccountError != null }
        assertEquals(DeleteAccountError.RecentLoginRequired, failed.deleteAccountError)
        assertEquals(DeleteAccountStep.Confirm, failed.deleteAccountStep)
        assertFalse(failed.accountDeleted)
        viewModel.awaitIdle()

        // Retry: confirm -> password again -> the deletion runs a second time.
        viewModel.send(SettingsIntent.ConfirmDeleteAccount)
        viewModel.awaitState { it.deleteAccountStep == DeleteAccountStep.ReauthPassword }
        viewModel.send(SettingsIntent.ReauthenticateWithPassword("secret"))

        val done = viewModel.awaitState { it.accountDeleted }
        assertEquals(null, done.deleteAccountError)
        verify(deleteCurrentUser, times(2)).invoke()
    }

    @Test
    fun `data deleted but the account kept is reported, and retrying finishes the job`() = runTest {
        val viewModel = viewModel(AccountProvider.Other)
        whenever(deleteCurrentUser())
            .thenReturn(DeleteAccountResult.Failed("network", dataDeleted = true))
            .thenReturn(DeleteAccountResult.Success)

        viewModel.send(SettingsIntent.ConfirmDeleteAccount)
        val failed = viewModel.awaitState { it.deleteAccountError != null }
        assertEquals(DeleteAccountError.DataDeletedAccountKept, failed.deleteAccountError)
        assertEquals(DeleteAccountStep.Confirm, failed.deleteAccountStep)
        viewModel.awaitIdle()

        viewModel.send(SettingsIntent.ConfirmDeleteAccount)

        viewModel.awaitState { it.accountDeleted }
        verify(deleteCurrentUser, times(2)).invoke()
    }

    @Test
    fun `a failure before the data was deleted is a generic error`() = runTest {
        val viewModel = viewModel(AccountProvider.Other)
        whenever(deleteCurrentUser()).thenReturn(DeleteAccountResult.Failed("chat", dataDeleted = false))

        viewModel.send(SettingsIntent.ConfirmDeleteAccount)

        val state = viewModel.awaitState { it.deleteAccountError != null }
        assertEquals(DeleteAccountError.Generic, state.deleteAccountError)
        assertFalse(state.accountDeleted)
    }

    @Test
    fun `an exception from the use case is a generic error, not a crash`() = runTest {
        val viewModel = viewModel(AccountProvider.Other)
        whenever(deleteCurrentUser()).thenThrow(IllegalStateException("boom"))

        viewModel.send(SettingsIntent.ConfirmDeleteAccount)

        val state = viewModel.awaitState { it.deleteAccountError != null }
        assertEquals(DeleteAccountError.Generic, state.deleteAccountError)
        viewModel.awaitIdle()
    }

    @Test
    fun `no user is reported and nothing navigates`() = runTest {
        val viewModel = viewModel(AccountProvider.Other)
        whenever(deleteCurrentUser()).thenReturn(DeleteAccountResult.NotSignedIn)

        viewModel.send(SettingsIntent.ConfirmDeleteAccount)

        val state = viewModel.awaitState { it.deleteAccountError != null }
        assertEquals(DeleteAccountError.NotSignedIn, state.deleteAccountError)
        assertFalse(state.accountDeleted)
        viewModel.awaitIdle()
        verify(removeUserListener, never()).invoke()
    }

    // endregion

    // region state handling

    @Test
    fun `dismissing while the deletion runs is ignored`() = runTest {
        val viewModel = viewModel(AccountProvider.Other)
        val release = CompletableDeferred<Unit>()
        val started = CompletableDeferred<Unit>()
        whenever(deleteCurrentUser()).thenAnswer {
            started.complete(Unit)
            kotlinx.coroutines.runBlocking { release.await() }
            DeleteAccountResult.Success
        }
        viewModel.send(SettingsIntent.RequestDeleteAccount)

        viewModel.send(SettingsIntent.ConfirmDeleteAccount)
        withContext(Dispatchers.Default) { withTimeout(WAIT_MS) { started.await() } }
        assertTrue(viewModel.loading.value)
        viewModel.send(SettingsIntent.DismissDeleteAccount)
        assertEquals(DeleteAccountStep.Confirm, viewModel.uiState.value.deleteAccountStep)

        release.complete(Unit)
        viewModel.awaitState { it.accountDeleted }
    }

    @Test
    fun `the screen acknowledges the navigation`() = runTest {
        val viewModel = viewModel(AccountProvider.Other)
        whenever(deleteCurrentUser()).thenReturn(DeleteAccountResult.Success)
        viewModel.send(SettingsIntent.ConfirmDeleteAccount)
        viewModel.awaitState { it.accountDeleted }

        viewModel.send(SettingsIntent.AccountDeletedHandled)

        assertFalse(viewModel.uiState.value.accountDeleted)
    }

    // endregion

    private companion object {
        const val WAIT_MS = 5_000L
    }
}
