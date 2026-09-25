package es.sebas1705.datastore.datasources

import es.sebas1705.datastore.SettingsPreferences
import es.sebas1705.datastore.config.DefaultValuesDS
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The defaults are written once. Writing them on every launch reset the user's settings, and with
 * them `firstTime`, which sent the app back to the guide on every start.
 */
class SettingsDefaultsTest {

    @Test
    fun `first launch writes the defaults`() {
        val written = SettingsPreferencesDataSource.withDefaults(SettingsPreferences.getDefaultInstance())

        assertTrue(written.defaultSet)
        assertEquals(DefaultValuesDS.FIRST_TIME, written.firstTime)
        assertEquals(DefaultValuesDS.MUSIC_VOLUME, written.musicVolume)
    }

    @Test
    fun `later launches keep what the user stored`() {
        val stored = SettingsPreferencesDataSource.withDefaults(SettingsPreferences.getDefaultInstance())
            .toBuilder().setFirstTime(true).setMusicVolume(0.1f).setLanguage(2).build()

        assertEquals(stored, SettingsPreferencesDataSource.withDefaults(stored))
    }
}
