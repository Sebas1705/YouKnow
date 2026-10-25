package es.sebas1705.game.wordpass.viewmodel

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.wordpass.Letter
import es.sebas1705.common.games.wordpass.WordPassMode
import es.sebas1705.common.games.wordpass.WordPassStatus
import es.sebas1705.game.GameTestSupport
import es.sebas1705.game.GameTestSupport.awaitToast
import es.sebas1705.game.awaitUntil
import es.sebas1705.room.entities.WordEntity
import es.sebas1705.room.repository.DatabaseRepository
import es.sebas1705.wordpassusescases.WordPassUsesCases
import es.sebas1705.wordpassusescases.usescases.GenerateWheelWordPass
import es.sebas1705.wordpassusescases.usescases.GenerateWordPass
import es.sebas1705.wordpassusescases.usescases.InsertWordPassList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.wheneverBlocking
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class WordPassViewModelTest {

    private val application: Application = ApplicationProvider.getApplicationContext()
    private val database: DatabaseRepository = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun viewModel() = WordPassViewModel(
        wordPassUsesCases = WordPassUsesCases(GenerateWordPass(database), GenerateWheelWordPass(database), InsertWordPassList(database)),
        userUsesCases = GameTestSupport.userUsesCases(GameTestSupport.firestoreWithUser()),
        authUsesCases = GameTestSupport.authUsesCases(),
        settingUsesCases = GameTestSupport.settingUsesCases(),
        application = application
    )

    private fun word(text: String, letter: Letter, difficulty: Difficulty = Difficulty.EASY) =
        WordEntity(text, listOf("definition"), letter, Languages.ES, difficulty)

    private suspend fun runningGame(words: List<WordEntity>, mode: WordPassMode = WordPassMode.ALEATORY): WordPassViewModel {
        wheneverBlocking { database.getWords(eq(words.size), eq(Letter.ANY), any(), any()) }.thenReturn(words)
        val viewModel = viewModel()
        viewModel.uiState.test {
            viewModel.sendIntent(WordPassIntent.SelectMode(mode))
            viewModel.sendIntent(WordPassIntent.GenerateGame(Difficulty.EASY, mode, words.size))
            awaitUntil { it.status == WordPassStatus.RUNNING }
            cancelAndIgnoreRemainingEvents()
        }
        return viewModel
    }

    @Test
    fun `selecting a mode only records it`() {
        val viewModel = viewModel()

        viewModel.sendIntent(WordPassIntent.SelectMode(WordPassMode.SURVIVAL))

        assertEquals(WordPassMode.SURVIVAL, viewModel.uiState.value.mode)
        assertEquals(WordPassStatus.SELECTION_MODE, viewModel.uiState.value.status)
    }

    @Test
    fun `a generated game runs with the words from the repository`() = runTest {
        val viewModel = runningGame(listOf(word("árbol", Letter.A), word("barco", Letter.B)))

        val state = viewModel.uiState.value
        assertEquals(listOf("árbol", "barco"), state.words.map { it.word })
        assertTrue(state.results.isEmpty())
        assertEquals(0, state.actualWord)
    }

    @Test
    fun `the fire wheel asks for one word per letter`() = runTest {
        wheneverBlocking { database.getWords(any(), any(), any(), any()) }.thenReturn(emptyList())
        wheneverBlocking { database.getWords(eq(1), eq(Letter.A), any(), any()) }.thenReturn(listOf(word("ala", Letter.A)))
        wheneverBlocking { database.getWords(eq(1), eq(Letter.M), any(), any()) }.thenReturn(listOf(word("mar", Letter.M)))
        val viewModel = viewModel()

        viewModel.uiState.test {
            viewModel.sendIntent(WordPassIntent.GenerateGame(Difficulty.EASY, WordPassMode.FIRE_WHEEL, 0))
            val running = awaitUntil { it.status == WordPassStatus.RUNNING }

            assertEquals(listOf("ala", "mar"), running.words.map { it.word })
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `answers are compared ignoring accents and case`() = runTest {
        val viewModel = runningGame(listOf(word("Árbol", Letter.A), word("barco", Letter.B)))

        viewModel.sendIntent(WordPassIntent.Response("arbol"))

        val state = viewModel.uiState.value
        assertEquals(WordPassStatus.RUNNING, state.status)
        assertEquals(5, state.points) // EASY 5 * ALEATORY 1.0
        assertEquals(listOf(true), state.results)
        assertEquals(1, state.correctAnswers)
        assertEquals(3, state.lives)
    }

    @Test
    fun `a wrong answer costs a life, records a miss and toasts the word`() = runTest {
        val viewModel = runningGame(listOf(word("árbol", Letter.A), word("barco", Letter.B)))

        viewModel.sendIntent(WordPassIntent.Response("avión"))

        val state = viewModel.uiState.value
        assertEquals(listOf(false), state.results)
        assertEquals(2, state.lives)
        assertEquals(0, state.points)
        assertTrue(awaitToast().endsWith("árbol"))
    }

    @Test
    fun `the last word finishes the round with the accuracy buff`() = runTest {
        val viewModel = runningGame(listOf(word("uno", Letter.U), word("dos", Letter.D)))

        viewModel.sendIntent(WordPassIntent.Response("uno"))
        viewModel.sendIntent(WordPassIntent.Response("dos"))

        val state = viewModel.uiState.value
        assertEquals(WordPassStatus.FINISHED, state.status)
        assertEquals(15, state.points) // 5 + 5 + 10 * (1 correct so far / 2 words)
        assertEquals(listOf(true, true), state.results)
    }
}
