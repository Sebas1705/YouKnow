package es.sebas1705.youknow.presentation.features.home.screens.social.composables
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.icon.IStandardIconButton
import es.sebas1705.youknow.core.composables.surfaces.IPrimarySurface
import es.sebas1705.youknow.core.composables.textfields.IOutlinedTextField
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.data.firebase.realtime.config.SettingsRT
import es.sebas1705.youknow.domain.model.social.MessageModel
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Chat composable that will show the messages of the chat.
 * The user can send messages in the chat.
 * The messages will be shown in a list.
 *
 * @param messageModels [List]<[MessageModel]>: List of messages to show in the chat.
 * @param onMessageSend (String) -> Unit: Function to send a message.
 *
 * @see MessageModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Chat(
    windowState: WindowState = WindowState.default(),
    firebaseId: String = "",
    messageModels: List<MessageModel> = (1..10).map {
        MessageModel(
            text = "Message $it",
            time = System.currentTimeMillis(),
            authorId = it.toString(),
            authorName = "Author $it",
        )
    },
    onMessageSend: (String) -> Unit = {},
) {

    var message by remember { mutableStateOf("") }
    val lazyListState = rememberLazyListState()

    LaunchedEffect(messageModels.size) {
        lazyListState.scrollToItem(
            if (messageModels.isEmpty()) 0
            else messageModels.size - 1
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        IPrimarySurface(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = SmallPadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IOutlinedTextField(
                    modifier = Modifier
                        .padding(SmallPadding)
                        .weight(8f),
                    value = message,
                    placeholder = "Search",
                    label = "Search",
                    onValueChange = { message = it }
                )
                IStandardIconButton(
                    onClick = {
                        onMessageSend(message)
                        message = ""
                    },
                    contentDescription = "Send",
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    modifier = Modifier
                        .padding(end = SmallPadding)
                        .weight(1f),
                    enabled = message.isNotEmpty() || message.length >= SettingsRT.MESSAGE_MAX_LENGTH
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = SmallestPadding, bottom = SmallestPadding),
            state = lazyListState,
        ) {
            messageModels.sortedBy { it.time }.forEach {
                item {
                    MessageCard(
                        messageModel = it,
                        isCurrentUser = it.authorId == firebaseId
                    )
                }
            }
        }
    }
}

@UiModePreviews
@Composable
private fun ChatPreview() {
    YouKnowTheme {
        Chat()
    }
}