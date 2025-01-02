package es.sebas1705.youknow.domain.model.games

import es.sebas1705.youknow.core.classes.enums.Difficulty

data class NumberModel(
    val number: Int,
    val difficulty: Difficulty
)