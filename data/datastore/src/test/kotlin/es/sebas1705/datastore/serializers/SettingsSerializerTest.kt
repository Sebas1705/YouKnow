package es.sebas1705.datastore.serializers

import es.sebas1705.datastore.SettingsPreferences
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

/**
 * Round-trips the settings through the real protobuf runtime on the classpath, so a mismatch
 * between the protoc-generated code and the protobuf-javalite version resolved by Gradle fails
 * here instead of when the app first reads its settings.
 */
class SettingsSerializerTest {

    private val serializer = SettingsSerializer()

    @Test
    fun `settings survive a write and read`() = runBlocking {
        val settings = SettingsPreferences.newBuilder()
            .setFirstTime(true)
            .setMusicVolume(0.25f)
            .setSoundVolume(0.75f)
            .setContrast(2)
            .setLanguage(1)
            .setDefaultSet(true)
            .build()

        val bytes = ByteArrayOutputStream().also { serializer.writeTo(settings, it) }.toByteArray()
        val read = serializer.readFrom(ByteArrayInputStream(bytes))

        assertEquals(settings, read)
    }

    @Test
    fun `empty input reads as the default instance`() = runBlocking {
        val read = serializer.readFrom(ByteArrayInputStream(ByteArray(0)))

        assertEquals(serializer.defaultValue, read)
    }
}
