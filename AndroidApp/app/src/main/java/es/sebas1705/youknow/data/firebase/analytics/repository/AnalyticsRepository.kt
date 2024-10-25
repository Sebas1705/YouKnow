package es.sebas1705.youknow.data.firebase.analytics.repository
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
import com.google.firebase.database.core.Repo
import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData
import es.sebas1705.youknow.data.firebase.analytics.config.EventLog
import es.sebas1705.youknow.data.firebase.analytics.config.Layer
import es.sebas1705.youknow.data.firebase.analytics.config.Repository
import es.sebas1705.youknow.data.firebase.analytics.config.UserProperty
import es.sebas1705.youknow.domain.model.AnalyticsModel

/**
 * Repository interface to log events and set user properties
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
interface AnalyticsRepository {

    /**
     * Log an event
     *
     * @param event [EventLog]: Event to log
     * @param bundle [Bundle]: Bundle with the event data
     *
     * @see EventLog
     * @see Bundle
     */
    fun logEvent(event: EventLog, bundle: Bundle)

    /**
     * Log an error event
     *
     * @param classLogData [ClassLogData]: Class that logs the event
     * @param error [String]: Error message
     *
     * @see ClassLogData
     */
    fun logError(classLogData: ClassLogData, error: String)

    /**
     * Set a user property
     *
     * @param property [UserProperty]: Property to set
     * @param value [String]: Value to set
     *
     * @see UserProperty
     */
    fun setUserProperty(property: UserProperty, value: String)

    /**
     * Send an event
     *
     * @param analyticsModel [AnalyticsModel]: Model with the event data
     *
     * @see AnalyticsModel
     */
    fun sendEvent(analyticsModel: AnalyticsModel)

}