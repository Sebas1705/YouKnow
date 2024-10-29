package es.sebas1705.youknow.presentation.features.home.viewmodels
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

import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.core.classes.MVIBaseIntent
import es.sebas1705.youknow.core.classes.MVIBaseState
import es.sebas1705.youknow.core.classes.MVIBaseViewModel
import es.sebas1705.youknow.data.model.ResponseState
import es.sebas1705.youknow.domain.model.GroupModel
import es.sebas1705.youknow.domain.model.MessageModel
import es.sebas1705.youknow.domain.usecases.AuthenticationUsesCases
import es.sebas1705.youknow.domain.usecases.RealtimeUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel fon [es.sebas1705.youknow.presentation.features.home.screens.SocialScreen] that will handle the logic of the screen.
 * It will load the groups and the global chat. It will also send messages to the global chat.
 *
 * @param realtimeUsesCases [RealtimeUsesCases]: UseCase to get the messages from the global chat and send messages to it.
 * @param authenticationUsesCases [AuthenticationUsesCases]: UseCase to get the current user.
 *
 * @see MVIBaseViewModel
 * @see HiltViewModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class SocialViewModel @Inject constructor(
    private val realtimeUsesCases: RealtimeUsesCases,
    private val authenticationUsesCases: AuthenticationUsesCases
) : MVIBaseViewModel<SocialState, SocialIntent>() {

    override fun initState(): SocialState = SocialState.default()

    override fun intentHandler(intent: SocialIntent) {
        when (intent) {
            is SocialIntent.LoadGroups -> loadGroups()
            is SocialIntent.LoadGlobalChat -> loadGlobalChat()
            is SocialIntent.SendMessage -> sendMessage(intent.message)
        }
    }

    override fun onViewModelInit() {
        loadGroups()
        loadGlobalChat()
    }

    //Actions:
    /**
     * Action associated with [SocialIntent.SendMessage] that will send a message to the global chat.
     *
     * @param message [String]: Message to send.
     *
     * @see [SocialIntent.SendMessage]
     */
    private fun sendMessage(message: String) {
        val user = authenticationUsesCases.getCurrentUser()!!
        execute(Dispatchers.IO) {
            realtimeUsesCases.addMessageToGlobalChat(
                MessageModel(
                    text = message,
                    time = System.currentTimeMillis(),
                    authorId = user.uid,
                    authorName = user.email
                        ?: "email@random.es"
                )
            ).collect {
                it.catcher(

                )
            }
        }
    }

    /**
     * Action associated with [SocialIntent.LoadGlobalChat] that will load the messages from the global chat.
     * Execute the [RealtimeUsesCases.getMessagesFromGlobalChat] UseCase to get the messages from the global chat
     * using a [Dispatchers.IO] context.
     *
     * @see [SocialIntent.LoadGlobalChat]
     * @see [RealtimeUsesCases.getMessagesFromGlobalChat]
     * @see [MessageModel]
     */
    private fun loadGlobalChat() {
        execute(Dispatchers.IO) {
            realtimeUsesCases.getMessagesFromGlobalChat().collect {
                it.catcher(
                    onSuccess = { data ->
                        updateUi {
                            it.copy(chatGlobal = data)
                        }
                    }
                )
            }
        }
    }

    /**
     * Action associated with [SocialIntent.LoadGroups] that will load the groups.
     *
     * @see [SocialIntent.LoadGroups]
     */
    private fun loadGroups() {

    }
}

/**
 * State of the [SocialViewModel] that will handle the data of the screen.
 *
 * @param groups [List]<[GroupModel]>: List of groups.
 * @param myGroup [Int]: Id of the group of the user.
 * @param chatGlobal [List]<[MessageModel]>: List of messages from the global chat.
 *
 * @see MVIBaseState
 * @see GroupModel
 * @see MessageModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class SocialState(
    val groups: List<GroupModel>,
    val myGroup: Int,
    val chatGlobal: List<MessageModel>,
) : MVIBaseState {
    companion object {

        /**
         * Default state of the [SocialViewModel].
         *
         * @return [SocialState]: Default state.
         */
        fun default() = SocialState(
            groups = emptyList(),
            myGroup = 0,
            chatGlobal = emptyList(),
        )
    }
}

/**
 * Sealed interface that represents the possible actions of the [SocialViewModel].
 *
 * @property LoadGroups [SocialIntent]: Action to load the groups.
 * @property LoadGlobalChat [SocialIntent]: Action to load the global chat.
 * @property SendMessage [SocialIntent]: Action to send a message to the global chat.
 *
 * @see MVIBaseIntent
 * @see SocialViewModel
 * @see SendMessage
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface SocialIntent : MVIBaseIntent {

    data object LoadGroups : SocialIntent
    data object LoadGlobalChat : SocialIntent

    /**
     * Action to send a message to the global chat.
     *
     * @param message [String]: Message to send.
     *
     * @see SocialIntent
     */
    data class SendMessage(val message: String) : SocialIntent
}