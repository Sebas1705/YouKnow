package es.sebas1705.repositories.interfaces

import android.os.Bundle
import es.sebas1705.analytics.config.EventLog
import es.sebas1705.analytics.config.UserProperty
import es.sebas1705.analytics.model.AnalyticsModel
import es.sebas1705.common.managers.ClassLogData

interface IAnalyticsRepository {

    fun logEvent(event: EventLog, bundle: Bundle)

    fun logError(classLogData: ClassLogData, error: String)

    fun setUserProperty(property: UserProperty, value: String)

    fun sendEvent(analyticsModel: AnalyticsModel)

}
