package es.sebas1705.youknow.presentation.features.game.features.quiz
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
import es.sebas1705.youknow.presentation.features.game.features.quiz.design.QuizDesign
import es.sebas1705.youknow.presentation.features.game.features.quiz.viewmodel.QuizIntent
import es.sebas1705.youknow.presentation.features.game.features.quiz.viewmodel.QuizMode
import es.sebas1705.youknow.presentation.features.game.features.quiz.viewmodel.QuizViewModel

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
                QuizIntent.Response(
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

