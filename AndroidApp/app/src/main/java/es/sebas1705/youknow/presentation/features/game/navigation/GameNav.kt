package es.sebas1705.youknow.presentation.features.game.navigation
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.presentation.features.game.screens.families.FamiliesScreen
import es.sebas1705.youknow.presentation.features.game.screens.mysterynumber.MysteryNumberScreen
import es.sebas1705.youknow.presentation.features.game.screens.quiz.QuizScreen
import es.sebas1705.youknow.presentation.features.game.screens.wordpass.WordPassScreen
import es.sebas1705.youknow.presentation.features.game.windows.GameOutWindow

/**
 * Home Navigation Composable that will handle the navigation between the different screens of the app.
 * It will also handle the bottom navigation bar and the floating action button.
 * It will also handle the back button to avoid the user to go back to the splash screen.
 * It will also handle the logout alert dialog.
 *
 * @param onLogOutNavigation () -> Unit: Function that will navigate to the logout screen.
 * @param onSettingsNavigation () -> Unit: Function that will navigate to the settings screen.
 *
 * @see BackHandler
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun GameNav(
    windowState: WindowState,
    startDestination: GameScreens,
    onOutGameNavigation: () -> Unit,
    onSettingsNavigation: () -> Unit
) {
    var outFlag by rememberSaveable { mutableStateOf(false) }
    var destination by rememberSaveable { mutableStateOf(startDestination) }
    BackHandler { outFlag = true }

    val context = LocalContext.current

    if (outFlag) GameOutWindow(
        windowState = windowState,
        onConfirm = {
            outFlag = false
            onOutGameNavigation()
        },
        onDismiss = { outFlag = false }
    )

    when (destination) {
        is GameScreens.MysteryNumberScreen -> {
            MysteryNumberScreen(
                windowState = windowState,
                onOutGameNavigation
            )
        }

        is GameScreens.QuizScreen -> {
            QuizScreen(
                windowState = windowState,
                onOutGameNavigation
            )
        }

        is GameScreens.WordPassScreen -> {
            WordPassScreen(
                windowState = windowState,
                onOutGameNavigation
            )
        }

        is GameScreens.FamiliesScreen -> {
            FamiliesScreen(
                windowState = windowState,
                onOutGameNavigation
            )
        }
    }
}






