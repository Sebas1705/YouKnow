package es.sebas1705.mappers

import es.sebas1705.files.json.QuestionJson
import es.sebas1705.models.games.QuestionModel
import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.QuizType
import es.sebas1705.room.entities.QuestionEntity

fun QuestionEntity.toQuestionModel() = QuestionModel(
    question = question,
    answers = answers,
    correctAnswer = correctAnswer,
    category = category,
    language = language,
    difficulty = difficulty,
    quizType = quizType
)

fun QuestionModel.toQuestionEntity() = QuestionEntity(
    question = question,
    answers = answers,
    correctAnswer = correctAnswer,
    category = category,
    language = language,
    difficulty = difficulty,
    quizType = quizType
)

fun QuestionJson.toQuestionEntity() = QuestionEntity(
    question = question,
    answers = answers,
    correctAnswer = correctAnswer,
    category = Category.entries[category],
    language = Languages.entries[language],
    difficulty = Difficulty.entries[difficulty],
    quizType = QuizType.entries[quizType]
)
