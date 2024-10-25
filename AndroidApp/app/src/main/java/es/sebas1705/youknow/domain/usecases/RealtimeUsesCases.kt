package es.sebas1705.youknow.domain.usecases
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
import es.sebas1705.youknow.data.firebase.realtime.repository.RealtimeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case to write a message on the default topic
 *
 * @param realtimeRepository [RealtimeRepository]: repository to write the message
 *
 * @see RealtimeRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class WriteOnDefault(
    private val realtimeRepository: RealtimeRepository
) {
    operator fun invoke(value: String) {
        realtimeRepository.writeOnDefault("hello", value)
    }
}

/**
 * Use case to write a message on the global chat topic
 *
 * @param realtimeRepository [RealtimeRepository]: repository to write the message
 *
 * @see RealtimeRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class WriteOnGlobalChat(
    private val realtimeRepository: RealtimeRepository
) {
    operator fun invoke(value: String) {
        realtimeRepository.writeOnGlobalChat("hello", value)
    }
}

/**
 * Use case to add a message to the global chat
 *
 * @param realtimeRepository [RealtimeRepository]: repository to add the message
 *
 * @return [Flow] with a [ResponseState] indicating if the message was added
 *
 * @see RealtimeRepository
 * @see MessageModel
 * @see ResponseState
 * @see Flow
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class AddMessageToGlobalChat(
    private val realtimeRepository: RealtimeRepository
) {
    operator fun invoke(message: MessageModel): Flow<ResponseState<Boolean>> {
        return realtimeRepository.addMessageToGlobalChat(message)
    }
}

/**
 * Use case to get the messages from the global chat
 *
 * @param realtimeRepository [RealtimeRepository]: repository to get the messages
 *
 * @return [Flow] with a [ResponseState] with a list of [MessageModel]
 *
 * @see RealtimeRepository
 * @see MessageModel
 * @see ResponseState
 * @see Flow
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class GetMessagesFromGlobalChat(
    private val realtimeRepository: RealtimeRepository
) {
    operator fun invoke(): Flow<ResponseState<List<MessageModel>>> {
        return realtimeRepository.getMessagesFromGlobalChat()
    }
}

/**
 * Data class to group all the use cases related to realtime
 *
 * @property writeOnDefault [WriteOnDefault]: use case to write a message on the default topic
 * @property writeOnGlobalChat [WriteOnGlobalChat]: use case to write a message on the global chat topic
 * @property addMessageToGlobalChat [AddMessageToGlobalChat]: use case to add a message to the global chat
 * @property getMessagesFromGlobalChat [GetMessagesFromGlobalChat]: use case to get the messages from the global chat
 *
 * @see WriteOnDefault
 * @see WriteOnGlobalChat
 * @see AddMessageToGlobalChat
 * @see GetMessagesFromGlobalChat
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class RealtimeUsesCases(
    val writeOnDefault: WriteOnDefault,
    val writeOnGlobalChat: WriteOnGlobalChat,
    val addMessageToGlobalChat: AddMessageToGlobalChat,
    val getMessagesFromGlobalChat: GetMessagesFromGlobalChat
)