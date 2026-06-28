package es.sebas1705.mappers

import es.sebas1705.files.json.FamiliesJson
import es.sebas1705.models.games.FamiliesModel
import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.room.entities.FamiliesEntity

fun FamiliesEntity.toFamiliesModel() = FamiliesModel(
    answers = answers,
    correctAnswer = correctAnswer,
    category = category,
    language = language,
    difficulty = difficulty
)

fun FamiliesModel.toFamiliesEntity() = FamiliesEntity(
    answers = answers,
    correctAnswer = correctAnswer,
    category = category,
    language = language,
    difficulty = difficulty
)

fun FamiliesJson.toFamiliesEntity() = FamiliesEntity(
    answers = answers,
    correctAnswer = correctAnswer,
    category = Category.entries[category],
    language = Languages.entries[language],
    difficulty = Difficulty.entries[difficulty]
)
