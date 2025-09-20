package es.sebas1705.quizusescases.usescases

import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.QuizType
import es.sebas1705.mappers.toQuestionModel
import es.sebas1705.models.games.QuestionModel
import es.sebas1705.room.repository.DatabaseRepository

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
 * Use case to generate a list of questions
 *
 * @param databaseRepository [DatabaseRepository]: Repository to get questions
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class GenerateQuestionList(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        numberQuestions: Int,
        category: Category,
        difficulty: Difficulty,
        languages: Languages,
        quizType: QuizType,
        onLoading: () -> Unit = {},
        onSuccess: (List<QuestionModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val questions = databaseRepository.getQuestions(
            numberQuestions,
            category,
            languages,
            difficulty,
            quizType
        )
        if (questions.isEmpty())
            onError("No questions found")
        else if (questions.size < numberQuestions)
            onError("Not enough questions found")
        else
            onSuccess(questions.map { it.toQuestionModel() })
    }
}