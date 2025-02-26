package es.sebas1705.room.di
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

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.analytics.repository.AnalyticsRepository
import es.sebas1705.room.config.SettingsDB
import es.sebas1705.room.repository.DatabaseRepository
import es.sebas1705.room.repository.DatabaseRepositoryImpl
import es.sebas1705.youknow.data.local.database.Database
import javax.inject.Singleton

/**
 * Module to provide
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object Module {

    /**
     * Provides [Database] that is used to manage the local database
     *
     * @param application [Application]: Application
     *
     * @return [Database]
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideLocalDatabase(
        application: Application
    ): Database = Room.databaseBuilder(
        application,
        Database::class.java,
        SettingsDB.DATABASE_NAME
    ).build()

    /**
     * Provides [DatabaseRepository] that is used to manage the local database
     *
     * @param database [Database]: Database
     * @param analyticsRepository [AnalyticsRepository]: Analytics Repository
     *
     * @return [DatabaseRepository]
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideDatabaseRepository(
        database: Database,
        analyticsRepository: AnalyticsRepository
    ): DatabaseRepository = DatabaseRepositoryImpl(database, analyticsRepository)

}