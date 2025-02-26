package es.sebas1705.youknow.presentation.features.home.features.chat.viewmodel
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

import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.domain.model.social.MessageModel

/**
 * State of the [ChatViewModel] that will handle the data of the screen.
 *
 * @property isLoading [Boolean]: True if the screen is loading data.
 * @property chatGlobal [List]<[MessageModel]>: List of messages from the global chat.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class ChatState(
    val isLoading: Boolean,
    val chatGlobal: List<MessageModel>,
) : MVIBaseState {
    companion object {

        /**
         * Default state of the [ChatViewModel].
         *
         * @return [ChatState]: Default state.
         *
         * @since 1.0.0
         * @author Sebastián Ramiro Entrerrios García
         */
        fun default() = ChatState(
            isLoading = false,
            chatGlobal = emptyList(),
        )
    }
}