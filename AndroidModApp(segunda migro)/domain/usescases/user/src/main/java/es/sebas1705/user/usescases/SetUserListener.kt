package es.sebas1705.user.usescases


import es.sebas1705.mappers.toUserModel
import es.sebas1705.models.social.UserModel
import es.sebas1705.firestore.repository.FirestoreRepository

/**
 * Use case to set a listener to get user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to get the user
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class SetUserListener(
    private val firestoreRepository: FirestoreRepository
) {
    operator fun invoke(
        firebaseId: String,
        onDataChange: (UserModel) -> Unit
    ) = firestoreRepository.setUserListener(firebaseId, {
        onDataChange(it.toUserModel(firebaseId))
    })
}