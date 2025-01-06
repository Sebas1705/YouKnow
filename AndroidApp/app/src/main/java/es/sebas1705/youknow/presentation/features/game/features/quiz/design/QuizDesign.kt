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

import androidx.compose.runtime.Composable
import es.sebas1705.youknow.core.classes.enums.Category
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.enums.QuizType
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.game.features.quiz.composables.Custom
import es.sebas1705.youknow.presentation.features.game.features.quiz.composables.Finished
import es.sebas1705.youknow.presentation.features.game.features.quiz.composables.Running
import es.sebas1705.youknow.presentation.features.game.features.quiz.composables.SelectionMode
import es.sebas1705.youknow.presentation.features.game.features.quiz.viewmodel.QuizMode
import es.sebas1705.youknow.presentation.features.game.features.quiz.viewmodel.QuizState
import es.sebas1705.youknow.presentation.features.game.features.quiz.viewmodel.QuizStatus
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
fun QuizDesign(
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
        QuizStatus.SELECTION_MODE -> SelectionMode(windowState, onSelectMode)
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
