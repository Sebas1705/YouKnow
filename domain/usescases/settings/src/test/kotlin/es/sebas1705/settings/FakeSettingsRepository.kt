package es.sebas1705.settings

import es.sebas1705.datastore.model.SettingsData
import es.sebas1705.repositories.interfaces.ISettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/** In-memory stand-in for the DataStore-backed repository. */
internal class FakeSettingsRepository(initial: SettingsData = DEFAULTS) : ISettingsRepository {
    val state = MutableStateFlow(initial)
    override fun read(): Flow<SettingsData> = state
    override suspend fun update(settingsData: SettingsData) {
        state.value = settingsData
    }

    companion object {
        val DEFAULTS = SettingsData(
            firstTime = false,
            musicVolume = 0.5f,
            soundVolume = 0.5f,
            appContrast = 0,
            language = 0,
            defaultSet = true
        )
    }
}
