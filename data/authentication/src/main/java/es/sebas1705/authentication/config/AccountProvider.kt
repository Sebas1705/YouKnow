package es.sebas1705.authentication.config

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.GoogleAuthProvider
import es.sebas1705.common.states.ErrorDataType

/**
 * How the current user proves who they are before a sensitive operation such as deleting the account.
 *
 * @property Password email and password: the app asks for the password
 * @property Google Google account: the app shows the Google account picker again
 * @property Other no session or a provider the app does not sign in with: there is nothing to ask
 *
 * @since 1.6.0
 * @author Sebas1705 11/11/2026
 */
enum class AccountProvider {
    Password,
    Google,
    Other
}

/**
 * Resolves the [AccountProvider] from the Firebase provider ids of the user (either one works when
 * both are linked; the password wins because it needs no UI outside the app).
 *
 * @since 1.6.0
 * @author Sebas1705 11/11/2026
 */
internal fun accountProviderOf(providerIds: List<String>): AccountProvider = when {
    EmailAuthProvider.PROVIDER_ID in providerIds -> AccountProvider.Password
    GoogleAuthProvider.PROVIDER_ID in providerIds -> AccountProvider.Google
    else -> AccountProvider.Other
}

/**
 * A rejected password or a different Google account is the user's to fix
 * ([ErrorDataType.FORBIDDEN]), unlike any other failure.
 *
 * @since 1.6.0
 * @author Sebas1705 11/11/2026
 */
internal fun reauthFailureType(exception: Exception): ErrorDataType = when (exception) {
    is FirebaseAuthInvalidCredentialsException,
    is FirebaseAuthInvalidUserException -> ErrorDataType.FORBIDDEN
    else -> ErrorDataType.BAD_REQUEST
}

/**
 * Firebase only deletes an account that signed in recently: [ErrorDataType.UNAUTHORIZED] tells the
 * caller to re-authenticate and try again.
 *
 * @since 1.6.0
 * @author Sebas1705 11/11/2026
 */
internal fun deleteFailureType(exception: Exception): ErrorDataType = when (exception) {
    is FirebaseAuthRecentLoginRequiredException -> ErrorDataType.UNAUTHORIZED
    else -> ErrorDataType.BAD_REQUEST
}
