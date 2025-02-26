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

import android.media.SoundPool
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.presentation.features.home.features.profile.design.ProfileDesign
import es.sebas1705.youknow.presentation.features.home.features.profile.viewmodel.ProfileIntent
import es.sebas1705.youknow.presentation.features.home.features.profile.viewmodel.ProfileViewModel
import es.sebas1705.youknow.presentation.features.home.navigation.viewmodel.HomeState

/**
 * Profile Screen that shows the user's data.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun ProfileScreen(
    windowState: WindowState,
    homeState: HomeState,
    soundPool: Pair<SoundPool, Float>,
    onAuthNav: () -> Unit,
) {
    //Locals:
    val ctx = LocalContext.current
    BackHandler {}

    //ViewModel:
    val profileViewModel: ProfileViewModel = hiltViewModel()

    //State:
    val profileState by profileViewModel.uiState.collectAsStateWithLifecycle()

    //Body:
    ProfileDesign(
        windowState,
        profileState,
        homeState,
        soundPool,
        onLogout = {
            profileViewModel.eventHandler(ProfileIntent.SignOut)
            onAuthNav()
        },
        onChangePhoto = { photo ->
            homeState.userModel?.let { userModel ->
                profileViewModel.eventHandler(
                    ProfileIntent.ChangePhoto(
                        userModel.firebaseId,
                        photo
                    )
                )
            } ?: ctx.printTextInToast(ctx.getString(R.string.user_not_logged))
        },
        onChangeNickname = {
            homeState.userModel?.let { userModel ->
                profileViewModel.eventHandler(
                    ProfileIntent.ChangeNickname(
                        userModel.firebaseId,
                        it
                    )
                )
            } ?: ctx.printTextInToast(ctx.getString(R.string.user_not_logged))
        },
        onChangePassword = {
            homeState.userModel?.let { userModel ->
                profileViewModel.eventHandler(
                    ProfileIntent.SendPasswordChanger(
                        userModel.email,
                    )
                )
            } ?: ctx.printTextInToast(ctx.getString(R.string.user_not_logged))
        }
    )
}

