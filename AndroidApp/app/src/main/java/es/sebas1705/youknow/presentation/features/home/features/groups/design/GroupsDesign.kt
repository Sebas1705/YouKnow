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

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.composables.dialogs.UserInfoDialog
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.domain.model.UserModel
import es.sebas1705.youknow.domain.model.social.GroupModel
import es.sebas1705.youknow.presentation.features.home.features.groups.composables.Group
import es.sebas1705.youknow.presentation.features.home.features.groups.composables.GroupsList
import es.sebas1705.youknow.presentation.features.home.features.groups.viewmodel.GroupsState
import es.sebas1705.youknow.presentation.features.home.navigation.viewmodel.HomeState
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Design of the Groups Screen.
 *
 * @param windowState [WindowState]: The state of the window.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun GroupsDesign(
    windowState: WindowState = WindowState.default(),
    groupsState: GroupsState = GroupsState.default(),
    homeState: HomeState = HomeState.default(),
    groupCreator: (String, String) -> Unit = { _, _ -> },
    groupJoin: (GroupModel) -> Unit = {},
    onGroupOutButton: () -> Unit = {},
    onKickButton: (String) -> Unit = {},
    onUserInfoSearch: (String) -> Unit = {}
) {
    //States:
    var infoDisplay by remember { mutableStateOf(false) }
    var userInfoId by remember { mutableStateOf("") }

    ApplyBack(
        backId = windowState.backEmpty,
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (groupsState.isLoading)
            LoadingDialog()
        else if (infoDisplay) UserInfoDialog(
            userModel = homeState.infoUser,
            onDismiss = { infoDisplay = false }
        )

        groupsState.myGroup?.let {
            Group(
                windowState,
                groupsState,
                homeState,
                onOutButton = onGroupOutButton,
                onInfoButton = {
                    onUserInfoSearch(it)
                    userInfoId = it
                    infoDisplay = true
                },
                onKickButton = onKickButton,
            )
        } ?: GroupsList(
            windowState,
            groupsState,
            onGroupClick = groupJoin,
            onGroupCreate = groupCreator
        )
    }

}

@UiModePreviews
@Composable
private fun GroupsPreview() {
    YouKnowTheme {
        GroupsDesign()
    }
}