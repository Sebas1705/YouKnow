package es.sebas1705.wordpassusescases.usescases

import es.sebas1705.models.games.WordModel
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.wordpass.Letter
import es.sebas1705.room.entities.WordEntity
import es.sebas1705.room.repository.DatabaseRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GenerateWordPassTest {

    private val repository: DatabaseRepository = mock()
    private val useCase = GenerateWordPass(repository)

    private fun entity(word: String, letter: Letter) =
        WordEntity(word, listOf("def"), letter, Languages.ES, Difficulty.EASY)

    @Test
    fun `maps the words the repository returns for the filters`() = runTest {
        whenever(repository.getWords(2, Letter.A, Languages.ES, Difficulty.EASY))
            .thenReturn(listOf(entity("arbol", Letter.A), entity("agua", Letter.A)))
        var result: List<WordModel>? = null

        useCase(2, Letter.A, Languages.ES, Difficulty.EASY,
            onLoading = {}, onSuccess = { result = it }, onError = { error("unexpected $it") })

        assertEquals(listOf("arbol", "agua"), result?.map { it.word })
        assertEquals(Letter.A, result?.first()?.letter)
    }

    @Test
    fun `fewer words than asked for is still a round`() = runTest {
        whenever(repository.getWords(5, Letter.ANY, Languages.ANY, Difficulty.HARD))
            .thenReturn(listOf(entity("zeta", Letter.Z)))
        var result: List<WordModel>? = null

        useCase(5, Letter.ANY, Languages.ANY, Difficulty.HARD,
            onLoading = {}, onSuccess = { result = it }, onError = { error("unexpected $it") })

        assertEquals(1, result?.size)
    }

    @Test
    fun `no words reports an error`() = runTest {
        whenever(repository.getWords(3, Letter.X, Languages.EN, Difficulty.MEDIUM)).thenReturn(emptyList())
        var result: List<WordModel>? = null
        var error: String? = null

        useCase(3, Letter.X, Languages.EN, Difficulty.MEDIUM,
            onLoading = {}, onSuccess = { result = it }, onError = { error = it })

        assertNull(result)
        assertEquals("No words found", error)
    }
}
