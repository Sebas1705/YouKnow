package es.sebas1705.auth

import es.sebas1705.authentication.config.AccountProvider
import es.sebas1705.repositories.interfaces.IAuthenticationRepository

/**
 * Use case to know how the current user confirms their identity: with their password, with their
 * Google account, or not at all.
 *
 * @property authenticationRepository [IAuthenticationRepository]: repository to read the provider
 *
 * @since 1.6.0
 * @author Sebas1705 11/11/2026
 */
class GetAccountProvider(
    private val authenticationRepository: IAuthenticationRepository
) {
    operator fun invoke(): AccountProvider = authenticationRepository.getAccountProvider()
}
