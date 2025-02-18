package es.sebas1705.room.repository
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

import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.QuizType
import es.sebas1705.common.managers.ClassLogData
import es.sebas1705.common.managers.Layer
import es.sebas1705.common.managers.Repository
import es.sebas1705.room.entities.FamiliesEntity
import es.sebas1705.room.entities.QuestionEntity
import es.sebas1705.room.entities.SurveyEntity
import es.sebas1705.room.entities.WordEntity
import es.sebas1705.youknow.core.classes.enums.games.wordpass.Letter
import es.sebas1705.analytics.repository.AnalyticsRepository
import es.sebas1705.youknow.data.local.database.Database
import javax.inject.Inject

/**
 * Class to represent the repository of the database
 *
 * @property database [Database]: Database
 * @property analyticsRepository [AnalyticsRepository]: Analytics repository
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class DatabaseRepositoryImpl @Inject constructor(
    private val database: Database,
    private val analyticsRepository: AnalyticsRepository
) : DatabaseRepository, ClassLogData {

    override val layer: Layer = Layer.Data
    override val repository: Repository = Repository.Database

    //Selects
    override suspend fun containsQuestion(
        question: String
    ): Boolean = database.questionDao().contains(question) > 0

    override suspend fun containsFamilies(
        answers: List<String>
    ): Boolean = database.familiesDao().contains(answers) > 0

    override suspend fun containsWord(
        word: String
    ): Boolean = database.wordDao().contains(word) > 0

    override suspend fun containsSurvey(
        authorFirebaseId: String
    ): Boolean = database.surveyDao().contains(authorFirebaseId) > 0

    override suspend fun getQuestions(
        numOfQuestions: Int,
        category: Category,
        language: Languages,
        difficulty: Difficulty,
        quizType: QuizType
    ): List<QuestionEntity> = database.questionDao().getQuestions(
        numOfQuestions,
        category.ordinal,
        language.ordinal,
        difficulty.ordinal,
        quizType.ordinal
    )

    override suspend fun getFamilies(
        numOfFamilies: Int,
        category: Category,
        language: Languages,
        difficulty: Difficulty
    ): List<FamiliesEntity> = database.familiesDao().getFamilies(
        numOfFamilies,
        category.ordinal,
        language.ordinal,
        difficulty.ordinal
    )

    override suspend fun getWords(
        numOfWord: Int,
        letter: Letter,
        language: Languages,
        difficulty: Difficulty
    ): List<WordEntity> = database.wordDao().getWords(
        numOfWord,
        letter.ordinal,
        language.ordinal,
        difficulty.ordinal
    )

    override suspend fun getSurvey(
        authorFirebaseId: String
    ): SurveyEntity? = database.surveyDao().getSurvey(
        authorFirebaseId
    )

    //Inserts
    override suspend fun insertOrReplace(questionEntity: QuestionEntity) {
        database.questionDao().insertOrReplace(questionEntity)
    }

    override suspend fun insertOrReplace(familiesEntity: FamiliesEntity) {
        database.familiesDao().insertOrReplace(familiesEntity)
    }

    override suspend fun insertOrReplace(wordEntity: WordEntity) {
        database.wordDao().insertOrReplace(wordEntity)
    }

    override suspend fun insertOrReplace(surveyEntity: SurveyEntity) {
        database.surveyDao().insertOrReplace(surveyEntity)
    }

    //Updates


    //Deletes
    override suspend fun deleteQuestion(question: String): Boolean {
        return database.questionDao().deleteById(question) > 0
    }

    override suspend fun deleteFamilies(answers: List<String>): Boolean {
        return database.familiesDao().deleteById(answers) > 0
    }

    override suspend fun deleteWord(word: String): Boolean {
        return database.wordDao().deleteById(word) > 0
    }

    override suspend fun deleteSurvey(authorFirebaseId: String): Boolean {
        return database.surveyDao().deleteSurvey(authorFirebaseId) > 0
    }
}