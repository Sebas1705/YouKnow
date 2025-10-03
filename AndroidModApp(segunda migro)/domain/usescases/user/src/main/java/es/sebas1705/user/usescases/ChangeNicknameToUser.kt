package es.sebas1705.user.usescases


import es.sebas1705.common.utlis.extensions.types.collect
import es.sebas1705.firestore.repository.FirestoreRepository

/**
 * Use case to change nickname to user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to change nickname to user
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class ChangeNicknameToUser(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        firebaseId: String,
        nickname: String,
        onLoading: () -> Unit = {},
        onEmptySuccess: () -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.changeNicknameToUser(firebaseId, nickname)
        .collect(onLoading, onEmptySuccess, onError)
}