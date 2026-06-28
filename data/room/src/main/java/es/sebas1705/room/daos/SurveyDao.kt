package es.sebas1705.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.sebas1705.room.entities.SurveyEntity

/**
 * Data Access Object to manage the survey in the database
 *
 * @since 1.0.0
 * @author Sebas1705 30/09/2025
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
     * @author Sebas1705 30/09/2025
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
     * @author Sebas1705 30/09/2025
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
     * @author Sebas1705 30/09/2025
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
     * @author Sebas1705 30/09/2025
     */
    @Query("DELETE FROM survey_table WHERE authorFirebaseId = :authorFirebaseId")
    suspend fun deleteSurvey(authorFirebaseId: String): Int


}