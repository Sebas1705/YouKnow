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

import android.app.Application
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.core.classes.MVIBaseIntent
import es.sebas1705.youknow.core.classes.MVIBaseState
import es.sebas1705.youknow.core.classes.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.printTextInToast
import es.sebas1705.youknow.domain.model.GroupModel
import es.sebas1705.youknow.domain.model.MessageModel
import es.sebas1705.youknow.domain.model.UserModel
import es.sebas1705.youknow.domain.usecases.social.ChatUsesCases
import es.sebas1705.youknow.domain.usecases.social.GroupUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel fon [es.sebas1705.youknow.presentation.features.home.screens.social.SocialScreen] that will handle the logic of the screen.
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
    private val chatUsesCases: ChatUsesCases,
    private val groupUsesCases: GroupUsesCases,
    private val application: Application
) : MVIBaseViewModel<SocialState, SocialIntent>() {

    override fun initState(): SocialState = SocialState.default()

    override fun intentHandler(intent: SocialIntent) {
        when (intent) {
            is SocialIntent.LoadGroups -> loadGroups(intent)
            is SocialIntent.LoadGlobalChat -> loadGlobalChat()
            is SocialIntent.SendMessage -> sendMessage(intent)
            is SocialIntent.CreateGroup -> createGroup(intent)
        }
    }

    override fun onCleared() {
        Log.d("SocialViewModel", "onCleared")
        chatUsesCases.removeMessagesListener()
        groupUsesCases.removeGroupsListener()
        super.onCleared()
    }

    //Actions:
    /**
     * Action associated with [SocialIntent.SendMessage] that will send a message to the global chat.
     *
     * @see [SocialIntent.SendMessage]
     */
    private fun sendMessage(
        sendMessage: SocialIntent.SendMessage
    ) {
        execute(Dispatchers.IO) {
            chatUsesCases.sendMessage(
                sendMessage.message,
                sendMessage.userModel.firebaseId,
                sendMessage.userModel.nickName,
                onSuccess = {},
                onError = { error ->
                    execute{
                        application.applicationContext.printTextInToast("Error in message sending: "+error)
                    }
                }
            )
        }
    }

    /**
     * Action associated with [SocialIntent.CreateGroup] that will create a group.
     *
     * @see [SocialIntent.CreateGroup]
     */
    private fun createGroup(
        createGroup: SocialIntent.CreateGroup
    ) {
        execute(Dispatchers.IO) {
            groupUsesCases.createGroup(
                createGroup.name,
                createGroup.description,
                createGroup.userId,
                onSuccess = {},
                onError = { error ->
                    execute{
                        application.applicationContext.printTextInToast("Error in group creation: "+error)
                    }
                }
            )
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
    private fun loadGlobalChat() = execute(Dispatchers.IO) {
        chatUsesCases.setMessagesListener(
            onSuccess = { data ->
                updateUi {
                    it.copy(chatGlobal = data)
                }
            },
            onError = { error ->
                execute{
                    application.applicationContext.printTextInToast("Error in global chat loading: "+error)
                }
            }
        )
    }




    /**
     * Action associated with [SocialIntent.LoadGroups] that will load the groups.
     *
     * @see [SocialIntent.LoadGroups]
     */
    private fun loadGroups(intent: SocialIntent.LoadGroups) {
        groupUsesCases.setGroupsListener(
            onSuccess = { data ->
                updateUi {
                    it.copy(
                        groups = data,
                        myGroup = if (intent.myGroupId != null) data.find { it.groupId == intent.myGroupId } else null
                    )
                }
            },
            onError = { error ->
                execute{
                    application.applicationContext.printTextInToast("Error in groups loading: "+error)
                }
            }
        )
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
    val myGroup: GroupModel?,
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
            myGroup = null,
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
 * @property CreateGroup [SocialIntent]: Action to create a group.
 *
 * @see MVIBaseIntent
 * @see SocialViewModel
 * @see SendMessage
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface SocialIntent : MVIBaseIntent {

    /**
     * Action to load the groups.
     *
     * @param myGroupId [String]: Id of the group of the user.
     *
     * @see SocialIntent
     */
    data class LoadGroups(
        val myGroupId: String?
    ) : SocialIntent

    /**
     * Action to load the global chat.
     *
     * @see SocialIntent
     */
    data object LoadGlobalChat : SocialIntent

    /**
     * Action to send a message to the global chat.
     *
     * @param message [String]: Message to send.
     *
     * @see SocialIntent
     */
    data class SendMessage(
        val message: String,
        val userModel: UserModel
    ) : SocialIntent

    /**
     * Action to create a group.
     *
     * @param name [String]: Name of the group.
     * @param description [String]: Description of the group.
     * @param userId [String]: Id of the user that creates the group.
     *
     * @see SocialIntent
     */
    data class CreateGroup(
        val name: String,
        val description: String,
        val userId: String
    ) : SocialIntent
}