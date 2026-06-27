package es.sebas1705.mappers

import es.sebas1705.models.social.MessageModel
import es.sebas1705.realtime.jsons.MessageJson

fun MessageModel.toMessageJson() = MessageJson(
    text = text,
    authorName = authorName
)

fun MessageJson.toMessageModel(messageId: String) = MessageModel(
    text = text,
    time = messageId.substringAfterLast("-").toLong(),
    authorId = messageId.substringBeforeLast("-"),
    authorName = authorName
)
