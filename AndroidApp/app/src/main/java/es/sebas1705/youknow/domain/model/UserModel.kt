package es.sebas1705.youknow.domain.model

import com.google.firebase.auth.FirebaseUser
import es.sebas1705.youknow.data.firebase.authentication.config.ProviderAuth
import es.sebas1705.youknow.data.firebase.firestore.documents.UserDocument

data class UserModel(
    val firebaseId: String,
    val email: String,
    val provider: ProviderAuth,
    val nickName: String,
    val photoUrl: String?,
    val groupId: String?,
    val points: Int,
    val credits: Int,
    val friends: List<String>
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
            credits = 1000,
            friends = emptyList(),
        )

        fun newEmailUser(nickName: String, firebaseUser: FirebaseUser) = UserModel(
            firebaseId = firebaseUser.uid,
            email = firebaseUser.email!!,
            provider = ProviderAuth.EMAIL,
            nickName = nickName,
            photoUrl = null,
            groupId = null,
            points = 0,
            credits = 1000,
            friends = emptyList(),
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
                friends = emptyList(),
                provider = ProviderAuth.EMAIL
            )
        }
    }

    /**
     * Transform the [UserModel] to a [UserDocument]
     *
     * @return [UserDocument]: the user document
     */
    fun toUserDocument() = UserDocument(
        email,
        provider.ordinal,
        nickName,
        photoUrl,
        groupId,
        points,
        credits,
        friends
    )

    fun memberId() = "$firebaseId-$nickName"

}
