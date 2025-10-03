package es.sebas1705.home.groups.viewmodel


import es.sebas1705.common.classes.mvi.MVIBaseIntent
import es.sebas1705.models.social.GroupModel
import es.sebas1705.models.social.UserModel

/**
 * Sealed interface that represents the possible actions of the [GroupsViewModel].
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
sealed interface GroupsIntent : MVIBaseIntent {

    data class CreateGroup(
        val name: String,
        val description: String,
        val userModel: UserModel
    ) : GroupsIntent

    data class JoinGroup(
        val groupModel: GroupModel,
        val userModel: UserModel
    ) : GroupsIntent

    data class OutGroup(
        val groupModel: GroupModel,
        val userModel: UserModel
    ) : GroupsIntent

    data class KickGroup(
        val groupModel: GroupModel,
        val userToKickMemberId: String
    ) : GroupsIntent

    data class LoadGroups(
        val myGroupId: String?
    ) : GroupsIntent

    data object ClearGroups : GroupsIntent

}