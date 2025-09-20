package es.sebas1705.auth.usescases


import es.sebas1705.authentication.repository.AuthenticationRepository
import es.sebas1705.common.utlis.extensions.types.catcher

/**
 * Use case to send forgot password
 *
 * @property authenticationRepository [AuthenticationRepository]: repository to send forgot password
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class SendForgotPassword(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(
        email: String,
        onLoading: () -> Unit = {},
        onEmptySuccess: () -> Unit,
        onError: (String) -> Unit
    ) = authenticationRepository.sendForgotPassword(email)
        .catcher(onLoading, onEmptySuccess, onError)
}