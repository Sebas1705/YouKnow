package es.sebas1705.user.usescases


import es.sebas1705.common.utlis.extensions.types.collect
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.models.social.GroupModel
import es.sebas1705.realtime.repository.RealtimeRepository

/**
 * Use case to remove a group to user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to remove a group to user
 * @property realTimeRepository [RealtimeRepository]: repository to remove a group to user
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class RemoveGroupToUser(
    private val firestoreRepository: FirestoreRepository,
    private val realTimeRepository: RealtimeRepository
) {
    suspend operator fun invoke(
        group: GroupModel,
        firebaseId: String,
        onLoading: () -> Unit = {},
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.removeGroupFromUser(firebaseId)
        .collect(
            onLoading,
            onEmptySuccess = {
                val list = group.members.toMutableList()
                list.remove(firebaseId)
                realTimeRepository.changeMembersToGroup(
                    group.groupId,
                    list.toList()
                ).collect(
                    onEmptySuccess = { onSuccess() },
                    onError = onError
                )
            },
            onError
        )
}