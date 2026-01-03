package es.sebas1705.repositories.abstracts

import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.common.utlis.extensions.types.logE
import kotlin.reflect.KClass

abstract class Repository(
    private val logEventDataSource: LogEventDataSource
) {
    protected fun loggerAction(clazz: KClass<*>, message: String) {
        logEventDataSource.logError(clazz, message)
        logE(message)
    }
}