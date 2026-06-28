package es.sebas1705.groups


import es.sebas1705.groups.usescases.CreateGroup
import es.sebas1705.groups.usescases.RemoveGroup
import es.sebas1705.groups.usescases.RemoveGroupsListener
import es.sebas1705.groups.usescases.SetGroupsListener

/**
 * Use cases for the groups
 *
 * @property createGroup [CreateGroup]: Use case to create a group
 * @property removeGroup [RemoveGroup]: Use case to remove a group
 * @property setGroupsListener [SetGroupsListener]: Use case to set a listener to get groups
 * @property removeGroupsListener [RemoveGroupsListener]: Use case to remove the listener to get groups
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class GroupUsesCases(
    val createGroup: CreateGroup,
    val removeGroup: RemoveGroup,
    val setGroupsListener: SetGroupsListener,
    val removeGroupsListener: RemoveGroupsListener
)