package es.sebas1705.youknow.presentation.features.home.features.profile
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

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.domain.model.UserModel
import es.sebas1705.youknow.presentation.features.home.features.profile.design.ProfileDesign
import es.sebas1705.youknow.presentation.features.home.features.profile.viewmodel.ProfileIntent
import es.sebas1705.youknow.presentation.features.home.features.profile.viewmodel.ProfileViewModel

/**
 * Profile Screen that shows the user's data.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun ProfileScreen(
    windowState: WindowState,
    userModel: UserModel,
    onAuthNav: () -> Unit,
) {
    BackHandler {}
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val profileState by profileViewModel.uiState.collectAsStateWithLifecycle()

    ProfileDesign(
        windowState,
        profileState,
        userModel,
        onLogout = {
            profileViewModel.eventHandler(ProfileIntent.SignOut)
            onAuthNav()
        },
        onChangePhoto = {
            profileViewModel.eventHandler(
                ProfileIntent.ChangePhoto(
                    userModel.firebaseId,
                    it
                )
            )
        },
        onChangeNickname = {
            profileViewModel.eventHandler(
                ProfileIntent.ChangeNickname(
                    userModel.firebaseId,
                    it
                )
            )
        },
        onChangePassword = {
            profileViewModel.eventHandler(
                ProfileIntent.SendPasswordChanger(
                    userModel.email,
                )
            )
        }
    )
}

