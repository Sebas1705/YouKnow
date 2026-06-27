package es.sebas1705.analytics

data class AnalyticsUsesCases(
    val logEvent: LogEventUseCase,
    val setUserProperty: SetUserPropertyUseCase
)
