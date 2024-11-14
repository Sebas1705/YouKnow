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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.core.utlis.Constants
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.domain.model.GroupModel
import es.sebas1705.youknow.presentation.composables.CustomFilledButton
import es.sebas1705.youknow.presentation.composables.Spacers.VerticalSpacer
import es.sebas1705.youknow.presentation.features.home.windows.CreateGroupWindow
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

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
fun GroupsList(
    groupModels: List<GroupModel> = (1..10).map { value ->
        GroupModel(
            name = "Group $value",
            description = "Description $value",
            members = (1..10).map { it.toString() },
            leaderUID = value.toString()
        )
    },
    onGroupClick: (GroupModel) -> Unit = {},
    onGroupCreate: (String, String) -> Unit = { _, _ -> }
) {
    var alertDisplay by remember { mutableStateOf(false) }
    var search by remember { mutableStateOf("") }
    val groupsFilter by remember {
        derivedStateOf {
            groupModels.filter { search.isEmpty() || it.name.contains(search, ignoreCase = true) }
        }
    }

    if (alertDisplay) {
        CreateGroupWindow(
            onConfirmButton = { name, description ->
                onGroupCreate(name, description)
                alertDisplay = false
            },
            onDismissAction = {
                alertDisplay = false
            }
        )
    }

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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = SmallPadding),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        modifier = Modifier.weight(0.70f),
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
                    VerticalSpacer(0.05f)
                    IconButton(
                        modifier = Modifier
                            .weight(0.25f)
                            .fillMaxHeight(),
                        onClick = { alertDisplay = true },
                        content = {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Create Group",
                            )
                        },
                        colors =  IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
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

            VerticalDivider(thickness = 5.dp)

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

@UiModePreviews
@Composable
private fun GroupsListPreview(){
    YouKnowTheme {
        GroupsList()
    }
}