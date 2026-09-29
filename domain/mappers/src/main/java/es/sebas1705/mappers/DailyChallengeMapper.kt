package es.sebas1705.mappers

import es.sebas1705.firestore.documents.DailyChallengeDocument
import es.sebas1705.firestore.documents.DailyChallengeQuestionDocument
import es.sebas1705.models.games.QuestionModel
import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.QuizType

/**
 * A [DailyChallengeDocument] carries no language of its own — the questions are shared verbatim
 * by every player regardless of device, same as they were fetched by whoever published the day.
 */
fun DailyChallengeQuestionDocument.toQuestionModel() = QuestionModel(
    question = question,
    answers = answers,
    correctAnswer = correctAnswer,
    category = runCatching { Category.valueOf(category) }.getOrDefault(Category.ANY),
    language = Languages.ANY,
    difficulty = runCatching { Difficulty.valueOf(difficulty) }.getOrDefault(Difficulty.ANY),
    quizType = runCatching { QuizType.valueOf(quizType) }.getOrDefault(QuizType.MULTIPLE)
)

fun QuestionModel.toDailyChallengeQuestionDocument() = DailyChallengeQuestionDocument(
    question = question,
    answers = answers,
    correctAnswer = correctAnswer,
    category = category.name,
    difficulty = difficulty.name,
    quizType = quizType.name
)

fun DailyChallengeDocument.toQuestionModels(): List<QuestionModel> = questions.map { it.toQuestionModel() }

fun List<QuestionModel>.toDailyChallengeDocument(date: String) = DailyChallengeDocument(
    date = date,
    questions = map { it.toDailyChallengeQuestionDocument() }
)
