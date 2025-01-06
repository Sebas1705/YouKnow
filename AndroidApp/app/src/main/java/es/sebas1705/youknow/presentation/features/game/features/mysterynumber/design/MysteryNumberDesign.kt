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

import androidx.compose.runtime.Composable
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.composables.Custom
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.composables.Finished
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.composables.Running
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.composables.SelectionMode
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.viewmodel.MysteryNumberMode
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.viewmodel.MysteryNumberState
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.viewmodel.MysteryNumberStatus
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
fun MysteryNumberDesign(
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
            onSelectMode
        )

        MysteryNumberStatus.CUSTOM -> Custom(windowState, onStartGame)
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