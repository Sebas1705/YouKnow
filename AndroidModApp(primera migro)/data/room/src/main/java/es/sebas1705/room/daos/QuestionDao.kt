package es.sebas1705.room.daos
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

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.sebas1705.room.entities.QuestionEntity

/**
 * Data Access Object to manage the questions in the database
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Dao
interface QuestionDao {

    //Selects:
    /**
     * Count the number of questions with a specific question
     *
     * @param question [String]: question to count
     *
     * @return [Int]: number of questions with the specific question
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Query("SELECT COUNT(*) FROM questions_table WHERE question = :question")
    suspend fun contains(question: String): Int

    /**
     * Get a list of questions with a specific category, language, difficulty and quiz type
     *
     * @param numOfQuestions [Int]: number of questions to get
     * @param category [Int]: category of the questions
     * @param language [Int]: language of the questions
     * @param difficulty [Int]: difficulty of the questions
     * @param quizType [Int]: quiz type of the questions
     *
     * @return [List]<[QuestionEntity]>: list of questions
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Query(
        """
        SELECT * FROM questions_table 
        WHERE (:category = 0 OR category = :category) 
        AND (:difficulty = 0 OR difficulty = :difficulty)
        AND (:language = 0 OR language = :language)
        AND (:quizType = 0 OR quizType = :quizType)
        ORDER BY RANDOM()
        LIMIT :numOfQuestions
    """
    )
    suspend fun getQuestions(
        numOfQuestions: Int,
        category: Int,
        language: Int,
        difficulty: Int,
        quizType: Int
    ): List<QuestionEntity>

    //Inserts:
    /**
     * Insert a question in the database or replace it if it already exists
     *
     * @param questionEntity [QuestionEntity]: question to insert or replace
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(questionEntity: QuestionEntity)

    //Updates:

    //Deletes:
    /**
     * Delete a question using the question sentence
     *
     * @param question [String]: question to delete
     *
     * @return [Int]: number of questions deleted
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Query("DELETE FROM questions_table WHERE question = :question")
    suspend fun deleteById(question: String): Int

}