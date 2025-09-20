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

import android.util.Log
import es.sebas1705.analytics.config.UserProperty
import es.sebas1705.analytics.repository.AnalyticsRepository

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
        Log.i("SetUserProperty", "UserProperty: ${userProperty.tag}, Value: $value")
        analyticsRepository.setUserProperty(userProperty, value)
    }
}