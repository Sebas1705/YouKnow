package es.sebas1705.home.play


import es.sebas1705.feature.home.R

/**
 * Data class that represents a game item.
 *
 * @property strRes [Int]: The string resource of the game.
 * @property icon [Int]: The icon of the game.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class GameItem(
    val strRes: Int,
    val icon: Int
) {
    companion object {
        val games = listOf(
            GameItem(R.string.feature_home_mystery_number, R.drawable.numbers),
            GameItem(R.string.feature_home_quiz, R.drawable.quiz),
            GameItem(R.string.feature_home_word_pass, R.drawable.wordpass),
            GameItem(R.string.feature_home_families, R.drawable.family),
        )
    }
}