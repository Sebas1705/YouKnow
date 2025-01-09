package es.sebas1705.youknow.domain.model.games

import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.enums.Languages
import es.sebas1705.youknow.core.classes.enums.Letter
import es.sebas1705.youknow.data.local.database.entities.WordEntity

data class WordModel(
    val word: String,
    val definitions: List<String>,
    val letter: Letter,
    val language: Languages,
    val difficulty: Difficulty
) {
    companion object {
        fun defaultList(index: Int): List<WordModel> = (1..index).map {
            WordModel(
                "W--W--W",
                listOf("Definition 1", "Definition 2", "Definition 3"),
                Letter.W,
                Languages.EN,
                Difficulty.entries.random()
            )
        }
    }

    fun toWordEntity() = WordEntity(
        word,
        definitions,
        letter.ordinal,
        language.ordinal,
        difficulty.ordinal
    )

    fun toMoultedString(): String {
        return this.word.map { char ->
            if (char.lowercase()[0] == this.letter.letter) char
            else '_'
        }.joinToString("")
    }

}
