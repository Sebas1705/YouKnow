package es.sebas1705.auth

import com.google.firebase.auth.FirebaseUser
import es.sebas1705.repositories.interfaces.IAuthenticationRepository
import jakarta.inject.Inject

/**
 * Use case to get firebase user
 *
 * @property authenticationRepository [IAuthenticationRepository]: repository to get firebase user
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class GetFirebaseUser @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
) {
    operator fun invoke(): FirebaseUser? = authenticationRepository.getCurrentUser()
}