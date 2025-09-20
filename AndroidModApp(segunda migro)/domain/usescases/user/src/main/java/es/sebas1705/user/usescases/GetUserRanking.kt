package es.sebas1705.user.usescases


import es.sebas1705.common.utlis.extensions.types.catcher
import es.sebas1705.firestore.repository.FirestoreRepository

/**
 * Use case to get user ranking
 *
 * @property firestoreRepository [FirestoreRepository]: repository to get user ranking
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class GetUserRanking(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        onLoading: () -> Unit,
        onSuccess: (List<Pair<String, Int>>) -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.getUserRanking().catcher(onLoading, onSuccess, onError)
}