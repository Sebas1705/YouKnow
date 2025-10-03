package es.sebas1705.user.usescases


import es.sebas1705.common.utlis.extensions.types.collect
import es.sebas1705.models.social.UserModel
import es.sebas1705.firestore.repository.FirestoreRepository

/**
 * Use case to add credits to user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to add credits to user
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class AddCreditsToUser(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        user: UserModel,
        creditsToAdd: Int,
        onLoading: () -> Unit = {},
        onSuccess: (Int) -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.addCreditsToUser(user.firebaseId, user.credits, creditsToAdd)
        .collect(onLoading, onSuccess, onError)
}