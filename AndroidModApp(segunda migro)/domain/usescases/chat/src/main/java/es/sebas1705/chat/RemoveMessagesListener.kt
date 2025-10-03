package es.sebas1705.chat

import es.sebas1705.repositories.interfaces.IRealtimeRepository

/**
 * Use case to remove the listener to get messages
 *
 * @property realtimeRepository [IRealtimeRepository]: repository to remove the listener
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class RemoveMessagesListener(
    private val realtimeRepository: IRealtimeRepository
) {
    operator fun invoke() {
        realtimeRepository.removeMessagesListener()
    }
}