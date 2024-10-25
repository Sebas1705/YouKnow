package es.sebas1705.youknow.data.local.datastore.repository

import es.sebas1705.youknow.presentation.ui.classes.ThemeContrast
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface to write and read data from the preferences
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
interface DatastoreRepository {

    /**
     * Save if the user has opened the app for the first time
     */
    suspend fun saveFirstTime()

    /**
     * Read if the user has opened the app for the first time
     *
     * @return a [Flow] with [Boolean] value of the first time
     *
     * @see Flow
     */
    fun readFirstTime(): Flow<Boolean>

    /**
     * Save the volume of the app
     *
     * @param volume [Float]: the volume of the app
     *
     * @see Float
     */
    suspend fun saveAppVolume(volume: Float)

    /**
     * Read the volume of the app
     *
     * @return a [Flow] with the [Float] volume of the app
     *
     * @see Flow
     * @see Float
     */
    fun readAppVolume(): Flow<Float>

    /**
     * Save the contrast of the app
     *
     * @param themeContrast [ThemeContrast]: the contrast of the app
     *
     * @see ThemeContrast
     */
    suspend fun saveAppContrast(themeContrast: ThemeContrast)

    /**
     * Read the contrast of the app
     *
     * @return a [Flow] with the [ThemeContrast] contrast of the app
     *
     * @see Flow
     * @see ThemeContrast
     */
    fun readAppContrast(): Flow<ThemeContrast>

}