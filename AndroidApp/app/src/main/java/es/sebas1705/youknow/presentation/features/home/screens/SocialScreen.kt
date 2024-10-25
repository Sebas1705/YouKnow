package es.sebas1705.youknow.presentation.features.home.screens
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

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.utlis.Constants
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.millisToFormatDate
import es.sebas1705.youknow.data.firebase.realtime.config.SettingsRT
import es.sebas1705.youknow.domain.model.GroupModel
import es.sebas1705.youknow.domain.model.MessageModel
import es.sebas1705.youknow.presentation.composables.ApplyBack
import es.sebas1705.youknow.presentation.composables.CustomFilledButton
import es.sebas1705.youknow.presentation.composables.Spacers.VerticalSpacer
import es.sebas1705.youknow.presentation.features.home.viewmodels.SocialIntent
import es.sebas1705.youknow.presentation.features.home.viewmodels.SocialState
import es.sebas1705.youknow.presentation.features.home.viewmodels.SocialViewModel
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
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
) {

    val socialViewModel: SocialViewModel = hiltViewModel()
    val socialState by socialViewModel.uiState.collectAsStateWithLifecycle()
    SocialDesign(
        socialViewModel = socialViewModel,
        socialState = socialState
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
 * @see MyGroup
 * @see NewGroup
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun SocialDesign(
    socialViewModel: SocialViewModel? = null,
    socialState: SocialState? = null,
) {

    var isGroup by remember { mutableStateOf(false) }
    val myGroupModel by remember {
        mutableStateOf<GroupModel?>(
            null
        )
    }

    ApplyBack(
        R.drawable.back_portrait_empty,
    ) {
        Column(
            modifier = Modifier.padding(top = MediumPadding),
        ) {
            IconsBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.8f),
                isGroup = isGroup,
                changeToGroup = { isGroup = true },
                changeToChat = { isGroup = false },
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(20f)
                    .padding(horizontal = SmallPadding)
                    .padding(bottom = MediumPadding),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.primaryContainer,
                border = BorderStroke(
                    width = 4.dp,
                    color = MaterialTheme.colorScheme.primary,
                ),
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(SmallPadding),
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                ) {
                    if (isGroup) {
                        myGroupModel?.let {
                            MyGroup(it)
                        } ?: NewGroup()
                    } else {
                        Chat(
                            messageModels = socialState?.chatGlobal ?: emptyList(),
                            onMessageSend = {
                                socialViewModel?.eventHandler(
                                    SocialIntent.SendMessage(
                                        it
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

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
                                if(it.authorId == "1") Alignment.End
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

/**
 * Group composable that will show the group information.
 * The user can see the group information and the members of the group.
 * The user can add or delete members if it is the admin of the group.
 *
 * @param groupModel [GroupModel]: Group to show.
 * @param admin [Boolean]: If the user is the admin of the group.
 *
 * @see GroupModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun MyGroup(
    groupModel: GroupModel,
    admin: Boolean = true,
) {
    Surface(
        color = MaterialTheme.colorScheme.onPrimaryContainer,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SmallPadding),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = groupModel.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(SmallPadding),
                )
                HorizontalDivider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    Text(
                        text = groupModel.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(3f)
                            .padding(SmallPadding),
                    )
                    VerticalDivider()
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .padding(SmallPadding),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Members",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                        Text(
                            text = "${groupModel.members.size}/${Constants.MAX_GROUP}",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                    }
                }

            }

            Text(
                text = "Members:",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SmallestPadding),
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
                    .padding(SmallPadding),
            ) {
                items(groupModel.members.size) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(SmallPadding),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(SmallPadding),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Member $index",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .padding(SmallPadding),
                            )
                            IconButton(
                                onClick = { /*TODO*/ },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AddCircleOutline,
                                    contentDescription = "Fight",
                                    modifier = Modifier
                                        .padding(SmallPadding),
                                )
                            }
                            if (admin) IconButton(
                                onClick = { /*TODO*/ },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    modifier = Modifier
                                        .padding(SmallPadding),
                                )
                            }
                        }
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

/**
 * Icons for navigation between chat and group.
 *
 * @param modifier [Modifier]: Modifier to apply to the icons.
 * @param isGroup [Boolean]: If the user is in the group section.
 * @param changeToGroup () -> Unit: Function to change to the group section.
 * @param changeToChat () -> Unit: Function to change to the chat section.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun IconsBar(
    modifier: Modifier = Modifier,
    isGroup: Boolean,
    changeToGroup: () -> Unit,
    changeToChat: () -> Unit,
) {
    Row(
        modifier = modifier,
    ) {
        VerticalSpacer(5f)
        IconItem(
            modifier = Modifier
                .fillMaxHeight()
                .weight(20f),
            enabled = !isGroup,
            onClick = changeToGroup,
            imageVector = Icons.Default.Groups,
            contentDescription = "Group",
        )
        VerticalSpacer(1f)
        IconItem(
            modifier = Modifier
                .fillMaxHeight()
                .weight(20f),
            enabled = isGroup,
            onClick = changeToChat,
            imageVector = Icons.Default.Forum,
            contentDescription = "Menu",
        )
        VerticalSpacer(5f)
    }

}

/**
 * Icon item for the navigation between chat and group.
 * The icon will be a button that will change the section when clicked.
 * The icon will be enabled if the user is in the section of the icon.
 *
 * @param modifier [Modifier]: Modifier to apply to the icon.
 * @param enabled [Boolean]: If the icon is enabled.
 * @param onClick () -> Unit: Function to change the section.
 * @param imageVector [ImageVector]: Icon to show.
 * @param contentDescription [String]: Content description of the icon.
 *
 * @see IconsBar
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun IconItem(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
        ),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary,
        )
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(0.7f),
            imageVector = imageVector,
            contentDescription = contentDescription,
        )
    }
}

/**
 * New group composable that will show the groups to join.
 * The user can search for a group and join it.
 * The groups will be shown in a list.
 *
 * @param groupModels [List]<[GroupModel]>: List of groups to show.
 * @param onGroupClick (GroupModel) -> Unit: Function to join a group.
 *
 * @see GroupModel
 * @see CardGroup
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NewGroup(
    groupModels: List<GroupModel> = (1..10).map { value ->
        GroupModel(
            name = "Group $value",
            description = "Description $value",
            members = (1..10).map { it.toLong() },
            leaderUID = value.toLong()
        )
    },
    onGroupClick: (GroupModel) -> Unit = {},
) {
    var search by remember { mutableStateOf("") }
    val groupsFilter by remember {
        derivedStateOf {
            groupModels.filter { search.isEmpty() || it.name.contains(search, ignoreCase = true) }
        }
    }
    Log.d("GroupsFilter", groupsFilter.toString())
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(SmallPadding),
    ) {

        stickyHeader {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = SmallPadding),
                    value = search,
                    onValueChange = {
                        search = it
                    },
                    placeholder = { Text("Search") },
                    textStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        focusedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    ),
                    shape = MaterialTheme.shapes.medium,
                )
            }
        }

        items(groupsFilter.size) { index ->
            CardGroup(
                groupsFilter[index],
                onGroupClick = onGroupClick
            )
        }
    }

}

/**
 * Card group composable that will show the group information.
 * The user can see the group information and join the group.
 * The user can join the group if it is not full.
 *
 * @param groupModel [GroupModel]: Group to show.
 * @param onGroupClick (GroupModel) -> Unit: Function to join the group.
 *
 * @see GroupModel
 * @see NewGroup
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun CardGroup(
    groupModel: GroupModel,
    onGroupClick: (GroupModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = SmallPadding)
            .padding(horizontal = SmallestPadding),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SmallPadding),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = groupModel.name,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(SmallPadding),
            )

            VerticalDivider()

            Text(
                text = "${groupModel.members.size}/${Constants.MAX_GROUP}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(SmallPadding),
            )

            CustomFilledButton(
                modifier = Modifier,
                text = "Join",
                onClick = { onGroupClick(groupModel) }
            )
        }
    }
}

