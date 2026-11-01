package es.sebas1705.mappers

import es.sebas1705.core.resources.ProviderAuth
import es.sebas1705.firestore.documents.UserDocument
import es.sebas1705.models.social.UserModel

fun UserDocument.toUserModel(firebaseId: String) = UserModel(
    firebaseId = firebaseId,
    provider = ProviderAuth.entries.getOrElse(provider) { ProviderAuth.EMAIL },
    nickName = nickName,
    photoUrl = photoUrl,
    groupId = groupId,
    points = points,
    credits = credits
)

fun UserModel.toUserDocument() = UserDocument(
    provider = provider.ordinal,
    nickName = nickName,
    photoUrl = photoUrl,
    groupId = groupId,
    points = points,
    credits = credits,
    logged = true
)
