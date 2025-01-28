package es.sebas1705.youknow.presentation.features.game.features.families.design
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
import es.sebas1705.youknow.core.classes.enums.games.Category
import es.sebas1705.youknow.core.classes.enums.games.Difficulty
import es.sebas1705.youknow.core.classes.enums.games.families.FamiliesMode
import es.sebas1705.youknow.core.classes.enums.games.families.FamiliesStatus
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.game.features.families.composables.Custom
import es.sebas1705.youknow.presentation.features.game.features.families.composables.Finished
import es.sebas1705.youknow.presentation.features.game.features.families.composables.Running
import es.sebas1705.youknow.presentation.features.game.features.families.composables.SelectionMode
import es.sebas1705.youknow.presentation.features.game.features.families.viewmodel.FamiliesState
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Design of the Mystery Number Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param familiesState [FamiliesState]: State of the game.
 * @param onSelectMode ([FamiliesMode]) -> Unit: Function to select the mode of the game.
 * @param onResponseQuestion (String) -> Unit: Function to respond to the question.
 * @param onRestartGame () -> Unit: Function to restart the game.
 * @param onOutGame () -> Unit: Function to exit the game.
 * @param onStartGame (Difficulty, Category, Int) -> Unit: Function to start the game.
 *
 * @since 1.0.0
 * @Author Sebastián Ramiro Entrerrios
 */
@Composable
fun FamiliesDesign(
    windowState: WindowState = WindowState.default(),
    familiesState: FamiliesState = FamiliesState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onSelectMode: (FamiliesMode) -> Unit = { },
    onResponseQuestion: (String) -> Unit = { },
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { },
    onStartGame: (Difficulty, Category, Int) -> Unit = { _, _, _ -> }
) {
    //Body:
    if (familiesState.isLoading) LoadingDialog(windowState)

    when (familiesState.status) {
        FamiliesStatus.SELECTION_MODE -> SelectionMode(windowState, soundPool, onSelectMode)
        FamiliesStatus.CUSTOM -> Custom(windowState, soundPool, onStartGame)
        FamiliesStatus.RUNNING -> Running(windowState, familiesState, soundPool, onResponseQuestion)
        FamiliesStatus.FINISHED -> Finished(windowState, familiesState, soundPool, onRestartGame, onOutGame)
    }
}

@UiModePreviews
@Composable
private fun FamiliesPreview() {
    YouKnowTheme {
        FamiliesDesign()
    }
}
