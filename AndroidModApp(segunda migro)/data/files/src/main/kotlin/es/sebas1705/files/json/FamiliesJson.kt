package es.sebas1705.files.json

import es.sebas1705.files.json.interfaces.FileJson
import kotlinx.serialization.Serializable

/**
 * Data class to represent the families data and use as entity in the database
 *
 * @property answers [List]<[String]>: List of answers
 * @property correctAnswer [String]: Correct answer
 * @property category [Int]: Category of the question
 * @property language [Int]: Language of the question
 * @property difficulty [Int]: Difficulty of the question
 *
 * @since 1.0.0
 * @author Sebas1705 09/09/2025
 */
@Serializable
data class FamiliesJson(
    val answers: List<String>,
    val correctAnswer: String,
    val category: Int,
    val language: Int,
    val difficulty: Int,
): FileJson
