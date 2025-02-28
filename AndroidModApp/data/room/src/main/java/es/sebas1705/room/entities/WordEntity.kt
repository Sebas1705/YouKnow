package es.sebas1705.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.room.config.SettingsDB
import es.sebas1705.common.games.wordpass.Letter

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
 * @author Sebastián Ramiro Entrerrios García
 */
@Entity(tableName = SettingsDB.WORD_TABLE)
data class WordEntity(
    @PrimaryKey val word: String,
    val definitions: List<String>,
    val letter: Letter,
    val language: Languages,
    val difficulty: Difficulty
)