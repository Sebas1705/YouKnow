package es.sebas1705.user.usescases


import es.sebas1705.common.utlis.extensions.types.collect
import es.sebas1705.firestore.repository.FirestoreRepository

/**
 * Use case to delete data user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to delete data user
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class DeleteDataUser(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        firebaseId: String,
        onLoading: () -> Unit = {},
        onEmptySuccess: () -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.deleteDataUser(firebaseId)
        .collect(onLoading, onEmptySuccess, onError)
}