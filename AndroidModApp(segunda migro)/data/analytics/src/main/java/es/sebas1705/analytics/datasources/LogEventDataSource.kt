package es.sebas1705.analytics.datasources

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import es.sebas1705.analytics.config.EventLog
import es.sebas1705.analytics.model.AnalyticsModel
import es.sebas1705.common.managers.ClassLogData
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Data source for logging events to Firebase Analytics.
 *
 * @property firebaseAnalytics [FirebaseAnalytics]: Instance of Firebase Analytics.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
class LogEventDataSource @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
) {

    fun logEvent(event: EventLog, bundle: Bundle) {
        firebaseAnalytics.logEvent(event.tag, bundle)
    }

    fun logError(clazz: KClass<*>, error: String) {
        firebaseAnalytics.logEvent(EventLog.Error.tag) {
            param("package", clazz.javaObjectType.packageName)
            param("class", clazz.simpleName ?: "Unknown")
            param("error", error)
        }
    }

    fun logError(classLogData: ClassLogData, error: String) = logError(classLogData::class, error)

    fun sendEvent(analyticsModel: AnalyticsModel) {
        firebaseAnalytics.logEvent(analyticsModel.title) {
            analyticsModel.analyticsString.forEach { (key, value) -> param(key, value) }
            analyticsModel.analyticsLong.forEach { (key, value) -> param(key, value) }
            analyticsModel.analyticsDouble.forEach { (key, value) -> param(key, value) }
            analyticsModel.analyticsBundle.forEach { (key, value) -> param(key, value) }
            analyticsModel.analyticsBundleArray.forEach { (key, value) -> param(key, value) }
        }
    }
}
