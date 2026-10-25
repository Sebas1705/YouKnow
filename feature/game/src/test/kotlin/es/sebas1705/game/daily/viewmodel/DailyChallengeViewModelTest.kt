package es.sebas1705.game.daily.viewmodel

import kotlin.time.Duration.Companion.seconds
import android.app.Application
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import es.sebas1705.dailychallengeusescases.DailyChallengeUsesCases
import es.sebas1705.dailychallengeusescases.usescases.GetOrCreateDailyChallenge
import es.sebas1705.firestore.documents.DailyChallengeDocument
import es.sebas1705.firestore.documents.DailyChallengeQuestionDocument
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.game.GameTestSupport
import es.sebas1705.game.GameTestSupport.awaitCallback
import es.sebas1705.game.awaitUntil
import es.sebas1705.quizusescases.usescases.GenerateQuestionList
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
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/** The daily challenge loads itself on construction; today's document is served by a mocked Firestore. */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class DailyChallengeViewModelTest {

    private val application: Application = ApplicationProvider.getApplicationContext()
    private val database: DatabaseRepository = mock()

    private val today = DailyChallengeDocument(
        date = "today",
        questions = listOf(
            DailyChallengeQuestionDocument("Q1", listOf("Right 1", "Wrong"), "Right 1", "HISTORY", "MEDIUM", "MULTIPLE"),
            DailyChallengeQuestionDocument("Q2", listOf("Right 2", "Wrong"), "Right 2", "ART", "HARD", "BOOLEAN"),
        )
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun viewModel(firestore: FirestoreRepository) = DailyChallengeViewModel(
        dailyChallengeUsesCases = DailyChallengeUsesCases(GetOrCreateDailyChallenge(firestore, GenerateQuestionList(database))),
        userUsesCases = GameTestSupport.userUsesCases(firestore),
        authUsesCases = GameTestSupport.authUsesCases(),
        application = application
    )

    private fun firestoreWithToday(): FirestoreRepository = GameTestSupport.firestoreWithUser().also {
        whenever(it.getDailyChallenge(any())).thenReturn(GameTestSupport.success(today))
    }

    private suspend fun runningChallenge(): DailyChallengeViewModel {
        val viewModel = viewModel(firestoreWithToday())
        viewModel.uiState.test(timeout = 30.seconds) {
            awaitUntil { it.status == DailyChallengeStatus.RUNNING }
            cancelAndIgnoreRemainingEvents()
        }
        return viewModel
    }

    @Test
    fun `loads today's round on creation`() = runTest {
        val viewModel = viewModel(firestoreWithToday())

        viewModel.uiState.test(timeout = 30.seconds) {
            val running = awaitUntil { it.status == DailyChallengeStatus.RUNNING }

            assertEquals(listOf("Q1", "Q2"), running.questions.map { it.question })
            assertEquals(0, running.actualQuestion)
            assertEquals(0, running.points)
            assertNull(running.loadError)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `a load failure is kept in the state instead of toasted`() = runTest {
        val firestore = GameTestSupport.firestoreWithUser().also {
            whenever(it.getDailyChallenge(any())).thenReturn(GameTestSupport.failure("offline"))
        }
        val viewModel = viewModel(firestore)

        viewModel.uiState.test(timeout = 30.seconds) {
            val failed = awaitUntil { it.loadError != null }

            assertEquals("offline", failed.loadError)
            assertEquals(DailyChallengeStatus.LOADING, failed.status)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `a correct answer scores difficulty times type with no mode multiplier`() = runTest {
        val viewModel = runningChallenge()

        viewModel.sendIntent(DailyChallengeIntent.Response("Right 1"))

        val state = viewModel.uiState.value
        assertEquals(DailyChallengeStatus.RUNNING, state.status)
        assertEquals(10, state.points) // MEDIUM 10 * MULTIPLE 1.0
        assertEquals(1, state.correctAnswers)
        assertEquals(1, state.actualQuestion)
    }

    @Test
    fun `the last answer finishes the round and a wrong one scores nothing`() = runTest {
        val viewModel = runningChallenge()

        viewModel.sendIntent(DailyChallengeIntent.Response("Right 1"))
        viewModel.sendIntent(DailyChallengeIntent.Response("Wrong"))

        val state = viewModel.uiState.value
        assertEquals(DailyChallengeStatus.FINISHED, state.status)
        assertEquals(10, state.points)
        assertEquals(1, state.correctAnswers)
        assertEquals(2, state.actualQuestion)
    }

    @Test
    fun `leaving with no points calls back without touching the user`() = runTest {
        val firestore = firestoreWithToday()
        val viewModel = viewModel(firestore)
        val done = CompletableDeferred<Unit>()

        viewModel.sendIntent(DailyChallengeIntent.OutGame { done.complete(Unit) })
        done.awaitCallback()

        verify(firestore, never()).getUser(any())
    }

    @Test
    fun `leaving with points stores them before calling back`() = runTest {
        val firestore = firestoreWithToday()
        val viewModel = viewModel(firestore)
        viewModel.uiState.test(timeout = 30.seconds) {
            awaitUntil { it.status == DailyChallengeStatus.RUNNING }
            cancelAndIgnoreRemainingEvents()
        }
        viewModel.sendIntent(DailyChallengeIntent.Response("Right 1"))
        val done = CompletableDeferred<Unit>()

        viewModel.sendIntent(DailyChallengeIntent.OutGame { done.complete(Unit) })
        done.awaitCallback()

        verify(firestore).addPointsToUser(GameTestSupport.UID, 0, 10)
    }
}
