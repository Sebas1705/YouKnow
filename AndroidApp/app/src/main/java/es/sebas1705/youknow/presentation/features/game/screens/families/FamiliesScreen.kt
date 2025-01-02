package es.sebas1705.youknow.presentation.features.game.screens.families
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
import es.sebas1705.youknow.core.classes.enums.Category
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.game.screens.families.composables.Custom
import es.sebas1705.youknow.presentation.features.game.screens.families.composables.Finished
import es.sebas1705.youknow.presentation.features.game.screens.families.composables.Running
import es.sebas1705.youknow.presentation.features.game.screens.families.composables.SelectionMode
import es.sebas1705.youknow.presentation.features.game.viewmodels.FamiliesIntent
import es.sebas1705.youknow.presentation.features.game.viewmodels.FamiliesMode
import es.sebas1705.youknow.presentation.features.game.viewmodels.FamiliesState
import es.sebas1705.youknow.presentation.features.game.viewmodels.FamiliesStatus
import es.sebas1705.youknow.presentation.features.game.viewmodels.FamiliesViewModel
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
fun FamiliesScreen(
    windowState: WindowState,
    onOutGameNavigation: () -> Unit
) {
    val familiesViewModel: FamiliesViewModel = hiltViewModel()
    val familiesState by familiesViewModel.uiState.collectAsStateWithLifecycle()

    FamiliesDesign(
        windowState,
        familiesState,
        onSelectMode = { mode ->
            familiesViewModel.eventHandler(FamiliesIntent.SelectMode(mode))
            if (mode != FamiliesMode.CUSTOM) {
                familiesViewModel.eventHandler(
                    FamiliesIntent.GenerateGame(
                        Category.ANY,
                        Difficulty.ANY,
                        mode.numFamilies
                    )
                )
            }
        },
        onResponseQuestion = { response ->
            familiesViewModel.eventHandler(
                FamiliesIntent.Response(
                    response,
                    familiesState
                )
            )
        },
        onRestartGame = {
            familiesViewModel.eventHandler(FamiliesIntent.ResetGame(familiesState.points))
        },
        onOutGame = {
            familiesViewModel.eventHandler(FamiliesIntent.OutGame(familiesState.points) {
                onOutGameNavigation()
            })
        },
        onStartGame = { difficulty, category, numFamilies ->
            familiesViewModel.eventHandler(
                FamiliesIntent.GenerateGame(
                    category,
                    difficulty,
                    numFamilies
                )
            )
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
private fun FamiliesDesign(
    windowState: WindowState = WindowState.default(),
    familiesState: FamiliesState = FamiliesState.default(),
    onSelectMode: (FamiliesMode) -> Unit = { },
    onResponseQuestion: (String) -> Unit = { },
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { },
    onStartGame: (Difficulty, Category, Int) -> Unit = { _, _, _ -> }
) {
    if (familiesState.isLoading) LoadingDialog(windowState)

    when (familiesState.status) {
        FamiliesStatus.SELECTION_MODE -> SelectionMode(windowState, familiesState, onSelectMode)
        FamiliesStatus.CUSTOM -> Custom(windowState, familiesState, onStartGame)
        FamiliesStatus.RUNNING -> Running(windowState, familiesState, onResponseQuestion)
        FamiliesStatus.FINISHED -> Finished(windowState, familiesState, onRestartGame, onOutGame)
    }
}

/**
 * Preview of the Mystery Number Game.
 *
 * @see UiModePreviews
 * @see FamiliesDesign
 *
 * @since 1.0.0
 */
@UiModePreviews
@Composable
private fun FamiliesPreview() {
    YouKnowTheme {
        FamiliesDesign()
    }
}
