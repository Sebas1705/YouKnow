package es.sebas1705.youknow.presentation.features.home.features.chat.design
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.fab.ISmallFAB
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.textfields.IFilledTextField
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.data.firebase.realtime.config.SettingsRT
import es.sebas1705.youknow.presentation.features.home.features.chat.composables.Chat
import es.sebas1705.youknow.presentation.features.home.features.chat.viewmodel.ChatState
import es.sebas1705.youknow.presentation.features.home.features.chat.viewmodel.ChatViewModel
import es.sebas1705.youknow.presentation.features.home.navigation.viewmodel.HomeState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Design of the Social Screen that will show the chat and group options.
 * It will show the chat by default and the group options if the user wants to.
 * The user can send messages in the chat and join groups.
 *
 * @param chatState [ChatState]: State of the screen.
 *
 * @see ChatViewModel
 * @see ChatState
 * @see Chat
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatDesign(
    windowState: WindowState = WindowState.default(),
    chatState: ChatState = ChatState.default(),
    homeState: HomeState = HomeState.default(),
    messageSender: (String) -> Unit = {},
) {
    var message by rememberSaveable { mutableStateOf("") }
    var showMessage by rememberSaveable { mutableStateOf(false) }

    ApplyBack(
        backId = windowState.backEmpty,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Chat(
            windowState = windowState,
            firebaseId = homeState.userModel?.firebaseId ?: "",
            messageModels = chatState.chatGlobal
        )

        if (showMessage) IFilledTextField(
            modifier = Modifier
                .fillMaxWidth(windowState.widthFilter(0.8f, 0.6f, 0.5f))
                .align(Alignment.BottomCenter)
                .padding(bottom = MediumPadding),
            value = message,
            onValueChange = { message = it },
            leadingIcon = Icons.Filled.Cancel to {
                showMessage = !showMessage
            },
            trailingIcon = Icons.AutoMirrored.Filled.Send to {
                messageSender(message)
            },
            trailingEnabled = message.isNotEmpty() || message.length >= SettingsRT.MESSAGE_MAX_LENGTH
        )
        else ISmallFAB(
            onClick = { showMessage = !showMessage },
            contentDescription = stringResource(R.string.chat_enabled),
            imageVector = Icons.Filled.Edit,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = MediumPadding, bottom = MediumPadding)
        )
    }
}


/**
 * Preview of the Social Screen.
 *
 * @see ChatDesign
 */
@UiModePreviews
@Composable
private fun SocialPreview() {
    YouKnowTheme {
        ChatDesign()
    }
}