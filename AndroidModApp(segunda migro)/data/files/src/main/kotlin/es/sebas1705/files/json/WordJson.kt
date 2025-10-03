package es.sebas1705.files.json

import es.sebas1705.files.json.interfaces.FileJson
import kotlinx.serialization.Serializable

/**
 * Data class to represent the word data and use as entity in the database
 *
 * @property word [String]: Word
 * @property definitions [List]<[String]>: List of definitions
 * @property letter [Int]: Letter of the word
 * @property language [Int]: Language of the word
 * @property difficulty [Int]: Difficulty of the word
 *
 * @since 1.0.0
 * @author Sebas1705 09/09/2025
 */
@Serializable
data class WordJson(
    val word: String,
    val definitions: List<String>,
    val letter: Int,
    val language: Int,
    val difficulty: Int
): FileJson