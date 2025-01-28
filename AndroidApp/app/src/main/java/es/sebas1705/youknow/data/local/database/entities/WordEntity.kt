package es.sebas1705.youknow.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.sebas1705.youknow.core.classes.enums.games.Difficulty
import es.sebas1705.youknow.core.classes.enums.games.Languages
import es.sebas1705.youknow.core.classes.enums.games.wordpass.Letter
import es.sebas1705.youknow.data.local.database.config.SettingsDB

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