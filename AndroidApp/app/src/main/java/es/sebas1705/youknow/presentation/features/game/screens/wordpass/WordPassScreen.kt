package es.sebas1705.youknow.presentation.features.game.screens.wordpass
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
import es.sebas1705.youknow.core.classes.enums.WordPassType
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.game.screens.wordpass.composables.Finished
import es.sebas1705.youknow.presentation.features.game.screens.wordpass.composables.Running
import es.sebas1705.youknow.presentation.features.game.screens.wordpass.composables.SelectionMode
import es.sebas1705.youknow.presentation.features.game.viewmodels.WordPassIntent
import es.sebas1705.youknow.presentation.features.game.viewmodels.WordPassMode
import es.sebas1705.youknow.presentation.features.game.viewmodels.WordPassState
import es.sebas1705.youknow.presentation.features.game.viewmodels.WordPassStatus
import es.sebas1705.youknow.presentation.features.game.viewmodels.WordPassViewModel
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
fun WordPassScreen(
    windowState: WindowState,
    onOutGameNavigation: () -> Unit
) {
    val wordPassViewModel: WordPassViewModel = hiltViewModel()
    val wordPassState by wordPassViewModel.uiState.collectAsStateWithLifecycle()

    WordPassDesign(
        windowState,
        wordPassState,
        onSelectMode = { mode ->
            wordPassViewModel.eventHandler(WordPassIntent.SelectMode(mode))
            wordPassViewModel.eventHandler(
                WordPassIntent.GenerateGame(
                    Difficulty.ANY,
                    WordPassType.ANY,
                    mode,
                    mode.numWords
                )
            )
        },
        onResponse = { response ->
            wordPassViewModel.eventHandler(
                WordPassIntent.Response(
                    response,
                    wordPassState
                )
            )
        },
        onRestartGame = {
            wordPassViewModel.eventHandler(WordPassIntent.ResetGame(wordPassState.points))
        },
        onOutGame = {
            wordPassViewModel.eventHandler(WordPassIntent.OutGame(wordPassState.points) {
                onOutGameNavigation()
            })
        },
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
private fun WordPassDesign(
    windowState: WindowState = WindowState.default(),
    wordPassState: WordPassState = WordPassState.default(),
    onSelectMode: (WordPassMode) -> Unit = { },
    onResponse: (String) -> Unit = { },
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { }
) {
    if (wordPassState.isLoading) LoadingDialog(windowState)

    when (wordPassState.status) {
        WordPassStatus.SELECTION_MODE -> SelectionMode(windowState, wordPassState, onSelectMode)
        WordPassStatus.RUNNING -> Running(windowState, wordPassState, onResponse)
        WordPassStatus.FINISHED -> Finished(windowState, wordPassState, onRestartGame, onOutGame)
    }
}

/**
 * Preview of the Mystery Number Game.
 *
 * @see UiModePreviews
 * @see WordPassDesign
 *
 * @since 1.0.0
 */
@UiModePreviews
@Composable
private fun WordPassPreview() {
    YouKnowTheme {
        WordPassDesign()
    }
}
