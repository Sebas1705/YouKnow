package es.sebas1705.youknow.presentation.navigation
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.utlis.extensions.composables.navAndPopUp
import es.sebas1705.youknow.presentation.features.guide.GuideScreen
import es.sebas1705.youknow.presentation.features.settings.SettingsScreen
import es.sebas1705.youknow.presentation.features.settings.viewmodel.SettingsState
import es.sebas1705.youknow.presentation.features.settings.viewmodel.SettingsViewModel
import es.sebas1705.youknow.presentation.features.auth.navigation.AuthNav
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthViewModel
import es.sebas1705.youknow.presentation.features.game.navigation.GameNav
import es.sebas1705.youknow.presentation.features.game.navigation.GameScreens
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
    startDestination: AppScreens,
    windowState: WindowState,
) {
    // NavController:
    val appNavController = rememberNavController()

    // ViewModel:
    val authViewModel = hiltViewModel<AuthViewModel>()

    // States:
    val authState by authViewModel.uiState.collectAsStateWithLifecycle()
    var lastComposable by remember { mutableStateOf(startDestination) }
    var gameComposable: GameScreens by remember { mutableStateOf(GameScreens.MysteryNumberScreen) }

    // Context:
    val context = LocalContext.current
    var lastGame: GameScreens = GameScreens.MysteryNumberScreen

    NavHost(navController = appNavController, startDestination = startDestination) {
        composable<AppScreens.GuideScreen> {
            GuideScreen(
                onSuccessNavigation = {
                    appNavController.navigate(
                        AppScreens.AuthNavigation
                    )
                }
            )
        }
        composable<AppScreens.TriviaScreen> {
            TriviaScreen(
                onSuccessLogOutNavigation = {
                    appNavController.navAndPopUp(AppScreens.AuthNavigation, AppScreens.TriviaScreen)
                },
                onErrorButton = {
                    appNavController.navigate(AppScreens.TriviaScreen)
                }
            )
        }
        composable<AppScreens.SettingsScreen> {
            SettingsScreen(
                windowState,
                onBack = {
                    appNavController.navAndPopUp(lastComposable, AppScreens.SettingsScreen)
                }
            )
        }
        composable<AppScreens.AuthNavigation> {
            AuthNav(
                windowState,
                authState,
                authViewModel,
                toHomeNav = {
                    appNavController.navAndPopUp(
                        AppScreens.HomeNavigation,
                        AppScreens.AuthNavigation
                    )
                },
            )
        }
        composable<AppScreens.HomeNavigation> {
            HomeNav(
                windowState = windowState,
                authViewModel = authViewModel,
                onLogOutNavigation = {
                    appNavController.navAndPopUp(
                        AppScreens.AuthNavigation,
                        AppScreens.HomeNavigation
                    )
                },
                onSettingsNavigation = {
                    lastComposable = AppScreens.HomeNavigation
                    appNavController.navigate(AppScreens.SettingsScreen)
                },
                onGameNavigation = { game ->
                    lastGame = game
                    lastComposable = AppScreens.HomeNavigation
                    appNavController.navigate(AppScreens.GameNavigation)
                }
            )
        }
        composable<AppScreens.GameNavigation> {
            GameNav(
                windowState,
                startDestination = lastGame,
                onOutGameNavigation = {
                    appNavController.navAndPopUp(
                        AppScreens.HomeNavigation,
                        AppScreens.GameNavigation
                    )
                },
                onSettingsNavigation = {
                    lastComposable = AppScreens.GameNavigation
                    appNavController.navigate(AppScreens.SettingsScreen)
                }
            )
        }
    }
}

