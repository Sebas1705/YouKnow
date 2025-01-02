package es.sebas1705.youknow.presentation.features.game.screens.quiz
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
import es.sebas1705.youknow.core.classes.enums.QuizType
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.game.screens.quiz.composables.Custom
import es.sebas1705.youknow.presentation.features.game.screens.quiz.composables.Finished
import es.sebas1705.youknow.presentation.features.game.screens.quiz.composables.Running
import es.sebas1705.youknow.presentation.features.game.screens.quiz.composables.SelectionMode
import es.sebas1705.youknow.presentation.features.game.viewmodels.QuizIntent
import es.sebas1705.youknow.presentation.features.game.viewmodels.QuizMode
import es.sebas1705.youknow.presentation.features.game.viewmodels.QuizState
import es.sebas1705.youknow.presentation.features.game.viewmodels.QuizStatus
import es.sebas1705.youknow.presentation.features.game.viewmodels.QuizViewModel
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
fun QuizScreen(
    windowState: WindowState,
    onOutGameNavigation: () -> Unit
) {
    val quizViewModel: QuizViewModel = hiltViewModel()
    val quizState by quizViewModel.uiState.collectAsStateWithLifecycle()

    QuizDesign(
        windowState,
        quizState,
        onSelectMode = { mode ->
            quizViewModel.eventHandler(QuizIntent.SelectMode(mode))
            if (mode != QuizMode.CUSTOM) {
                quizViewModel.eventHandler(
                    QuizIntent.GenerateGame(
                        Category.ANY,
                        Difficulty.ANY,
                        QuizType.ANY,
                        mode.numQuestions
                    )
                )
            }
        },
        onResponseQuestion = { response ->
            quizViewModel.eventHandler(
                QuizIntent.ResponseQuestion(
                    response,
                    quizState
                )
            )
        },
        onRestartGame = {
            quizViewModel.eventHandler(QuizIntent.ResetGame(quizState.points))
        },
        onOutGame = {
            quizViewModel.eventHandler(QuizIntent.OutGame(quizState.points) {
                onOutGameNavigation()
            })
        },
        onStartGame = { difficulty, category, type, numQuestions ->
            quizViewModel.eventHandler(
                QuizIntent.GenerateGame(
                    category,
                    difficulty,
                    type,
                    numQuestions
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
private fun QuizDesign(
    windowState: WindowState = WindowState.default(),
    quizState: QuizState = QuizState.default(),
    onSelectMode: (QuizMode) -> Unit = { },
    onResponseQuestion: (String) -> Unit = { },
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { },
    onStartGame: (Difficulty, Category, QuizType, Int) -> Unit = { _, _, _, _ -> }
) {
    if (quizState.isLoading) LoadingDialog(windowState)

    when (quizState.status) {
        QuizStatus.SELECTION_MODE -> SelectionMode(windowState, quizState, onSelectMode)
        QuizStatus.CUSTOM -> Custom(windowState, quizState, onStartGame)
        QuizStatus.RUNNING -> Running(windowState, quizState, onResponseQuestion)
        QuizStatus.FINISHED -> Finished(windowState, quizState, onRestartGame, onOutGame)
    }
}

/**
 * Preview of the Mystery Number Game.
 *
 * @see UiModePreviews
 * @see QuizDesign
 *
 * @since 1.0.0
 */
@UiModePreviews
@Composable
private fun QuizPreview() {
    YouKnowTheme {
        QuizDesign()
    }
}
