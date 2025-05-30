package es.sebas1705.models.games

import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.QuizType

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


/**
 * Model to represent a question
 *
 * @property question [String]: Question
 * @property answers [List]<[String]>: List of answers
 * @property correctAnswer [String]: Correct answer
 * @property category [Category]: Category of the question
 * @property language [Languages]: Language of the question
 * @property difficulty [Difficulty]: Difficulty of the question
 * @property quizType [QuizType]: Type of the question
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class QuestionModel(
    val question: String,
    val answers: List<String>,
    val correctAnswer: String,
    val category: Category,
    val language: Languages,
    val difficulty: Difficulty,
    val quizType: QuizType
) {
    companion object {
        fun defaultMultipleList(index: Int): List<QuestionModel> = (1..index).map {
            QuestionModel(
                "Question $it",
                listOf("A", "B", "C", "D"),
                "A",
                Category.ANY,
                Languages.EN,
                listOf(Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD).random(),
                QuizType.MULTIPLE
            )
        }

        fun defaultBooleanList(index: Int): List<QuestionModel> = (1..index).map {
            QuestionModel(
                "Question $it",
                listOf("True", "False"),
                if (it % 2 == 0) "True" else "False",
                Category.ANY,
                Languages.EN,
                listOf(Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD).random(),
                QuizType.BOOLEAN
            )
        }
    }
}