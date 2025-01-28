package es.sebas1705.youknow.presentation.features.home.features.groups
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
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.presentation.features.home.features.groups.design.GroupsDesign
import es.sebas1705.youknow.presentation.features.home.features.groups.viewmodel.GroupsIntent
import es.sebas1705.youknow.presentation.features.home.features.groups.viewmodel.GroupsViewModel
import es.sebas1705.youknow.presentation.features.home.navigation.viewmodel.HomeState

/**
 * Groups Screen that will show the groups options.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param homeState [HomeState]: The state of the home.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onUserInfoSearch (String) -> Unit: The search of the user info.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun GroupsScreen(
    windowState: WindowState,
    homeState: HomeState,
    soundPool: Pair<SoundPool, Float>,
    onUserInfoSearch: (String) -> Unit,
) {
    //Locals:
    val ctx = LocalContext.current
    BackHandler {}

    //ViewModel:
    val groupsViewModel: GroupsViewModel = hiltViewModel()

    //State:
    val groupsState by groupsViewModel.uiState.collectAsStateWithLifecycle()

    //Effects:
    LaunchedEffect(homeState.userModel) {
        groupsViewModel.eventHandler(GroupsIntent.LoadGroups(homeState.userModel?.groupId))
    }

    //Body:
    GroupsDesign(
        windowState,
        groupsState,
        homeState,
        soundPool,
        groupCreator = { name, description ->
            homeState.userModel?.let { userModel ->
                groupsViewModel.eventHandler(GroupsIntent.CreateGroup(name, description, userModel))
            } ?: ctx.printTextInToast(ctx.getString(R.string.user_not_logged))
        },
        groupJoin = { groupModel ->
            homeState.userModel?.let { userModel ->
                groupsViewModel.eventHandler(GroupsIntent.JoinGroup(groupModel, userModel))
            } ?: ctx.printTextInToast(ctx.getString(R.string.user_not_logged))
        },
        onGroupOutButton = {
            groupsState.groups.firstOrNull { it.groupId == homeState.userModel?.groupId }
                ?.let { groupModel ->
                    homeState.userModel?.let { userModel ->
                        groupsViewModel.eventHandler(GroupsIntent.OutGroup(groupModel, userModel))
                    } ?: ctx.printTextInToast(ctx.getString(R.string.user_not_logged))
                } ?: ctx.printTextInToast(ctx.getString(R.string.not_in_group))
        },
        onKickButton = { user ->
            groupsState.groups.firstOrNull { it.groupId == homeState.userModel?.groupId }
                ?.let { groupModel ->
                    groupsViewModel.eventHandler(GroupsIntent.KickGroup(groupModel, user))
                } ?: ctx.printTextInToast(ctx.getString(R.string.not_in_group))
        },
        onUserInfoSearch
    )
}