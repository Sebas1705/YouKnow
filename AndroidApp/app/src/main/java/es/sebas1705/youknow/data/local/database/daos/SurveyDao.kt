package es.sebas1705.youknow.data.local.database.daos
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
import es.sebas1705.youknow.data.local.database.entities.SurveyEntity

/**
 * Data Access Object to manage the survey in the database
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Dao
interface SurveyDao {

    //Selects:
    /**
     * Count the number of surveys with a specific authorFirebaseId
     *
     * @param authorFirebaseId [String]: authorFirebaseId to count
     *
     * @return [Int]: number of surveys with the specific authorFirebaseId
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Query("SELECT COUNT(*) FROM survey_table WHERE authorFirebaseId = :authorFirebaseId")
    suspend fun contains(authorFirebaseId: String): Int

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
    @Query("SELECT * FROM survey_table WHERE authorFirebaseId = :authorFirebaseId")
    suspend fun getSurvey(authorFirebaseId: String): SurveyEntity?

    //Inserts:
    /**
     * Insert a survey
     *
     * @param surveyEntity [SurveyEntity]: survey to insert
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(surveyEntity: SurveyEntity)

    //Deletes:
    /**
     * Delete a survey with a specific authorFirebaseId
     *
     * @param authorFirebaseId [String]: authorFirebaseId of the survey to delete
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Query("DELETE FROM survey_table WHERE authorFirebaseId = :authorFirebaseId")
    suspend fun deleteSurvey(authorFirebaseId: String): Int


}