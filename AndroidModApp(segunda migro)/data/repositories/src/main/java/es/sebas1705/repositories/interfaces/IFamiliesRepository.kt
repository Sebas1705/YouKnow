package es.sebas1705.repositories.interfaces

/**
 * Repository interface to manage families data
 *
 * @since 1.0.0
 * @author Sebas1705 30/09/2025
 */
interface IFamiliesRepository {

    // Local:

    fun chargeDefaultFamiliesFromLocal(): Flow<Sta>
}