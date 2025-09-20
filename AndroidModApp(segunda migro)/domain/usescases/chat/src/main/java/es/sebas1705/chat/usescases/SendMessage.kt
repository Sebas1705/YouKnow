package es.sebas1705.chat.usescases


import es.sebas1705.mappers.toMessageJson
import es.sebas1705.models.social.MessageModel
import es.sebas1705.realtime.repository.RealtimeRepository

/**
 * Use case to send a message
 *
 * @property realtimeRepository [RealtimeRepository]: repository to send the message
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class SendMessage(
    private val realtimeRepository: RealtimeRepository
) {
    suspend operator fun invoke(
        message: String,
        firebaseId: String,
        nickname: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val messageModel = MessageModel(
            text = message,
            time = System.currentTimeMillis(),
            authorId = firebaseId,
            authorName = nickname,
        )
        realtimeRepository.addMessageToGlobalChat(
            messageModel.messageId,
            messageModel.toMessageJson()
        ).collect {
            it.catcher(
                onEmptySuccess = onSuccess,
                onError = { onError(it.message) },
            )
        }
    }
}