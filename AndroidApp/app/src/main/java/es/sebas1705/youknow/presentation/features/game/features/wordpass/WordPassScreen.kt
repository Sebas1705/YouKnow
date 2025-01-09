package es.sebas1705.youknow.presentation.features.game.features.wordpass
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
import es.sebas1705.youknow.presentation.features.game.features.wordpass.design.WordPassDesign
import es.sebas1705.youknow.presentation.features.game.features.wordpass.viewmodel.WordPassIntent
import es.sebas1705.youknow.presentation.features.game.features.wordpass.viewmodel.WordPassViewModel

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


