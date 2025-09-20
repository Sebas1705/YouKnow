package es.sebas1705.quizusescases.usescases

import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.QuizType
import es.sebas1705.mappers.toQuestionModel
import es.sebas1705.models.games.QuestionModel
import es.sebas1705.room.repository.DatabaseRepository



/**
 * Use case to generate a list of questions
 *
 * @param databaseRepository [DatabaseRepository]: Repository to get questions
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class GenerateQuestionList(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        numberQuestions: Int,
        category: Category,
        difficulty: Difficulty,
        languages: Languages,
        quizType: QuizType,
        onLoading: () -> Unit = {},
        onSuccess: (List<QuestionModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val questions = databaseRepository.getQuestions(
            numberQuestions,
            category,
            languages,
            difficulty,
            quizType
        )
        if (questions.isEmpty())
            onError("No questions found")
        else if (questions.size < numberQuestions)
            onError("Not enough questions found")
        else
            onSuccess(questions.map { it.toQuestionModel() })
    }
}