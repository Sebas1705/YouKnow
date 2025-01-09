package es.sebas1705.youknow.domain.model.games

import es.sebas1705.youknow.core.classes.enums.Category
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.enums.Languages
import es.sebas1705.youknow.data.local.database.entities.FamiliesEntity

data class FamiliesModel(
    val answers: List<String>,
    val correctAnswer: String,
    val category: Category,
    val language: Languages,
    val difficulty: Difficulty,
) {
    companion object {
        fun defaultList(index: Int): List<FamiliesModel> = (1..index).map {
            FamiliesModel(
                listOf("A", "B", "C", "D"),
                "A",
                Category.ANY,
                Languages.EN,
                listOf(Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD).random()
            )
        }
    }

    fun toFamiliesEntity() = FamiliesEntity(
        answers,
        correctAnswer,
        category.ordinal,
        language.ordinal,
        difficulty.ordinal
    )
}