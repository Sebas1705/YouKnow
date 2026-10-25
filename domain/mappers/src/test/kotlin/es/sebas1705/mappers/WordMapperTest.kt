package es.sebas1705.mappers

import es.sebas1705.files.json.WordJson
import es.sebas1705.models.games.WordModel
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.wordpass.Letter
import es.sebas1705.room.entities.WordEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class WordMapperTest {

    private val entity = WordEntity(
        word = "Árbol",
        definitions = listOf("Planta perenne", "Tiene tronco"),
        letter = Letter.A,
        language = Languages.ES,
        difficulty = Difficulty.EASY
    )

    @Test
    fun `entity to model copies every field`() {
        val model = entity.toWordModel()

        assertEquals(entity.word, model.word)
        assertEquals(entity.definitions, model.definitions)
        assertEquals(entity.letter, model.letter)
        assertEquals(entity.language, model.language)
        assertEquals(entity.difficulty, model.difficulty)
    }

    @Test
    fun `model to entity and back is lossless`() {
        val model = WordModel(
            word = "Zebra",
            definitions = listOf("Striped animal"),
            letter = Letter.Z,
            language = Languages.EN,
            difficulty = Difficulty.HARD
        )

        assertEquals(model, model.toWordEntity().toWordModel())
        assertEquals(entity, entity.toWordModel().toWordEntity())
    }

    @Test
    fun `bundled json uses enum ordinals`() {
        val json = WordJson(
            word = "Mesa",
            definitions = listOf("Mueble"),
            letter = Letter.M.ordinal,
            language = Languages.ES.ordinal,
            difficulty = Difficulty.MEDIUM.ordinal
        )

        val mapped = json.toWordEntity()

        assertEquals("Mesa", mapped.word)
        assertEquals(listOf("Mueble"), mapped.definitions)
        assertEquals(Letter.M, mapped.letter)
        assertEquals(Languages.ES, mapped.language)
        assertEquals(Difficulty.MEDIUM, mapped.difficulty)
    }
}
