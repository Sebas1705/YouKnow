package es.sebas1705.models.games

import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages



/**
 * Model class to represent the families game
 *
 * @param answers [List]<[String]>: List of answers
 * @param correctAnswer [String]: Correct answer
 * @param category [Category]: Category of the question
 * @param language [Languages]: Language of the question
 * @param difficulty [Difficulty]: Difficulty of the question
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class FamiliesModel(
    val answers: List<String>,
    val correctAnswer: String,
    val category: Category,
    val language: Languages,
    val difficulty: Difficulty,
)