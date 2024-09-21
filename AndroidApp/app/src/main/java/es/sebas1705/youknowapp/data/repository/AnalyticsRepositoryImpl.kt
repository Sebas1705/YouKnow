package es.sebas1705.youknowapp.data.repository

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import es.sebas1705.youknowapp.data.source.remote.analyticsManager.EventLog
import es.sebas1705.youknowapp.data.source.remote.analyticsManager.UserProperty
import es.sebas1705.youknowapp.domain.repository.AnalyticsRepository
import javax.inject.Inject

/**
 * Implementation of the AnalyticsRepository
 *
 * @param firebaseAnalytics the firebase analytics instance
 */
class AnalyticsRepositoryImpl @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
): AnalyticsRepository {

    /**
     * Log an event
     *
     * @param event the event to log
     * @param bundle the bundle to add to the event
     */
    override fun logEvent(event: EventLog, bundle: Bundle) {
        firebaseAnalytics.logEvent(event.name, bundle)
    }

    /**
     * Set a user property
     *
     * @param property the property to set
     * @param value the value of the property
     */
    override fun setUserProperty(property: UserProperty, value: String) {
        firebaseAnalytics.setUserProperty(property.name, value)
    }
}