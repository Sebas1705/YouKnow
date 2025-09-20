package es.sebas1705.resources.games


import es.sebas1705.core.resources.R

/**
 * Enum class for the difficulty of the game.
 *
 * @property id [String?]: Difficulty id
 * @property strRes [Int]: Difficulty string resources
 * @property points [Int]: Difficulty points
 * @property maxMysteryNumber [Int]: Difficulty max mystery number
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
enum class Difficulty(
    val id: String?,
    val strRes: Int,
    val points: Int,
    val maxMysteryNumber: Int
) {
    ANY(null, R.string.core_resources_any, 10, 0),
    EASY("easy", R.string.core_resources_dif_easy, 5, 100),
    MEDIUM("medium", R.string.core_resources_dif_medium, 10, 1_000),
    HARD("hard", R.string.core_resources_dif_hard, 15, 1_000_000);

    companion object {
        fun getDifficulty(value: String): Difficulty = entries.find { it.id == value } ?: ANY
    }
}