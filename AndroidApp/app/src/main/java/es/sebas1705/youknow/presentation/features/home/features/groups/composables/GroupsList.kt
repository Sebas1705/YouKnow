package es.sebas1705.youknow.presentation.features.home.features.groups.composables
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.IFilledButton
import es.sebas1705.youknow.core.composables.buttons.icon.IFilledIconButton
import es.sebas1705.youknow.core.composables.cards.IInteractiveCard
import es.sebas1705.youknow.core.composables.dialogs.CreateGroupDialog
import es.sebas1705.youknow.core.composables.surfaces.IPrimarySurface
import es.sebas1705.youknow.core.composables.textfields.IOutlinedTextField
import es.sebas1705.youknow.core.utlis.Constants
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.domain.model.social.GroupModel
import es.sebas1705.youknow.presentation.features.home.features.groups.viewmodel.GroupsState
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
    windowState: WindowState = WindowState.default(),
    groupsState: GroupsState = GroupsState.default(),
    onGroupClick: (GroupModel) -> Unit = {},
    onGroupCreate: (String, String) -> Unit = { _, _ -> }
) {
    var alertDisplay by rememberSaveable { mutableStateOf(false) }
    var search by rememberSaveable { mutableStateOf("") }

    if (alertDisplay) {
        CreateGroupDialog(
            onConfirm = { name, description ->
                onGroupCreate(name, description)
                alertDisplay = false
            },
            onDismiss = {
                alertDisplay = false
            }
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
                    value = search,
                    placeholder = "Search",
                    label = "Search",
                    onValueChange = { search = it }
                )
                IFilledIconButton(
                    onClick = { alertDisplay = true },
                    contentDescription = "Create Group",
                    modifier = Modifier
                        .padding(end = SmallPadding)
                        .weight(1f),
                    imageVector = Icons.Filled.Add,
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = SmallestPadding, bottom = SmallestPadding),
        ) {
            val groups = groupsState.groups.filter { search.isEmpty() || it.name.contains(search, ignoreCase = true) }
            items(groups.size) { index ->
                IInteractiveCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = SmallPadding)
                        .padding(horizontal = SmallestPadding),
                    title = groups[index].name,
                    subtitle = "${groups[index].members.size}/${Constants.MAX_GROUP}",
                    buttons = {
                        IFilledButton(
                            label = "Join",
                            onClick = { onGroupClick(groups[index]) },
                            enabled = groups[index].members.size < Constants.MAX_GROUP
                        )
                    }
                )
            }
        }
    }
}

@UiModePreviews
@Composable
private fun GroupsListPreview() {
    YouKnowTheme {
        GroupsList()
    }
}