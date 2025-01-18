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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.IFilledButton
import es.sebas1705.youknow.core.composables.cards.IInteractiveCard
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
    searchFilter: String = "",
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SmallestPadding, bottom = SmallestPadding),
    ) {
        val groups = groupsState.groups.filter {
            searchFilter.isEmpty() || it.name.contains(
                searchFilter,
                ignoreCase = true
            )
        }
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

@UiModePreviews
@Composable
private fun GroupsListPreview() {
    YouKnowTheme {
        GroupsList()
    }
}