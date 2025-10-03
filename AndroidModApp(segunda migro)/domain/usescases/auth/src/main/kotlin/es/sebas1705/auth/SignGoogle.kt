package es.sebas1705.auth

import es.sebas1705.common.utlis.extensions.types.collect
import es.sebas1705.repositories.interfaces.IAuthenticationRepository
import jakarta.inject.Inject

/**
 * Use case to sign with google
 *
 * @property authenticationRepository [IAuthenticationRepository]: repository to sign with google
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class SignGoogle @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository,
) {
    suspend operator fun invoke(
        onLoading: () -> Unit = {},
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = authenticationRepository.signWithGoogle().collect(
        onLoading,
        onEmptySuccess = { onSuccess(authenticationRepository.getCurrentUser()!!.uid) },
        onError
    )
}
