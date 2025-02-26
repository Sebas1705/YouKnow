package es.sebas1705.youknow.presentation.features.game.features.mysterynumber
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
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.design.MysteryNumberDesign
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.viewmodel.MysteryNumberIntent
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.viewmodel.MysteryNumberViewModel

/**
 * Screen of the Mystery Number Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onOutGameNavigation () -> Unit: Function to exit the game.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun MysteryNumberScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    onOutGameNavigation: () -> Unit
) {
    //ViewModel:
    val mysteryNumberViewModel: MysteryNumberViewModel = hiltViewModel()

    //State:
    val mysteryNumberState by mysteryNumberViewModel.uiState.collectAsStateWithLifecycle()

    //Body:
    MysteryNumberDesign(
        windowState,
        mysteryNumberState,
        soundPool,
        onSelectMode = { mode ->
            mysteryNumberViewModel.eventHandler(MysteryNumberIntent.SelectMode(mode))
        },
        onResponseNumber = { response, time ->
            mysteryNumberViewModel.eventHandler(
                MysteryNumberIntent.Response(
                    response,
                    time
                )
            )
        },
        onRestartGame = {
            mysteryNumberViewModel.eventHandler(MysteryNumberIntent.ResetGame)
        },
        onOutGame = {
            mysteryNumberViewModel.eventHandler(MysteryNumberIntent.OutGame {
                onOutGameNavigation()
            })
        },
        onStartGame = { difficulty, lives ->
            mysteryNumberViewModel.eventHandler(MysteryNumberIntent.GenerateGame(difficulty, lives))
        }
    )
}


