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
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GenerateWheelWordPassTest {

    private val repository: DatabaseRepository = mock()
    private val useCase = GenerateWheelWordPass(repository)

    private fun entity(word: String, letter: Letter) =
        WordEntity(word, listOf("def"), letter, Languages.EN, Difficulty.MEDIUM)

    @Test
    fun `asks for one word per real letter and skips ANY`() = runTest {
        whenever(repository.getWords(any(), any(), any(), any())).thenReturn(emptyList())
        whenever(repository.getWords(1, Letter.A, Languages.EN, Difficulty.MEDIUM)).thenReturn(listOf(entity("apple", Letter.A)))

        useCase(Difficulty.MEDIUM, Languages.EN, onLoading = {}, onSuccess = {}, onError = {})

        Letter.entries.filter { it != Letter.ANY }.forEach {
            verify(repository).getWords(1, it, Languages.EN, Difficulty.MEDIUM)
        }
        verify(repository, never()).getWords(any(), eq(Letter.ANY), any(), any())
    }

    @Test
    fun `letters without a word are skipped and the rest form the wheel in order`() = runTest {
        whenever(repository.getWords(any(), any(), any(), any())).thenReturn(emptyList())
        whenever(repository.getWords(1, Letter.B, Languages.EN, Difficulty.MEDIUM)).thenReturn(listOf(entity("bee", Letter.B)))
        whenever(repository.getWords(1, Letter.Z, Languages.EN, Difficulty.MEDIUM)).thenReturn(listOf(entity("zoo", Letter.Z)))
        var result: List<WordModel>? = null

        useCase(Difficulty.MEDIUM, Languages.EN, onLoading = {}, onSuccess = { result = it }, onError = { error(it) })

        assertEquals(listOf("bee", "zoo"), result?.map { it.word })
    }

    @Test
    fun `only the first word of a letter is used`() = runTest {
        whenever(repository.getWords(any(), any(), any(), any())).thenReturn(emptyList())
        whenever(repository.getWords(1, Letter.C, Languages.EN, Difficulty.MEDIUM))
            .thenReturn(listOf(entity("cat", Letter.C), entity("cow", Letter.C)))
        var result: List<WordModel>? = null

        useCase(Difficulty.MEDIUM, Languages.EN, onLoading = {}, onSuccess = { result = it }, onError = { error(it) })

        assertEquals(listOf("cat"), result?.map { it.word })
    }

    @Test
    fun `an empty wheel is an error`() = runTest {
        whenever(repository.getWords(any(), any(), any(), any())).thenReturn(emptyList())
        var result: List<WordModel>? = null
        var error: String? = null

        useCase(Difficulty.HARD, Languages.ES, onLoading = {}, onSuccess = { result = it }, onError = { error = it })

        assertNull(result)
        assertEquals("No words found", error)
    }
}
