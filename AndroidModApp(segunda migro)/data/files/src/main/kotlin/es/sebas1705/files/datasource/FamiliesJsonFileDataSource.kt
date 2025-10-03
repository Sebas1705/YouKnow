package es.sebas1705.files.datasource

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.files.config.SettingsFL
import es.sebas1705.files.datasource.interfaces.FileDataSource
import es.sebas1705.files.json.FamiliesJson
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 * Class to represent the data source of the files
 *
 * @property context [Context]: Application context
 * @property logEventDataSource [LogEventDataSource]: Data source for logging events
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
class FamiliesJsonFileDataSource @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val logEventDataSource: LogEventDataSource
) : FileDataSource<FamiliesJson> (
    context,
    logEventDataSource,
    FamiliesJson.serializer(),
    SettingsFL.FAMILIES_DEFAULT_BD_JSON
)