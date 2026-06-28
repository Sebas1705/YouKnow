package es.sebas1705.familiesusescases.usescases

import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.mappers.toFamiliesModel
import es.sebas1705.models.games.FamiliesModel
import es.sebas1705.room.repository.DatabaseRepository



/**
 * Use case to generate families
 *
 * @param databaseRepository [DatabaseRepository]: Repository to get families
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class GenerateFamilies(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        numFamilies: Int,
        category: Category,
        difficulty: Difficulty,
        languages: Languages,
        onLoading: () -> Unit,
        onSuccess: (List<FamiliesModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val families = databaseRepository.getFamilies(
            numFamilies,
            category,
            languages,
            difficulty
        )
        if (families.isEmpty())
            onError("No families found")
        else if (families.size < numFamilies)
            onError("Not enough families found")
        else
            onSuccess(families.map { it.toFamiliesModel() })
    }
}