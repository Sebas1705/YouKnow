package es.sebas1705.realtime.config
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

/**
 * Realtime references used in the app
 *
 * @property DEFAULT_REFERENCE [String]: Default reference
 * @property CHAT_GLOBAL_REFERENCE [String]: Global chat reference
 * @property GROUPS_REFERENCE [String]: Groups reference
 * @property MESSAGE_MAX_LENGTH [Int]: Max length of a message
 * @property MAX_MESSAGES_ON_GLOBAL_CHAT [Int]: Max messages on global chat
 * @property ERROR_GENERIC_MESSAGE_EX [String]: Generic error message by exception
 * @property ERROR_GENERIC_MESSAGE_FAIL [String]: Generic error message by failure listener
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
object SettingsRT {
    const val DEFAULT_REFERENCE = "default"
    const val CHAT_GLOBAL_REFERENCE = "chat-global-youknow"
    const val GROUPS_REFERENCE = "groups-youknow"
    const val MEMBERS_REFERENCE = "members"
    const val MESSAGE_MAX_LENGTH = 50
    const val MAX_MESSAGES_ON_GLOBAL_CHAT = 100

    const val ERROR_GENERIC_MESSAGE_EX = "An error occurred on realtime by an exception"
    const val ERROR_GENERIC_MESSAGE_FAIL = "An error occurred on realtime by failure listener"
}

