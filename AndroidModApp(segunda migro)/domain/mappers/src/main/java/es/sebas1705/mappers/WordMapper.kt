package es.sebas1705.mappers

import es.sebas1705.files.json.WordJson
import es.sebas1705.models.games.WordModel
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.wordpass.Letter
import es.sebas1705.room.entities.WordEntity

fun WordEntity.toWordModel() = WordModel(
    word = word,
    definitions = definitions,
    letter = letter,
    language = language,
    difficulty = difficulty
)

fun WordModel.toWordEntity() = WordEntity(
    word = word,
    definitions = definitions,
    letter = letter,
    language = language,
    difficulty = difficulty
)

fun WordJson.toWordEntity() = WordEntity(
    word = word,
    definitions = definitions,
    letter = Letter.entries[letter],
    language = Languages.entries[language],
    difficulty = Difficulty.entries[difficulty]
)
