package es.sebas1705.files.datasource.interfaces

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.common.managers.ClassLogData
import es.sebas1705.files.json.interfaces.FileJson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

open class FileDataSource<T : FileJson> (
    @param:ApplicationContext private val context: Context,
    private val logEventDataSource: LogEventDataSource,
    private val serializer: KSerializer<T>,
    private val fileName: String
) : ClassLogData() {

    /**
     * Reads the asset [fileName], a JSON array of [T].
     *
     * @return the decoded items, or an empty list if the asset is missing or malformed (the error
     * is logged to analytics).
     */
    suspend fun readJsonFile(): List<T> = try {
        val json = context.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }
        decode(json, serializer)
    } catch (ex: Exception) {
        logEventDataSource.logError(this, ex.message.toString())
        emptyList()
    }

    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        /**
         * Decodes a JSON array of [T]. The default data files are arrays; decoding them as a
         * single object failed and left the games without data.
         */
        fun <T> decode(text: String, serializer: KSerializer<T>): List<T> =
            json.decodeFromString(ListSerializer(serializer), text)
    }
}
