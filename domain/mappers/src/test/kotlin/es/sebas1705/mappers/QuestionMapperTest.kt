package es.sebas1705.mappers

import es.sebas1705.files.json.QuestionJson
import es.sebas1705.models.games.QuestionModel
import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.QuizType
import es.sebas1705.room.entities.QuestionEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class QuestionMapperTest {

    private val entity = QuestionEntity(
        question = "Capital of Spain?",
        answers = listOf("Madrid", "Lisbon", "Paris", "Rome"),
        correctAnswer = "Madrid",
        category = Category.GEOGRAPHY,
        language = Languages.EN,
        difficulty = Difficulty.EASY,
        quizType = QuizType.MULTIPLE
    )

    @Test
    fun `entity to model copies every field`() {
        val model = entity.toQuestionModel()

        assertEquals(entity.question, model.question)
        assertEquals(entity.answers, model.answers)
        assertEquals(entity.correctAnswer, model.correctAnswer)
        assertEquals(entity.category, model.category)
        assertEquals(entity.language, model.language)
        assertEquals(entity.difficulty, model.difficulty)
        assertEquals(entity.quizType, model.quizType)
    }

    @Test
    fun `model to entity and back is lossless`() {
        val model = QuestionModel(
            question = "¿Verdadero o falso?",
            answers = listOf("Verdadero", "Falso"),
            correctAnswer = "Falso",
            category = Category.HISTORY,
            language = Languages.ES,
            difficulty = Difficulty.HARD,
            quizType = QuizType.BOOLEAN
        )

        assertEquals(model, model.toQuestionEntity().toQuestionModel())
        assertEquals(entity, entity.toQuestionModel().toQuestionEntity())
    }

    @Test
    fun `bundled json uses enum ordinals`() {
        val json = QuestionJson(
            question = "Q",
            answers = listOf("A", "B"),
            correctAnswer = "B",
            category = Category.SPORTS.ordinal,
            language = Languages.ES.ordinal,
            difficulty = Difficulty.MEDIUM.ordinal,
            quizType = QuizType.BOOLEAN.ordinal
        )

        val mapped = json.toQuestionEntity()

        assertEquals("Q", mapped.question)
        assertEquals(listOf("A", "B"), mapped.answers)
        assertEquals("B", mapped.correctAnswer)
        assertEquals(Category.SPORTS, mapped.category)
        assertEquals(Languages.ES, mapped.language)
        assertEquals(Difficulty.MEDIUM, mapped.difficulty)
        assertEquals(QuizType.BOOLEAN, mapped.quizType)
    }

    @Test
    fun `json ordinal zero maps to the ANY values`() {
        val mapped = QuestionJson("Q", listOf("A"), "A", 0, 0, 0, 0).toQuestionEntity()

        assertEquals(Category.ANY, mapped.category)
        assertEquals(Languages.ANY, mapped.language)
        assertEquals(Difficulty.ANY, mapped.difficulty)
        assertEquals(QuizType.ANY, mapped.quizType)
    }
}
