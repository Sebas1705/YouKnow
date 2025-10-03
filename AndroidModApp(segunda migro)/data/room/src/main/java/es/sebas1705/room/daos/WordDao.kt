package es.sebas1705.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.sebas1705.room.entities.WordEntity

/**
 * Data Access Object to manage the words in the database
 *
 * @author Sebas1705 30/09/2025
 * @since 1.0.0
 */
@Dao
interface WordDao {

    //Selects:
    /**
     * Count the number of words with a specific word
     *
     * @param word [String]: word in a word
     *
     * @since 1.0.0
     * @author Sebas1705 30/09/2025
     */
    @Query("SELECT COUNT(*) FROM word_table WHERE word = :word")
    suspend fun contains(word: String): Int

    /**
     * Get a list of words
     *
     * @param numOfWord [Int]: number of words to get
     * @param letter [Int]: letter of the word
     * @param language [Int]: language of the word
     * @param difficulty [Int]: difficulty of the word
     *
     * @return [List]<[WordEntity]>: list of words with the specific word
     *
     * @since 1.0.0
     * @author Sebas1705 30/09/2025
     */
    @Query(
        """
        SELECT * FROM word_table 
        WHERE (:difficulty = 0 OR difficulty = :difficulty)
        AND (:language = 0 OR language = :language)
        AND (:letter = 0 OR letter = :letter)
        ORDER BY RANDOM()
        LIMIT :numOfWord
    """
    )
    suspend fun getWords(
        numOfWord: Int,
        letter: Int,
        language: Int,
        difficulty: Int,
    ): List<WordEntity>

    //Inserts:
    /**
     * Insert a word in the database or replace it if it already exists
     *
     * @param wordEntity [WordEntity]: word to insert or replace
     *
     * @since 1.0.0
     * @author Sebas1705 30/09/2025
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(wordEntity: WordEntity)

    //Updates:

    //Deletes:
    /**
     * Delete a word from the database
     *
     * @param word [String]: word to delete
     *
     * @return [Int]: number of words deleted
     *
     * @since 1.0.0
     * @author Sebas1705 30/09/2025
     */
    @Query("DELETE FROM word_table WHERE word = :word")
    suspend fun deleteById(word: String): Int

}