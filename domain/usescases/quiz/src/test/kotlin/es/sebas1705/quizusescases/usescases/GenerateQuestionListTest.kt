package es.sebas1705.quizusescases.usescases

import es.sebas1705.models.games.QuestionModel
import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.QuizType
import es.sebas1705.room.entities.QuestionEntity
import es.sebas1705.room.repository.DatabaseRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GenerateQuestionListTest {

    private val repository: DatabaseRepository = mock()
    private val useCase = GenerateQuestionList(repository)

    private fun entity(index: Int) = QuestionEntity(
        question = "Q$index",
        answers = listOf("A", "B", "C", "D"),
        correctAnswer = "A",
        category = Category.HISTORY,
        language = Languages.EN,
        difficulty = Difficulty.MEDIUM,
        quizType = QuizType.MULTIPLE
    )

    @Test
    fun `asks the repository with the requested filters and maps the result`() = runTest {
        whenever(repository.getQuestions(5, Category.HISTORY, Languages.EN, Difficulty.MEDIUM, QuizType.MULTIPLE))
            .thenReturn(listOf(entity(1), entity(2)))
        var result: List<QuestionModel>? = null

        useCase(5, Category.HISTORY, Difficulty.MEDIUM, Languages.EN, QuizType.MULTIPLE,
            onSuccess = { result = it }, onError = { error("unexpected $it") })

        assertEquals(listOf("Q1", "Q2"), result?.map { it.question })
        assertEquals(Category.HISTORY, result?.first()?.category)
        verify(repository).getQuestions(5, Category.HISTORY, Languages.EN, Difficulty.MEDIUM, QuizType.MULTIPLE)
    }

    @Test
    fun `fewer questions than asked for is still a game`() = runTest {
        whenever(repository.getQuestions(10, Category.ANY, Languages.ANY, Difficulty.ANY, QuizType.ANY))
            .thenReturn(listOf(entity(1)))
        var result: List<QuestionModel>? = null

        useCase(10, Category.ANY, Difficulty.ANY, Languages.ANY, QuizType.ANY,
            onSuccess = { result = it }, onError = { error("unexpected $it") })

        assertEquals(1, result?.size)
    }

    @Test
    fun `no questions reports an error and never calls onSuccess`() = runTest {
        whenever(repository.getQuestions(3, Category.ART, Languages.ES, Difficulty.HARD, QuizType.BOOLEAN))
            .thenReturn(emptyList())
        var result: List<QuestionModel>? = null
        var error: String? = null

        useCase(3, Category.ART, Difficulty.HARD, Languages.ES, QuizType.BOOLEAN,
            onSuccess = { result = it }, onError = { error = it })

        assertNull(result)
        assertEquals("No questions found", error)
    }

    @Test
    fun `onLoading runs before the result is delivered`() = runTest {
        whenever(repository.getQuestions(1, Category.ANY, Languages.ANY, Difficulty.ANY, QuizType.ANY))
            .thenReturn(listOf(entity(1)))
        val events = mutableListOf<String>()

        useCase(1, Category.ANY, Difficulty.ANY, Languages.ANY, QuizType.ANY,
            onLoading = { events += "loading" }, onSuccess = { events += "success" }, onError = { events += "error" })

        assertEquals(listOf("loading", "success"), events)
    }
}
