package es.sebas1705.youknow.data.mappers
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
import es.sebas1705.youknow.domain.model.social.MessageModel


// MODEL

/**
 * Mapper function
 *
 * @receiver [MessageModel]: The object to convert
 *
 * @return [MessageJson]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun MessageModel.toMessageJson(): MessageJson {
    return MessageJson(
        text = this.text,
        authorName = this.authorName
    )
}

// JSON

/**
 * Mapper function
 *
 * @receiver [MessageJson]: The object to convert
 * @param messageId [String]: The message id
 *
 * @return [MessageModel]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun MessageJson.toMessageModel(messageId: String): MessageModel {
    val messageData = messageId.split("-")
    return MessageModel(
        text = this.text,
        time = messageData[1].toLong(),
        authorId = messageData[0],
        authorName = this.authorName,
    )
}
