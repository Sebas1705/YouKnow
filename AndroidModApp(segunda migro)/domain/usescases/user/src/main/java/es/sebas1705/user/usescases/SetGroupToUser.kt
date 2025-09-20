package es.sebas1705.user.usescases


import es.sebas1705.common.utlis.extensions.types.catcher
import es.sebas1705.models.social.GroupModel
import es.sebas1705.models.social.UserModel
import es.sebas1705.realtime.repository.RealtimeRepository
import es.sebas1705.firestore.repository.FirestoreRepository

/**
 * Use case to set a group to user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to set a group to user
 * @property realTimeRepository [RealtimeRepository]: repository to set a group to user
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class SetGroupToUser(
    private val firestoreRepository: FirestoreRepository,
    private val realTimeRepository: RealtimeRepository
) {
    suspend operator fun invoke(
        group: GroupModel,
        user: UserModel,
        creator: Boolean,
        onLoading: () -> Unit = {},
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.setGroupToUser(user.firebaseId, group.groupId)
        .catcher(
            onLoading,
            onEmptySuccess = {
                if (!creator) {
                    val list = group.members.toMutableList()
                    list.add(user.firebaseId)
                    val response =
                        if (group.members.isEmpty())
                            realTimeRepository.pushMembersToGroup(
                                group.groupId,
                                list.toList()
                            )
                        else
                            realTimeRepository.changeMembersToGroup(
                                group.groupId,
                                list.toList()
                            )
                    response.catcher(
                        onEmptySuccess = { onSuccess() },
                        onError = onError
                    )
                } else onSuccess()
            },
            onError
        )
}