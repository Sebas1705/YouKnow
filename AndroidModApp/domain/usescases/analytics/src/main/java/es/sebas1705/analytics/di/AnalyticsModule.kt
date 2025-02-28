package es.sebas1705.analytics.di
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

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.analytics.AnalyticsUsesCases
import es.sebas1705.analytics.repository.AnalyticsRepository
import es.sebas1705.analytics.usescases.LogEvent
import es.sebas1705.analytics.usescases.SetUserProperty
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {

    /**
     * Function to provide analytics use cases
     *
     * @param analyticsRepository [AnalyticsRepository]: Repository to access to the analytics
     *
     * @return [AnalyticsUsesCases]: Use cases of the analytics
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideAnalyticsUsesCases(
        analyticsRepository: AnalyticsRepository
    ): AnalyticsUsesCases = AnalyticsUsesCases(
        logEvent = LogEvent(analyticsRepository),
        setUserProperty = SetUserProperty(analyticsRepository)
    )

}