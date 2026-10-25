package es.sebas1705.familiesusescases.usescases

import es.sebas1705.models.games.FamiliesModel
import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.room.entities.FamiliesEntity
import es.sebas1705.room.repository.DatabaseRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GenerateFamiliesTest {

    private val repository: DatabaseRepository = mock()
    private val useCase = GenerateFamilies(repository)

    private fun entity(odd: String) =
        FamiliesEntity(listOf("Dog", "Cat", odd, "Horse"), odd, Category.ANIMALS, Languages.EN, Difficulty.EASY)

    @Test
    fun `asks the repository with the requested filters and maps the result`() = runTest {
        whenever(repository.getFamilies(2, Category.ANIMALS, Languages.EN, Difficulty.EASY))
            .thenReturn(listOf(entity("Car"), entity("Table")))
        var result: List<FamiliesModel>? = null

        useCase(2, Category.ANIMALS, Difficulty.EASY, Languages.EN,
            onLoading = {}, onSuccess = { result = it }, onError = { error("unexpected $it") })

        assertEquals(listOf("Car", "Table"), result?.map { it.correctAnswer })
        verify(repository).getFamilies(2, Category.ANIMALS, Languages.EN, Difficulty.EASY)
    }

    @Test
    fun `fewer families than asked for is still a game`() = runTest {
        whenever(repository.getFamilies(10, Category.ANY, Languages.ANY, Difficulty.ANY)).thenReturn(listOf(entity("Car")))
        var result: List<FamiliesModel>? = null

        useCase(10, Category.ANY, Difficulty.ANY, Languages.ANY,
            onLoading = {}, onSuccess = { result = it }, onError = { error("unexpected $it") })

        assertEquals(1, result?.size)
    }

    @Test
    fun `no families reports an error`() = runTest {
        whenever(repository.getFamilies(4, Category.MUSIC, Languages.ES, Difficulty.HARD)).thenReturn(emptyList())
        var result: List<FamiliesModel>? = null
        var error: String? = null

        useCase(4, Category.MUSIC, Difficulty.HARD, Languages.ES,
            onLoading = {}, onSuccess = { result = it }, onError = { error = it })

        assertNull(result)
        assertEquals("No families found", error)
    }
}
