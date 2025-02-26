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

import android.media.SoundPool
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.ComposableConstants
import es.sebas1705.youknow.core.composables.buttons.common.IFilledButton
import es.sebas1705.youknow.core.composables.cards.IInteractiveCard
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
 * @param windowState [WindowState]: The state of the window.
 * @param groupsState [GroupsState]: The state of the Groups Screen.
 * @param groups [List]<[GroupModel]>: The list of groups to show.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onGroupClick (GroupModel) -> Unit: The click on a group.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun GroupsList(
    windowState: WindowState = WindowState.default(),
    groupsState: GroupsState = GroupsState.default(),
    groups: List<GroupModel> = groupsState.groups,
    soundPool: Pair<SoundPool, Float>? = null,
    onGroupClick: (GroupModel) -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SmallestPadding, bottom = SmallestPadding),
    ) {
        items(groups.size) { index ->
            IInteractiveCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = SmallPadding)
                    .padding(horizontal = SmallestPadding),
                title = groups[index].name,
                subtitle = "${groups[index].members.size}/${ComposableConstants.MAX_GROUP}",
                buttons = {
                    IFilledButton(
                        label = "Join",
                        onClick = { onGroupClick(groups[index]) },
                        enabled = groups[index].members.size < ComposableConstants.MAX_GROUP,
                        soundPool = soundPool
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