package es.sebas1705.youknow.domain.usecases.social
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

import es.sebas1705.youknow.data.firebase.realtime.repository.RealtimeRepository
import es.sebas1705.youknow.domain.model.social.MessageModel

/**
 * Use case to send a message
 *
 * @property realtimeRepository [RealtimeRepository]: repository to send the message
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class SendMessage(
    private val realtimeRepository: RealtimeRepository
) {
    suspend operator fun invoke(
        message: String,
        firebaseId: String,
        nickname: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val messageModel = MessageModel(
            text = message,
            time = System.currentTimeMillis(),
            authorId = firebaseId,
            authorName = nickname,
        )
        realtimeRepository.addMessageToGlobalChat(messageModel).collect {
            it.catcher(
                onEmptySuccess = onSuccess,
                onError = { onError(it.message) },
            )
        }
    }
}

/**
 * Use case to set a listener to get messages
 *
 * @property realtimeRepository [RealtimeRepository]: repository to get messages
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class SetMessagesListener(
    private val realtimeRepository: RealtimeRepository
) {
    operator fun invoke(
        onSuccess: (List<MessageModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        realtimeRepository.setMessagesListener(
            onDataChange = onSuccess,
            onCancelled = onError
        )
    }
}

/**
 * Use case to remove the listener to get messages
 *
 * @property realtimeRepository [RealtimeRepository]: repository to remove the listener
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class RemoveMessagesListener(
    private val realtimeRepository: RealtimeRepository
) {
    operator fun invoke() {
        realtimeRepository.removeMessagesListener()
    }
}

/**
 * Data class to group the chat use cases
 *
 * @param sendMessage [SendMessage]: use case to send a message
 * @param setMessagesListener [SetMessagesListener]: use case to set a listener to get messages
 * @param removeMessagesListener [RemoveMessagesListener]: use case to remove the listener to get messages
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class ChatUsesCases(
    val sendMessage: SendMessage,
    val setMessagesListener: SetMessagesListener,
    val removeMessagesListener: RemoveMessagesListener
)