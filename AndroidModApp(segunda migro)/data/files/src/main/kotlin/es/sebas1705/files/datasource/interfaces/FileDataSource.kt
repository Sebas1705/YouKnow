package es.sebas1705.files.datasource.interfaces

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.files.json.interfaces.FileJson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

/**
 * Open class to represent the data source of the files
 *
 * @param T [FileJson]: Type of the file json
 * @property context [Context]: Application context
 * @property logEventDataSource [LogEventDataSource]: Data source for logging events
 * @property serializer [KSerializer<T>]: Serializer for the file json
 * @property fileName [String]: Name of the file to read
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
open class FileDataSource<T : FileJson> (
    @param:ApplicationContext private val context: Context,
    private val logEventDataSource: LogEventDataSource,
    private val serializer: KSerializer<T>,
    private val fileName: String
) : ClassLogData() {

    suspend fun readJsonFile(): T? = try {
        val json = context.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }
        Json.decodeFromString(serializer, json)
    } catch (ex: Exception) {
        logEventDataSource.logError(this, ex.message.toString())
        null
    }
}