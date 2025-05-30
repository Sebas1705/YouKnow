package es.sebas1705.chat.usescases
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

import es.sebas1705.mappers.toMessageJson
import es.sebas1705.models.social.MessageModel
import es.sebas1705.realtime.repository.RealtimeRepository

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
        realtimeRepository.addMessageToGlobalChat(
            messageModel.messageId,
            messageModel.toMessageJson()
        ).collect {
            it.catcher(
                onEmptySuccess = onSuccess,
                onError = { onError(it.message) },
            )
        }
    }
}