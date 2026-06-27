package es.sebas1705.chat

import es.sebas1705.realtime.repository.RealtimeRepository

class RemoveMessagesListener(
    private val realtimeRepository: RealtimeRepository
) {
    operator fun invoke() {
        realtimeRepository.removeMessagesListener()
    }
}
