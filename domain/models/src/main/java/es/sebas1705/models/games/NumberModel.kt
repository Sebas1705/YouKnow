package es.sebas1705.models.games

import es.sebas1705.resources.games.Difficulty




/**
 * Model class to represent the number game
 *
 * @param number [Int]: Number to guess
 * @param difficulty [Difficulty]: Difficulty of the question
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class NumberModel(
    val number: Int,
    val difficulty: Difficulty
)