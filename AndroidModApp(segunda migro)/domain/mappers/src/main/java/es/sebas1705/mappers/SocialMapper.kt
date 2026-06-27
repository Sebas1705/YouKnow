package es.sebas1705.mappers

import es.sebas1705.firestore.documents.NewDocument
import es.sebas1705.models.social.NewModel
import es.sebas1705.models.social.GroupModel
import es.sebas1705.realtime.jsons.GroupJson

fun NewDocument.toNewModel() = NewModel(
    titleEs = titleES,
    titleEn = titleEN,
    bodyEs = bodyES,
    bodyEn = bodyEN
)

fun GroupJson.toGroupModel(groupId: String) = GroupModel(
    name = groupId.substringBeforeLast("-"),
    description = description,
    members = members,
    leaderUID = members.firstOrNull() ?: ""
)

fun GroupModel.toGroupJson() = GroupJson(
    description = description,
    members = members
)
