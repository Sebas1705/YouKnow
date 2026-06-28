package es.sebas1705.realtime.datasources

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.common.classes.TaskFlow
import es.sebas1705.common.states.DataState
import es.sebas1705.common.utlis.alias.DataEmptyFlow
import es.sebas1705.common.utlis.extensions.types.logE
import es.sebas1705.realtime.config.SettingsRT
import es.sebas1705.realtime.jsons.GroupJson
import javax.inject.Inject

/**
 * Data source for managing Group JSON data in Firebase Realtime Database.
 *
 * @property database [FirebaseDatabase]: Firebase database
 * @property logEventDataSource [LogEventDataSource]: Data source for logging events
 *
 * @since 0.1.0
 * @author Sebas1705 22/09/2025
 */
class GroupRealtimeDataSource @Inject constructor(
    private val database: FirebaseDatabase,
    private val logEventDataSource: LogEventDataSource
) {

    //Managers:
    private val taskFlow = TaskFlow(
        this.javaClass.kotlin,
        SettingsRT.ERROR_GENERIC_MESSAGE_FAIL,
        SettingsRT.ERROR_GENERIC_MESSAGE_EX
    ) { clazz, error ->
        logE(error)
        logEventDataSource.logError(clazz, error)
    }

    //References:
    private val groupsReference = database.getReference(SettingsRT.GROUPS_REFERENCE)

    //Listeners:
    private var groupsListener: ValueEventListener? = null

    //Tasks:
    /**
     * Add a group to the database
     *
     * @param groupId [String]: Group id
     * @param groupJson [GroupJson]: Group to add
     *
     * @return [DataEmptyFlow] with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun addGroup(
        groupId: String,
        groupJson: GroupJson
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = {
            groupsReference.child(groupId).setValue(groupJson)
        },
        onSuccessListener = { DataState.EmptySuccess },
    )

    /**
     * Delete a group from the database
     *
     * @param groupId [String]: Group to delete
     *
     * @return [DataEmptyFlow] with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun removeGroup(
        groupId: String
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = { groupsReference.child(groupId).removeValue() },
        onSuccessListener = { DataState.EmptySuccess },
    )

    /**
     * Change the members of a group
     *
     * @param groupId [String]: Group id
     * @param newMembersList [List]<[String]>: New members list
     *
     * @return [DataEmptyFlow] with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun changeMembersToGroup(
        groupId: String,
        newMembersList: List<String>
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = {
            groupsReference.child(groupId)
                .updateChildren(mapOf(SettingsRT.MEMBERS_REFERENCE to newMembersList))
        },
        onSuccessListener = { DataState.EmptySuccess }
    )

    /**
     * Push members to a group
     *
     * @param groupId [String]: Group id
     * @param newMembersList [List]<[String]>: New members list
     *
     * @return [DataEmptyFlow] with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun pushMembersToGroup(
        groupId: String,
        newMembersList: List<String>
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = {
            groupsReference.child(groupId)
                .child(SettingsRT.MEMBERS_REFERENCE)
                .setValue(newMembersList)
        },
        onSuccessListener = { DataState.EmptySuccess }
    )

    //Sets Listeners:
    /**
     * Get the groups from the database
     *
     * @property onDataChange ([List]<[GroupJson]>) -> [Unit]: Function to execute when the data changes
     * @property onCancelled ([String]) -> [Unit]: Function to execute when the data is cancelled
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun setGroupsListener(
        onDataChange: (List<Pair<GroupJson, String>>) -> Unit,
        onCancelled: (String) -> Unit
    ) {
        groupsListener = groupsReference.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val groups = mutableListOf<Pair<GroupJson, String>>()
                    snapshot.children.forEach {
                        val group = it.getValue(GroupJson::class.java)
                        if (group != null) {
                            groups.add(group to it.key!!)
                        }
                    }
                    onDataChange(groups)
                }

                override fun onCancelled(error: DatabaseError) {
                    onCancelled(error.message)
                }

            }
        )
    }

    //Removes Listeners:
    /**
     * Remove the listener from the groups
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun removeGroupsListener() {
        groupsListener?.let {
            groupsReference.removeEventListener(it)
            groupsListener = null
        }
    }

}