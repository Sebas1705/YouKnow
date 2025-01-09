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
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.domain.model.UserModel
import es.sebas1705.youknow.domain.model.social.GroupModel
import es.sebas1705.youknow.domain.model.social.MessageModel
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

    private val ctx = application.applicationContext

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
                    ctx.printTextInToast("Error in message sending: $error")
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
                stopAndError(it, ctx::printTextInToast)
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

/**
 * State of the [ChatViewModel] that will handle the data of the screen.
 *
 * @property isLoading [Boolean]: True if the screen is loading data.
 * @property chatGlobal [List]<[MessageModel]>: List of messages from the global chat.
 *
 * @see MVIBaseState
 * @see GroupModel
 * @see MessageModel
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
         */
        fun default() = ChatState(
            isLoading = false,
            chatGlobal = emptyList(),
        )
    }
}

/**
 * Sealed interface that represents the possible actions of the [ChatViewModel].
 *
 * @property SendMessage [ChatIntent]: Action to send a message to the global chat.
 * @property CreateGroup [ChatIntent]: Action to create a group.
 * @property JoinGroup [ChatIntent]: Action to join a group.
 * @property LoadChat [ChatIntent]: Action to load the social data.
 * @property ClearChat [ChatIntent]: Action to clear the social data.
 *
 * @see MVIBaseIntent
 * @see ChatViewModel
 * @see SendMessage
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface ChatIntent : MVIBaseIntent {

    /**
     * Action to send a message to the global chat.
     *
     * @param message [String]: Message to send.
     *
     * @see ChatIntent
     */
    data class SendMessage(
        val message: String,
        val userModel: UserModel
    ) : ChatIntent

    /**
     * Action to load the social data.
     *
     * @see ChatIntent
     */
    data object LoadChat : ChatIntent

    /**
     * Action to clear the social data.
     *
     * @see ChatIntent
     */
    data object ClearChat : ChatIntent

}