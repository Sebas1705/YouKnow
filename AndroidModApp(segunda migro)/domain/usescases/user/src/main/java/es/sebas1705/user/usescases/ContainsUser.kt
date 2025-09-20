package es.sebas1705.user.usescases


import es.sebas1705.common.utlis.extensions.types.catcher
import es.sebas1705.firestore.repository.FirestoreRepository

/**
 * Use case to check if user exists
 *
 * @property firestoreRepository [FirestoreRepository]: repository to check if user exists
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class ContainsUser(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        firebaseId: String,
        onLoading: () -> Unit = {},
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.containsUser(firebaseId).catcher(
        onLoading,
        onSuccess,
        onError
    )
}