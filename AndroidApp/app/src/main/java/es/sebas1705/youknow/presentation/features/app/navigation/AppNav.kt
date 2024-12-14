package es.sebas1705.youknow.presentation.features.app.navigation
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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.utlis.navAndPopUp
import es.sebas1705.youknow.core.utlis.printTextInToast
import es.sebas1705.youknow.presentation.features.app.screens.GuideScreen
import es.sebas1705.youknow.presentation.features.app.screens.SettingsScreen
import es.sebas1705.youknow.presentation.features.app.viewmodels.SettingsState
import es.sebas1705.youknow.presentation.features.app.viewmodels.SettingsViewModel
import es.sebas1705.youknow.presentation.features.auth.navigation.AuthNav
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthIntent
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthViewModel
import es.sebas1705.youknow.presentation.features.game.screens.TriviaScreen
import es.sebas1705.youknow.presentation.features.home.navigation.HomeNav

/**
 * Navigation for the app.
 *
 * @param startDestination [Any]: Start destination of the app.
 * @param settingsState [SettingsState]: State of the settings.
 * @param settingsViewModel [SettingsViewModel]: ViewModel of the settings.
 * @param windowState [WindowState]: State of the window.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun AppNav(
    startDestination: Any,
    windowState: WindowState,
    settingsState: SettingsState,
    settingsViewModel: SettingsViewModel,
) {
    // NavController:
    val appNavController = rememberNavController()

    // ViewModel:
    val authViewModel = hiltViewModel<AuthViewModel>()

    // States:
    val authState by authViewModel.uiState.collectAsStateWithLifecycle()

    // Context:
    val context = LocalContext.current

    NavHost(navController = appNavController, startDestination = startDestination) {
        composable<GuideScreen> {
            GuideScreen(
                onSuccessNavigation = {
                    appNavController.navigate(
                        AuthNavigation
                    )
                }
            )
        }
        composable<TriviaScreen> {
            TriviaScreen(
                onSuccessLogOutNavigation = {
                    appNavController.navAndPopUp(AuthNavigation, TriviaScreen)
                },
                onErrorButton = {
                    appNavController.navigate(TriviaScreen)
                }
            )
        }
        composable<SettingsScreen> {
            SettingsScreen(
                windowState,
                settingsState,
                settingsViewModel,
                onBack = {
                    appNavController.navAndPopUp(HomeNavigation, SettingsScreen)
                }
            )
        }
        composable<AuthNavigation> {
            AuthNav(
                windowState,
                authState,
                authViewModel,
                toHomeNav = {
                    appNavController.navAndPopUp(HomeNavigation, AuthNavigation)
                },
            )
        }
        composable<HomeNavigation> {
            HomeNav(
                windowState,
                onLogOutNavigation = {
                    authViewModel.eventHandler(AuthIntent.SignOut(
                        { appNavController.navAndPopUp(AuthNavigation, HomeNavigation) },
                        { context.printTextInToast("Error in sign out, try again or reinstall the app") }
                    ))
                },
                onSettingsNavigation = {
                    appNavController.navigate(SettingsScreen)
                },
            )
        }
    }
}

