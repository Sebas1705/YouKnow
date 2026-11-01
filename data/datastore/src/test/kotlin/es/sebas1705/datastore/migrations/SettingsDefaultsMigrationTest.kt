package es.sebas1705.datastore.migrations

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import es.sebas1705.datastore.SettingsPreferences
import es.sebas1705.datastore.config.DefaultValuesDS
import es.sebas1705.datastore.serializers.SettingsSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

/**
 * The defaults are written once, by a DataStore migration that runs before the first read. Writing
 * them on every launch reset the user's settings (and `firstTime`, which brought the guide back);
 * writing them fire-and-forget from `init` let the first read see an empty proto.
 */
class SettingsDefaultsMigrationTest {

    @get:Rule
    val tmp = TemporaryFolder()

    @Test
    fun `first launch writes the defaults`() {
        val written = SettingsDefaultsMigration.withDefaults(SettingsPreferences.getDefaultInstance())

        assertTrue(written.defaultSet)
        assertEquals(DefaultValuesDS.FIRST_TIME, written.firstTime)
        assertEquals(DefaultValuesDS.MUSIC_VOLUME, written.musicVolume)
        assertEquals(DefaultValuesDS.SOUND_VOLUME, written.soundVolume)
        assertEquals(DefaultValuesDS.APP_UI_CONTRAST.ordinal, written.contrast)
        assertEquals(DefaultValuesDS.GAME_LANGUAGE.ordinal, written.language)
    }

    @Test
    fun `later launches keep what the user stored`() {
        val stored = SettingsDefaultsMigration.withDefaults(SettingsPreferences.getDefaultInstance())
            .toBuilder().setFirstTime(true).setMusicVolume(0.1f).setLanguage(2).build()

        assertEquals(stored, SettingsDefaultsMigration.withDefaults(stored))
    }

    @Test
    fun `migration only runs while the defaults are missing`() = runBlocking {
        val empty = SettingsPreferences.getDefaultInstance()
        assertTrue(SettingsDefaultsMigration.shouldMigrate(empty))
        assertFalse(SettingsDefaultsMigration.shouldMigrate(SettingsDefaultsMigration.migrate(empty)))
    }

    @Test
    fun `very first read of a fresh store already sees the defaults`() = runBlocking {
        val file = File(tmp.root, "settings_prefs.pb")
        withStore(file) { store ->
            val first = store.data.first()
            assertTrue(first.defaultSet)
            assertEquals(DefaultValuesDS.MUSIC_VOLUME, first.musicVolume)
            assertEquals(DefaultValuesDS.SOUND_VOLUME, first.soundVolume)
        }
    }

    @Test
    fun `opening an existing store keeps the user's values`() = runBlocking {
        val file = File(tmp.root, "settings_prefs.pb")
        // What a previous launch left on disk: defaults written, then changed by the user.
        val stored = SettingsDefaultsMigration.withDefaults(SettingsPreferences.getDefaultInstance())
            .toBuilder().setFirstTime(true).setMusicVolume(0.25f).build()
        file.outputStream().use { stored.writeTo(it) }

        withStore(file) { store ->
            val reread = store.data.first()
            assertTrue(reread.firstTime)
            assertEquals(0.25f, reread.musicVolume)
        }
    }

    private suspend fun withStore(
        file: File,
        block: suspend (DataStore<SettingsPreferences>) -> Unit
    ) {
        val job = SupervisorJob()
        val scope = CoroutineScope(Dispatchers.IO + job)
        try {
            block(
                DataStoreFactory.create(
                    serializer = SettingsSerializer(),
                    migrations = listOf(SettingsDefaultsMigration),
                    scope = scope,
                    produceFile = { file }
                )
            )
        } finally {
            job.cancelAndJoin()
        }
    }
}
