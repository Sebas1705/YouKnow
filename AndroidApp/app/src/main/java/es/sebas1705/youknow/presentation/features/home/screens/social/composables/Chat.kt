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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.core.utlis.millisToFormatDate
import es.sebas1705.youknow.data.firebase.realtime.config.SettingsRT
import es.sebas1705.youknow.domain.model.MessageModel
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding

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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        state = lazyListState,
    ) {
        stickyHeader {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(SmallPadding)
                        .weight(8f),
                    value = message,
                    onValueChange = { message = it },
                    placeholder = { Text("Message") },
                    singleLine = true,
                )

                IconButton(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = SmallPadding)
                        .weight(1f),
                    enabled = message.isNotEmpty() || message.length >= SettingsRT.MESSAGE_MAX_LENGTH,
                    onClick = {
                        onMessageSend(message)
                        message = ""
                    },
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send",
                        modifier = Modifier,
                    )
                }
            }
        }

        messageModels.sortedBy { it.time }.forEach {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ){
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .align(
                                if (it.authorId == "1") Alignment.End
                                else Alignment.Start
                            )
                            .height(150.dp)
                            .padding(SmallPadding),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            ) {
                                Text(
                                    text = it.authorName,
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                    modifier = Modifier
                                        .padding(SmallPadding)
                                        .weight(1f),
                                )
                                Text(
                                    text = it.time.millisToFormatDate(),
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier
                                        .padding(SmallPadding)
                                        .weight(1.5f),
                                )
                            }
                            HorizontalDivider(thickness = 2.dp)
                            Text(
                                text = it.text,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2f)
                                    .padding(SmallPadding),
                            )
                        }
                    }
                }
            }
        }
    }
}