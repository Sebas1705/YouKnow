package es.sebas1705.user.usescases


import es.sebas1705.firestore.repository.FirestoreRepository

/**
 * Use case to remove the listener to get user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to remove the listener
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class RemoveUserListener(
    private val firestoreRepository: FirestoreRepository
) {
    operator fun invoke() = firestoreRepository.removeUserListener()
}