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

import android.media.SoundPool
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.presentation.features.auth.navigation.AuthScreens.LogScreen
import es.sebas1705.youknow.presentation.features.auth.navigation.AuthScreens.MenuScreen
import es.sebas1705.youknow.presentation.features.auth.navigation.AuthScreens.SignScreen
import es.sebas1705.youknow.presentation.features.auth.screens.log.LogScreen
import es.sebas1705.youknow.presentation.features.auth.screens.menu.MenuScreen
import es.sebas1705.youknow.presentation.features.auth.screens.sign.SignScreen

/**
 * Navigation for the Auth feature.
 *
 * @param windowState [WindowState]: State of the window.
 * @param toHomeNav () -> Unit: Function to navigate to the Home feature.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun AuthNav(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    toHomeNav: () -> Unit,
) {
    // NavController:
    val authNavController = rememberNavController()

    // Body:
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = authNavController,
        startDestination = MenuScreen
    ) {
        composable<MenuScreen> {
            MenuScreen(
                windowState,
                soundPool,
                toSignNav = {
                    authNavController.navigate(SignScreen)
                },
                toHomeNav,
                toLogNav = {
                    authNavController.navigate(LogScreen)
                }
            )
        }
        composable<LogScreen> {
            LogScreen(
                windowState,
                soundPool,
                toHomeNav,
                toSignNav = {
                    authNavController.navigate(SignScreen)
                }
            )
        }
        composable<SignScreen> {
            SignScreen(
                windowState,
                soundPool,
                toLogNav = {
                    authNavController.navigate(LogScreen)
                }
            )
        }
    }
}