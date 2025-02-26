package es.sebas1705.youknow.presentation.features.game.features.quiz.viewmodel
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

import es.sebas1705.youknow.core.classes.enums.games.Languages
import es.sebas1705.youknow.core.classes.enums.games.quiz.QuizMode
import es.sebas1705.youknow.core.classes.enums.games.quiz.QuizStatus
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.domain.model.games.QuestionModel

/**
 * Data class that represents the state of the Quiz Screen.
 *
 * @param isLoading [Boolean]: Flag that indicates if the screen is loading.
 * @param actualQuestion [Int]: Number of the current question.
 * @param points [Int]: Points obtained in the game.
 * @param correctAnswers [Int]: Number of correct answers.
 * @param lives [Int]: Number of lives.
 * @param questions [List]: List of questions.
 * @param status [QuizStatus]: Status of the game.
 * @param mode [QuizMode]: Mode of the game.
 * @param languages [Languages]: Language of the questions.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class QuizState(
    var isLoading: Boolean,
    var actualQuestion: Int,
    var points: Int,
    var correctAnswers: Int,
    var lives: Int,
    var questions: List<QuestionModel>,
    var status: QuizStatus,
    var mode: QuizMode?,
    var languages: Languages
) : MVIBaseState {

    companion object {
        /**
         * Default state of the Quiz Screen.
         *
         * @return [QuizState]: Default state of the Quiz Screen.
         *
         * @since 1.0.0
         * @author Sebastián Ramiro Entrerrios García
         */
        fun default() = QuizState(
            isLoading = false,
            actualQuestion = 0,
            points = 0,
            correctAnswers = 0,
            lives = 3,
            questions = emptyList(),
            status = QuizStatus.SELECTION_MODE,
            mode = null,
            languages = Languages.ANY
        )
    }
}