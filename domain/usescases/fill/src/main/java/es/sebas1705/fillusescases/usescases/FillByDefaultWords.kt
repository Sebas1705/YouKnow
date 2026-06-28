package es.sebas1705.fillusescases.usescases


import android.util.Log
import es.sebas1705.files.repository.FileRepository
import es.sebas1705.mappers.toWordEntity
import es.sebas1705.room.repository.DatabaseRepository

/**
 * Use case to fill the database with default words
 *
 * @param fileRepository [FileRepository]: Repository to get the default words
 * @param databaseRepository [DatabaseRepository]: Repository to insert the words
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class FillByDefaultWords(
    private val fileRepository: FileRepository,
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        onLoading: () -> Unit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val words = fileRepository.readDefaultBDWords()
        Log.d("FillUsesCases", "invoke: $words")
        words.forEach { databaseRepository.insertOrReplace(it.toWordEntity()) }
        if(words.isEmpty()) onError("Empty words")
        else onSuccess()
    }
}