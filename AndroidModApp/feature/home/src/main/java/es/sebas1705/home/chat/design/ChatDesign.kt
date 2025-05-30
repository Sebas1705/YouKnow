package es.sebas1705.home.chat.design
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

import android.media.SoundPool
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.fab.ISmallFAB
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.textfields.IFilledTextField
import es.sebas1705.designsystem.texts.Title
import es.sebas1705.home.chat.composables.Chat
import es.sebas1705.realtime.config.SettingsRT
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.YouKnowTheme
import es.sebas1705.youknow.feature.home.R
import es.sebas1705.home.chat.viewmodel.ChatState
import es.sebas1705.home.navigation.viewmodel.HomeState

/**
 * Design of the Social Screen that will show the chat and group options.
 * It will show the chat by default and the group options if the user wants to.
 * The user can send messages in the chat and join groups.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param chatState [ChatState]: The state of the Chat Screen.
 * @param homeState [HomeState]: The state of the home.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param messageSender (String) -> Unit: The sender of the message.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun ChatDesign(
    windowState: WindowState = WindowState.default(),
    chatState: ChatState = ChatState.default(),
    homeState: HomeState = HomeState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    messageSender: (String) -> Unit = {},
) {
    //States:
    var message by rememberSaveable { mutableStateOf("") }

    //Flags:
    var showMessage by rememberSaveable { mutableStateOf(false) }

    //Body:
    ApplyBack(
        backId = windowState.backEmpty,
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (chatState.chatGlobal.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Title(
                    text = stringResource(R.string.feature_home_no_chats),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        } else Chat(
            windowState = windowState,
            soundPool = soundPool,
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
            trailingEnabled = message.isNotEmpty() || message.length >= SettingsRT.MESSAGE_MAX_LENGTH,
            soundPool = soundPool
        )
        else ISmallFAB(
            onClick = { showMessage = !showMessage },
            contentDescription = stringResource(R.string.feature_home_chat_enabled),
            imageVector = Icons.Filled.Edit,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = MediumPadding, bottom = MediumPadding),
            soundPool = soundPool
        )
    }
}

@UiModePreviews
@Composable
private fun SocialPreview() {
    YouKnowTheme {
        ChatDesign()
    }
}