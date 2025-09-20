package es.sebas1705.user.usescases


import es.sebas1705.common.utlis.extensions.types.catcher
import es.sebas1705.firestore.repository.FirestoreRepository

/**
 * Use case to change photo to user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to change photo to user
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class ChangePhotoToUser(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        firebaseId: String,
        photoUrl: String,
        onLoading: () -> Unit = {},
        onEmptySuccess: () -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.changePhotoToUser(firebaseId, photoUrl)
        .catcher(onLoading, onEmptySuccess, onError)
}