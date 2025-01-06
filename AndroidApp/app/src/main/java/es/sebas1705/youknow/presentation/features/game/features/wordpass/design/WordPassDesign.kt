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

import androidx.compose.runtime.Composable
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.game.features.wordpass.composables.Finished
import es.sebas1705.youknow.presentation.features.game.features.wordpass.composables.Running
import es.sebas1705.youknow.presentation.features.game.features.wordpass.composables.SelectionMode
import es.sebas1705.youknow.presentation.features.game.features.wordpass.viewmodel.WordPassMode
import es.sebas1705.youknow.presentation.features.game.features.wordpass.viewmodel.WordPassState
import es.sebas1705.youknow.presentation.features.game.features.wordpass.viewmodel.WordPassStatus
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

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
fun WordPassDesign(
    windowState: WindowState = WindowState.default(),
    wordPassState: WordPassState = WordPassState.default(),
    onSelectMode: (WordPassMode) -> Unit = { },
    onResponse: (String) -> Unit = { },
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { }
) {
    if (wordPassState.isLoading) LoadingDialog(windowState)

    when (wordPassState.status) {
        WordPassStatus.SELECTION_MODE -> SelectionMode(windowState, onSelectMode)
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