package es.sebas1705.chat.usescases

import es.sebas1705.mappers.toMessageModel
import es.sebas1705.models.social.MessageModel
import es.sebas1705.realtime.repository.RealtimeRepository

/**
 * Use case to set a listener to get messages
 *
 * @property realtimeRepository [RealtimeRepository]: repository to get messages
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class SetMessagesListener(
    private val realtimeRepository: RealtimeRepository
) {
    operator fun invoke(
        onSuccess: (List<MessageModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        realtimeRepository.setMessagesListener(
            onDataChange = {
                onSuccess(
                    it.sortedBy { message ->
                        message.second.split("-")[1].toLong()
                    }.map { message ->
                        message.first.toMessageModel(message.second)
                    }
                )
            },
            onCancelled = onError
        )
    }
}
