package es.sebas1705.game.quiz.viewmodel

import kotlin.time.Duration.Companion.seconds
import android.app.Application
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.QuizType
import es.sebas1705.common.games.quiz.QuizMode
import es.sebas1705.common.games.quiz.QuizStatus
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.game.GameTestSupport
import es.sebas1705.game.GameTestSupport.UID
import es.sebas1705.game.GameTestSupport.awaitCallback
import es.sebas1705.game.GameTestSupport.awaitToast
import es.sebas1705.game.GameTestSupport.questionEntity
import es.sebas1705.game.GameTestSupport.settle
import es.sebas1705.game.awaitUntil
import es.sebas1705.quizusescases.QuizUsesCases
import es.sebas1705.quizusescases.usescases.GenerateQuestionList
import es.sebas1705.quizusescases.usescases.InsertQuestionList
import es.sebas1705.room.entities.QuestionEntity
import es.sebas1705.room.repository.DatabaseRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.wheneverBlocking
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Drives the quiz through its real use cases on top of a mocked Room repository and a mocked
 * Firestore. Robolectric supplies the Application the ViewModel toasts errors through.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class QuizViewModelTest {

    private val database: DatabaseRepository = mock()
    private val application: Application = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun viewModel(
        firestore: FirestoreRepository = GameTestSupport.firestoreWithUser(),
        language: Languages = Languages.ANY,
    ) = QuizViewModel(
        quizUsesCases = QuizUsesCases(GenerateQuestionList(database), InsertQuestionList(database)),
        userUsesCases = GameTestSupport.userUsesCases(firestore),
        authUsesCases = GameTestSupport.authUsesCases(),
        settingUsesCases = GameTestSupport.settingUsesCases(language),
        application = application
    )

    private fun stubQuestions(numQuestions: Int, questions: List<QuestionEntity>) {
        wheneverBlocking { database.getQuestions(eq(numQuestions), any(), any(), any(), any()) }.thenReturn(questions)
    }

    /** Starts an ALEATORY round with [questions] and returns the ViewModel once it is RUNNING. */
    private suspend fun runningGame(questions: List<QuestionEntity>, mode: QuizMode = QuizMode.ALEATORY): QuizViewModel {
        stubQuestions(mode.numQuestions, questions)
        val viewModel = viewModel()
        viewModel.uiState.test(timeout = 30.seconds) {
            viewModel.sendIntent(QuizIntent.SelectMode(mode))
            awaitUntil { it.status == QuizStatus.RUNNING }
            cancelAndIgnoreRemainingEvents()
        }
        return viewModel
    }

    @Test
    fun `starts in selection mode with three lives and no questions`() {
        val state = viewModel().uiState.value

        assertEquals(QuizState.default(), state)
        assertEquals(QuizStatus.SELECTION_MODE, state.status)
        assertEquals(3, state.lives)
        assertNull(state.mode)
    }

    @Test
    fun `selecting CUSTOM only opens the custom form`() = runTest {
        val viewModel = viewModel()

        viewModel.sendIntent(QuizIntent.SelectMode(QuizMode.CUSTOM))

        assertEquals(QuizStatus.CUSTOM, viewModel.uiState.value.status)
        assertEquals(QuizMode.CUSTOM, viewModel.uiState.value.mode)
        verify(database, never()).getQuestions(any(), any(), any(), any(), any())
    }

    @Test
    fun `selecting a preset mode loads its question count and starts running`() = runTest {
        val questions = (1..3).map { questionEntity(it) }
        stubQuestions(QuizMode.ALEATORY.numQuestions, questions)
        val viewModel = viewModel()

        viewModel.uiState.test(timeout = 30.seconds) {
            viewModel.sendIntent(QuizIntent.SelectMode(QuizMode.ALEATORY))
            val running = awaitUntil { it.status == QuizStatus.RUNNING }

            assertEquals(QuizMode.ALEATORY, running.mode)
            assertFalse(running.isLoading)
            assertEquals(questions.map { it.question }, running.questions.map { it.question })
            // Answers are shuffled per question but none is lost.
            running.questions.forEachIndexed { i, q -> assertEquals(questions[i].answers.toSet(), q.answers.toSet()) }
            cancelAndIgnoreRemainingEvents()
        }
        verify(database).getQuestions(10, Category.ANY, Languages.ANY, Difficulty.ANY, QuizType.ANY)
    }

    @Test
    fun `a custom game queries the chosen filters in the stored language`() = runTest {
        wheneverBlocking { database.getQuestions(any(), any(), any(), any(), any()) }.thenReturn(listOf(questionEntity(1)))
        val viewModel = viewModel(language = Languages.ES)

        viewModel.uiState.test(timeout = 30.seconds) {
            viewModel.sendIntent(QuizIntent.ReadLanguages)
            awaitUntil { it.languages == Languages.ES }
            viewModel.sendIntent(QuizIntent.GenerateGame(Category.HISTORY, Difficulty.HARD, QuizType.BOOLEAN, 5))
            val running = awaitUntil { it.status == QuizStatus.RUNNING }

            assertEquals(QuizMode.CUSTOM, running.mode)
            assertEquals(1, running.questions.size)
            cancelAndIgnoreRemainingEvents()
        }
        verify(database).getQuestions(5, Category.HISTORY, Languages.ES, Difficulty.HARD, QuizType.BOOLEAN)
    }

    @Test
    fun `a game with no questions toasts the error and stays in selection`() = runTest {
        stubQuestions(QuizMode.SURVIVAL.numQuestions, emptyList())
        val viewModel = viewModel()

        viewModel.sendIntent(QuizIntent.SelectMode(QuizMode.SURVIVAL))

        assertEquals("No questions found", awaitToast())
        settle()
        assertEquals(QuizStatus.SELECTION_MODE, viewModel.uiState.value.status)
        assertFalse(viewModel.uiState.value.isLoading)
    }

    @Test
    fun `a correct answer scores difficulty times type times mode and advances`() = runTest {
        val viewModel = runningGame((1..3).map { questionEntity(it, Difficulty.MEDIUM, QuizType.MULTIPLE) })

        viewModel.sendIntent(QuizIntent.Response("Right 1"))

        val state = viewModel.uiState.value
        assertEquals(QuizStatus.RUNNING, state.status)
        // MEDIUM 10 points * MULTIPLE 1.0 * ALEATORY 1.0
        assertEquals(10, state.points)
        assertEquals(1, state.correctAnswers)
        assertEquals(1, state.actualQuestion)
        assertEquals(3, state.lives)
    }

    @Test
    fun `a boolean question is worth half`() = runTest {
        val viewModel = runningGame((1..3).map { questionEntity(it, Difficulty.HARD, QuizType.BOOLEAN) })

        viewModel.sendIntent(QuizIntent.Response("Right 1"))

        // HARD 15 * BOOLEAN 0.5 * ALEATORY 1.0 = 7.5 -> 7
        assertEquals(7, viewModel.uiState.value.points)
    }

    @Test
    fun `a wrong answer costs a life and scores nothing`() = runTest {
        val viewModel = runningGame((1..3).map { questionEntity(it) })

        viewModel.sendIntent(QuizIntent.Response("Wrong 1"))

        val state = viewModel.uiState.value
        assertEquals(QuizStatus.RUNNING, state.status)
        assertEquals(0, state.points)
        assertEquals(0, state.correctAnswers)
        assertEquals(1, state.actualQuestion)
        assertEquals(2, state.lives)
    }

    @Test
    fun `the last question finishes the game and applies the accuracy buff`() = runTest {
        val viewModel = runningGame((1..2).map { questionEntity(it, Difficulty.MEDIUM, QuizType.MULTIPLE) })

        viewModel.sendIntent(QuizIntent.Response("Right 1"))
        viewModel.sendIntent(QuizIntent.Response("Right 2"))

        val state = viewModel.uiState.value
        assertEquals(QuizStatus.FINISHED, state.status)
        // 10 (first) + 10 (second) + 20 * buff, where buff = 1 correct so far / 2 questions = 0.5
        assertEquals(30, state.points)
        assertEquals(2, state.correctAnswers)
    }

    @Test
    fun `survival ends when the last life is lost`() = runTest {
        val viewModel = runningGame((1..5).map { questionEntity(it) }, QuizMode.SURVIVAL)

        viewModel.sendIntent(QuizIntent.Response("Wrong 1"))
        viewModel.sendIntent(QuizIntent.Response("Wrong 1"))
        assertEquals(QuizStatus.RUNNING, viewModel.uiState.value.status)
        assertEquals(1, viewModel.uiState.value.lives)

        viewModel.sendIntent(QuizIntent.Response("Wrong 1"))

        assertEquals(QuizStatus.FINISHED, viewModel.uiState.value.status)
        assertEquals(0, viewModel.uiState.value.lives)
    }

    @Test
    fun `leaving the game stores points and credits before calling back`() = runTest {
        val firestore = GameTestSupport.firestoreWithUser(points = 100, credits = 5)
        stubQuestions(QuizMode.ALEATORY.numQuestions, listOf(questionEntity(1, Difficulty.MEDIUM)))
        val viewModel = viewModel(firestore)
        viewModel.uiState.test(timeout = 30.seconds) {
            viewModel.sendIntent(QuizIntent.SelectMode(QuizMode.ALEATORY))
            awaitUntil { it.status == QuizStatus.RUNNING }
            cancelAndIgnoreRemainingEvents()
        }
        viewModel.sendIntent(QuizIntent.Response("Right 1"))
        val earned = viewModel.uiState.value.points
        val done = CompletableDeferred<Unit>()

        viewModel.sendIntent(QuizIntent.OutGame { done.complete(Unit) })
        done.awaitCallback()

        verify(firestore).getUser(UID)
        verify(firestore).addPointsToUser(UID, 100, earned)
        verify(firestore).addCreditsToUser(eq(UID), eq(5), any())
        assertFalse(viewModel.uiState.value.isLoading)
    }

    @Test
    fun `leaving the game when the user cannot be read toasts and never calls back`() = runTest {
        val viewModel = viewModel(GameTestSupport.firestoreWithoutUser("User not found"))
        val done = CompletableDeferred<Unit>()

        viewModel.sendIntent(QuizIntent.OutGame { done.complete(Unit) })

        assertEquals("User not found", awaitToast())
        settle()
        assertFalse(done.isCompleted)
        assertFalse(viewModel.uiState.value.isLoading)
    }

    @Test
    fun `resetting stores the score and returns to the default state`() = runTest {
        val firestore = GameTestSupport.firestoreWithUser()
        stubQuestions(QuizMode.ALEATORY.numQuestions, listOf(questionEntity(1)))
        val viewModel = viewModel(firestore)
        viewModel.uiState.test(timeout = 30.seconds) {
            viewModel.sendIntent(QuizIntent.SelectMode(QuizMode.ALEATORY))
            awaitUntil { it.status == QuizStatus.RUNNING }
            viewModel.sendIntent(QuizIntent.Response("Right 1"))
            awaitUntil { it.status == QuizStatus.FINISHED }

            viewModel.sendIntent(QuizIntent.ResetGame)
            val reset = awaitUntil { it.status == QuizStatus.SELECTION_MODE && !it.isLoading }

            assertEquals(QuizState.default(), reset)
            cancelAndIgnoreRemainingEvents()
        }
        verify(firestore).addPointsToUser(eq(UID), eq(0), any())
    }
}
