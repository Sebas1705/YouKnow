package es.sebas1705.youknow.data.firebase.realtime.jsons
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

import es.sebas1705.youknow.domain.model.MessageModel

/**
 * JSON representation of a message
 *
 * @property text [String]: Text of the message
 * @property authorName [String]: Name of the author
 *
 * @see MessageModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class MessageJson (
    val text: String = "",
    val authorName: String = "",
) {


    /**
     * Convert the JSON representation to a [MessageModel]
     *
     * @param messageId [String]: Id of the message
     *
     * @return [MessageModel]: Message model
     *
     * @see MessageModel
     */
    fun toMessageModel(messageId: String): MessageModel {
        val messageData = messageId.split("-")
        return MessageModel(
            text = this.text,
            time = messageData[1].toLong(),
            authorId = messageData[0],
            authorName = this.authorName,
        )
    }
}