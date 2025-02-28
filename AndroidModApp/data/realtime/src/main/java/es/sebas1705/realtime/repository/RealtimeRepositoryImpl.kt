package es.sebas1705.realtime.repository
/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import es.sebas1705.common.managers.ClassLogData
import es.sebas1705.common.managers.Layer
import es.sebas1705.common.managers.Repository
import es.sebas1705.common.managers.ResponseState
import es.sebas1705.common.managers.TaskFlowManager
import es.sebas1705.common.utlis.alias.FlowResponseNothing
import es.sebas1705.analytics.repository.AnalyticsRepository
import es.sebas1705.realtime.config.SettingsRT
import es.sebas1705.realtime.jsons.GroupJson
import es.sebas1705.realtime.jsons.MessageJson
import javax.inject.Inject

/**
 * Realtime repository implementation
 *
 * @property database [FirebaseDatabase]: Firebase database
 * @property analyticsRepository [AnalyticsRepository]: Analytics repository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class RealtimeRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase,
    private val analyticsRepository: AnalyticsRepository
) : RealtimeRepository, ClassLogData {

    //Properties:
    override val layer: Layer = Layer.Data
    override val repository: Repository = Repository.Realtime

    //Managers:
    private val taskFlowManager = TaskFlowManager(
        this,
        analyticsRepository::logError,
        SettingsRT.ERROR_GENERIC_MESSAGE_FAIL,
        SettingsRT.ERROR_GENERIC_MESSAGE_EX
    )

    //References:
    private val defaultReference = database.reference.child(SettingsRT.DEFAULT_REFERENCE)
    private val globalChatReference = database.reference.child(SettingsRT.CHAT_GLOBAL_REFERENCE)
    private val groupsReference = database.reference.child(SettingsRT.GROUPS_REFERENCE)

    //Listeners:
    private var messagesListener: ValueEventListener? = null
    private var groupsListener: ValueEventListener? = null

    //Tasks:
    override fun addMessageToGlobalChat(
        messageId: String,
        value: MessageJson
    ): FlowResponseNothing = taskFlowManager.taskFlowProducer(
        taskAction = { globalChatReference.child(messageId).setValue(value) },
        onSuccessListener = { ResponseState.EmptySuccess },
    )

    override fun addGroup(
        groupId: String,
        groupJson: GroupJson
    ): FlowResponseNothing = taskFlowManager.taskFlowProducer(
        taskAction = {
            groupsReference.child(groupId).setValue(groupJson)
        },
        onSuccessListener = { ResponseState.EmptySuccess },
    )

    override fun removeGroup(
        groupId: String
    ): FlowResponseNothing = taskFlowManager.taskFlowProducer(
        taskAction = { groupsReference.child(groupId).removeValue() },
        onSuccessListener = { ResponseState.EmptySuccess },
    )

    override fun changeMembersToGroup(
        groupId: String,
        newMembersList: List<String>
    ): FlowResponseNothing = taskFlowManager.taskFlowProducer(
        taskAction = {
            groupsReference.child(groupId)
                .updateChildren(mapOf(SettingsRT.MEMBERS_REFERENCE to newMembersList))
        },
        onSuccessListener = { ResponseState.EmptySuccess }
    )

    override fun pushMembersToGroup(
        groupId: String,
        newMembersList: List<String>
    ): FlowResponseNothing = taskFlowManager.taskFlowProducer(
        taskAction = {
            groupsReference.child(groupId)
                .child(SettingsRT.MEMBERS_REFERENCE)
                .setValue(newMembersList)
        },
        onSuccessListener = { ResponseState.EmptySuccess }
    )

    //Sets Listeners:
    override fun setMessagesListener(
        onDataChange: (List<Pair<MessageJson, String>>) -> Unit,
        onCancelled: (String) -> Unit
    ) {
        messagesListener = globalChatReference.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val messages = mutableListOf<Pair<MessageJson, String>>()
                    snapshot.children.forEach {
                        val message = it.getValue(MessageJson::class.java)
                        if (message != null) {
                            messages.add(message to it.key!!)
                        }
                    }
                    if (messages.size > SettingsRT.MAX_MESSAGES_ON_GLOBAL_CHAT) {
                        val key = messages[0].second
                        globalChatReference.child(key).removeValue()
                    }
                    onDataChange(messages)
                }

                override fun onCancelled(error: DatabaseError) {
                    onCancelled(error.message)
                }

            }
        )
    }

    override fun setGroupsListener(
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
    override fun removeMessagesListener() {
        messagesListener?.let {
            globalChatReference.removeEventListener(it)
            messagesListener = null
        }
    }

    override fun removeGroupsListener() {
        groupsListener?.let {
            groupsReference.removeEventListener(it)
            groupsListener = null
        }
    }
}