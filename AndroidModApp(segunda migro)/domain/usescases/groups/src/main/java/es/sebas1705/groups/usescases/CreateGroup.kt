package es.sebas1705.groups.usescases


import es.sebas1705.mappers.toGroupJson
import es.sebas1705.models.social.GroupModel
import es.sebas1705.models.social.UserModel
import es.sebas1705.realtime.repository.RealtimeRepository

/**
 * Use case to create a group
 *
 * @property realtimeRepository [RealtimeRepository]: repository to create the group
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class CreateGroup(
    private val realtimeRepository: RealtimeRepository
) {
    suspend operator fun invoke(
        name: String,
        description: String,
        userModel: UserModel,
        onSuccess: suspend (GroupModel) -> Unit,
        onError: (String) -> Unit
    ) {
        val groupModel = GroupModel(
            name = name,
            description = description,
            members = listOf(userModel.firebaseId),
            leaderUID = userModel.firebaseId
        )
        realtimeRepository.addGroup(
            groupModel.groupId,
            groupModel.toGroupJson()
        ).collect { flow ->
            flow.catcher(
                onEmptySuccess = { onSuccess(groupModel) },
                onError = { onError(it.message) },
            )
        }
    }
}