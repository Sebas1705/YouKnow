package es.sebas1705.youknow.data.firebase.realtime.repository
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
import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData
import es.sebas1705.youknow.data.firebase.analytics.config.Layer
import es.sebas1705.youknow.data.firebase.analytics.config.Repository
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepository
import es.sebas1705.youknow.data.firebase.authentication.config.SettingsAuth
import es.sebas1705.youknow.data.firebase.realtime.config.SettingsRT
import es.sebas1705.youknow.data.firebase.realtime.jsons.GroupJson
import es.sebas1705.youknow.data.firebase.realtime.jsons.MessageJson
import es.sebas1705.youknow.data.model.ErrorResponseType
import es.sebas1705.youknow.data.model.ResponseState
import es.sebas1705.youknow.domain.model.GroupModel
import es.sebas1705.youknow.domain.model.MessageModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

/**
 * Realtime repository implementation
 *
 * @property database [FirebaseDatabase]: Firebase database
 *
 * @see es.sebas1705.youknow.data.firebase.realtime.repository.RealtimeRepository
 * @see FirebaseDatabase
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class RealtimeRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase,
    private val analyticsRepository: AnalyticsRepository
) : RealtimeRepository, ClassLogData {

    override val layer: Layer = Layer.Data
    override val repository: Repository = Repository.Realtime

    private val defaultReference = database.reference.child(SettingsRT.DEFAULT_REFERENCE)
    private val globalChatReference = database.reference.child(SettingsRT.CHAT_GLOBAL_REFERENCE)
    private val groupsReference = database.reference.child(SettingsRT.GROUPS_REFERENCE)

    private var messagesListener: ValueEventListener? = null
    private var groupsListener: ValueEventListener? = null

    override fun addMessageToGlobalChat(
        value: MessageModel
    ): Flow<ResponseState<Nothing>> = callbackFlow {
        try {
            this@callbackFlow.trySendBlocking(ResponseState.Loading)
            val ref = globalChatReference.child(value.messageId)
            ref.setValue(value.toMessageJson())
                .addOnCompleteListener {
                    this@callbackFlow.trySendBlocking(ResponseState.EmptySuccess)
                }
                .addOnFailureListener {
                    this@callbackFlow.trySendBlocking(
                        ResponseState.Error(
                            this@RealtimeRepositoryImpl as ClassLogData,
                            ErrorResponseType.BadRequest,
                            it.message ?: SettingsRT.ERROR_GENERIC_MESSAGE,
                            analyticsRepository::logError
                        )
                    )
                }

        } catch (e: Exception) {
            this@callbackFlow.trySendBlocking(
                ResponseState.Error(
                    this@RealtimeRepositoryImpl as ClassLogData,
                    ErrorResponseType.InternalError,
                    e.message ?: SettingsAuth.ERROR_GENERIC_MESSAGE,
                    analyticsRepository::logError
                )
            )
        }
        awaitClose {
            channel.close()
            close()
        }
    }

    override fun setMessagesListener(
        onDataChange: (List<MessageModel>) -> Unit,
        onCancelled: (String) -> Unit
    ) {
        messagesListener = globalChatReference.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val messages = mutableListOf<Pair<String, MessageJson>>()
                    snapshot.children.forEach {
                        val message = it.getValue(MessageJson::class.java)
                        if (message != null) {
                            messages.add(it.key!! to message)
                        }
                    }
                    val messageModels = messages.map { it.second.toMessageModel(it.first) }
                        .sortedBy { it.time }
                    if (messageModels.size > SettingsRT.MAX_MESSAGES_ON_GLOBAL_CHAT) {
                        val key = messageModels[0].messageId
                        globalChatReference.child(key).removeValue()
                    }
                    onDataChange(messageModels)
                }

                override fun onCancelled(error: DatabaseError) {
                    onCancelled(error.message)
                }

            }
        )
    }

    override fun removeMessagesListener() {
        messagesListener?.let {
            globalChatReference.removeEventListener(it)
            messagesListener = null
        }
    }

    override fun addGroup(
        value: GroupModel
    ): Flow<ResponseState<Nothing>> = callbackFlow {
        try {
            this@callbackFlow.trySendBlocking(ResponseState.Loading)
            val ref = groupsReference.child(value.groupId)
            ref.setValue(value.toGroupJson())
                .addOnCompleteListener {
                    this@callbackFlow.trySendBlocking(ResponseState.EmptySuccess)
                }
                .addOnFailureListener {
                    this@callbackFlow.trySendBlocking(
                        ResponseState.Error(
                            this@RealtimeRepositoryImpl as ClassLogData,
                            ErrorResponseType.BadRequest,
                            it.message ?: SettingsRT.ERROR_GENERIC_MESSAGE,
                            analyticsRepository::logError
                        )
                    )
                }

        } catch (e: Exception) {
            this@callbackFlow.trySendBlocking(
                ResponseState.Error(
                    this@RealtimeRepositoryImpl as ClassLogData,
                    ErrorResponseType.InternalError,
                    e.message ?: SettingsAuth.ERROR_GENERIC_MESSAGE,
                    analyticsRepository::logError
                )
            )
        }
        awaitClose {
            channel.close()
            close()
        }
    }

    override fun addMemberToGroup(
        groupId: String,
        newMembersList: List<String>
    ): Flow<ResponseState<Nothing>> = callbackFlow {
        try {
            this@callbackFlow.trySendBlocking(ResponseState.Loading)
            val ref = groupsReference.child(groupId).child(SettingsRT.MEMBERS_REFERENCE)
            ref.setValue(newMembersList)
                .addOnCompleteListener {
                    this@callbackFlow.trySendBlocking(ResponseState.EmptySuccess)
                }
                .addOnFailureListener {
                    this@callbackFlow.trySendBlocking(
                        ResponseState.Error(
                            this@RealtimeRepositoryImpl as ClassLogData,
                            ErrorResponseType.BadRequest,
                            it.message ?: SettingsRT.ERROR_GENERIC_MESSAGE,
                            analyticsRepository::logError
                        )
                    )
                }

        } catch (e: Exception) {
            this@callbackFlow.trySendBlocking(
                ResponseState.Error(
                    this@RealtimeRepositoryImpl as ClassLogData,
                    ErrorResponseType.InternalError,
                    e.message ?: SettingsAuth.ERROR_GENERIC_MESSAGE,
                    analyticsRepository::logError
                )
            )
        }
        awaitClose {
            channel.close()
            close()
        }
    }

    override fun setGroupsListener(
        onDataChange: (List<GroupModel>) -> Unit,
        onCancelled: (String) -> Unit
    ) {
        groupsListener = groupsReference.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val groups = mutableListOf<Pair<String, GroupJson>>()
                    snapshot.children.forEach {
                        val group = it.getValue(GroupJson::class.java)
                        if (group != null) {
                            groups.add(it.key!! to group)
                        }
                    }
                    val messageModels = groups.map { it.second.toGroupModel(it.first) }
                    onDataChange(messageModels)
                }

                override fun onCancelled(error: DatabaseError) {
                    onCancelled(error.message)
                }

            }
        )
    }

    override fun removeGroupsListener() {
        groupsListener?.let {
            groupsReference.removeEventListener(it)
            groupsListener = null
        }
    }
}