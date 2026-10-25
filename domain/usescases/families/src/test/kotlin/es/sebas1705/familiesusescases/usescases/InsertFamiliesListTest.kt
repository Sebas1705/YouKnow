package es.sebas1705.familiesusescases.usescases

import es.sebas1705.mappers.toFamiliesEntity
import es.sebas1705.models.games.FamiliesModel
import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.room.repository.DatabaseRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class InsertFamiliesListTest {

    private val repository: DatabaseRepository = mock()
    private val useCase = InsertFamiliesList(repository)

    @Test
    fun `inserts every family as an entity and then reports success`() = runTest {
        val families = listOf("Car", "Table").map {
            FamiliesModel(listOf("Dog", "Cat", it), it, Category.ANIMALS, Languages.EN, Difficulty.EASY)
        }
        val events = mutableListOf<String>()

        useCase(families, onLoading = { events += "loading" }, onSuccess = { events += "success" })

        families.forEach { verify(repository).insertOrReplace(it.toFamiliesEntity()) }
        assertEquals(listOf("loading", "success"), events)
    }
}
