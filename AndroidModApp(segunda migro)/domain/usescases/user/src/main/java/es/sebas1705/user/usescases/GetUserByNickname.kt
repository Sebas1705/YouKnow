package es.sebas1705.user.usescases


import es.sebas1705.common.utlis.extensions.types.collect
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.mappers.toUserModel
import es.sebas1705.models.social.UserModel

/**
 * Use case to get user by nickname
 *
 * @property firestoreRepository [FirestoreRepository]: repository to get user by nickname
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class GetUserByNickname(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        nickname: String,
        onLoading: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.getUserByNickname(nickname).collect(
        onLoading,
        onSuccess = {
            onSuccess(it.first.toUserModel(it.second))
        },
        onError
    )
}