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

import es.sebas1705.youknow.data.model.ResponseState
import es.sebas1705.youknow.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface to write and read data from the realtime database
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
interface RealtimeRepository {

    /**
     * Add a message to the global chat
     *
     * @param value [MessageModel]: Message to add
     *
     * @return [Flow] with the response of the operation
     *
     * @see MessageModel
     * @see Flow
     */
    fun addMessageToGlobalChat(value: MessageModel) : Flow<ResponseState<Nothing>>

    /**
     * Get the messages from the global chat
     *
     * @return [Flow] with the [ResponseState] of the [List]<[MessageModel]>
     *
     * @see MessageModel
     * @see Flow
     */
    fun getMessagesFromGlobalChat(): Flow<ResponseState<List<MessageModel>>>

}