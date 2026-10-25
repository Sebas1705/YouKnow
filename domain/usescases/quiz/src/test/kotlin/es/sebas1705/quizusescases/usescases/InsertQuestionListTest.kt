package es.sebas1705.quizusescases.usescases

import es.sebas1705.mappers.toQuestionEntity
import es.sebas1705.models.games.QuestionModel
import es.sebas1705.room.repository.DatabaseRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoInteractions

class InsertQuestionListTest {

    private val repository: DatabaseRepository = mock()
    private val useCase = InsertQuestionList(repository)

    @Test
    fun `inserts every question as an entity in order`() = runTest {
        val questions = QuestionModel.defaultMultipleList(3)

        useCase(questions, onSuccess = {})

        inOrder(repository) {
            questions.forEach { verify(repository).insertOrReplace(it.toQuestionEntity()) }
        }
    }

    @Test
    fun `an empty list only runs the callbacks`() = runTest {
        val events = mutableListOf<String>()

        useCase(emptyList(), onLoading = { events += "loading" }, onSuccess = { events += "success" })

        assertEquals(listOf("loading", "success"), events)
        verifyNoInteractions(repository)
    }
}
