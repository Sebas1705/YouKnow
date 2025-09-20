package es.sebas1705.groups.usescases

import es.sebas1705.mappers.toGroupModel
import es.sebas1705.models.social.GroupModel
import es.sebas1705.realtime.repository.RealtimeRepository

/**
 * Use case to set a listener to get groups
 *
 * @property realtimeRepository [RealtimeRepository]: repository to get groups
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class SetGroupsListener(
    private val realtimeRepository: RealtimeRepository
) {
    operator fun invoke(
        onSuccess: (List<GroupModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        realtimeRepository.setGroupsListener(
            onDataChange = {
                onSuccess(it.map { group ->
                    group.first.toGroupModel(group.second)
                })
            },
            onCancelled = onError
        )
    }
}