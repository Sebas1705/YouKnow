package es.sebas1705.youknow.core.classes.managers/*
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

import com.google.android.gms.tasks.Task
import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData
import es.sebas1705.youknow.data.model.ErrorResponseType
import es.sebas1705.youknow.data.model.ResponseState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Class that will manage the task flow
 *
 * @property genericFailMessage [String]: Generic fail message
 * @property genericExMessage [String]: Generic exception message
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class TaskFlowManager(
    private val classLogData: ClassLogData,
    private val loggerAction: (ClassLogData, String) -> Unit,
    private val genericFailMessage: String,
    private val genericExMessage: String
) {

    /**
     * Function that will produce a task flow
     *
     * @param assertChecker () -> String?: Function to check if the operation is valid
     * @param taskAction () -> Task<Q>: Function to get the data
     * @param onSuccessListener (Task<Q>) -> ResponseState<T>: Function to handle the onCompleteListener
     *
     * @return [Flow]<[ResponseState]<Q>>: Flow with the response of the operation
     */
    fun <T, Q> taskFlowProducer(
        assertChecker: suspend () -> String? = { null },
        taskAction: suspend () -> Task<Q>,
        onSuccessListener: (Q) -> ResponseState<T>
    ): Flow<ResponseState<T>> = callbackFlow {
        val sender = this@callbackFlow::trySendBlocking
        try {
            sender(ResponseState.Loading)
            assertChecker()?.let { sender(createResponse(ErrorResponseType.BadRequest, it)) }
                ?: taskAction()
                    .addOnSuccessListener {
                        sender(onSuccessListener(it))
                    }.addOnFailureListener {
                        sender(
                            ResponseState.Error(
                                classLogData,
                                ErrorResponseType.BadRequest,
                                it.message ?: genericFailMessage,
                                loggerAction
                            )
                        )
                    }
        } catch (e: Exception) {
            sender(
                ResponseState.Error(
                    classLogData,
                    ErrorResponseType.InternalError,
                    e.message ?: genericExMessage,
                    loggerAction
                )
            )
        }
        awaitClose {
            channel.close()
            close()
        }
    }

    fun createResponse(
        responseType: ErrorResponseType, message: String
    ) = ResponseState.Error(
        classLogData, responseType, message, loggerAction
    )

}