package es.sebas1705.auth

import es.sebas1705.common.states.DataState
import es.sebas1705.common.states.ErrorDataType
import es.sebas1705.common.utlis.extensions.types.awaitResult
import es.sebas1705.repositories.interfaces.IAuthenticationRepository

/**
 * Use case to confirm the current (email and password) user's identity before a sensitive
 * operation, such as deleting the account.
 *
 * @property authenticationRepository [IAuthenticationRepository]: repository to re-authenticate
 *
 * @since 1.6.0
 * @author Sebas1705 11/11/2026
 */
class ReauthenticateWithPassword(
    private val authenticationRepository: IAuthenticationRepository
) {
    suspend operator fun invoke(password: String): ReauthResult {
        // Firebase would throw on an empty password: it can only be wrong.
        if (password.isEmpty()) return ReauthResult.WrongCredentials
        return authenticationRepository.reauthenticateWithPassword(password).awaitResult().toReauthResult()
    }
}

internal fun DataState<*>.toReauthResult(): ReauthResult = when (this) {
    is DataState.Error ->
        if (type == ErrorDataType.FORBIDDEN) ReauthResult.WrongCredentials else ReauthResult.Failed(message)
    else -> ReauthResult.Success
}
