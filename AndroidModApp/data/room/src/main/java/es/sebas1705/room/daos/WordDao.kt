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
import es.sebas1705.room.entities.WordEntity

/**
 * Data Access Object to manage the words in the database
 *
 * @author Sebastián Ramiro Entrerrios García
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
     * @author Sebastián Ramiro Entrerrios García
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
     * @author Sebastián Ramiro Entrerrios García
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
     * @author Sebastián Ramiro Entrerrios García
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
     * @author Sebastián Ramiro Entrerrios García
     */
    @Query("DELETE FROM word_table WHERE word = :word")
    suspend fun deleteById(word: String): Int

}