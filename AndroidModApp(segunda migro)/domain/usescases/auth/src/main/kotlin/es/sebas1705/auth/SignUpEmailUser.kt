package es.sebas1705.auth

import es.sebas1705.common.utlis.extensions.types.collect
import es.sebas1705.models.social.UserModel
import es.sebas1705.repositories.interfaces.IAuthenticationRepository
import jakarta.inject.Inject

/**
 * Use case to sign up with email
 *
 * @property authenticationRepository [IAuthenticationRepository]: repository to sign up with email
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class SignUpEmailUser @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository,
) {
    suspend operator fun invoke(
        nickname: String,
        email: String,
        password: String,
        onLoading: () -> Unit = {},
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) = authenticationRepository.signUpWithEmail(email, password).collect(
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