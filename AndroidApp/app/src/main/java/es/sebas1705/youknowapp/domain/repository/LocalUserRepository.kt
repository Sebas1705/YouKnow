package es.sebas1705.youknowapp.domain.repository

import es.sebas1705.youknowapp.ui.Contrast
import kotlinx.coroutines.flow.Flow

/**
 * Interface to manage the local user data
 */
interface LocalUserRepository {

    /**
     * Save the first time the user opens the app
     */
    suspend fun saveFirstTime()

    /**
     * Read if the user has opened the app for the first time
     *
     * @return a flow with the value of the first time
     */
    fun readFirstTime(): Flow<Boolean>

    /**
     * Save the volume of the app
     *
     * @param volume the volume of the app
     */
    suspend fun saveAppVolume(volume: Float)

    /**
     * Read the volume of the app
     *
     * @return a flow with the volume of the app
     */
    fun readAppVolume(): Flow<Float>

    /**
     * Save the contrast of the app
     *
     * @param contrast the contrast of the app
     */
    suspend fun saveAppContrast(contrast: Contrast)

    /**
     * Read the contrast of the app
     *
     * @return a flow with the contrast of the app ([Contrast])
     */
    fun readAppContrast(): Flow<Contrast>

}