package es.sebas1705.youknow.presentation.features.game.features.wordpass.design
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
import es.sebas1705.youknow.core.classes.enums.games.wordpass.WordPassMode
import es.sebas1705.youknow.core.classes.enums.games.wordpass.WordPassStatus
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.game.features.wordpass.composables.Finished
import es.sebas1705.youknow.presentation.features.game.features.wordpass.composables.Running
import es.sebas1705.youknow.presentation.features.game.features.wordpass.composables.SelectionMode
import es.sebas1705.youknow.presentation.features.game.features.wordpass.viewmodel.WordPassState
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Design of the Mystery Number Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param wordPassState [WordPassState]: State of the game.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onSelectMode (WordPassMode) -> Unit: Function that will be called when the user selects a mode.
 * @param onResponse (String) -> Unit: Function that will be called when the user responds to the game.
 * @param onRestartGame () -> Unit: Function that will be called when the user wants to restart the game.
 * @param onOutGame () -> Unit: Function that will be called when the user wants to go out of the game.
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun WordPassDesign(
    windowState: WindowState = WindowState.default(),
    wordPassState: WordPassState = WordPassState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onSelectMode: (WordPassMode) -> Unit = { },
    onResponse: (String) -> Unit = { },
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { }
) {
    //Body:
    if (wordPassState.isLoading) LoadingDialog(windowState)

    when (wordPassState.status) {
        WordPassStatus.SELECTION_MODE -> SelectionMode(windowState, soundPool, onSelectMode)
        WordPassStatus.RUNNING -> Running(windowState, wordPassState, soundPool, onResponse)
        WordPassStatus.FINISHED -> Finished(windowState, wordPassState, soundPool, onRestartGame, onOutGame)
    }
}

@UiModePreviews
@Composable
private fun WordPassPreview() {
    YouKnowTheme {
        WordPassDesign()
    }
}