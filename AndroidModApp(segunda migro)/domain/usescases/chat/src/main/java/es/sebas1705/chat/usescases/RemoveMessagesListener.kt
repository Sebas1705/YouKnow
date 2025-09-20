package es.sebas1705.chat.usescases


import es.sebas1705.realtime.repository.RealtimeRepository

/**
 * Use case to remove the listener to get messages
 *
 * @property realtimeRepository [RealtimeRepository]: repository to remove the listener
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class RemoveMessagesListener(
    private val realtimeRepository: RealtimeRepository
) {
    operator fun invoke() {
        realtimeRepository.removeMessagesListener()
    }
}