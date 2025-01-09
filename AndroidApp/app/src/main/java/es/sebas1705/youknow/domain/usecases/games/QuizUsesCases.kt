package es.sebas1705.youknow.domain.usecases.games
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

import es.sebas1705.youknow.core.classes.enums.Category
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.enums.Languages
import es.sebas1705.youknow.core.classes.enums.QuizType
import es.sebas1705.youknow.data.local.database.repository.DatabaseRepository
import es.sebas1705.youknow.domain.model.games.QuestionModel

class GenerateQuestionList(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        numberQuestions: Int,
        category: Category,
        difficulty: Difficulty,
        quizType: QuizType,
        onLoading: () -> Unit = {},
        onSuccess: (List<QuestionModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val questions = databaseRepository.getQuestions(
            numberQuestions,
            category,
            Languages.ANY,
            difficulty,
            quizType
        )
        if (questions.isEmpty())
            onError("No questions found")
        else if (questions.size < numberQuestions)
            onError("Not enough questions found")
        else
            onSuccess(questions)
    }
}

class InsertQuestionList(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        questions: List<QuestionModel>,
        onLoading: () -> Unit = {},
        onSuccess: () -> Unit
    ) {
        onLoading()
        questions.forEach {
            databaseRepository.insertOrReplace(it)
        }
        onSuccess()
    }
}


data class QuizUsesCases(
    val generateQuestionList: GenerateQuestionList,
    val insertQuestionList: InsertQuestionList
)