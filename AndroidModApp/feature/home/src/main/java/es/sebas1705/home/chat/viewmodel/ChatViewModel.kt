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

import android.app.Application
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.domain.usecases.social.ChatUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel of the Chat Screen that will handle the data of the screen.
 *
 * @param chatUsesCases [ChatUsesCases]: Uses cases of the chat.
 * @param application [Application]: Application context.
 *
 * @see MVIBaseViewModel
 * @see HiltViewModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatUsesCases: ChatUsesCases,
    private val application: Application
) : MVIBaseViewModel<ChatState, ChatIntent>() {

    override fun initState(): ChatState = ChatState.default()

    override fun intentHandler(intent: ChatIntent) {
        when (intent) {
            is ChatIntent.SendMessage -> sendMessage(intent)
            is ChatIntent.LoadChat -> loadChat()
            is ChatIntent.ClearChat -> clearChat()
        }
    }

    //Actions:
    /**
     * Action associated with [ChatIntent.SendMessage] that will send a message to the global chat.
     *
     * @see [ChatIntent.SendMessage]
     */
    private fun sendMessage(
        intent: ChatIntent.SendMessage
    ) = execute(Dispatchers.IO) {
        chatUsesCases.sendMessage(
            intent.message,
            intent.userModel.firebaseId,
            intent.userModel.nickName,
            onSuccess = {},
            onError = { error ->
                execute {
                    application.printTextInToast("Error in message sending: $error")
                }
            }
        )
    }

    /**
     * Action associated with [ChatIntent.LoadChat] that will load the social data.
     *
     * @see [ChatIntent.LoadChat]
     */
    private fun loadChat() {
        chatUsesCases.setMessagesListener(
            onSuccess = { data ->
                Log.i("ChatViewModel", "loadChat: $data")
                updateUi {
                    it.copy(chatGlobal = data)
                }
            },
            onError = {
                stopAndError(it, application::printTextInToast)
            }
        )
    }

    /**
     * Action associated with [ChatIntent.ClearChat] that will clear the social data.
     *
     * @see [ChatIntent.ClearChat]
     */
    private fun clearChat() {
        chatUsesCases.removeMessagesListener()
    }

    //Privates:
    private fun startLoading() {
        updateUi { it.copy(isLoading = true) }
    }

    private fun stopLoading() {
        updateUi { it.copy(isLoading = false) }
    }

    private fun stopAndError(error: String, onError: (String) -> Unit) {
        stopLoading()
        execute { onError(error) }
    }
}