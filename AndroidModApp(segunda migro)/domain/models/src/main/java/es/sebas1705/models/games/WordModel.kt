package es.sebas1705.models.games


import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.wordpass.Letter

/**
 * Model class to represent the word game
 *
 * @param word [String]: Word to guess
 * @param definitions [List]<[String]>: List of definitions
 * @param letter [Letter]: Letter to guess
 * @param language [Languages]: Language of the question
 * @param difficulty [Difficulty]: Difficulty of the question
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
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

    fun toMoultedString(): String {
        return this.word.map { char ->
            if (char.lowercase()[0] == this.letter.letter) char
            else '_'
        }.joinToString("")
    }

}
