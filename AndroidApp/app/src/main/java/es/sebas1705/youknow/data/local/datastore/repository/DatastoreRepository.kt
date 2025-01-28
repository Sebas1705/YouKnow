package es.sebas1705.youknow.data.local.datastore.repository

import es.sebas1705.youknow.core.classes.enums.games.Languages
import es.sebas1705.youknow.core.classes.enums.theme.ThemeContrast
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface to write and read data from the preferences
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
interface DatastoreRepository {

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
     * Save the volume of the music
     *
     * @param volume the volume of the app
     */
    suspend fun saveMusicVolume(volume: Float)

    /**
     * Read the volume of the music
     *
     * @return a flow with the volume of the app
     */
    fun readMusicVolume(): Flow<Float>

    /**
     * Save the volume of the sound
     *
     * @param volume the volume of the app
     */
    suspend fun saveSoundVolume(volume: Float)

    /**
     * Read the volume of the sound
     *
     * @return a flow with the volume of the app
     */
    fun readSoundVolume(): Flow<Float>

    /**
     * Save the contrast of the app
     *
     * @param contrast the contrast of the app
     */
    suspend fun saveAppContrast(contrast: ThemeContrast)

    /**
     * Read the contrast of the app
     *
     * @return a flow with the contrast of the app ([ThemeContrast])
     */
    fun readAppContrast(): Flow<ThemeContrast>

    /**
     * Save the game language
     *
     * @param language the language of the game
     */
    suspend fun saveGameLanguage(language: Languages)

    /**
     * Read the game language
     *
     * @return a flow with the language of the game ([Languages])
     */
    fun readGameLanguage(): Flow<Languages>


}