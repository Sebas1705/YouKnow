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
import es.sebas1705.room.entities.FamiliesEntity
import es.sebas1705.room.entities.QuestionEntity

/**
 * Data Access Object to manage the families in the database
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Dao
interface FamiliesDao {

    //Selects:
    /**
     * Count the number of families with a specific answers
     *
     * @param answers [List]<[String]>: answers in a families
     *
     * @return [Int]: number of questions with the specific question
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Query("SELECT COUNT(*) FROM families_table WHERE answers = :answers")
    suspend fun contains(answers: List<String>): Int

    /**
     * Get a list of families with a specific answers
     *
     * @param numOfFamilies [Int]: number of families to get
     * @param category [Int]: category of the families
     * @param language [Int]: language of the families
     * @param difficulty [Int]: difficulty of the families
     *
     * @return [List]<[QuestionEntity]>: list of families with the specific answers
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Query(
        """
        SELECT * FROM families_table 
        WHERE (:category = 0 OR category = :category) 
        AND (:difficulty = 0 OR difficulty = :difficulty)
        AND (:language = 0 OR language = :language)
        ORDER BY RANDOM()
        LIMIT :numOfFamilies
    """
    )
    suspend fun getFamilies(
        numOfFamilies: Int,
        category: Int,
        language: Int,
        difficulty: Int,
    ): List<FamiliesEntity>

    //Inserts:
    /**
     * Insert a families in the database or replace it if it already exists
     *
     * @param familiesEntity [FamiliesEntity]: families to insert or replace
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(familiesEntity: FamiliesEntity)

    //Updates:

    //Deletes:
    /**
     * Delete a families with a specific answers
     *
     * @param answers [List]<[String]>: answers in a families
     *
     * @return [Int]: number of families deleted
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Query("DELETE FROM families_table WHERE answers = :answers")
    suspend fun deleteById(answers: List<String>): Int

}