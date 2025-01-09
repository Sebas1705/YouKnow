package es.sebas1705.youknow.presentation.features.home.features.profile.design
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.fab.IFAB
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.composables.dialogs.LogoutDialog
import es.sebas1705.youknow.core.composables.dialogs.NickDialog
import es.sebas1705.youknow.core.composables.dialogs.ResetPasswordDialog
import es.sebas1705.youknow.core.composables.dialogs.UrlRequestDialog
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.domain.model.UserModel
import es.sebas1705.youknow.presentation.features.home.features.profile.composables.LazyProfileItem
import es.sebas1705.youknow.presentation.features.home.features.profile.viewmodel.ProfileState
import es.sebas1705.youknow.presentation.features.home.navigation.viewmodel.HomeState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Design of the Profile Screen. It shows the user's data.
 *
 * @param windowState [WindowState]: State of the window.
 * @param userState [UserState]: State of the Home Screen.
 * @param profileState [ProfileState]: State of the Profile Screen.
 * @param onChangePhoto (String) -> Unit: Function to change the user's photo.
 * @param onChangeNickname (String) -> Unit: Function to change the user's nickname.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileDesign(
    windowState: WindowState = WindowState.default(),
    profileState: ProfileState = ProfileState.default(),
    homeState: HomeState = HomeState.defaultWithUser(),
    onLogout: () -> Unit = {},
    onChangePhoto: (String) -> Unit = {},
    onChangeNickname: (String) -> Unit = {},
    onChangePassword: () -> Unit = {}
) {
    var nickname by remember { mutableStateOf(homeState.userModel?.nickName ?: "") }

    //Flag:
    var changePhotoDialog by remember { mutableStateOf(false) }
    var changeNicknameDialog by remember { mutableStateOf(false) }
    var changePassDialog by remember { mutableStateOf(false) }
    var signOutDialog by remember { mutableStateOf(false) }

    ApplyBack(
        backId = windowState.backFill,
        modifier = Modifier.fillMaxSize()
    ) {
        if (profileState.isLoading)
            LoadingDialog(windowState)
        else if (signOutDialog)
            LogoutDialog(
                windowState,
                onConfirm = {
                    signOutDialog = false
                    onLogout()
                },
                onDismiss = {
                    signOutDialog = false
                }
            )
        else if (changePhotoDialog) UrlRequestDialog(
            windowState,
            onConfirmButton = {
                changePhotoDialog = false
                onChangePhoto(it)
            },
            onDismissAction = { changePhotoDialog = false }
        )
        else if (changeNicknameDialog) NickDialog(
            nickname = nickname,
            firebaseId = homeState.userModel?.firebaseId ?: "",
            onConfirm = {
                changeNicknameDialog = false
                onChangeNickname(it)
            },
            onDismiss = { changeNicknameDialog = false }
        )
        else if (changePassDialog)
            ResetPasswordDialog(
                email = homeState.userModel?.email ?: "",
                windowState,
                onConfirm = {
                    changePassDialog = false
                    onChangePassword()
                },
                onDismiss = {
                    changePassDialog = false
                }
            )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                LazyProfileItem(
                    windowState = windowState,
                    userModel = homeState.userModel ?: UserModel.default(),
                    nickname = nickname,
                    onChangeNickname = { nickname = it },
                    onChangeNicknameDialog = { changeNicknameDialog = true },
                    onChangePhotoDialog = { changePhotoDialog = true },
                    onChangePassDialog = { changePassDialog = true }
                )
            }
        }

        IFAB(
            onClick = { signOutDialog = true },
            contentDescription = stringResource(R.string.logout),
            imageVector = Icons.AutoMirrored.Filled.Logout,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = MediumPadding, bottom = MediumPadding)
        )
    }
}

/**
 * Preview of the [ProfileDesign] composable.
 *
 * @see [ProfileDesign]
 */
@UiModePreviews
@Composable
private fun ProfilePreview() {
    YouKnowTheme {
        ProfileDesign()
    }
}