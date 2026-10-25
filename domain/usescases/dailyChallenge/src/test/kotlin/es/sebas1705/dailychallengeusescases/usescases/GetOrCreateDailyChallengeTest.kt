package es.sebas1705.dailychallengeusescases.usescases

import es.sebas1705.common.states.DataState
import es.sebas1705.common.states.ErrorDataType
import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.firestore.config.SettingsFS
import es.sebas1705.firestore.documents.DailyChallengeDocument
import es.sebas1705.firestore.documents.DailyChallengeQuestionDocument
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.models.games.QuestionModel
import es.sebas1705.quizusescases.usescases.GenerateQuestionList
import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.QuizType
import es.sebas1705.room.entities.QuestionEntity
import es.sebas1705.room.repository.DatabaseRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

/**
 * First device of the day publishes the round, everyone else reads it; losing the publish race
 * means reading what did get published. Firestore is mocked, the quiz generator runs for real on a
 * mocked Room repository.
 */
class GetOrCreateDailyChallengeTest {

    private val firestore: FirestoreRepository = mock()
    private val database: DatabaseRepository = mock()
    private val useCase = GetOrCreateDailyChallenge(firestore, GenerateQuestionList(database))

    private val date = "2026-10-25"

    private val published = DailyChallengeDocument(
        date = date,
        questions = listOf(
            DailyChallengeQuestionDocument("Published?", listOf("Yes", "No"), "Yes", "HISTORY", "MEDIUM", "MULTIPLE")
        )
    )

    private fun localEntity(index: Int) = QuestionEntity(
        "Local $index", listOf("A", "B", "C", "D"), "A", Category.ART, Languages.EN, Difficulty.MEDIUM, QuizType.MULTIPLE
    )

    private fun <T> success(value: T): DataFlow<T> = flowOf(DataState.Loading, DataState.Success(value))

    private fun <T> failure(message: String): DataFlow<T> = flowOf(
        DataState.Loading,
        DataState.Error(GetOrCreateDailyChallengeTest::class, ErrorDataType.INTERNAL, message) { _, _ -> }
    )

    private suspend fun run(): Triple<List<String>, List<QuestionModel>?, String?> {
        val events = mutableListOf<String>()
        var result: List<QuestionModel>? = null
        var error: String? = null
        useCase(date, numQuestions = 2,
            onLoading = { events += "loading" },
            onSuccess = { result = it; events += "success" },
            onError = { error = it; events += "error" })
        return Triple(events, result, error)
    }

    @Test
    fun `an already published day is read as-is`() = runTest {
        whenever(firestore.getDailyChallenge(date)).thenReturn(success(published))

        val (events, result, error) = run()

        assertNull(error)
        assertEquals(listOf("Published?"), result?.map { it.question })
        assertEquals(Category.HISTORY, result?.first()?.category)
        assertEquals(listOf("loading", "success"), events)
        verify(firestore, never()).createDailyChallenge(any(), any())
    }

    @Test
    fun `a day nobody published yet is generated locally and published`() = runTest {
        whenever(firestore.getDailyChallenge(date)).thenReturn(failure(SettingsFS.DAILY_CHALLENGE_NOT_FOUND))
        whenever(database.getQuestions(2, Category.ANY, Languages.ANY, Difficulty.MEDIUM, QuizType.MULTIPLE))
            .thenReturn(listOf(localEntity(1), localEntity(2)))
        whenever(firestore.createDailyChallenge(eq(date), any())).thenReturn(success(DailyChallengeDocument()))

        val (_, result, error) = run()

        assertNull(error)
        assertEquals(listOf("Local 1", "Local 2"), result?.map { it.question })
        val document = argumentCaptor<DailyChallengeDocument>()
        verify(firestore).createDailyChallenge(eq(date), document.capture())
        assertEquals(date, document.firstValue.date)
        assertEquals(listOf("Local 1", "Local 2"), document.firstValue.questions.map { it.question })
    }

    @Test
    fun `losing the publish race falls back to the round that did get published`() = runTest {
        whenever(firestore.getDailyChallenge(date))
            .thenReturn(failure(SettingsFS.DAILY_CHALLENGE_NOT_FOUND), success(published))
        whenever(database.getQuestions(2, Category.ANY, Languages.ANY, Difficulty.MEDIUM, QuizType.MULTIPLE))
            .thenReturn(listOf(localEntity(1)))
        whenever(firestore.createDailyChallenge(eq(date), any())).thenReturn(failure("PERMISSION_DENIED"))

        val (_, result, error) = run()

        assertNull(error)
        assertEquals(listOf("Published?"), result?.map { it.question })
    }

    @Test
    fun `any other read failure is reported without trying to publish`() = runTest {
        whenever(firestore.getDailyChallenge(date)).thenReturn(failure("offline"))

        val (events, result, error) = run()

        assertNull(result)
        assertEquals("offline", error)
        assertEquals(listOf("loading", "error"), events)
        verify(firestore, never()).createDailyChallenge(any(), any())
    }

    @Test
    fun `no local questions to publish is reported as an error`() = runTest {
        whenever(firestore.getDailyChallenge(date)).thenReturn(failure(SettingsFS.DAILY_CHALLENGE_NOT_FOUND))
        whenever(database.getQuestions(2, Category.ANY, Languages.ANY, Difficulty.MEDIUM, QuizType.MULTIPLE))
            .thenReturn(emptyList())

        val (_, result, error) = run()

        assertNull(result)
        assertEquals("No questions found", error)
        verify(firestore, never()).createDailyChallenge(any(), any())
    }
}
