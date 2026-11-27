package es.sebas1705.auth

import es.sebas1705.common.utlis.extensions.types.awaitResult
import es.sebas1705.repositories.interfaces.IAuthenticationRepository

/**
 * Use case to confirm the current (Google) user's identity before a sensitive operation, such as
 * deleting the account: it shows the Google account picker again, like signing in does.
 *
 * @property authenticationRepository [IAuthenticationRepository]: repository to re-authenticate
 *
 * @since 1.6.0
 * @author Sebas1705 11/11/2026
 */
class ReauthenticateWithGoogle(
    private val authenticationRepository: IAuthenticationRepository
) {
    suspend operator fun invoke(): ReauthResult =
        authenticationRepository.reauthenticateWithGoogle().awaitResult().toReauthResult()
}
