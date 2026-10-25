package es.sebas1705.mappers

import es.sebas1705.files.json.FamiliesJson
import es.sebas1705.models.games.FamiliesModel
import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.room.entities.FamiliesEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class FamiliesMapperTest {

    private val entity = FamiliesEntity(
        answers = listOf("Dog", "Cat", "Car", "Horse"),
        correctAnswer = "Car",
        category = Category.ANIMALS,
        language = Languages.EN,
        difficulty = Difficulty.EASY
    )

    @Test
    fun `entity to model copies every field`() {
        val model = entity.toFamiliesModel()

        assertEquals(entity.answers, model.answers)
        assertEquals(entity.correctAnswer, model.correctAnswer)
        assertEquals(entity.category, model.category)
        assertEquals(entity.language, model.language)
        assertEquals(entity.difficulty, model.difficulty)
    }

    @Test
    fun `model to entity and back is lossless`() {
        val model = FamiliesModel(
            answers = listOf("Rojo", "Verde", "Lunes", "Azul"),
            correctAnswer = "Lunes",
            category = Category.GENERAL_KNOWLEDGE,
            language = Languages.ES,
            difficulty = Difficulty.MEDIUM
        )

        assertEquals(model, model.toFamiliesEntity().toFamiliesModel())
        assertEquals(entity, entity.toFamiliesModel().toFamiliesEntity())
    }

    @Test
    fun `bundled json uses enum ordinals`() {
        val json = FamiliesJson(
            answers = listOf("A", "B", "C", "D"),
            correctAnswer = "D",
            category = Category.MUSIC.ordinal,
            language = Languages.ES.ordinal,
            difficulty = Difficulty.HARD.ordinal
        )

        val mapped = json.toFamiliesEntity()

        assertEquals(listOf("A", "B", "C", "D"), mapped.answers)
        assertEquals("D", mapped.correctAnswer)
        assertEquals(Category.MUSIC, mapped.category)
        assertEquals(Languages.ES, mapped.language)
        assertEquals(Difficulty.HARD, mapped.difficulty)
    }
}
