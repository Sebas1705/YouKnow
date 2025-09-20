package es.sebas1705.auth.usescases


import es.sebas1705.authentication.repository.AuthenticationRepository
import es.sebas1705.common.utlis.extensions.types.catcher
import es.sebas1705.models.social.UserModel

/**
 * Use case to sign up with email
 *
 * @property authenticationRepository [AuthenticationRepository]: repository to sign up with email
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class SignUpEmailUser(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(
        nickname: String,
        email: String,
        password: String,
        onLoading: () -> Unit = {},
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) = authenticationRepository.signUpWithEmail(email, password).catcher(
        onLoading,
        onEmptySuccess = {
            onSuccess(
                UserModel.newEmailUser(
                    nickname,
                    authenticationRepository.getCurrentUser()!!
                )
            )
        },
        onError
    )
}