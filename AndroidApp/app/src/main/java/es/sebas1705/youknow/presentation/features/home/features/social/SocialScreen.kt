package es.sebas1705.youknow.presentation.features.home.features.social
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

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.domain.model.UserModel
import es.sebas1705.youknow.presentation.features.home.features.social.design.SocialDesign
import es.sebas1705.youknow.presentation.features.home.features.social.viewmodel.SocialIntent
import es.sebas1705.youknow.presentation.features.home.features.social.viewmodel.SocialState
import es.sebas1705.youknow.presentation.features.home.features.social.viewmodel.SocialViewModel

/**
 * Social Screen that will show the chat and group options.
 * It will show the chat by default and the group options if the user wants to.
 * The user can send messages in the chat and join groups.
 *
 * @see SocialViewModel
 * @see SocialState
 * @see SocialDesign
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun SocialScreen(
    windowState: WindowState,
    userModel: UserModel,
    infoUser: UserModel?,
    onUserInfoSearch: (String) -> Unit,
) {
    //Locals:
    BackHandler {}

    //ViewModel:
    val socialViewModel: SocialViewModel = hiltViewModel()

    //State:
    val socialState: SocialState by socialViewModel.uiState.collectAsStateWithLifecycle()

    socialViewModel.eventHandler(SocialIntent.LoadSocial(userModel.groupId))

    SocialDesign(
        windowState,
        socialState,
        userModel,
        infoUser,
        messageSender = { message ->
            socialViewModel.eventHandler(SocialIntent.SendMessage(message, userModel))
        },
        groupCreator = { name, description ->
            socialViewModel.eventHandler(SocialIntent.CreateGroup(name, description, userModel))
        },
        groupJoin = { groupModel ->
            socialViewModel.eventHandler(SocialIntent.JoinGroup(groupModel, userModel))
        },
        onGroupOutButton = {
            socialViewModel.eventHandler(
                SocialIntent.OutGroup(
                    socialState.myGroup!!,
                    userModel
                )
            )
        },
        onKickButton = {
            socialViewModel.eventHandler(SocialIntent.KickGroup(socialState.myGroup!!, it))
        },
        onUserInfoSearch
    )
}







