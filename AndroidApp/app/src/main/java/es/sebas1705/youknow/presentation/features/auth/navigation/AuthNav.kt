package es.sebas1705.youknow.presentation.features.auth.navigation
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
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.sebas1705.youknow.presentation.features.auth.screens.sign.SignScreen
import es.sebas1705.youknow.presentation.features.auth.screens.log.LogScreen
import es.sebas1705.youknow.presentation.features.auth.screens.menu.MenuScreen
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthViewModel
import es.sebas1705.youknow.presentation.ui.classes.WindowState

/**
 * Navigation for the Auth feature.
 *
 * @param windowState [WindowState]: State of the window.
 * @param toHomeNav () -> Unit: Function to navigate to the Home feature.
 *
 * @see WindowState
 * @see AuthViewModel
 * @see SignScreen
 * @see LogScreen
 * @see MenuScreen
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun AuthNav(
    windowState: WindowState,
    toHomeNav: () -> Unit,
) {
    // NavController:
    val authNavController = rememberNavController()

    // ViewModel:
    val authViewModel: AuthViewModel = hiltViewModel()

    // Navigation functions:
    val toSignNav = { authNavController.navigate(SignScreen) }
    val toLogNav = { authNavController.navigate(LogScreen) }

    // Body:
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = authNavController,
        startDestination = MenuScreen
    ) {
        composable<MenuScreen> { MenuScreen(windowState, authViewModel, toSignNav, toHomeNav, toLogNav) }
        composable<LogScreen> { LogScreen(windowState, authViewModel, toHomeNav, toSignNav) }
        composable<SignScreen> { SignScreen(windowState, authViewModel, toLogNav) }
    }
}