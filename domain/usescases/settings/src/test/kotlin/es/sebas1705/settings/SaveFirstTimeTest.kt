package es.sebas1705.settings

import es.sebas1705.datastore.model.SettingsData
import es.sebas1705.repositories.interfaces.ISettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * `firstTime` starts false (DefaultValuesDS.FIRST_TIME) and MainViewModel shows the guide while it
 * stays false, so finishing the guide must store true. Storing false (as after the migration) sent
 * every launch back to the guide.
 */
class SaveFirstTimeTest {

    private class FakeSettingsRepository(initial: SettingsData) : ISettingsRepository {
        val state = MutableStateFlow(initial)
        override fun read(): Flow<SettingsData> = state
        override suspend fun update(settingsData: SettingsData) {
            state.value = settingsData
        }
    }

    private val defaults = SettingsData(
        firstTime = false,
        musicVolume = 0.5f,
        soundVolume = 0.5f,
        appContrast = 0,
        language = 0,
        defaultSet = true
    )

    @Test
    fun `finishing the guide marks it as seen and keeps the other settings`() = runBlocking {
        val repository = FakeSettingsRepository(defaults)

        SaveFirstTime(repository)()

        assertTrue(repository.state.value.firstTime)
        assertEquals(defaults.copy(firstTime = true), repository.state.value)
    }
}
