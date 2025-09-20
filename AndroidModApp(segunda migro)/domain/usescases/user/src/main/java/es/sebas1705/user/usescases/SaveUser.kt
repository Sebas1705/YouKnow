package es.sebas1705.user.usescases


import es.sebas1705.common.utlis.extensions.types.catcher
import es.sebas1705.mappers.toUserDocument
import es.sebas1705.models.social.UserModel
import es.sebas1705.firestore.repository.FirestoreRepository

/**
 * Use case to save user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to save the user
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class SaveUser(
    private val firestoreRepository: FirestoreRepository,
) {
    suspend operator fun invoke(
        userModel: UserModel,
        onLoading: () -> Unit = {},
        onEmptySuccess: () -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.saveUser(
        userModel.firebaseId,
        userModel.toUserDocument()
    ).catcher(onLoading, onEmptySuccess, onError)
}