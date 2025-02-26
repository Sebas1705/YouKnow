package es.sebas1705.youknow.presentation.features.home.features.groups.design
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
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
import es.sebas1705.youknow.core.composables.buttons.fab.IFAB
import es.sebas1705.youknow.core.composables.buttons.fab.ISmallFAB
import es.sebas1705.youknow.core.composables.dialogs.CreateGroupDialog
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.composables.dialogs.UserInfoDialog
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.textfields.IFilledTextField
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.composables.texts.Title
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.domain.model.social.GroupModel
import es.sebas1705.youknow.domain.model.social.UserModel
import es.sebas1705.youknow.presentation.features.home.features.groups.composables.Group
import es.sebas1705.youknow.presentation.features.home.features.groups.composables.GroupsList
import es.sebas1705.youknow.presentation.features.home.features.groups.viewmodel.GroupsState
import es.sebas1705.youknow.presentation.features.home.navigation.viewmodel.HomeState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Design of the Groups Screen.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param groupsState [GroupsState]: The state of the Groups Screen.
 * @param homeState [HomeState]: The state of the home.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param groupCreator (String, String) -> Unit: The creation of a group.
 * @param groupJoin (GroupModel) -> Unit: The join of a group.
 * @param onGroupOutButton () -> Unit: The out of a group.
 * @param onKickButton (String) -> Unit: The kick of a member.
 * @param onUserInfoSearch (String) -> Unit: The search of the user info.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun GroupsDesign(
    windowState: WindowState = WindowState.default(),
    groupsState: GroupsState = GroupsState.default(),
    homeState: HomeState = HomeState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    groupCreator: (String, String) -> Unit = { _, _ -> },
    groupJoin: (GroupModel) -> Unit = {},
    onGroupOutButton: () -> Unit = {},
    onKickButton: (String) -> Unit = {},
    onUserInfoSearch: (List<String>) -> Unit = {}
) {
    //States:
    var userInfoId by rememberSaveable { mutableStateOf("") }
    var search by rememberSaveable { mutableStateOf("") }

    //Flags:
    var infoDisplay by rememberSaveable { mutableStateOf(false) }
    var showSearch by rememberSaveable { mutableStateOf(false) }
    var createFlag by rememberSaveable { mutableStateOf(false) }

    //Body:
    if (createFlag) {
        CreateGroupDialog(
            onConfirm = { name, description ->
                groupCreator(name, description)
                createFlag = false
            },
            onDismiss = {
                createFlag = false
            },
            soundPool = soundPool
        )
    }

    ApplyBack(
        backId = windowState.backEmpty,
        modifier = Modifier
            .fillMaxSize()
    ) {

        if (groupsState.isLoading)
            LoadingDialog(windowState)
        else if (infoDisplay && homeState.infoUsers.containsKey(userInfoId))
            UserInfoDialog(
                windowState,
                userModel = homeState.infoUsers[userInfoId]?: UserModel.default(),
                onDismiss = { infoDisplay = false },
                soundPool = soundPool
            )

        val myGroup = groupsState.groups.firstOrNull { it.groupId == homeState.userModel?.groupId }
        if (myGroup != null) {
            Group(
                windowState,
                homeState,
                soundPool,
                groupModel = myGroup,
                onOutButton = onGroupOutButton,
                onInfoButton = {
                    userInfoId = it
                    infoDisplay = true
                },
                onKickButton = onKickButton,
                onUsersLoad = onUserInfoSearch
            )
        } else {
            val groups = groupsState.groups.filter {
                search.isEmpty() || it.name.contains(
                    search,
                    ignoreCase = true
                )
            }
            if(groups.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Title(
                        text = stringResource(R.string.no_groups),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            } else GroupsList(
                windowState,
                groupsState,
                groups,
                soundPool,
                onGroupClick = groupJoin
            )

            if (showSearch) IFilledTextField(
                modifier = Modifier
                    .fillMaxWidth(windowState.widthFilter(0.8f, 0.6f, 0.5f))
                    .align(Alignment.BottomCenter)
                    .padding(bottom = MediumPadding),
                value = search,
                placeholder = stringResource(R.string.search),
                leadingIcon = Icons.Filled.Cancel to {
                    showSearch = !showSearch
                },
                onValueChange = { search = it },
                soundPool = soundPool
            )
            else Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = MediumPadding, bottom = MediumPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ISmallFAB(
                    onClick = { createFlag = !createFlag },
                    contentDescription = stringResource(R.string.add_group),
                    imageVector = Icons.Filled.Add,
                    modifier = Modifier
                        .padding(bottom = SmallPadding),
                    soundPool = soundPool
                )
                IFAB(
                    onClick = { showSearch = !showSearch },
                    contentDescription = stringResource(R.string.search_enabled),
                    imageVector = Icons.Filled.Search,
                    soundPool = soundPool
                )
            }
        }
    }
}

@UiModePreviews
@Composable
private fun GroupsPreview() {
    YouKnowTheme {
        GroupsDesign()
    }
}