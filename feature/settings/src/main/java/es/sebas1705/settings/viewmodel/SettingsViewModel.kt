package es.sebas1705.settings.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.auth.AuthUsesCases
import es.sebas1705.auth.ReauthResult
import es.sebas1705.authentication.config.AccountProvider
import es.sebas1705.common.classes.mvi.MVIBaseViewModel
import es.sebas1705.datastore.config.DefaultValuesDS
import es.sebas1705.models.SettingsModel
import es.sebas1705.settings.ReadSettingsUseCase
import es.sebas1705.settings.UpdateSettingsUseCase
import es.sebas1705.user.UserUsesCases
import es.sebas1705.user.usescases.DeleteAccountResult
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.cancellation.CancellationException
import javax.inject.Inject

/**
 * ViewModel of the Settings Screen: the app settings and the account deletion flow.
 *
 * Deleting the account is: confirm -> re-authenticate (password, or the Google account picker) ->
 * [UserUsesCases.deleteCurrentUser] -> navigate to the auth screen ([SettingsState.accountDeleted]).
 * Re-authenticating right before deleting is what keeps Firebase from refusing the deletion for an
 * old sign-in after the data is gone; if it happens anyway, or any step fails, the dialog shows why
 * and confirming again retries (every step of the deletion tolerates having been done already).
 *
 * @property readSettingsUseCase [ReadSettingsUseCase]: use case to read the settings
 * @property updateSettingsUseCase [UpdateSettingsUseCase]: use case to save the settings
 * @property authUsesCases [AuthUsesCases]: use cases to know the provider and re-authenticate
 * @property userUsesCases [UserUsesCases]: use cases to delete the account
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val readSettingsUseCase: ReadSettingsUseCase,
    private val updateSettingsUseCase: UpdateSettingsUseCase,
    private val authUsesCases: AuthUsesCases,
    private val userUsesCases: UserUsesCases,
) : MVIBaseViewModel<SettingsState, SettingsIntent>() {

    override fun initState(): SettingsState = SettingsState.default()

    override fun intentHandler(
        intent: SettingsIntent
    ) {
        when (intent) {
            is SettingsIntent.ReadSettings -> readSettings()
            is SettingsIntent.ChangeSettings -> changeSettings(intent)
            is SettingsIntent.RestoreSettings -> restoreSettings()
            is SettingsIntent.RequestDeleteAccount -> showDeleteStep(DeleteAccountStep.Confirm)
            is SettingsIntent.DismissDeleteAccount -> showDeleteStep(DeleteAccountStep.Hidden)
            is SettingsIntent.ConfirmDeleteAccount -> confirmDeleteAccount()
            is SettingsIntent.ReauthenticateWithPassword -> reauthenticateWithPassword(intent.password)
            is SettingsIntent.AccountDeletedHandled -> updateUi { it.copy(accountDeleted = false) }
        }
    }

    private fun readSettings() = execute(Dispatchers.IO) {
        readSettingsUseCase().collect { settings ->
            updateUi {
                it.copy(
                    settings = settings
                )
            }
        }
    }

    private fun restoreSettings() = execute(Dispatchers.IO) {
        updateSettingsUseCase(
            settingsModel = SettingsModel(
                firstTime = DefaultValuesDS.FIRST_TIME,
                musicVolume = DefaultValuesDS.MUSIC_VOLUME,
                soundVolume = DefaultValuesDS.SOUND_VOLUME,
                appContrast = DefaultValuesDS.APP_UI_CONTRAST,
                language = DefaultValuesDS.GAME_LANGUAGE
            )
        )
    }

    private fun changeSettings(
        intent: SettingsIntent.ChangeSettings
    ) = execute(Dispatchers.IO) {
        updateSettingsUseCase(
            settingsModel = intent.settings
        )
    }

    //Account deletion:

    /** Opens or closes a dialog; ignored while a deletion is running, which cannot be cancelled. */
    private fun showDeleteStep(step: DeleteAccountStep) {
        if (loading.value) return
        updateUi { it.copy(deleteAccountStep = step, deleteAccountError = null) }
    }

    private fun confirmDeleteAccount() = execute(Dispatchers.IO) {
        if (loading.value) return@execute
        when (authUsesCases.getAccountProvider()) {
            AccountProvider.Password -> showDeleteStep(DeleteAccountStep.ReauthPassword)
            AccountProvider.Google -> reauthenticateAndDelete(DeleteAccountStep.Confirm) {
                authUsesCases.reauthenticateWithGoogle()
            }
            AccountProvider.Other -> deleteAccount(DeleteAccountStep.Confirm)
        }
    }

    private fun reauthenticateWithPassword(password: String) = execute(Dispatchers.IO) {
        if (loading.value) return@execute
        reauthenticateAndDelete(DeleteAccountStep.ReauthPassword) {
            authUsesCases.reauthenticateWithPassword(password)
        }
    }

    private suspend fun reauthenticateAndDelete(
        stepOnFailure: DeleteAccountStep,
        reauthenticate: suspend () -> ReauthResult
    ) {
        setLoading(true)
        val result = try {
            reauthenticate()
        } catch (cancellation: CancellationException) {
            throw cancellation
        } catch (exception: Exception) {
            ReauthResult.Failed(exception.message.orEmpty())
        }
        when (result) {
            is ReauthResult.Success -> deleteAccount(stepOnFailure)
            is ReauthResult.WrongCredentials ->
                showDeleteFailure(stepOnFailure, DeleteAccountError.WrongCredentials)
            is ReauthResult.Failed -> showDeleteFailure(stepOnFailure, DeleteAccountError.Generic)
        }
    }

    private suspend fun deleteAccount(stepOnFailure: DeleteAccountStep) {
        setLoading(true)
        val result = try {
            userUsesCases.deleteCurrentUser()
        } catch (cancellation: CancellationException) {
            throw cancellation
        } catch (exception: Exception) {
            DeleteAccountResult.Failed(exception.message.orEmpty(), dataDeleted = false)
        }
        when (result) {
            is DeleteAccountResult.Success -> onAccountDeleted()
            // Back to the first dialog: confirming again re-authenticates, then retries.
            is DeleteAccountResult.RecentLoginRequired ->
                showDeleteFailure(DeleteAccountStep.Confirm, DeleteAccountError.RecentLoginRequired)
            is DeleteAccountResult.NotSignedIn ->
                showDeleteFailure(DeleteAccountStep.Confirm, DeleteAccountError.NotSignedIn)
            is DeleteAccountResult.Failed -> showDeleteFailure(
                if (result.dataDeleted) DeleteAccountStep.Confirm else stepOnFailure,
                if (result.dataDeleted) DeleteAccountError.DataDeletedAccountKept else DeleteAccountError.Generic
            )
        }
    }

    private fun onAccountDeleted() {
        // Deleting the Firebase user signs it out; only the profile listener is left to drop.
        userUsesCases.removeUserListener()
        setLoading(false)
        updateUi {
            it.copy(
                deleteAccountStep = DeleteAccountStep.Hidden,
                deleteAccountError = null,
                accountDeleted = true
            )
        }
    }

    private fun showDeleteFailure(step: DeleteAccountStep, error: DeleteAccountError) {
        setLoading(false)
        updateUi { it.copy(deleteAccountStep = step, deleteAccountError = error) }
    }
}
