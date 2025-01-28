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
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData
import es.sebas1705.youknow.data.firebase.analytics.config.EventLog
import es.sebas1705.youknow.data.firebase.analytics.config.UserProperty
import es.sebas1705.youknow.domain.model.stats.AnalyticsModel
import javax.inject.Inject

/**
 * Analytics repository implementation
 *
 * @property firebaseAnalytics [FirebaseAnalytics]: firebase analytics instance
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class AnalyticsRepositoryImpl @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
) : AnalyticsRepository {

    override fun logEvent(event: EventLog, bundle: Bundle) {
        firebaseAnalytics.logEvent(event.tag, bundle)
    }

    override fun logError(classLogData: ClassLogData, error: String) {
        firebaseAnalytics.logEvent(EventLog.Error.tag) {
            param("layer", classLogData.layer.tag)
            param("repository", classLogData.repository.tag)
            param("error", error)
        }
    }

    override fun setUserProperty(property: UserProperty, value: String) {
        firebaseAnalytics.setUserProperty(property.tag, value)
    }

    override fun sendEvent(analyticsModel: AnalyticsModel) {
        firebaseAnalytics.logEvent(analyticsModel.title) {
            analyticsModel.analyticsString.forEach { (key, value) -> param(key, value) }
            analyticsModel.analyticsLong.forEach { (key, value) -> param(key, value) }
            analyticsModel.analyticsDouble.forEach { (key, value) -> param(key, value) }
            analyticsModel.analyticsBundle.forEach { (key, value) -> param(key, value) }
            analyticsModel.analyticsBundleArray.forEach { (key, value) -> param(key, value) }
        }
    }
}