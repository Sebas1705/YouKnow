package es.sebas1705.youknow.domain.model.games

import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.enums.Languages
import es.sebas1705.youknow.core.classes.enums.Letter
import es.sebas1705.youknow.core.classes.enums.WordPassType

data class WordModel(
    val word: String,
    val definitions: List<String>,
    val letter: Letter,
    val language: Languages,
    val difficulty: Difficulty,
    val wordPassType: WordPassType
) {
    companion object {
        fun defaultList(index: Int): List<WordModel> = (1..index).map {
            WordModel(
                "W--W--W",
                listOf("Definition 1", "Definition 2", "Definition 3"),
                Letter.W,
                Languages.EN,
                Difficulty.entries.random(),
                WordPassType.entries.random()
            )
        }
    }

    fun toMoultedString(): String {
        return this.word.mapIndexed { index, char ->
            if (wordPassType == WordPassType.FIRST_LETTER && index == 0)
                if(char.lowercase()[0] == letter.letter) char else '_'
            else if (wordPassType == WordPassType.LAST_LETTER && index == this.word.length - 1)
                if(char.lowercase()[0] == letter.letter) char else '_'
            else
                if(char.lowercase()[0] == letter.letter) char else '_'
        }.joinToString("")
    }
}
