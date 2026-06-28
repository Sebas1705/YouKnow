package es.sebas1705.familiesusescases.usescases


import es.sebas1705.mappers.toFamiliesEntity
import es.sebas1705.models.games.FamiliesModel
import es.sebas1705.room.repository.DatabaseRepository

/**
 * Use case to insert families list
 *
 * @param databaseRepository [DatabaseRepository]: Repository to insert families
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class InsertFamiliesList(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        families: List<FamiliesModel>,
        onLoading: () -> Unit,
        onSuccess: () -> Unit
    ) {
        onLoading()
        families.forEach {
            databaseRepository.insertOrReplace(it.toFamiliesEntity())
        }
        onSuccess()
    }
}