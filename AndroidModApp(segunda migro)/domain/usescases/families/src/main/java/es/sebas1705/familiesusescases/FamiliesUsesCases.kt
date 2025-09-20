package es.sebas1705.familiesusescases

import es.sebas1705.familiesusescases.usescases.GenerateFamilies
import es.sebas1705.familiesusescases.usescases.InsertFamiliesList



/**
 * Use cases for families
 *
 * @property generateFamilies [GenerateFamilies]: Use case to generate families
 * @property insertFamiliesList [InsertFamiliesList]: Use case to insert families list
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class FamiliesUsesCases(
    val generateFamilies: GenerateFamilies,
    val insertFamiliesList: InsertFamiliesList
)
