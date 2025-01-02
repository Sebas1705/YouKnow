package es.sebas1705.youknow.presentation.features.home.screens.social
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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.domain.model.social.GroupModel
import es.sebas1705.youknow.presentation.features.home.screens.social.composables.Chat
import es.sebas1705.youknow.presentation.features.home.screens.social.composables.Group
import es.sebas1705.youknow.presentation.features.home.screens.social.composables.GroupsList
import es.sebas1705.youknow.presentation.features.home.screens.social.composables.IconsBar
import es.sebas1705.youknow.presentation.features.home.viewmodels.SocialIntent
import es.sebas1705.youknow.presentation.features.home.viewmodels.SocialState
import es.sebas1705.youknow.presentation.features.home.viewmodels.SocialViewModel
import es.sebas1705.youknow.presentation.features.home.viewmodels.UserIntent
import es.sebas1705.youknow.presentation.features.home.viewmodels.UserState
import es.sebas1705.youknow.presentation.features.home.viewmodels.UserViewModel
import es.sebas1705.youknow.presentation.features.home.windows.UserInfoWindow
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

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
    userState: UserState,
    socialState: SocialState,
    userViewModel: UserViewModel,
    socialViewModel: SocialViewModel
) {
    //Locals:
    val context = LocalContext.current
    BackHandler {}

    SocialDesign(
        windowState = windowState,
        userState = userState,
        socialState = socialState,
        socialViewModel = socialViewModel,
        messageSender = { message ->
            userState.userModel?.let {
                socialViewModel.eventHandler(SocialIntent.SendMessage(message, it))
            } ?: context.printTextInToast(context.getString(R.string.user_not_found))
        },
        groupCreator = { name, description ->
            userState.userModel?.let {
                socialViewModel.eventHandler(SocialIntent.CreateGroup(name, description, it))
            } ?: context.printTextInToast(context.getString(R.string.user_not_found))
        },
        groupJoin = { groupModel ->
            userState.userModel?.let {
                socialViewModel.eventHandler(SocialIntent.JoinGroup(groupModel, it))
            } ?: context.printTextInToast(context.getString(R.string.user_not_found))
        },
        onGroupOutButton = {
            userState.userModel?.let {
                socialViewModel.eventHandler(
                    SocialIntent.OutGroup(
                        socialState.myGroup!!,
                        it
                    )
                )
            } ?: context.printTextInToast(context.getString(R.string.user_not_found))
        },
        onKickButton = {
            socialViewModel.eventHandler(SocialIntent.KickGroup(socialState.myGroup!!, it))
        },
        userGet = {
            userViewModel.eventHandler(UserIntent.GetUser(it))
        }
    )
}

/**
 * Design of the Social Screen that will show the chat and group options.
 * It will show the chat by default and the group options if the user wants to.
 * The user can send messages in the chat and join groups.
 *
 * @param socialViewModel [SocialViewModel]: ViewModel to handle the logic of the screen.
 * @param socialState [SocialState]: State of the screen.
 *
 * @see SocialViewModel
 * @see SocialState
 * @see Chat
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun SocialDesign(
    windowState: WindowState = WindowState.default(),
    userState: UserState = UserState.default(),
    socialState: SocialState = SocialState.default(),
    socialViewModel: SocialViewModel? = null,
    messageSender: (String) -> Unit = {},
    groupCreator: (String, String) -> Unit = { _, _ -> },
    groupJoin: (GroupModel) -> Unit = {},
    onGroupOutButton: () -> Unit = {},
    onKickButton: (String) -> Unit = {},
    userGet: (String) -> Unit = {}
) {

    //States:
    var chatPage by rememberSaveable { mutableStateOf(true) }
    var infoDisplay by remember { mutableStateOf(false) }
    var userInfoId by remember { mutableStateOf("") }

    if (infoDisplay) UserInfoWindow(
        userModel = userState.infoUser,
        onDismiss = { infoDisplay = false }
    )
    ApplyBack(
        backId = windowState.backEmpty,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            val iconHeight = windowState.heightType.filter(50.dp, 62.dp, 75.dp)
            item {
                IconsBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(iconHeight)
                        .padding(top = SmallestPadding),
                    isGroup = !chatPage,
                    changeToGroup = { chatPage = false },
                    changeToChat = { chatPage = true },
                )
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(windowState.heightDp - iconHeight - 80.dp),
                ) {
                    when {
                        chatPage -> Chat(
                            windowState = windowState,
                            firebaseId = userState.userModel?.firebaseId ?: "",
                            messageModels = socialState.chatGlobal,
                            onMessageSend = messageSender
                        )

                        socialState.myGroup != null -> Group(
                            firebaseId = userState.userModel?.firebaseId ?: "",
                            groupModel = socialState.myGroup,
                            windowState = windowState,
                            admin = socialState.myGroup.leaderUID == userState.userModel?.firebaseId,
                            onOutButton = onGroupOutButton,
                            onInfoButton = {
                                userGet(it)
                                userInfoId = it
                                infoDisplay = true
                            },
                            onKickButton = onKickButton,
                        )

                        else -> GroupsList(
                            windowState = windowState,
                            groupModels = socialState.groups,
                            onGroupClick = groupJoin,
                            onGroupCreate = groupCreator
                        )
                    }
                }
            }
        }
    }
}


/**
 * Preview of the Social Screen.
 *
 * @see SocialDesign
 */
@UiModePreviews
@Composable
private fun SocialPreview() {
    YouKnowTheme {
        SocialDesign()
    }
}





