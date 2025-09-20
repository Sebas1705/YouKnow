package es.sebas1705.auth.usescases


import es.sebas1705.authentication.repository.AuthenticationRepository
import es.sebas1705.common.utlis.extensions.types.catcher

/**
 * Use case to sign in with email
 *
 * @property authenticationRepository [AuthenticationRepository]: repository to sign in with email
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class SignInEmailUser(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        onLoading: () -> Unit = {},
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = authenticationRepository.signInWithEmail(email, password).catcher(
        onLoading,
        onEmptySuccess = { onSuccess(authenticationRepository.getCurrentUser()!!.uid) },
        onError
    )
}