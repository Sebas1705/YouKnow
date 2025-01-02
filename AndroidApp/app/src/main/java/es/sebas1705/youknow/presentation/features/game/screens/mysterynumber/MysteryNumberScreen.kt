package es.sebas1705.youknow.presentation.features.game.screens.mysterynumber
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
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.game.screens.mysterynumber.composables.Custom
import es.sebas1705.youknow.presentation.features.game.screens.mysterynumber.composables.Finished
import es.sebas1705.youknow.presentation.features.game.screens.mysterynumber.composables.Running
import es.sebas1705.youknow.presentation.features.game.screens.mysterynumber.composables.SelectionMode
import es.sebas1705.youknow.presentation.features.game.viewmodels.MysteryNumberIntent
import es.sebas1705.youknow.presentation.features.game.viewmodels.MysteryNumberMode
import es.sebas1705.youknow.presentation.features.game.viewmodels.MysteryNumberState
import es.sebas1705.youknow.presentation.features.game.viewmodels.MysteryNumberStatus
import es.sebas1705.youknow.presentation.features.game.viewmodels.MysteryNumberViewModel
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Screen of the Mystery Number Game.
 *
 * @param windowState [WindowState]: State of the window.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun MysteryNumberScreen(
    windowState: WindowState,
    onOutGameNavigation: () -> Unit
) {
    val mysteryNumberViewModel: MysteryNumberViewModel = hiltViewModel()
    val mysteryNumberState by mysteryNumberViewModel.uiState.collectAsStateWithLifecycle()

    MysteryNumberDesign(
        windowState,
        mysteryNumberState,
        onSelectMode = { mode ->
            mysteryNumberViewModel.eventHandler(MysteryNumberIntent.SelectMode(mode))
            if (mode != MysteryNumberMode.CUSTOM)
                mysteryNumberViewModel.eventHandler(
                    MysteryNumberIntent.GenerateGame(
                        Difficulty.ANY,
                        mode.lives
                    )
                )
        },
        onResponseNumber = { response, time ->
            mysteryNumberViewModel.eventHandler(
                MysteryNumberIntent.Response(
                    response,
                    time,
                    mysteryNumberState
                )
            )
        },
        onRestartGame = {
            mysteryNumberViewModel.eventHandler(MysteryNumberIntent.ResetGame)
        },
        onOutGame = {
            mysteryNumberViewModel.eventHandler(MysteryNumberIntent.OutGame(mysteryNumberState.points) {
                onOutGameNavigation()
            })
        },
        onStartGame = { difficulty, lives ->
            mysteryNumberViewModel.eventHandler(MysteryNumberIntent.GenerateGame(difficulty, lives))
        }
    )
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
private fun MysteryNumberDesign(
    windowState: WindowState = WindowState.default(),
    mysteryNumberState: MysteryNumberState = MysteryNumberState.default(),
    onSelectMode: (MysteryNumberMode) -> Unit = { },
    onResponseNumber: (Int, Float) -> Unit = { _, _ -> },
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { },
    onStartGame: (Difficulty, Int) -> Unit = { _, _ -> }
) {
    if (mysteryNumberState.isLoading) LoadingDialog(windowState)

    when (mysteryNumberState.status) {
        MysteryNumberStatus.SELECTION_MODE -> SelectionMode(
            windowState,
            mysteryNumberState,
            onSelectMode
        )

        MysteryNumberStatus.CUSTOM -> Custom(windowState, mysteryNumberState, onStartGame)
        MysteryNumberStatus.RUNNING -> Running(windowState, mysteryNumberState, onResponseNumber)
        MysteryNumberStatus.FINISHED -> Finished(
            windowState,
            mysteryNumberState,
            onRestartGame,
            onOutGame
        )
    }
}

/**
 * Preview of the Mystery Number Game.
 *
 * @see UiModePreviews
 * @see MysteryNumberDesign
 *
 * @since 1.0.0
 */
@UiModePreviews
@Composable
private fun MysteryNumberPreview() {
    YouKnowTheme {
        MysteryNumberDesign()
    }
}
