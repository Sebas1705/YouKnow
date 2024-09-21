package es.sebas1705.youknowapp.domain.repository

import android.os.Bundle
import es.sebas1705.youknowapp.data.source.remote.analyticsManager.EventLog
import es.sebas1705.youknowapp.data.source.remote.analyticsManager.UserProperty

/**
 * Interface for manege the analytics of firebase
 */
interface AnalyticsRepository {

    /**
     * Log an event
     *
     * @param event the event to log
     * @param bundle the bundle to add to the event
     */
    fun logEvent(event: EventLog, bundle: Bundle)

    /**
     * Set a user property
     *
     * @param property the property to set
     * @param value the value of the property
     */
    fun setUserProperty(property: UserProperty, value: String)

}