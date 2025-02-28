package es.sebas1705.chat
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

import es.sebas1705.chat.usescases.RemoveMessagesListener
import es.sebas1705.chat.usescases.SendMessage
import es.sebas1705.chat.usescases.SetMessagesListener

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