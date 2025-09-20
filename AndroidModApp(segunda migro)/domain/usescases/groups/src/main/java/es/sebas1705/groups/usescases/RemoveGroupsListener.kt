package es.sebas1705.groups.usescases


import es.sebas1705.realtime.repository.RealtimeRepository

/**
 * Use case to remove the listener to get groups
 *
 * @property realtimeRepository [RealtimeRepository]: repository to remove the listener
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class RemoveGroupsListener(
    private val realtimeRepository: RealtimeRepository
) {
    operator fun invoke() {
        realtimeRepository.removeGroupsListener()
    }
}