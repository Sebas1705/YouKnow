package es.sebas1705.auth

import es.sebas1705.repositories.interfaces.IAuthenticationRepository
import jakarta.inject.Inject

/**
 * Use case to sign out
 *
 * @property authenticationRepository [IAuthenticationRepository]: repository to sign out
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class SignOut @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository,
) {
    operator fun invoke(
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val user = authenticationRepository.getCurrentUser()!!
        val out = authenticationRepository.signOut()
        if (out)
            onSuccess(user.uid)
        else
            onError("Not possible to sign out")
    }
}