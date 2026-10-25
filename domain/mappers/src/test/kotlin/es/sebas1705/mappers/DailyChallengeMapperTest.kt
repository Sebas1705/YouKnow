package es.sebas1705.mappers

import es.sebas1705.firestore.documents.DailyChallengeDocument
import es.sebas1705.firestore.documents.DailyChallengeQuestionDocument
import es.sebas1705.models.games.QuestionModel
import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.QuizType
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * The daily challenge document stores enums by name so that a renamed or reordered constant never
 * silently changes a published round; unknown names fall back instead of crashing every player.
 */
class DailyChallengeMapperTest {

    private val document = DailyChallengeQuestionDocument(
        question = "Who painted the Mona Lisa?",
        answers = listOf("Da Vinci", "Picasso", "Goya", "Dalí"),
        correctAnswer = "Da Vinci",
        category = "ART",
        difficulty = "MEDIUM",
        quizType = "MULTIPLE"
    )

    private val model = QuestionModel(
        question = "Is water wet?",
        answers = listOf("True", "False"),
        correctAnswer = "True",
        category = Category.SCIENCE_AND_NATURE,
        language = Languages.EN,
        difficulty = Difficulty.EASY,
        quizType = QuizType.BOOLEAN
    )

    @Test
    fun `document question maps enum names back to constants`() {
        val mapped = document.toQuestionModel()

        assertEquals(document.question, mapped.question)
        assertEquals(document.answers, mapped.answers)
        assertEquals(document.correctAnswer, mapped.correctAnswer)
        assertEquals(Category.ART, mapped.category)
        assertEquals(Difficulty.MEDIUM, mapped.difficulty)
        assertEquals(QuizType.MULTIPLE, mapped.quizType)
    }

    @Test
    fun `document question has no language of its own`() {
        assertEquals(Languages.ANY, document.toQuestionModel().language)
    }

    @Test
    fun `unknown enum names fall back instead of failing`() {
        val mapped = document.copy(category = "NOPE", difficulty = "", quizType = "ESSAY").toQuestionModel()

        assertEquals(Category.ANY, mapped.category)
        assertEquals(Difficulty.ANY, mapped.difficulty)
        assertEquals(QuizType.MULTIPLE, mapped.quizType)
    }

    @Test
    fun `model to document stores enum names`() {
        val mapped = model.toDailyChallengeQuestionDocument()

        assertEquals("SCIENCE_AND_NATURE", mapped.category)
        assertEquals("EASY", mapped.difficulty)
        assertEquals("BOOLEAN", mapped.quizType)
        assertEquals(model.question, mapped.question)
        assertEquals(model.answers, mapped.answers)
        assertEquals(model.correctAnswer, mapped.correctAnswer)
    }

    @Test
    fun `question list to document keeps date and order`() {
        val second = model.copy(question = "Second")

        val mapped = listOf(model, second).toDailyChallengeDocument("2026-10-25")

        assertEquals("2026-10-25", mapped.date)
        assertEquals(listOf("Is water wet?", "Second"), mapped.questions.map { it.question })
    }

    @Test
    fun `document to models round-trips everything except the language`() {
        val round = listOf(model).toDailyChallengeDocument("2026-10-25").toQuestionModels()

        assertEquals(listOf(model.copy(language = Languages.ANY)), round)
        assertEquals(emptyList<QuestionModel>(), DailyChallengeDocument().toQuestionModels())
    }
}
