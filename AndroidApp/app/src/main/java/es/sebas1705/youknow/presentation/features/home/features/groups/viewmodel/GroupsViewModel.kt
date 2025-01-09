package es.sebas1705.youknow.presentation.features.home.features.groups.viewmodel
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
import es.sebas1705.youknow.domain.usecases.social.GroupUsesCases
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel of the groups feature.
 *
 * @property groupUsesCases [GroupUsesCases]: Uses cases of the groups.
 * @property userUsesCases [UserUsesCases]: Uses cases of the user.
 * @property application [Application]: Application context.
 *
 * @see MVIBaseViewModel
 * @see HiltViewModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val groupUsesCases: GroupUsesCases,
    private val userUsesCases: UserUsesCases,
    private val application: Application
) : MVIBaseViewModel<GroupsState, GroupsIntent>() {

    private val ctx = application.applicationContext

    override fun initState(): GroupsState = GroupsState.default()

    override fun intentHandler(intent: GroupsIntent) {
        when (intent) {
            is GroupsIntent.CreateGroup -> createGroup(intent)
            is GroupsIntent.JoinGroup -> joinGroup(intent)
            is GroupsIntent.OutGroup -> outGroup(intent)
            is GroupsIntent.KickGroup -> kickGroup(intent)
            is GroupsIntent.LoadGroups -> loadGroups(intent)
            is GroupsIntent.ClearGroups -> clearGroups()
        }
    }

    //Actions:
    /**
     * Action associated with [GroupsIntent.CreateGroup] that will create a group.
     *
     * @see [GroupsIntent.CreateGroup]
     */
    private fun createGroup(
        intent: GroupsIntent.CreateGroup
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
     * Action associated with [GroupsIntent.JoinGroup] that will join a group.
     *
     * @see [GroupsIntent.JoinGroup]
     */
    private fun joinGroup(
        intent: GroupsIntent.JoinGroup
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

    /**
     * Action associated with [GroupsIntent.OutGroup] that will out of a group.
     *
     * @see [GroupsIntent.OutGroup]
     */
    private fun outGroup(
        intent: GroupsIntent.OutGroup
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

    /**
     * Action associated with [GroupsIntent.KickGroup] that will kick a user from a group.
     *
     * @see [GroupsIntent.KickGroup]
     */
    private fun kickGroup(
        intent: GroupsIntent.KickGroup
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
     * Action associated with [GroupsIntent.LoadGroups] that will load the social data.
     *
     * @see [GroupsIntent.LoadGroups]
     */
    private fun loadGroups(intent: GroupsIntent.LoadGroups) {
        groupUsesCases.setGroupsListener(
            onSuccess = { data ->
                updateUi {
                    Log.i("GroupsViewModel", "loadGroups: $data")
                    it.copy(
                        groups = data,
                        myGroup = data.find {
                            it.groupId == intent.myGroupId
                        }
                    )
                }
            },
            onError = {
                stopAndError(it, ctx::printTextInToast)
            }
        )
    }

    /**
     * Action associated with [GroupsIntent.ClearGroups] that will clear the social data.
     *
     * @see [GroupsIntent.ClearGroups]
     */
    private fun clearGroups() {
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
 * State of the [GroupsViewModel] that will handle the data of the screen.
 *
 * @property isLoading [Boolean]: Flag that indicates if the data is loading.
 * @property groups [List]<[GroupModel]>: List of groups.
 * @property myGroup [Int]: Id of the group of the user.
 *
 * @see MVIBaseState
 * @see GroupModel
 * @see MessageModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class GroupsState(
    val isLoading: Boolean,
    val groups: List<GroupModel>,
    val myGroup: GroupModel?
) : MVIBaseState {
    companion object {

        /**
         * Default state of the [GroupsViewModel].
         *
         * @return [GroupsState]: Default state.
         */
        fun default() = GroupsState(
            isLoading = false,
            groups = emptyList(),
            myGroup = null
        )
    }
}

/**
 * Sealed interface that represents the possible actions of the [GroupsViewModel].
 *
 * @property SendMessage [GroupsIntent]: Action to send a message to the global chat.
 * @property CreateGroup [GroupsIntent]: Action to create a group.
 * @property JoinGroup [GroupsIntent]: Action to join a group.
 * @property LoadGroups [GroupsIntent]: Action to load the social data.
 * @property ClearGroups [GroupsIntent]: Action to clear the social data.
 *
 * @see MVIBaseIntent
 * @see GroupsViewModel
 * @see SendMessage
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface GroupsIntent : MVIBaseIntent {

    /**
     * Action to create a group.
     *
     * @param name [String]: Name of the group.
     * @param description [String]: Description of the group.
     * @param userModel [UserModel]: User that creates the group.
     *
     * @see GroupsIntent
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    data class CreateGroup(
        val name: String,
        val description: String,
        val userModel: UserModel
    ) : GroupsIntent

    /**
     *  Action to join a group.
     *
     *  @param groupModel [GroupModel]: Group to join.
     *  @param userModel [UserModel]: User that joins the group.
     *
     *  @see GroupsIntent
     *
     *  @since 1.0.0
     *  @author Sebastián Ramiro Entrerrios García
     */
    data class JoinGroup(
        val groupModel: GroupModel,
        val userModel: UserModel
    ) : GroupsIntent

    /**
     * Action to out of a group.
     *
     * @param groupModel [GroupModel]: Group to out.
     * @param userModel [UserModel]: User that out of the group.
     *
     * @see GroupsIntent
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    data class OutGroup(
        val groupModel: GroupModel,
        val userModel: UserModel
    ) : GroupsIntent

    /**
     * Action to kick a user from a group.
     *
     * @param groupModel [GroupModel]: Group to kick the user.
     * @param userToKickMemberId [String]: Id of the user to kick.
     *
     * @see GroupsIntent
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    data class KickGroup(
        val groupModel: GroupModel,
        val userToKickMemberId: String
    ) : GroupsIntent

    /**
     * Action to load the groups data.
     *
     * @param myGroupId [String]: Id of the group of the user.
     * @param groups [List]<[GroupModel]>: List of groups.
     *
     * @see GroupsIntent
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    data class LoadGroups(
        val myGroupId: String?
    ) : GroupsIntent

    /**
     * Action to clear the groups data.
     *
     * @see GroupsIntent
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    data object ClearGroups : GroupsIntent

}