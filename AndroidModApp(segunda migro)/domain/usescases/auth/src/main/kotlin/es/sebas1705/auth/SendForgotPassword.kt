package es.sebas1705.auth

import es.sebas1705.common.utlis.extensions.types.collect
import es.sebas1705.repositories.interfaces.IAuthenticationRepository
import jakarta.inject.Inject

/**
 * Use case to send forgot password
 *
 * @property authenticationRepository [IAuthenticationRepository]: repository to send forgot password
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class SendForgotPassword @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository,
) {
    suspend operator fun invoke(
        email: String,
        onLoading: () -> Unit = {},
        onEmptySuccess: () -> Unit,
        onError: (String) -> Unit
    ) = authenticationRepository.sendForgotPassword(email)
        .collect(onLoading, onEmptySuccess, onError)
}