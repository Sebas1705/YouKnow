package es.sebas1705.fillusescases.usescases


import android.util.Log
import es.sebas1705.files.repository.FileRepository
import es.sebas1705.mappers.toQuestionEntity
import es.sebas1705.room.repository.DatabaseRepository

/**
 * Use case to fill the database with default questions
 *
 * @param fileRepository [FileRepository]: Repository to get the default questions
 * @param databaseRepository [DatabaseRepository]: Repository to insert the questions
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class FillByDefaultQuestions(
    private val fileRepository: FileRepository,
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        onLoading: () -> Unit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val questions = fileRepository.readDefaultBDQuestions()
        Log.d("FillUsesCases", "invoke: $questions")
        questions.forEach { databaseRepository.insertOrReplace(it.toQuestionEntity()) }
        if(questions.isEmpty()) onError("Empty Questions")
        else onSuccess()
    }
}