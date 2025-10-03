package es.sebas1705.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.room.config.SettingsDB

/**
 * Data class to represent the user data and use as entity in the database
 *
 * @property answers [List]<[String]>: List of answers
 * @property correctAnswer [String]: Correct answer
 * @property category [Int]: Category of the question
 * @property language [Int]: Language of the question
 * @property difficulty [Int]: Difficulty of the question
 *
 * @since 1.0.0
 * @author Sebas1705 30/09/2025
 */
@Entity(tableName = SettingsDB.FAMILIES_TABLE)
data class FamiliesEntity(
    @PrimaryKey val answers: List<String>,
    val correctAnswer: String,
    val category: Category,
    val language: Languages,
    val difficulty: Difficulty,
)