package es.sebas1705.game.families.viewmodel

import kotlin.time.Duration.Companion.seconds
import android.app.Application
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.families.FamiliesMode
import es.sebas1705.common.games.families.FamiliesStatus
import es.sebas1705.familiesusescases.FamiliesUsesCases
import es.sebas1705.familiesusescases.usescases.GenerateFamilies
import es.sebas1705.familiesusescases.usescases.InsertFamiliesList
import es.sebas1705.game.GameTestSupport
import es.sebas1705.game.GameTestSupport.awaitToast
import es.sebas1705.game.GameTestSupport.settle
import es.sebas1705.game.awaitUntil
import es.sebas1705.room.entities.FamiliesEntity
import es.sebas1705.room.repository.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
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

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class FamiliesViewModelTest {

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

    private fun viewModel() = FamiliesViewModel(
        familiesUsesCases = FamiliesUsesCases(GenerateFamilies(database), InsertFamiliesList(database)),
        userUsesCases = GameTestSupport.userUsesCases(GameTestSupport.firestoreWithUser()),
        authUsesCases = GameTestSupport.authUsesCases(),
        settingUsesCases = GameTestSupport.settingUsesCases(),
        application = application
    )

    private fun family(odd: String, difficulty: Difficulty = Difficulty.MEDIUM) =
        FamiliesEntity(listOf("Dog", "Cat", odd, "Horse"), odd, Category.ANIMALS, Languages.EN, difficulty)

    private suspend fun runningGame(families: List<FamiliesEntity>, mode: FamiliesMode = FamiliesMode.ALEATORY): FamiliesViewModel {
        wheneverBlocking { database.getFamilies(eq(mode.numFamilies), any(), any(), any()) }.thenReturn(families)
        val viewModel = viewModel()
        viewModel.uiState.test(timeout = 30.seconds) {
            viewModel.sendIntent(FamiliesIntent.SelectMode(mode))
            awaitUntil { it.status == FamiliesStatus.RUNNING }
            cancelAndIgnoreRemainingEvents()
        }
        return viewModel
    }

    @Test
    fun `selecting CUSTOM only opens the custom form`() = runTest {
        val viewModel = viewModel()

        viewModel.sendIntent(FamiliesIntent.SelectMode(FamiliesMode.CUSTOM))

        assertEquals(FamiliesStatus.CUSTOM, viewModel.uiState.value.status)
        assertEquals(FamiliesMode.CUSTOM, viewModel.uiState.value.mode)
        verify(database, never()).getFamilies(any(), any(), any(), any())
    }

    @Test
    fun `selecting a preset mode loads its family count and shuffles the options`() = runTest {
        val families = listOf(family("Car"), family("Table"))
        val viewModel = runningGame(families)

        val state = viewModel.uiState.value
        assertEquals(FamiliesMode.ALEATORY, state.mode)
        assertEquals(listOf("Car", "Table"), state.families.map { it.correctAnswer })
        state.families.forEachIndexed { i, f -> assertEquals(families[i].answers.toSet(), f.answers.toSet()) }
        verify(database).getFamilies(FamiliesMode.ALEATORY.numFamilies, Category.ANY, Languages.ANY, Difficulty.ANY)
    }

    @Test
    fun `a correct answer scores difficulty times mode and a wrong one costs a life`() = runTest {
        val viewModel = runningGame(listOf(family("Car", Difficulty.HARD), family("Table"), family("Pen")), FamiliesMode.SURVIVAL)

        viewModel.sendIntent(FamiliesIntent.Response("Car"))
        assertEquals(22, viewModel.uiState.value.points) // HARD 15 * SURVIVAL 1.5 = 22.5 -> 22
        assertEquals(1, viewModel.uiState.value.correctAnswers)

        viewModel.sendIntent(FamiliesIntent.Response("Dog"))
        val state = viewModel.uiState.value
        assertEquals(FamiliesStatus.RUNNING, state.status)
        assertEquals(22, state.points)
        assertEquals(2, state.lives)
        assertEquals(2, state.actualFamily)
    }

    @Test
    fun `a game with no families toasts the error and stays in selection`() = runTest {
        wheneverBlocking { database.getFamilies(any(), any(), any(), any()) }.thenReturn(emptyList())
        val viewModel = viewModel()

        viewModel.sendIntent(FamiliesIntent.GenerateGame(Category.MUSIC, Difficulty.HARD, 4))

        assertEquals("No families found", awaitToast())
        settle()
        assertEquals(FamiliesStatus.SELECTION_MODE, viewModel.uiState.value.status)
        assertFalse(viewModel.uiState.value.isLoading)
    }
}
