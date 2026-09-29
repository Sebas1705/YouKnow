package es.sebas1705.opendbusescases

import es.sebas1705.mappers.toQuestionEntity
import es.sebas1705.room.repository.DatabaseRepository

/**
 * Use case to grow the local question bank with a fresh batch fetched live from OpenTDB, on top
 * of (never replacing) the bundled default set — this is what "Reload db" cannot do, since it only
 * restores the local JSON.
 *
 * @property getTriviaTenQuestionsUseCase [GetTriviaTenQuestionsUseCase]: Fetches the raw batch.
 * @property databaseRepository [DatabaseRepository]: Where the mapped questions are stored.
 *
 * @since 1.3.1
 * @author Sebas1705 30/09/2026
 */
class DownloadQuestions(
    private val getTriviaTenQuestionsUseCase: GetTriviaTenQuestionsUseCase,
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        onLoading: () -> Unit,
        onSuccess: (Int) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        try {
            val response = getTriviaTenQuestionsUseCase()
            if (response.responseCode != 0 || response.questionOpendbDtos.isEmpty()) {
                onError("OpenTDB returned no questions (code ${response.responseCode})")
                return
            }
            response.questionOpendbDtos.forEach {
                databaseRepository.insertOrReplace(it.toQuestionEntity())
            }
            onSuccess(response.questionOpendbDtos.size)
        } catch (e: Exception) {
            onError(e.message ?: "Could not reach OpenTDB")
        }
    }
}
