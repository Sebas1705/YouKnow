package es.sebas1705.auth.usescases


import es.sebas1705.authentication.repository.AuthenticationRepository

/**
 * Use case to sign out
 *
 * @property authenticationRepository [AuthenticationRepository]: repository to sign out
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class SignOut(
    private val authenticationRepository: AuthenticationRepository,
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