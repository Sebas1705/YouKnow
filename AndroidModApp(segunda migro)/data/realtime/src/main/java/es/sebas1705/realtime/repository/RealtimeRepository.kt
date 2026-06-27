package es.sebas1705.realtime.repository

import es.sebas1705.common.utlis.alias.DataEmptyFlow
import es.sebas1705.realtime.datasources.GroupRealtimeDataSource
import es.sebas1705.realtime.datasources.MessageRealtimeDataSource
import es.sebas1705.realtime.jsons.GroupJson
import es.sebas1705.realtime.jsons.MessageJson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealtimeRepository @Inject constructor(
    private val groupDataSource: GroupRealtimeDataSource,
    private val messageDataSource: MessageRealtimeDataSource
) {
    // Group operations
    fun addGroup(groupId: String, groupJson: GroupJson): DataEmptyFlow = groupDataSource.addGroup(groupId, groupJson)
    fun removeGroup(groupId: String): DataEmptyFlow = groupDataSource.removeGroup(groupId)
    fun changeMembersToGroup(groupId: String, newMembersList: List<String>): DataEmptyFlow = groupDataSource.changeMembersToGroup(groupId, newMembersList)
    fun pushMembersToGroup(groupId: String, newMembersList: List<String>): DataEmptyFlow = groupDataSource.pushMembersToGroup(groupId, newMembersList)
    fun setGroupsListener(onDataChange: (List<Pair<GroupJson, String>>) -> Unit, onCancelled: (String) -> Unit) = groupDataSource.setGroupsListener(onDataChange, onCancelled)
    fun removeGroupsListener() = groupDataSource.removeGroupsListener()

    // Message operations
    fun addMessageToGlobalChat(messageId: String, value: MessageJson): DataEmptyFlow = messageDataSource.addMessageToGlobalChat(messageId, value)
    fun setMessagesListener(onDataChange: (List<Pair<MessageJson, String>>) -> Unit, onCancelled: (String) -> Unit) = messageDataSource.setMessagesListener(onDataChange, onCancelled)
    fun removeMessagesListener() = messageDataSource.removeMessagesListener()
}
