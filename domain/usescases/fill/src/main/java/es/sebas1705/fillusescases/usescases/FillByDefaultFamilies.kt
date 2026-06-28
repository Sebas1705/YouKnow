package es.sebas1705.fillusescases.usescases


import android.util.Log
import es.sebas1705.files.repository.FileRepository
import es.sebas1705.mappers.toFamiliesEntity
import es.sebas1705.room.repository.DatabaseRepository

/**
 * Use case to fill the database with default families
 *
 * @param fileRepository [FileRepository]: Repository to get the default families
 * @param databaseRepository [DatabaseRepository]: Repository to insert the families
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class FillByDefaultFamilies(
    private val fileRepository: FileRepository,
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        onLoading: () -> Unit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val families = fileRepository.readDefaultBDFamilies()
        Log.d("FillUsesCases", "invoke: $families")
        families.forEach { databaseRepository.insertOrReplace(it.toFamiliesEntity()) }
        if(families.isEmpty()) onError("Empty Families")
        else onSuccess()
    }
}