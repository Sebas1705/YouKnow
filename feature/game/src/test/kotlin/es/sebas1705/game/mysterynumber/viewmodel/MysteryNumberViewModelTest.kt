package es.sebas1705.game.mysterynumber.viewmodel

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.mysterynumber.MysteryNumberMode
import es.sebas1705.common.games.mysterynumber.MysteryNumberStatus
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.game.GameTestSupport
import es.sebas1705.game.GameTestSupport.UID
import es.sebas1705.game.GameTestSupport.awaitCallback
import es.sebas1705.game.GameTestSupport.awaitToast
import es.sebas1705.game.GameTestSupport.settle
import es.sebas1705.game.awaitUntil
import es.sebas1705.mysterynumberusescases.MysteryNumberUsesCases
import es.sebas1705.mysterynumberusescases.usescases.GenerateRandomNumber
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
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class MysteryNumberViewModelTest {

    private val application: Application = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun viewModel(firestore: FirestoreRepository = GameTestSupport.firestoreWithUser()) = MysteryNumberViewModel(
        mysteryNumberUsesCases = MysteryNumberUsesCases(GenerateRandomNumber()),
        userUsesCases = GameTestSupport.userUsesCases(firestore),
        authUsesCases = GameTestSupport.authUsesCases(),
        application = application
    )

    /** A custom game with a known difficulty and life count, returned once it is RUNNING. */
    private suspend fun runningGame(difficulty: Difficulty = Difficulty.EASY, lives: Int = 7): MysteryNumberViewModel {
        val viewModel = viewModel()
        viewModel.uiState.test {
            viewModel.sendIntent(MysteryNumberIntent.GenerateGame(difficulty, lives))
            awaitUntil { it.status == MysteryNumberStatus.RUNNING }
            cancelAndIgnoreRemainingEvents()
        }
        return viewModel
    }

    @Test
    fun `starts in selection mode with no number`() {
        val state = viewModel().uiState.value

        assertEquals(MysteryNumberState.default(), state)
        assertEquals(MysteryNumberStatus.SELECTION_MODE, state.status)
        assertEquals(-1, state.numberModel.number)
        assertNull(state.mode)
    }

    @Test
    fun `selecting CUSTOM only opens the custom form`() {
        val viewModel = viewModel()

        viewModel.sendIntent(MysteryNumberIntent.SelectMode(MysteryNumberMode.CUSTOM))

        assertEquals(MysteryNumberStatus.CUSTOM, viewModel.uiState.value.status)
        assertEquals(MysteryNumberMode.CUSTOM, viewModel.uiState.value.mode)
    }

    @Test
    fun `selecting a preset mode draws a number with the mode's lives`() = runTest {
        val viewModel = viewModel()

        viewModel.uiState.test {
            viewModel.sendIntent(MysteryNumberIntent.SelectMode(MysteryNumberMode.ALEATORY))
            val running = awaitUntil { it.status == MysteryNumberStatus.RUNNING }

            assertEquals(MysteryNumberMode.ALEATORY, running.mode)
            assertEquals(MysteryNumberMode.ALEATORY.lives, running.lives)
            assertNotEquals(Difficulty.ANY, running.numberModel.difficulty)
            assertTrue(running.numberModel.number in 1..running.numberModel.difficulty.maxMysteryNumber)
            assertTrue(running.guesses.isEmpty())
            assertFalse(running.isLoading)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `a custom game keeps the chosen difficulty and lives`() = runTest {
        val viewModel = runningGame(Difficulty.EASY, lives = 5)

        val state = viewModel.uiState.value
        assertEquals(Difficulty.EASY, state.numberModel.difficulty)
        assertTrue(state.numberModel.number in 1..100)
        assertEquals(5, state.lives)
        assertNull(state.mode)
    }

    @Test
    fun `guessing right finishes and scores difficulty times remaining lives`() = runTest {
        val viewModel = runningGame(Difficulty.EASY, lives = 7)
        val secret = viewModel.uiState.value.numberModel.number

        viewModel.sendIntent(MysteryNumberIntent.Response(secret, time = 12f))

        val state = viewModel.uiState.value
        assertEquals(MysteryNumberStatus.FINISHED, state.status)
        // EASY 5 points * no-mode multiplier 1.0 * 7 lives
        assertEquals(35, state.points)
        assertEquals(7, state.lives)
        assertEquals(12f, state.timeRemaining)
        assertEquals(listOf(secret), state.guesses)
    }

    @Test
    fun `guessing wrong costs a life, records the guess and keeps running`() = runTest {
        val viewModel = runningGame(Difficulty.EASY, lives = 7)
        val wrong = viewModel.uiState.value.numberModel.number % 100 + 1 // never the secret itself

        viewModel.sendIntent(MysteryNumberIntent.Response(wrong, time = 30f))

        val state = viewModel.uiState.value
        assertEquals(MysteryNumberStatus.RUNNING, state.status)
        assertEquals(0, state.points)
        assertEquals(6, state.lives)
        assertEquals(0f, state.timeRemaining)
        assertEquals(listOf(wrong), state.guesses)
    }

    @Test
    fun `giving up finishes without recording a guess`() = runTest {
        val viewModel = runningGame(Difficulty.MEDIUM, lives = 3)

        viewModel.sendIntent(MysteryNumberIntent.Response(-1, time = 4f))

        val state = viewModel.uiState.value
        assertEquals(MysteryNumberStatus.FINISHED, state.status)
        assertEquals(0, state.points)
        assertTrue(state.guesses.isEmpty())
        assertEquals(4f, state.timeRemaining)
    }

    @Test
    fun `losing the last life finishes the game`() = runTest {
        val viewModel = runningGame(Difficulty.EASY, lives = 1)
        val wrong = viewModel.uiState.value.numberModel.number % 100 + 1

        viewModel.sendIntent(MysteryNumberIntent.Response(wrong, time = 1f))

        assertEquals(MysteryNumberStatus.FINISHED, viewModel.uiState.value.status)
        assertEquals(0, viewModel.uiState.value.lives)
    }

    @Test
    fun `time attack scores with the remaining time instead of the lives`() = runTest {
        val viewModel = viewModel()
        viewModel.uiState.test {
            viewModel.sendIntent(MysteryNumberIntent.SelectMode(MysteryNumberMode.TIME_ATTACK))
            awaitUntil { it.status == MysteryNumberStatus.RUNNING }
            cancelAndIgnoreRemainingEvents()
        }
        val number = viewModel.uiState.value.numberModel

        viewModel.sendIntent(MysteryNumberIntent.Response(number.number, time = 10f))

        val expected = (number.difficulty.points * MysteryNumberMode.TIME_ATTACK.multiPoints * (10f * 2)).toInt()
        assertEquals(expected, viewModel.uiState.value.points)
        assertEquals(MysteryNumberStatus.FINISHED, viewModel.uiState.value.status)
    }

    @Test
    fun `resetting keeps the points but drops everything else`() = runTest {
        val viewModel = runningGame(Difficulty.EASY, lives = 2)
        viewModel.sendIntent(MysteryNumberIntent.Response(viewModel.uiState.value.numberModel.number, 1f))
        val points = viewModel.uiState.value.points

        viewModel.sendIntent(MysteryNumberIntent.ResetGame)

        assertEquals(MysteryNumberState.default().copy(points = points), viewModel.uiState.value)
        assertEquals(10, points)
    }

    @Test
    fun `leaving the game stores points and credits before calling back`() = runTest {
        val firestore = GameTestSupport.firestoreWithUser(points = 50, credits = 2)
        val viewModel = viewModel(firestore)
        val done = CompletableDeferred<Unit>()

        viewModel.sendIntent(MysteryNumberIntent.OutGame { done.complete(Unit) })
        done.awaitCallback()

        verify(firestore).getUser(UID)
        verify(firestore).addPointsToUser(UID, 50, 0)
        verify(firestore).addCreditsToUser(eq(UID), eq(2), any())
    }

    @Test
    fun `leaving the game when the user cannot be read toasts the error`() = runTest {
        val viewModel = viewModel(GameTestSupport.firestoreWithoutUser("offline"))
        val done = CompletableDeferred<Unit>()

        viewModel.sendIntent(MysteryNumberIntent.OutGame { done.complete(Unit) })

        assertEquals("offline", awaitToast())
        settle()
        assertFalse(done.isCompleted)
    }
}
