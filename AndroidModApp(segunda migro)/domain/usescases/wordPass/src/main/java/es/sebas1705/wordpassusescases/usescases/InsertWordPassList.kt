package es.sebas1705.wordpassusescases.usescases


import es.sebas1705.mappers.toWordEntity
import es.sebas1705.models.games.WordModel
import es.sebas1705.room.repository.DatabaseRepository

/**
 * Use case to insert a list of words
 *
 * @param databaseRepository [DatabaseRepository]: Repository to insert words
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class InsertWordPassList(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        families: List<WordModel>,
        onLoading: () -> Unit,
        onSuccess: () -> Unit
    ) {
        onLoading()
        families.forEach {
            databaseRepository.insertOrReplace(it.toWordEntity())
        }
        onSuccess()
    }
}