package es.sebas1705.auth

import es.sebas1705.common.states.DataState
import es.sebas1705.common.states.ErrorDataType
import es.sebas1705.common.utlis.alias.DataEmptyFlow
import es.sebas1705.repositories.interfaces.IAuthenticationRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

/** The two ways to confirm identity before deleting the account, and what each failure means. */
class ReauthenticateTest {

    private val repository: IAuthenticationRepository = mock()

    private fun ok(): DataEmptyFlow = flowOf(DataState.Loading, DataState.EmptySuccess)

    private fun failure(type: ErrorDataType, message: String = "boom"): DataEmptyFlow = flowOf(
        DataState.Loading,
        DataState.Error(ReauthenticateTest::class, type, message) { _, _ -> }
    )

    @Test
    fun `a correct password is a success`() = runTest {
        whenever(repository.reauthenticateWithPassword("secret")).thenReturn(ok())

        assertEquals(ReauthResult.Success, ReauthenticateWithPassword(repository)("secret"))
    }

    @Test
    fun `a rejected password is wrong credentials`() = runTest {
        whenever(repository.reauthenticateWithPassword("nope")).thenReturn(failure(ErrorDataType.FORBIDDEN))

        assertEquals(ReauthResult.WrongCredentials, ReauthenticateWithPassword(repository)("nope"))
    }

    @Test
    fun `an empty password is wrong credentials and never reaches Firebase`() = runTest {
        assertEquals(ReauthResult.WrongCredentials, ReauthenticateWithPassword(repository)(""))

        verify(repository, never()).reauthenticateWithPassword(any())
    }

    @Test
    fun `any other password failure keeps its message`() = runTest {
        whenever(repository.reauthenticateWithPassword("secret"))
            .thenReturn(failure(ErrorDataType.BAD_REQUEST, "network"))

        assertEquals(ReauthResult.Failed("network"), ReauthenticateWithPassword(repository)("secret"))
    }

    @Test
    fun `the right Google account is a success`() = runTest {
        whenever(repository.reauthenticateWithGoogle()).thenReturn(ok())

        assertEquals(ReauthResult.Success, ReauthenticateWithGoogle(repository)())
    }

    @Test
    fun `another Google account is wrong credentials`() = runTest {
        whenever(repository.reauthenticateWithGoogle()).thenReturn(failure(ErrorDataType.FORBIDDEN))

        assertEquals(ReauthResult.WrongCredentials, ReauthenticateWithGoogle(repository)())
    }

    @Test
    fun `a dismissed Google sheet is a failure, not wrong credentials`() = runTest {
        whenever(repository.reauthenticateWithGoogle())
            .thenReturn(failure(ErrorDataType.BAD_REQUEST, "cancelled"))

        assertEquals(ReauthResult.Failed("cancelled"), ReauthenticateWithGoogle(repository)())
    }
}
