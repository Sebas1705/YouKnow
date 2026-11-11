package es.sebas1705.authentication.config

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import es.sebas1705.common.states.ErrorDataType
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * How Firebase's account-deletion failures and providers become what the app acts on. Robolectric,
 * because the Firebase exceptions call `android.text.TextUtils`.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class AccountProviderTest {

    @Test
    fun `the password wins when both providers are linked`() {
        assertEquals(AccountProvider.Password, accountProviderOf(listOf("firebase", "google.com", "password")))
    }

    @Test
    fun `a Google-only account is Google`() {
        assertEquals(AccountProvider.Google, accountProviderOf(listOf("firebase", "google.com")))
    }

    @Test
    fun `no provider the app signs in with, or nobody signed in, is other`() {
        assertEquals(AccountProvider.Other, accountProviderOf(listOf("firebase", "phone")))
        assertEquals(AccountProvider.Other, accountProviderOf(emptyList()))
    }

    @Test
    fun `a rejected password or a different user is forbidden`() {
        assertEquals(
            ErrorDataType.FORBIDDEN,
            reauthFailureType(FirebaseAuthInvalidCredentialsException("ERROR_WRONG_PASSWORD", "wrong"))
        )
        assertEquals(
            ErrorDataType.FORBIDDEN,
            reauthFailureType(FirebaseAuthInvalidUserException("ERROR_USER_MISMATCH", "mismatch"))
        )
    }

    @Test
    fun `other reauthentication failures are a plain bad request`() {
        assertEquals(ErrorDataType.BAD_REQUEST, reauthFailureType(FirebaseException("network")))
    }

    @Test
    fun `a stale sign-in on delete is unauthorized so the caller can reauthenticate`() {
        assertEquals(
            ErrorDataType.UNAUTHORIZED,
            deleteFailureType(FirebaseAuthRecentLoginRequiredException("ERROR_REQUIRES_RECENT_LOGIN", "stale"))
        )
    }

    @Test
    fun `other delete failures are a plain bad request`() {
        assertEquals(ErrorDataType.BAD_REQUEST, deleteFailureType(FirebaseException("network")))
        assertEquals(ErrorDataType.BAD_REQUEST, deleteFailureType(IllegalStateException("not logged")))
    }
}
