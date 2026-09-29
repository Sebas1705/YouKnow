package es.sebas1705.mappers

import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.QuizType
import es.sebas1705.retrofit.opendb.dtos.QuestionOpendbDto
import es.sebas1705.room.entities.QuestionEntity
import java.net.URLDecoder

/** OpenTDB is asked for `url3986`-encoded text, so every string field needs decoding. */
private fun String.decodeOpendb(): String = URLDecoder.decode(this, "UTF-8")

/**
 * Maps a raw OpenTDB question to a Room [QuestionEntity]: text fields are URL-decoded, the answers
 * are combined and shuffled, and category/difficulty/type are matched back from the API's strings.
 * OpenTDB only serves English, so the language is always [Languages.EN].
 */
fun QuestionOpendbDto.toQuestionEntity() = QuestionEntity(
    question = question.decodeOpendb(),
    answers = (incorrectAnswers.map { it.decodeOpendb() } + correctAnswer.decodeOpendb()).shuffled(),
    correctAnswer = correctAnswer.decodeOpendb(),
    category = Category.getCategoryByOpendbName(category.decodeOpendb()),
    language = Languages.EN,
    difficulty = Difficulty.getDifficulty(difficulty.decodeOpendb()),
    quizType = QuizType.getType(type.decodeOpendb())
)
