package es.sebas1705.models.games

import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.wordpass.Letter
import org.junit.Assert.assertEquals
import org.junit.Test

class WordModelTest {

    private fun word(text: String, letter: Letter) = WordModel(text, emptyList(), letter, Languages.ES, Difficulty.EASY)

    @Test
    fun `moulted string hides every letter except the word's own letter`() {
        assertEquals("_a_a_a", word("banana", Letter.A).toMoultedString())
    }

    @Test
    fun `moulted string matches the letter case-insensitively`() {
        assertEquals("A___A", word("ArepA", Letter.A).toMoultedString())
    }

    @Test
    fun `moulted string keeps the length when the letter never appears`() {
        assertEquals("_____", word("hello", Letter.Z).toMoultedString())
    }
}
