package es.sebas1705.datastore.migrations

import androidx.datastore.core.DataMigration
import es.sebas1705.datastore.SettingsPreferences
import es.sebas1705.datastore.config.DefaultValuesDS

/**
 * Writes the default settings the first time the store is opened.
 *
 * DataStore runs migrations before it serves the first value, so no reader can ever observe the
 * un-initialised proto (volumes at 0, `defaultSet = false`). The previous approach launched the
 * write from the data source's `init` in a detached `CoroutineScope(Dispatchers.IO)`, which raced
 * with the first read.
 *
 * Once [SettingsPreferences.getDefaultSet] is true the migration is skipped, so the user's own
 * values (including `firstTime`) are never overwritten.
 *
 * @since 1.4.3
 * @author Sebas1705 29/10/2026
 */
object SettingsDefaultsMigration : DataMigration<SettingsPreferences> {

    override suspend fun shouldMigrate(currentData: SettingsPreferences): Boolean =
        !currentData.defaultSet

    override suspend fun migrate(currentData: SettingsPreferences): SettingsPreferences =
        withDefaults(currentData)

    override suspend fun cleanUp() = Unit

    /** [current] unchanged if the defaults were already written, else the defaults. */
    fun withDefaults(current: SettingsPreferences): SettingsPreferences =
        if (current.defaultSet) current
        else current.toBuilder()
            .setContrast(DefaultValuesDS.APP_UI_CONTRAST.ordinal)
            .setFirstTime(DefaultValuesDS.FIRST_TIME)
            .setMusicVolume(DefaultValuesDS.MUSIC_VOLUME)
            .setSoundVolume(DefaultValuesDS.SOUND_VOLUME)
            .setLanguage(DefaultValuesDS.GAME_LANGUAGE.ordinal)
            .setDefaultSet(true)
            .build()
}
