package es.sebas1705.analytics.usescases
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
import android.util.Log
import es.sebas1705.analytics.config.EventLog
import es.sebas1705.analytics.repository.AnalyticsRepository

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
        Log.i("LogEvent", "Event: ${event.tag}, Bundle: $bundle")
        analyticsRepository.logEvent(event, bundle)
    }
}