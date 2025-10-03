package es.sebas1705.user.usescases


import es.sebas1705.common.utlis.extensions.types.collect
import es.sebas1705.mappers.toUserModel
import es.sebas1705.models.social.UserModel
import es.sebas1705.firestore.repository.FirestoreRepository

/**
 * Use case to get user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to get the user
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class GetUser(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        firebaseId: String,
        onLoading: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.getUser(firebaseId).collect(
        onLoading,
        onSuccess = { onSuccess(it.toUserModel(firebaseId)) },
        onError
    )
}