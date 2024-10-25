package es.sebas1705.youknow.domain.usecases
/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import android.os.Bundle
import es.sebas1705.youknow.data.firebase.analytics.config.EventLog
import es.sebas1705.youknow.data.firebase.analytics.config.UserProperty
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepository

/**
 * Use case to log an event
 *
 * @param analyticsRepository [AnalyticsRepository]: repository to log the event
 *
 * @see AnalyticsRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class LogEvent(
    private val analyticsRepository: AnalyticsRepository
) {
    operator fun invoke(event: EventLog, bundle: Bundle) {
        analyticsRepository.logEvent(event, bundle)
    }
}

/**
 * Use case to set a user property
 *
 * @param analyticsRepository [AnalyticsRepository]: repository to set the user property
 *
 * @see AnalyticsRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class SetUserProperty(
    private val analyticsRepository: AnalyticsRepository
) {
    operator fun invoke(userProperty: UserProperty, value: String) {
        analyticsRepository.setUserProperty(userProperty, value)
    }
}


/**
 * Data class to group the analytics use cases
 *
 * @param logEvent [LogEvent]: use case to log an event
 * @param setUserProperty [SetUserProperty]: use case to set a user property
 *
 * @see LogEvent
 * @see SetUserProperty
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class AnalyticsUsesCases(
    val logEvent: LogEvent,
    val setUserProperty: SetUserProperty
)