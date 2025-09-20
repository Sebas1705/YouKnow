package es.sebas1705.auth.usescases

import com.google.firebase.auth.FirebaseUser
import es.sebas1705.authentication.repository.AuthenticationRepository

/**
 * Use case to get firebase user
 *
 * @property authenticationRepository [AuthenticationRepository]: repository to get firebase user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class GetFirebaseUser(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke(): FirebaseUser? = authenticationRepository.getCurrentUser()
}