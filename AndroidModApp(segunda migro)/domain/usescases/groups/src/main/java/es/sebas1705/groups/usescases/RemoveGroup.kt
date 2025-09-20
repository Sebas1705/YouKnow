package es.sebas1705.groups.usescases


import es.sebas1705.models.social.GroupModel
import es.sebas1705.realtime.repository.RealtimeRepository

/**
 * Use case to remove a group
 *
 * @property realtimeRepository [RealtimeRepository]: repository to remove the group
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class RemoveGroup(
    private val realtimeRepository: RealtimeRepository
) {
    suspend operator fun invoke(
        groupModel: GroupModel,
        onSuccess: suspend () -> Unit,
        onError: (String) -> Unit
    ) {
        realtimeRepository.removeGroup(groupModel.groupId).collect { flow ->
            flow.catcher(
                onEmptySuccess = { onSuccess() },
                onError = { onError(it.message) },
            )
        }
    }
}