package es.sebas1705.auth

import es.sebas1705.common.utlis.extensions.types.collect
import es.sebas1705.repositories.interfaces.IAuthenticationRepository
import jakarta.inject.Inject

/**
 * Use case to sign in with email
 *
 * @property authenticationRepository [IAuthenticationRepository]: repository to sign in with email
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class SignInEmailUser @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        onLoading: () -> Unit = {},
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = authenticationRepository.signInWithEmail(email, password).collect(
        onLoading,
        onEmptySuccess = { onSuccess(authenticationRepository.getCurrentUser()!!.uid) },
        onError
    )
}