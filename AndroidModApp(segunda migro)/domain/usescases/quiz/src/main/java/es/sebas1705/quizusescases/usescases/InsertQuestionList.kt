package es.sebas1705.quizusescases.usescases


import es.sebas1705.mappers.toQuestionEntity
import es.sebas1705.models.games.QuestionModel
import es.sebas1705.room.repository.DatabaseRepository

/**
 * Use case to insert a list of questions
 *
 * @param databaseRepository [DatabaseRepository]: Repository to insert questions
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class InsertQuestionList(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        questions: List<QuestionModel>,
        onLoading: () -> Unit = {},
        onSuccess: () -> Unit
    ) {
        onLoading()
        questions.forEach {
            databaseRepository.insertOrReplace(it.toQuestionEntity())
        }
        onSuccess()
    }
}