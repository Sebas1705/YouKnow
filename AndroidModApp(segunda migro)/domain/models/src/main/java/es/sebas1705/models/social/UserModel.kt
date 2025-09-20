package es.sebas1705.models.social


import com.google.firebase.auth.FirebaseUser
import es.sebas1705.core.resources.ProviderAuth

/**
 * Model to represent a user
 *
 * @property firebaseId [String]: Firebase ID of the user
 * @property email [String]: Email of the user
 * @property provider [ProviderAuth]: Provider of the user
 * @property nickName [String]: Nickname of the user
 * @property photoUrl [String]: Photo URL of the user
 * @property groupId [String]: Group ID of the user
 * @property points [Int]: Points of the user
 * @property credits [Int]: Credits of the user
 * @property friends [List]<[String]>: List of friends of the user
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class UserModel(
    val firebaseId: String,
    val email: String,
    val provider: ProviderAuth,
    val nickName: String,
    val photoUrl: String?,
    val groupId: String?,
    val points: Int,
    val credits: Int
) {

    companion object {
        fun newGoogleUser(firebaseUser: FirebaseUser) = UserModel(
            firebaseId = firebaseUser.uid,
            email = firebaseUser.email!!,
            provider = ProviderAuth.GOOGLE,
            nickName = firebaseUser.displayName!!,
            photoUrl = firebaseUser.photoUrl!!.toString(),
            groupId = null,
            points = 0,
            credits = 1000
        )

        fun newEmailUser(nickName: String, firebaseUser: FirebaseUser) = UserModel(
            firebaseId = firebaseUser.uid,
            email = firebaseUser.email!!,
            provider = ProviderAuth.EMAIL,
            nickName = nickName,
            photoUrl = null,
            groupId = null,
            points = 0,
            credits = 1000
        )

        fun default(): UserModel {
            return UserModel(
                firebaseId = "firebaseId",
                email = "email",
                nickName = "nickName",
                photoUrl = "photoUrl",
                credits = 0,
                points = 0,
                groupId = "groupId",
                provider = ProviderAuth.EMAIL
            )
        }
    }
}
