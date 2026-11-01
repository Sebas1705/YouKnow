package es.sebas1705.firestore.documents

/**
 * Data class to represent the user document in Firestore
 *
 * @property provider [Int]: Provider
 * @property nickName [String]: Nickname
 * @property photoUrl [String]: Photo url
 * @property groupId [String]: Group id
 * @property points [Int]: Points
 * @property credits [Int]: Credits
 * @property logged [Boolean]: Logged
 *
 * Every signed-in user can read this document (ranking, nickname search, group members), so it must
 * never hold private data. The email in particular stays in Firebase Auth. Documents written by
 * older versions may still carry an `email` field: the mapper ignores it, and
 * `firebase/rules-test/migrations/remove-user-emails.mjs` removes it.
 *
 * @author Sebas1705 22/09/2025
 * @since 1.0.0
 */
data class UserDocument(
    val provider: Int = 0,
    val nickName: String = "",
    val photoUrl: String? = null,
    val groupId: String? = null,
    val points: Int = 0,
    val credits: Int = 0,
    val logged: Boolean = false
)