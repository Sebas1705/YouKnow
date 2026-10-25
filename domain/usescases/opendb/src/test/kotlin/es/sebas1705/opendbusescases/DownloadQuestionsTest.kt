package es.sebas1705.opendbusescases

import es.sebas1705.repositories.interfaces.IOpendbRepository
import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.QuizType
import es.sebas1705.retrofit.opendb.dtos.QuestionOpendbDto
import es.sebas1705.retrofit.opendb.dtos.ResponseOpendbDto
import es.sebas1705.room.entities.QuestionEntity
import es.sebas1705.room.repository.DatabaseRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class DownloadQuestionsTest {

    /** Hand-written fake: the interface is small and the test only needs one canned answer. */
    private class FakeOpendbRepository(private val answer: () -> ResponseOpendbDto) : IOpendbRepository {
        override suspend fun getQuestions(
            amount: Int, category: Int?, difficulty: String?, type: String?, encode: String?
        ): ResponseOpendbDto = answer()

        override suspend fun getTenRandomQuestions(): ResponseOpendbDto = answer()
    }

    private val database: DatabaseRepository = mock()

    private fun dto(index: Int) = QuestionOpendbDto(
        category = "History",
        correctAnswer = "Right%20$index",
        difficulty = "hard",
        incorrectAnswers = listOf("Wrong"),
        question = "Question%20$index",
        type = "multiple"
    )

    private fun useCase(answer: () -> ResponseOpendbDto) =
        DownloadQuestions(GetTriviaTenQuestionsUseCase(FakeOpendbRepository(answer)), database)

    @Test
    fun `stores every fetched question decoded and reports how many`() = runTest {
        var count: Int? = null
        val events = mutableListOf<String>()

        useCase { ResponseOpendbDto(0, listOf(dto(1), dto(2))) }(
            onLoading = { events += "loading" },
            onSuccess = { count = it; events += "success" },
            onError = { events += "error" }
        )

        assertEquals(2, count)
        assertEquals(listOf("loading", "success"), events)
        val stored = argumentCaptor<QuestionEntity>()
        verify(database, times(2)).insertOrReplace(stored.capture())
        assertEquals(listOf("Question 1", "Question 2"), stored.allValues.map { it.question })
        assertEquals(Category.HISTORY, stored.firstValue.category)
        assertEquals(Difficulty.HARD, stored.firstValue.difficulty)
        assertEquals(QuizType.MULTIPLE, stored.firstValue.quizType)
        assertEquals(Languages.EN, stored.firstValue.language)
    }

    @Test
    fun `a non-zero response code is an error and stores nothing`() = runTest {
        var error: String? = null
        var count: Int? = null

        useCase { ResponseOpendbDto(5, listOf(dto(1))) }(
            onLoading = {}, onSuccess = { count = it }, onError = { error = it }
        )

        assertNull(count)
        assertEquals("OpenTDB returned no questions (code 5)", error)
        verify(database, never()).insertOrReplace(any<QuestionEntity>())
    }

    @Test
    fun `an empty result set is an error`() = runTest {
        var error: String? = null

        useCase { ResponseOpendbDto(0, emptyList()) }(onLoading = {}, onSuccess = {}, onError = { error = it })

        assertEquals("OpenTDB returned no questions (code 0)", error)
        verify(database, never()).insertOrReplace(any<QuestionEntity>())
    }

    @Test
    fun `a network failure surfaces its message`() = runTest {
        var error: String? = null

        useCase { throw IllegalStateException("timeout") }(onLoading = {}, onSuccess = {}, onError = { error = it })

        assertEquals("timeout", error)
    }

    @Test
    fun `a failure without message gets a readable fallback`() = runTest {
        var error: String? = null

        useCase { throw RuntimeException() }(onLoading = {}, onSuccess = {}, onError = { error = it })

        assertEquals("Could not reach OpenTDB", error)
    }
}
