package es.sebas1705.youknow.presentation.features.game.screens
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.texts.Title
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.home.viewmodels.UserState
import es.sebas1705.youknow.presentation.features.home.viewmodels.UserViewModel
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Screen of the Mystery Number Game.
 *
 * @see BackHandler
 * @see CalculationDesign
 *
 * @param windowState [WindowState]: State of the window.
 * @param userState [UserState]: State of the user.
 * @param userViewModel [UserViewModel]: ViewModel of the user.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun CalculationScreen(
    windowState: WindowState,
) {
    CalculationDesign(windowState)
}

/**
 * Design of the Mystery Number Game.
 *
 * @see ApplyBack
 * @see Title
 *
 * @param windowState [WindowState]: State of the window.
 *
 * @since 1.0.0
 */
@Composable
private fun CalculationDesign(
    windowState: WindowState = WindowState.default()
) {
    ApplyBack(
        windowState.backFill
    ) {
        Title(
            text = "(Coming soon)",
            modifier = Modifier.align(Alignment.Center)
        )
    }

}

/**
 * Preview of the Mystery Number Game.
 *
 * @see UiModePreviews
 * @see CalculationDesign
 *
 * @since 1.0.0
 */
@UiModePreviews
@Composable
private fun CalculationPreview() {
    YouKnowTheme {
        CalculationDesign()
    }
}

