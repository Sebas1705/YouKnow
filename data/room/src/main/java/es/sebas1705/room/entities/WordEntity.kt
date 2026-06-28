package es.sebas1705.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.wordpass.Letter
import es.sebas1705.room.config.SettingsDB

/**
 * Entity to represent the word in the database
 *
 * @property word [String]: word
 * @property definitions [List]<[String]>: definitions of the word
 * @property letter [Int]: letter of the word
 * @property language [Int]: language of the word
 * @property difficulty [Int]: difficulty of the word
 *
 * @since 1.0.0
 * @author Sebas1705 30/09/2025
 */
@Entity(tableName = SettingsDB.WORD_TABLE)
data class WordEntity(
    @PrimaryKey val word: String,
    val definitions: List<String>,
    val letter: Letter,
    val language: Languages,
    val difficulty: Difficulty
)