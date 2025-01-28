package es.sebas1705.youknow.presentation.features.game.features.mysterynumber.design
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
import es.sebas1705.youknow.core.classes.enums.games.Difficulty
import es.sebas1705.youknow.core.classes.enums.games.mysterynumber.MysteryNumberMode
import es.sebas1705.youknow.core.classes.enums.games.mysterynumber.MysteryNumberStatus
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.composables.Custom
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.composables.Finished
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.composables.Running
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.composables.SelectionMode
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.viewmodel.MysteryNumberState
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Design of the Mystery Number Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param mysteryNumberState [MysteryNumberState]: State of the game.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onSelectMode ([MysteryNumberMode]) -> Unit: Function to select the game mode.
 * @param onResponseNumber (Int, Float) -> Unit: Function to respond to the number.
 * @param onRestartGame () -> Unit: Function to restart the game.
 * @param onOutGame () -> Unit: Function to exit the game.
 * @param onStartGame (Difficulty, Int) -> Unit: Function to start the game.
 *
 * @since 1.0.0
 * @Author Sebastián Ramiro Entrerrios García
 */
@Composable
fun MysteryNumberDesign(
    windowState: WindowState = WindowState.default(),
    mysteryNumberState: MysteryNumberState = MysteryNumberState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onSelectMode: (MysteryNumberMode) -> Unit = { },
    onResponseNumber: (Int, Float) -> Unit = { _, _ -> },
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { },
    onStartGame: (Difficulty, Int) -> Unit = { _, _ -> }
) {
    //Body:
    if (mysteryNumberState.isLoading) LoadingDialog(windowState)

    when (mysteryNumberState.status) {
        MysteryNumberStatus.SELECTION_MODE -> SelectionMode(
            windowState,
            soundPool,
            onSelectMode
        )

        MysteryNumberStatus.CUSTOM -> Custom(
            windowState,
            soundPool,
            onStartGame
        )

        MysteryNumberStatus.RUNNING -> Running(
            windowState,
            mysteryNumberState,
            soundPool,
            onResponseNumber
        )

        MysteryNumberStatus.FINISHED -> Finished(
            windowState,
            mysteryNumberState,
            soundPool,
            onRestartGame,
            onOutGame
        )
    }
}

@UiModePreviews
@Composable
private fun MysteryNumberPreview() {
    YouKnowTheme {
        MysteryNumberDesign()
    }
}