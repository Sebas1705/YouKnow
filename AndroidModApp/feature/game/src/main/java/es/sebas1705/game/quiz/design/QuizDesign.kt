package es.sebas1705.youknow.presentation.features.game.features.quiz.design
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
import es.sebas1705.youknow.core.classes.enums.games.quiz.QuizMode
import es.sebas1705.youknow.core.classes.enums.games.quiz.QuizStatus
import es.sebas1705.youknow.core.classes.enums.games.quiz.QuizType
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.game.features.quiz.composables.Custom
import es.sebas1705.youknow.presentation.features.game.features.quiz.composables.Finished
import es.sebas1705.youknow.presentation.features.game.features.quiz.composables.Running
import es.sebas1705.youknow.presentation.features.game.features.quiz.composables.SelectionMode
import es.sebas1705.youknow.presentation.features.game.features.quiz.viewmodel.QuizState
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Design of the Mystery Number Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param quizState [QuizState]: State of the game.
 * @param onSelectMode ([QuizMode]) -> Unit: Function to select the game mode.
 * @param onResponseQuestion ([String]) -> Unit: Function to respond to the question.
 * @param onRestartGame () -> Unit: Function to restart the game.
 * @param onOutGame () -> Unit: Function to exit the game.
 * @param onStartGame ([Difficulty], [Category], [QuizType], [Int]) -> Unit: Function to start the game.
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun QuizDesign(
    windowState: WindowState = WindowState.default(),
    quizState: QuizState = QuizState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onSelectMode: (QuizMode) -> Unit = { },
    onResponseQuestion: (String) -> Unit = { },
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { },
    onStartGame: (Difficulty, Category, QuizType, Int) -> Unit = { _, _, _, _ -> }
) {
    //Body:
    if (quizState.isLoading) LoadingDialog(windowState)

    when (quizState.status) {
        QuizStatus.SELECTION_MODE -> SelectionMode(windowState, soundPool, onSelectMode)
        QuizStatus.CUSTOM -> Custom(windowState, quizState, soundPool, onStartGame)
        QuizStatus.RUNNING -> Running(windowState, quizState, soundPool, onResponseQuestion)
        QuizStatus.FINISHED -> Finished(windowState, quizState, soundPool, onRestartGame, onOutGame)
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
