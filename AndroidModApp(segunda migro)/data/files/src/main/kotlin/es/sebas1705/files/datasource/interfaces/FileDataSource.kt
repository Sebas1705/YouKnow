package es.sebas1705.files.datasource.interfaces

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.common.managers.ClassLogData
import es.sebas1705.files.json.interfaces.FileJson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

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
