package es.sebas1705.quizusescases.usescases
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

import es.sebas1705.mappers.toQuestionEntity
import es.sebas1705.models.games.QuestionModel
import es.sebas1705.room.repository.DatabaseRepository

/**
 * Use case to insert a list of questions
 *
 * @param databaseRepository [DatabaseRepository]: Repository to insert questions
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
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
            databaseRepository.insertOrReplace(it.toQuestionEntity())
        }
        onSuccess()
    }
}