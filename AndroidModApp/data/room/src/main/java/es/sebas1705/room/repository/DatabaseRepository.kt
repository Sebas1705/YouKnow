package es.sebas1705.room.repository

import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.QuizType
import es.sebas1705.room.entities.FamiliesEntity
import es.sebas1705.room.entities.QuestionEntity
import es.sebas1705.room.entities.SurveyEntity
import es.sebas1705.room.entities.WordEntity
import es.sebas1705.common.games.wordpass.Letter

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
 * Interface to represent the repository of the database
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
interface DatabaseRepository {

    //Selects
    /**
     * Check if the question is in the database
     *
     * @param question [String]: Question to check
     *
     * @return [Boolean]: True if the question is in the database, false otherwise
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun containsQuestion(question: String): Boolean

    /**
     * Check if the answers are in the database
     *
     * @param answers [List]<[String]>: Answers to check
     *
     * @return [Boolean]: True if the answers are in the database, false otherwise
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun containsFamilies(answers: List<String>): Boolean

    /**
     * Check if the word is in the database
     *
     * @param word [String]: Word to check
     *
     * @return [Boolean]: True if the word is in the database, false otherwise
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun containsWord(word: String): Boolean

    /**
     * Check if the survey is in the database
     *
     * @param authorFirebaseId [String]: authorFirebaseId to check
     *
     * @return [Boolean]: True if the survey is in the database, false otherwise
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun containsSurvey(authorFirebaseId: String): Boolean

    /**
     * Get a list of questions
     *
     * @param numOfQuestions [Int]: Number of questions to get
     * @param category [Category]: Category of the question
     * @param language [Languages]: Language of the question
     * @param difficulty [Difficulty]: Difficulty of the question
     * @param quizType [QuizType]: Type of the question
     * @return [List]<[QuestionEntity]>: List of questions with the specific question
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun getQuestions(
        numOfQuestions: Int,
        category: Category,
        language: Languages,
        difficulty: Difficulty,
        quizType: QuizType
    ): List<QuestionEntity>

    /**
     * Get a list of families
     *
     * @param numOfFamilies [Int]: Number of families to get
     * @param category [Category]: Category of the families
     * @param language [Languages]: Language of the families
     * @param difficulty [Difficulty]: Difficulty of the families
     *
     * @return [List]<[FamiliesEntity]>: List of families with the specific answers
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun getFamilies(
        numOfFamilies: Int,
        category: Category,
        language: Languages,
        difficulty: Difficulty
    ): List<FamiliesEntity>

    /**
     * Get a list of words
     *
     * @param numOfWord [Int]: Number of words to get
     * @param letter [Letter]: Letter of the word
     * @param language [Languages]: Language of the word
     * @param difficulty [Difficulty]: Difficulty of the word
     *
     * @return [List]<[WordEntity]>: List of words with the specific word
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun getWords(
        numOfWord: Int,
        letter: Letter,
        language: Languages,
        difficulty: Difficulty,
    ): List<WordEntity>

    /**
     * Get a survey with a specific authorFirebaseId
     *
     * @param authorFirebaseId [String]: authorFirebaseId of the survey
     *
     * @return [SurveyEntity]: survey
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun getSurvey(authorFirebaseId: String): SurveyEntity?

    //Inserts
    /**
     * Insert or replace a question in the database
     *
     * @param questionEntity [QuestionEntity]: Question to insert or replace
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun insertOrReplace(questionEntity: QuestionEntity)

    /**
     * Insert or replace a list of answers in the database
     *
     * @param familiesEntity [FamiliesEntity]: Answers to insert or replace
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun insertOrReplace(familiesEntity: FamiliesEntity)

    /**
     * Insert a word in the database or replace it if it already exists
     *
     * @param wordEntity [WordEntity]: Word to insert or replace
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun insertOrReplace(wordEntity: WordEntity)

    /**
     * Insert a survey in the database
     *
     * @param surveyEntity [SurveyEntity]: Survey to insert
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun insertOrReplace(surveyEntity: SurveyEntity)

    //Updates

    //Deletes
    /**
     * Delete a question from the database
     *
     * @param question [String]: Question to delete
     *
     * @return [Boolean]: True if the question was deleted, false otherwise
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun deleteQuestion(question: String): Boolean

    /**
     * Delete a families from the database
     *
     * @param answers [List]<[String]>: Answers to delete
     *
     * @return [Boolean]: True if the answers were deleted, false otherwise
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun deleteFamilies(answers: List<String>): Boolean

    /**
     * Delete a word from the database
     *
     * @param word [String]: Word to delete
     *
     * @return [Boolean]: True if the word was deleted, false otherwise
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun deleteWord(word: String): Boolean

    /**
     * Delete a survey from the database
     *
     * @param authorFirebaseId [String]: authorFirebaseId of the survey to delete
     *
     * @return [Boolean]: True if the survey was deleted, false otherwise
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun deleteSurvey(authorFirebaseId: String): Boolean

}