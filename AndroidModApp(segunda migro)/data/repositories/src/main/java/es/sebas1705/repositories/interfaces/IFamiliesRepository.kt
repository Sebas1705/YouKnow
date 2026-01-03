package es.sebas1705.repositories.interfaces

import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.files.json.FamiliesJson
import es.sebas1705.room.entities.FamiliesEntity

/**
 * Repository interface to manage families data
 *
 * @since 1.0.0
 * @author Sebas1705 30/09/2025
 */
interface IFamiliesRepository {

    /**
     * Fetch families data from a remote source.
     *
     * @return A [DataFlow] emitting the fetched [FamiliesJson].
     */
    fun chargeDefaultFamiliesFromLocal(): DataFlow<FamiliesJson>

    /**
     * Save families data to a local database.
     *
     * @param families The [FamiliesEntity] data to be saved.
     * @return A [DataFlow] emitting a [Boolean] indicating success or failure.
     */
    fun saveFamiliesToLocal(families: FamiliesEntity): DataFlow<Boolean>
}