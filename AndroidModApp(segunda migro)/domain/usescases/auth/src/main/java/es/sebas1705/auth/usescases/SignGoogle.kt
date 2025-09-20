package es.sebas1705.auth.usescases

import android.content.Context
import es.sebas1705.authentication.repository.AuthenticationRepository
import es.sebas1705.common.utlis.extensions.types.catcher

/**
 * Use case to sign with google
 *
 * @property authenticationRepository [AuthenticationRepository]: repository to sign with google
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class SignGoogle(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(
        context: Context,
        onLoading: () -> Unit = {},
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = authenticationRepository.signWithGoogle(context).catcher(
        onLoading,
        onEmptySuccess = { onSuccess(authenticationRepository.getCurrentUser()!!.uid) },
        onError
    )
}
