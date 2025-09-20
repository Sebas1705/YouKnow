package es.sebas1705.game.quiz
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
import es.sebas1705.common.states.WindowState
import es.sebas1705.game.quiz.design.QuizDesign
import es.sebas1705.game.quiz.viewmodel.QuizIntent
import es.sebas1705.game.quiz.viewmodel.QuizViewModel

/**
 * Screen of the Mystery Number Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onOutGameNavigation [Function]: Function to navigate to the previous screen.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun QuizScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    onOutGameNavigation: () -> Unit
) {
    //ViewModel:
    val quizViewModel: QuizViewModel = hiltViewModel()

    //State:
    val quizState by quizViewModel.uiState.collectAsStateWithLifecycle()

    //Effects:
    LaunchedEffect(Unit) {
        quizViewModel.eventHandler(QuizIntent.ReadLanguages)
    }

    //Body:
    QuizDesign(
        windowState,
        quizState,
        soundPool,
        onSelectMode = { mode ->
            quizViewModel.eventHandler(QuizIntent.SelectMode(mode))
        },
        onResponseQuestion = { response ->
            quizViewModel.eventHandler(QuizIntent.Response(response))
        },
        onRestartGame = {
            quizViewModel.eventHandler(QuizIntent.ResetGame)
        },
        onOutGame = {
            quizViewModel.eventHandler(QuizIntent.OutGame {
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

