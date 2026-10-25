package es.sebas1705.mappers

import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.QuizType
import es.sebas1705.retrofit.opendb.dtos.QuestionOpendbDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * OpenTDB is asked for `url3986`-encoded text, so every string field arrives percent-encoded and
 * the category/difficulty/type come back as the API's own names, not ours.
 */
class OpendbMapperTest {

    private fun dto(
        question: String = "What%20is%202%2B2%3F",
        correctAnswer: String = "4",
        incorrectAnswers: List<String> = listOf("3", "5", "22"),
        category: String = "Science%3A%20Mathematics",
        difficulty: String = "easy",
        type: String = "multiple",
    ) = QuestionOpendbDto(
        category = category,
        correctAnswer = correctAnswer,
        difficulty = difficulty,
        incorrectAnswers = incorrectAnswers,
        question = question,
        type = type
    )

    @Test
    fun `question text is url-decoded`() {
        assertEquals("What is 2+2?", dto().toQuestionEntity().question)
    }

    @Test
    fun `answers are url-decoded and the correct one stays matchable`() {
        val entity = dto(correctAnswer = "Caf%C3%A9", incorrectAnswers = listOf("T%C3%A9")).toQuestionEntity()

        assertEquals("Café", entity.correctAnswer)
        assertEquals(listOf("Café", "Té"), entity.answers.sorted())
        assertTrue(entity.correctAnswer in entity.answers)
    }

    @Test
    fun `answers keep every option exactly once`() {
        val entity = dto().toQuestionEntity()

        assertEquals(4, entity.answers.size)
        assertEquals(setOf("3", "4", "5", "22"), entity.answers.toSet())
    }

    @Test
    fun `category is matched back from the OpenTDB name`() {
        assertEquals(Category.MATHEMATICS, dto().toQuestionEntity().category)
        assertEquals(
            Category.ANIME_AND_MANGA,
            dto(category = "Entertainment%3A%20Japanese%20Anime%20%26%20Manga").toQuestionEntity().category
        )
        assertEquals(Category.GENERAL_KNOWLEDGE, dto(category = "General%20Knowledge").toQuestionEntity().category)
    }

    @Test
    fun `unknown category falls back to ANY`() {
        assertEquals(Category.ANY, dto(category = "Something%20New").toQuestionEntity().category)
    }

    @Test
    fun `difficulty strings map to the enum`() {
        assertEquals(Difficulty.EASY, dto(difficulty = "easy").toQuestionEntity().difficulty)
        assertEquals(Difficulty.MEDIUM, dto(difficulty = "medium").toQuestionEntity().difficulty)
        assertEquals(Difficulty.HARD, dto(difficulty = "hard").toQuestionEntity().difficulty)
    }

    @Test
    fun `unknown difficulty falls back to ANY`() {
        assertEquals(Difficulty.ANY, dto(difficulty = "impossible").toQuestionEntity().difficulty)
    }

    @Test
    fun `type strings map to the enum`() {
        assertEquals(QuizType.BOOLEAN, dto(type = "boolean").toQuestionEntity().quizType)
        assertEquals(QuizType.MULTIPLE, dto(type = "multiple").toQuestionEntity().quizType)
    }

    @Test
    fun `unknown type falls back to ANY`() {
        assertEquals(QuizType.ANY, dto(type = "essay").toQuestionEntity().quizType)
    }

    @Test
    fun `language is always English because OpenTDB only serves English`() {
        assertEquals(Languages.EN, dto().toQuestionEntity().language)
    }
}
