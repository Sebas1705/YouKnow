package es.sebas1705.youknow.domain.model.social
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

import es.sebas1705.youknow.data.firebase.realtime.jsons.MessageJson

/**
 * Model to represent a message
 *
 * @property text [String]: Text of the message
 * @property time [Long]: Time of the message
 * @property authorId [String]: Id of the author of the message
 * @property authorName [String]: Name of the author of the message
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class MessageModel(
    val text: String,
    val time: Long,
    val authorId: String,
    val authorName: String,
) {
    /**
     * Convert the message to a MessageJson
     *
     * @return [MessageJson]: Message converted to MessageJson
     */
    fun toMessageJson(): MessageJson {
        return MessageJson(
            text = this.text,
            authorName = this.authorName
        )
    }

    /**
     * Get the message id
     *
     * @return [String]: Message id
     */
    val messageId: String get() = this.authorId.toString() + "-" + this.time.toString()
}