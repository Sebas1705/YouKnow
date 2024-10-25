package es.sebas1705.youknow.data.local.datastore.repository

import es.sebas1705.youknow.data.model.ResponseState
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
     *
     * @return a [Flow] with [ResponseState] of the operation
     */
    fun saveFirstTime(): Flow<ResponseState<Nothing>>

    /**
     * Read if the user has opened the app for the first time
     *
     * @return a [Flow] with [ResponseState] of the operation and a [Boolean] with the result
     *
     */
    fun readFirstTime(): Flow<ResponseState<Boolean>>

    /**
     * Save the volume of the app
     *
     * @param volume [Float]: the volume of the app
     *
     * @return a [Flow] with [ResponseState] of the operation
     */
    fun saveAppVolume(volume: Float): Flow<ResponseState<Nothing>>

    /**
     * Read the volume of the app
     *
     * @return a [Flow] with [ResponseState] of the operation and a [Float] with the volume
     */
    fun readAppVolume(): Flow<ResponseState<Float>>

    /**
     * Save the contrast of the app
     *
     * @param themeContrast [ThemeContrast]: the contrast of the app
     *
     * @see ThemeContrast
     */
    fun saveAppContrast(themeContrast: ThemeContrast): Flow<ResponseState<Nothing>>

    /**
     * Read the contrast of the app
     *
     * @return a [Flow] with [ResponseState] of the operation and a [ThemeContrast] with the contrast
     */
    fun readAppContrast(): Flow<ResponseState<ThemeContrast>>

}