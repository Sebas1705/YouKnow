package es.sebas1705.youknow.domain.model

import es.sebas1705.youknow.data.firebase.authentication.config.ProviderAuth
import es.sebas1705.youknow.data.firebase.firestore.documents.UserDocument
import es.sebas1705.youknow.data.local.database.entities.UserEntity

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
    fun toUserDocument() = UserDocument(
        firebaseId,
        email,
        provider.ordinal,
        nickName,
        photoUrl,
        groupId,
        points,
        credits,
        friends
    )


    fun toUserEntity() = UserEntity(
        firebaseId,
        email,
        provider.ordinal,
        nickName,
        photoUrl,
        groupId,
        points,
        credits,
        friends
    )

}
