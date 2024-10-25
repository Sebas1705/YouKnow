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
import es.sebas1705.youknow.data.firebase.realtime.config.isRealtimeSavable
import es.sebas1705.youknow.data.firebase.realtime.jsons.MessageJson
import es.sebas1705.youknow.data.model.ErrorResponseType
import es.sebas1705.youknow.data.model.ResponseState
import es.sebas1705.youknow.domain.model.MessageModel
import kotlinx.coroutines.cancel
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

    private fun <T : Any> checksWrites(child: String, value: T) {
        require(value.isRealtimeSavable()) { "Value must be a real time savable type" }
        require(child.isNotEmpty()) { "Child must not be empty" }
    }

    private fun <T : Any> writeOnDefault(child: String, value: T) {
        checksWrites(child, value)
        val ref = defaultReference.child(child)
        ref.setValue(value)
    }

    private fun <T : Any> writeOnGlobalChat(child: String, value: T) {
        checksWrites(child, value)
        val ref = globalChatReference.child(child)
        ref.setValue(value)
    }

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
    }

    override fun getMessagesFromGlobalChat(): Flow<ResponseState<List<MessageModel>>> =
        callbackFlow {
            try {
                this@callbackFlow.trySendBlocking(ResponseState.Loading)

                val postListener = globalChatReference.addValueEventListener(
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
                            this@callbackFlow.trySendBlocking(ResponseState.Success(messageModels))
                        }

                        override fun onCancelled(error: DatabaseError) {
                            this@callbackFlow.trySendBlocking(
                                ResponseState.Error(
                                    this@RealtimeRepositoryImpl as ClassLogData,
                                    ErrorResponseType.Unauthorized,
                                    error.message,
                                    analyticsRepository::logError
                                )
                            )
                        }

                    }
                )

                globalChatReference.addValueEventListener(postListener)

                awaitClose {
                    globalChatReference.removeEventListener(postListener)
                    channel.close()
                    cancel()
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

        }


}