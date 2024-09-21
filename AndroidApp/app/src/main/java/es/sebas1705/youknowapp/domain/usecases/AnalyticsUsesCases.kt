package es.sebas1705.youknowapp.domain.usecases

import android.os.Bundle
import es.sebas1705.youknowapp.data.source.remote.analyticsManager.EventLog
import es.sebas1705.youknowapp.data.source.remote.analyticsManager.UserProperty
import es.sebas1705.youknowapp.domain.repository.AnalyticsRepository


class LogEvent(
    private val analyticsRepository: AnalyticsRepository
) {
    operator fun invoke(event: EventLog, bundle: Bundle) {
        analyticsRepository.logEvent(event, bundle)
    }
}

class SetUserProperty(
    private val analyticsRepository: AnalyticsRepository
) {
    operator fun invoke(userProperty: UserProperty, value: String) {
        analyticsRepository.setUserProperty(userProperty, value)
    }
}



data class AnalyticsUsesCases(
    val logEvent: LogEvent,
    val setUserProperty: SetUserProperty
)