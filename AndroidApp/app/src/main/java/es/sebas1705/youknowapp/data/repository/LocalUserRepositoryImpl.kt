package es.sebas1705.youknowapp.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import es.sebas1705.youknowapp.ui.Contrast
import es.sebas1705.youknowapp.data.source.local.localUserManager.DefaultValues
import es.sebas1705.youknowapp.data.source.local.localUserManager.PreferencesKeys
import es.sebas1705.youknowapp.data.source.local.localUserManager.dataStore
import es.sebas1705.youknowapp.domain.repository.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of the [LocalUserManager] interface
 *
 * @param context the context of the app
 */
class LocalUserRepositoryImpl @Inject constructor(
    private val context: Context
) : LocalUserRepository {

    /**
     * Save the first time the user opens the app
     */
    override suspend fun saveFirstTime() {
        context.dataStore.edit {
            it[PreferencesKeys.FIRST_TIME] = true
        }
    }

    /**
     * Read if the user has opened the app for the first time
     *
     * @return a flow with the value of the first time
     */
    override fun readFirstTime(): Flow<Boolean> {
        return context.dataStore.data.map{
            it[PreferencesKeys.FIRST_TIME] ?: DefaultValues.FIRST_TIME
        }
    }

    /**
     * Save the volume of the app
     *
     * @param volume the volume of the app ranging from 0.0 to 1.0
     */
    override suspend fun saveAppVolume(volume: Float) {
        require(volume in 0.0..1.0){
            "Volume must be between 0.0 and 1.0 (Volume: $volume)"
        }
        context.dataStore.edit {
            it[PreferencesKeys.APP_VOLUME] = volume
        }
    }

    /**
     * Read the volume of the app
     *
     * @return a flow with the volume of the app
     */
    override fun readAppVolume(): Flow<Float> {
        return context.dataStore.data.map{
            it[PreferencesKeys.APP_VOLUME] ?: DefaultValues.APP_VOLUME
        }
    }

    /**
     * Save the contrast of the app
     *
     * @param contrast the contrast of the app
     */
    override suspend fun saveAppContrast(contrast: Contrast) {
        context.dataStore.edit {
            it[PreferencesKeys.APP_CONTRAST] = contrast.value
        }
    }

    /**
     * Read the contrast of the app
     *
     * @return a flow with the contrast of the app ([Contrast])
     */
    override fun readAppContrast(): Flow<Contrast> {
        return context.dataStore.data.map{
            Contrast.entries.find { contrast ->
                contrast.value == (it[PreferencesKeys.APP_CONTRAST] ?: DefaultValues.APP_CONTRAST)
            } ?: Contrast.Low
        }
    }

}