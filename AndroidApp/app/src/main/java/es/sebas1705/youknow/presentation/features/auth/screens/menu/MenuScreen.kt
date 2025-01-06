package es.sebas1705.youknow.presentation.features.auth.screens.menu
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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.presentation.features.auth.screens.menu.design.MenuDesign
import es.sebas1705.youknow.presentation.features.auth.screens.menu.viewmodel.MenuIntent
import es.sebas1705.youknow.presentation.features.auth.screens.menu.viewmodel.MenuViewModel
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthViewModel

/**
 * Menu Screen that will show the user the options to sign in, log in or log in with Google.
 * The user will be able to navigate to the Sign In Screen, Log In Screen or Home Screen.
 * The user will be able to log in with Google.
 *
 * @param windowState [WindowState]: State of the window.
 * @param authViewModel [AuthViewModel]: ViewModel for the Auth Screen.
 * @param toSignNav () -> Unit: Function to navigate to the Sign In Screen.
 * @param toHomeNav () -> Unit: Function to navigate to the Home Screen.
 * @param toLogNav () -> Unit: Function to navigate to the Log In Screen.
 *
 * @see AuthViewModel
 * @see WindowState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun MenuScreen(
    windowState: WindowState,
    toSignNav: () -> Unit,
    toHomeNav: () -> Unit,
    toLogNav: () -> Unit,
) {
    val menuViewModel: MenuViewModel = hiltViewModel()
    val menuState by menuViewModel.uiState.collectAsStateWithLifecycle()

    MenuDesign(
        windowState,
        menuState,
        onSignButtonAction = toSignNav,
        onEmailLogButtonAction = toLogNav,
        onGoogleLogButtonAction = {
            menuViewModel.eventHandler(MenuIntent.SignWithGoogle(toHomeNav))
        }
    )
}