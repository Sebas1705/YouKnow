package es.sebas1705.youknow.data.local.database.repository
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

import es.sebas1705.youknow.core.classes.enums.games.Category
import es.sebas1705.youknow.core.classes.enums.games.Difficulty
import es.sebas1705.youknow.core.classes.enums.games.Languages
import es.sebas1705.youknow.core.classes.enums.games.wordpass.Letter
import es.sebas1705.youknow.core.classes.enums.games.quiz.QuizType
import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData
import es.sebas1705.youknow.data.firebase.analytics.config.Layer
import es.sebas1705.youknow.data.firebase.analytics.config.Repository
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepository
import es.sebas1705.youknow.data.local.database.Database
import es.sebas1705.youknow.data.mappers.toFamiliesEntity
import es.sebas1705.youknow.data.mappers.toFamiliesModel
import es.sebas1705.youknow.data.mappers.toQuestionEntity
import es.sebas1705.youknow.data.mappers.toQuestionModel
import es.sebas1705.youknow.data.mappers.toWordEntity
import es.sebas1705.youknow.data.mappers.toWordModel
import es.sebas1705.youknow.domain.model.games.FamiliesModel
import es.sebas1705.youknow.domain.model.games.QuestionModel
import es.sebas1705.youknow.domain.model.games.WordModel
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

    override suspend fun getQuestions(
        numOfQuestions: Int,
        category: Category,
        language: Languages,
        difficulty: Difficulty,
        quizType: QuizType
    ): List<QuestionModel> = database.questionDao().getQuestions(
        numOfQuestions,
        category.ordinal,
        language.ordinal,
        difficulty.ordinal,
        quizType.ordinal
    ).map { it.toQuestionModel() }

    override suspend fun getFamilies(
        numOfFamilies: Int,
        category: Category,
        language: Languages,
        difficulty: Difficulty
    ): List<FamiliesModel> = database.familiesDao().getFamilies(
        numOfFamilies,
        category.ordinal,
        language.ordinal,
        difficulty.ordinal
    ).map { it.toFamiliesModel() }

    override suspend fun getWords(
        numOfWord: Int,
        letter: Letter,
        language: Languages,
        difficulty: Difficulty
    ): List<WordModel> = database.wordDao().getWords(
        numOfWord,
        letter.ordinal,
        language.ordinal,
        difficulty.ordinal
    ).map { it.toWordModel() }

    //Inserts
    override suspend fun insertOrReplace(questionModel: QuestionModel) {
        database.questionDao().insertOrReplace(questionModel.toQuestionEntity())
    }

    override suspend fun insertOrReplace(familiesModel: FamiliesModel) {
        database.familiesDao().insertOrReplace(familiesModel.toFamiliesEntity())
    }

    override suspend fun insertOrReplace(wordModel: WordModel) {
        database.wordDao().insertOrReplace(wordModel.toWordEntity())
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
}