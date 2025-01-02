package es.sebas1705.youknow.core.classes.enums

import es.sebas1705.youknow.R

enum class Difficulty(
    val id: String?,
    val strRes: Int,
    val points: Int,
    val maxMysteryNumber: Int
) {
    ANY(null, R.string.any, 10, 0),
    EASY("easy", R.string.dif_easy, 5, 100),
    MEDIUM("medium", R.string.dif_medium, 10, 1_000),
    HARD("hard", R.string.dif_hard, 15, 1_000_000);

    companion object {
        fun getDifficulty(value: String): Difficulty = entries.find { it.id == value } ?: ANY
    }
}