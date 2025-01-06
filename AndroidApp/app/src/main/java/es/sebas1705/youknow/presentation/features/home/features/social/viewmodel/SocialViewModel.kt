package es.sebas1705.youknow.presentation.features.home.features.social.viewmodel
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
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.domain.model.UserModel
import es.sebas1705.youknow.domain.model.social.GroupModel
import es.sebas1705.youknow.domain.model.social.MessageModel
import es.sebas1705.youknow.domain.usecases.social.ChatUsesCases
import es.sebas1705.youknow.domain.usecases.social.GroupUsesCases
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel fon [es.sebas1705.youknow.presentation.features.home.features.social.SocialScreen] that will handle the logic of the screen.
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
    private val userUsesCases: UserUsesCases,
    private val application: Application
) : MVIBaseViewModel<SocialState, SocialIntent>() {

    private val ctx = application.applicationContext

    override fun initState(): SocialState = SocialState.default()

    override fun intentHandler(intent: SocialIntent) {
        when (intent) {
            is SocialIntent.SendMessage -> sendMessage(intent)
            is SocialIntent.CreateGroup -> createGroup(intent)
            is SocialIntent.JoinGroup -> joinGroup(intent)
            is SocialIntent.OutGroup -> outGroup(intent)
            is SocialIntent.KickGroup -> kickGroup(intent)
            is SocialIntent.LoadSocial -> loadSocial(intent)
            is SocialIntent.ClearSocial -> clearSocial()
        }
    }

    //Actions:
    /**
     * Action associated with [SocialIntent.SendMessage] that will send a message to the global chat.
     *
     * @see [SocialIntent.SendMessage]
     */
    private fun sendMessage(
        intent: SocialIntent.SendMessage
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
     * Action associated with [SocialIntent.CreateGroup] that will create a group.
     *
     * @see [SocialIntent.CreateGroup]
     */
    private fun createGroup(
        intent: SocialIntent.CreateGroup
    ) = execute(Dispatchers.IO) {
        userUsesCases.addCreditsToUser(
            user = intent.userModel,
            creditsToAdd = -2000,
            onLoading = { startLoading() },
            onSuccess = {
                execute(Dispatchers.IO) {
                    groupUsesCases.createGroup(
                        intent.name,
                        intent.description,
                        intent.userModel,
                        onSuccess = { group ->
                            userUsesCases.setGroupToUser(
                                group,
                                intent.userModel,
                                true,
                                onSuccess = {
                                    updateUi { it.copy(myGroup = group) }
                                    stopLoading()
                                },
                                onError = {
                                    stopAndError(
                                        it,
                                        ctx::printTextInToast
                                    )
                                }
                            )
                        },
                        onError = {
                            stopAndError(
                                it,
                                ctx::printTextInToast
                            )
                        }
                    )
                }
            },
            onError = { stopAndError(it, ctx::printTextInToast) }
        )
    }

    /**
     * Action associated with [SocialIntent.JoinGroup] that will join a group.
     *
     * @see [SocialIntent.JoinGroup]
     */
    private fun joinGroup(
        intent: SocialIntent.JoinGroup
    ) = execute(Dispatchers.IO) {
        userUsesCases.setGroupToUser(
            group = intent.groupModel,
            user = intent.userModel,
            creator = false,
            onSuccess = {
                updateUi { it.copy(myGroup = intent.groupModel) }
                stopLoading()
            },
            onError = {
                stopAndError(it, ctx::printTextInToast)
            }
        )
    }

    private fun outGroup(
        intent: SocialIntent.OutGroup
    ) = execute(Dispatchers.IO) {
        userUsesCases.removeGroupToUser(
            group = intent.groupModel,
            userMemberId = intent.userModel.memberId(),
            onLoading = { startLoading() },
            onSuccess = {
                if (intent.groupModel.leaderUID == intent.userModel.firebaseId || intent.groupModel.members.isEmpty()) {
                    execute(Dispatchers.IO) {
                        groupUsesCases.removeGroup(
                            intent.groupModel,
                            onSuccess = {
                                updateUi { it.copy(myGroup = null) }
                                stopLoading()
                            },
                            onError = {
                                stopAndError(
                                    it,
                                    ctx::printTextInToast
                                )
                            }
                        )
                    }
                } else {
                    updateUi { it.copy(myGroup = null) }
                    stopLoading()
                }
            },
            onError = {
                stopAndError(it, ctx::printTextInToast)
            }
        )
    }

    private fun kickGroup(
        intent: SocialIntent.KickGroup
    ) = execute(Dispatchers.IO) {
        userUsesCases.removeGroupToUser(
            group = intent.groupModel,
            userMemberId = intent.userToKickMemberId,
            onLoading = { startLoading() },
            onSuccess = { stopLoading() },
            onError = {
                stopAndError(it, ctx::printTextInToast)
            }
        )
    }

    /**
     * Action associated with [SocialIntent.LoadSocial] that will load the social data.
     *
     * @see [SocialIntent.LoadSocial]
     */
    private fun loadSocial(intent: SocialIntent.LoadSocial) {
        chatUsesCases.setMessagesListener(
            onSuccess = { data ->
                updateUi {
                    it.copy(chatGlobal = data)
                }
            },
            onError = {
                stopAndError(it, ctx::printTextInToast)
            }
        )
        groupUsesCases.setGroupsListener(
            onSuccess = { data ->
                updateUi {
                    val myGroup = data.find { it.groupId == intent.myGroupId }
                    it.copy(groups = data, myGroup = myGroup)
                }
            },
            onError = {
                stopAndError(it, ctx::printTextInToast)
            }
        )
    }


    /**
     * Action associated with [SocialIntent.ClearSocial] that will clear the social data.
     *
     * @see [SocialIntent.ClearSocial]
     */
    private fun clearSocial() {
        chatUsesCases.removeMessagesListener()
        groupUsesCases.removeGroupsListener()
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
    val isLoading: Boolean,
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
            isLoading = false,
            groups = emptyList(),
            myGroup = null,
            chatGlobal = emptyList(),
        )
    }
}

/**
 * Sealed interface that represents the possible actions of the [SocialViewModel].
 *
 * @property SendMessage [SocialIntent]: Action to send a message to the global chat.
 * @property CreateGroup [SocialIntent]: Action to create a group.
 * @property JoinGroup [SocialIntent]: Action to join a group.
 * @property LoadSocial [SocialIntent]: Action to load the social data.
 * @property ClearSocial [SocialIntent]: Action to clear the social data.
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
        val userModel: UserModel
    ) : SocialIntent

    /**
     *  Action to join a group.
     *
     *  @param groupModel [GroupModel]: Group to join.
     *  @param userId [String]: Id of the user that joins the group.
     *
     *  @see SocialIntent
     */
    data class JoinGroup(
        val groupModel: GroupModel,
        val userModel: UserModel
    ) : SocialIntent

    /**
     * Action to out of a group.
     *
     * @param userId [String]: Id of the user that out of the group.
     *
     * @see SocialIntent
     */
    data class OutGroup(
        val groupModel: GroupModel,
        val userModel: UserModel
    ) : SocialIntent

    data class KickGroup(
        val groupModel: GroupModel,
        val userToKickMemberId: String
    ) : SocialIntent

    /**
     * Action to load the social data.
     *
     * @param myGroupId [String]: Id of the group of the user.
     *
     * @see SocialIntent
     */
    data class LoadSocial(
        val myGroupId: String?
    ) : SocialIntent

    /**
     * Action to clear the social data.
     *
     * @see SocialIntent
     */
    data object ClearSocial : SocialIntent

}