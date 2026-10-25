package es.sebas1705.wordpassusescases.usescases

import es.sebas1705.mappers.toWordEntity
import es.sebas1705.models.games.WordModel
import es.sebas1705.room.repository.DatabaseRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class InsertWordPassListTest {

    private val repository: DatabaseRepository = mock()
    private val useCase = InsertWordPassList(repository)

    @Test
    fun `inserts every word as an entity and then reports success`() = runTest {
        val words = WordModel.defaultList(2).mapIndexed { i, w -> w.copy(word = "word$i") }
        val events = mutableListOf<String>()

        useCase(words, onLoading = { events += "loading" }, onSuccess = { events += "success" })

        words.forEach { verify(repository).insertOrReplace(it.toWordEntity()) }
        assertEquals(listOf("loading", "success"), events)
    }
}
