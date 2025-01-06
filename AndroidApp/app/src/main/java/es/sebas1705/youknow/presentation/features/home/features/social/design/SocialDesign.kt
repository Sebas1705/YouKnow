package es.sebas1705.youknow.presentation.features.home.features.social.design
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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.dialogs.UserInfoDialog
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.domain.model.UserModel
import es.sebas1705.youknow.domain.model.social.GroupModel
import es.sebas1705.youknow.presentation.features.home.features.social.composables.Chat
import es.sebas1705.youknow.presentation.features.home.features.social.composables.Group
import es.sebas1705.youknow.presentation.features.home.features.social.composables.GroupsList
import es.sebas1705.youknow.presentation.features.home.features.social.composables.IconsBar
import es.sebas1705.youknow.presentation.features.home.features.social.viewmodel.SocialState
import es.sebas1705.youknow.presentation.features.home.features.social.viewmodel.SocialViewModel
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Design of the Social Screen that will show the chat and group options.
 * It will show the chat by default and the group options if the user wants to.
 * The user can send messages in the chat and join groups.
 *
 * @param socialState [SocialState]: State of the screen.
 *
 * @see SocialViewModel
 * @see SocialState
 * @see Chat
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SocialDesign(
    windowState: WindowState = WindowState.default(),
    socialState: SocialState = SocialState.default(),
    userModel: UserModel = UserModel.default(),
    infoUser: UserModel? = null,
    messageSender: (String) -> Unit = {},
    groupCreator: (String, String) -> Unit = { _, _ -> },
    groupJoin: (GroupModel) -> Unit = {},
    onGroupOutButton: () -> Unit = {},
    onKickButton: (String) -> Unit = {},
    onUserInfoSearch: (String) -> Unit = {}
) {

    //States:
    var chatPage by rememberSaveable { mutableStateOf(true) }
    var infoDisplay by remember { mutableStateOf(false) }
    var userInfoId by remember { mutableStateOf("") }

    if (infoDisplay) UserInfoDialog(
        userModel = infoUser,
        onDismiss = { infoDisplay = false }
    )
    ApplyBack(
        backId = windowState.backEmpty,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val iconHeight = windowState.heightType.filter(50.dp, 62.dp, 75.dp)

            IconsBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(iconHeight)
                    .padding(top = SmallestPadding),
                isGroup = !chatPage,
                changeToGroup = { chatPage = false },
                changeToChat = { chatPage = true },
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(windowState.heightDp - iconHeight - 80.dp),
            ) {
                when {
                    chatPage -> Chat(
                        windowState = windowState,
                        firebaseId = userModel.firebaseId,
                        messageModels = socialState.chatGlobal,
                        onMessageSend = messageSender
                    )

                    socialState.myGroup != null -> Group(
                        firebaseId = userModel.firebaseId,
                        groupModel = socialState.myGroup,
                        windowState = windowState,
                        admin = socialState.myGroup.leaderUID == userModel.firebaseId,
                        onOutButton = onGroupOutButton,
                        onInfoButton = {
                            onUserInfoSearch(it)
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