package es.sebas1705.user.usescases


import es.sebas1705.common.utlis.extensions.types.collect
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.models.social.UserModel

/**
 * Use case to store what a finished round earned: points and credits of the user, together with
 * the event the security rules require for any gain (see docs/FIREBASE.md).
 *
 * @property firestoreRepository [FirestoreRepository]: repository that stores the rewards
 *
 * @since 1.6.0
 * @author Sebas1705 11/11/2026
 */
class AddRoundRewards(
    private val firestoreRepository: FirestoreRepository
) {
    /**
     * @param user [UserModel]: the user as it was read before the round
     * @param points [Int]: points earned, more than 0
     * @param credits [Int]: credits earned, at most a tenth of the points
     * @param dailyDate [String]?: UTC day (yyyy-MM-dd) when the round is the daily challenge
     */
    suspend operator fun invoke(
        user: UserModel,
        points: Int,
        credits: Int,
        dailyDate: String? = null,
        onLoading: () -> Unit = {},
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.addRoundRewards(
        user.firebaseId, user.points, user.credits, points, credits, dailyDate
    ).collect(onLoading, onEmptySuccess = onSuccess, onError)
}
