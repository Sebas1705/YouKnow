package es.sebas1705.game.wordpass
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.states.WindowState
import es.sebas1705.game.wordpass.design.WordPassDesign
import es.sebas1705.game.wordpass.viewmodel.WordPassIntent
import es.sebas1705.game.wordpass.viewmodel.WordPassViewModel

/**
 * Screen of the Mystery Number Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onOutGameNavigation () -> Unit: Function that will be called when the user wants to go out of the game.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun WordPassScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    onOutGameNavigation: () -> Unit
) {
    //ViewModel:
    val wordPassViewModel: WordPassViewModel = hiltViewModel()

    //State:
    val wordPassState by wordPassViewModel.uiState.collectAsStateWithLifecycle()

    //Effects:
    LaunchedEffect(null) {
        wordPassViewModel.eventHandler(WordPassIntent.ReadLanguages)
    }

    //Body:
    WordPassDesign(
        windowState,
        wordPassState,
        soundPool,
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
            wordPassViewModel.eventHandler(WordPassIntent.Response(response))
        },
        onRestartGame = {
            wordPassViewModel.eventHandler(WordPassIntent.ResetGame)
        },
        onOutGame = {
            wordPassViewModel.eventHandler(WordPassIntent.OutGame {
                onOutGameNavigation()
            })
        },
    )
}


