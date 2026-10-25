package es.sebas1705.mysterynumberusescases.usescases

import es.sebas1705.models.games.NumberModel
import es.sebas1705.resources.games.Difficulty
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GenerateRandomNumberTest {

    private val useCase = GenerateRandomNumber()

    private fun generate(difficulty: Difficulty): NumberModel {
        var result: NumberModel? = null
        useCase(difficulty, onLoading = {}, onSuccess = { result = it })
        return result ?: error("onSuccess was not called")
    }

    @Test
    fun `a fixed difficulty is kept and the number stays within its range`() {
        repeat(200) {
            val easy = generate(Difficulty.EASY)
            assertEquals(Difficulty.EASY, easy.difficulty)
            assertTrue("${easy.number} out of 1..100", easy.number in 1..Difficulty.EASY.maxMysteryNumber)

            val hard = generate(Difficulty.HARD)
            assertEquals(Difficulty.HARD, hard.difficulty)
            assertTrue(hard.number in 1..Difficulty.HARD.maxMysteryNumber)
        }
    }

    @Test
    fun `ANY picks a real difficulty, never ANY itself`() {
        repeat(200) {
            val model = generate(Difficulty.ANY)
            assertNotEquals(Difficulty.ANY, model.difficulty)
            assertTrue(model.number in 1..model.difficulty.maxMysteryNumber)
        }
    }

    @Test
    fun `ANY eventually uses every real difficulty`() {
        val seen = (1..500).map { generate(Difficulty.ANY).difficulty }.toSet()

        assertEquals(setOf(Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD), seen)
    }

    @Test
    fun `onLoading runs before onSuccess`() {
        val events = mutableListOf<String>()

        useCase(Difficulty.MEDIUM, onLoading = { events += "loading" }, onSuccess = { events += "success" })

        assertEquals(listOf("loading", "success"), events)
    }
}
