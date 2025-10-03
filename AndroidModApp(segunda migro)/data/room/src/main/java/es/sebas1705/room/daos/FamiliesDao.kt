package es.sebas1705.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.sebas1705.room.entities.FamiliesEntity
import es.sebas1705.room.entities.QuestionEntity

/**
 * Data Access Object to manage the families in the database
 *
 * @author Sebas1705 30/09/2025
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
     * @author Sebas1705 30/09/2025
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
     * @author Sebas1705 30/09/2025
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
     * @author Sebas1705 30/09/2025
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
     * @author Sebas1705 30/09/2025
     */
    @Query("DELETE FROM families_table WHERE answers = :answers")
    suspend fun deleteById(answers: List<String>): Int

}