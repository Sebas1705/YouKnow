package es.sebas1705.youknow.data.di
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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.sebas1705.youknow.data.local.database.Database
import es.sebas1705.youknow.data.local.datastore.repository.DatastoreRepositoryImpl
import es.sebas1705.youknow.data.local.datastore.repository.DatastoreRepository
import es.sebas1705.youknow.data.local.database.config.SettingsDB
import es.sebas1705.youknow.data.local.database.daos.UserDao
import javax.inject.Singleton

/**
 * Module to provide local dependencies
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(
        @ApplicationContext application: Application
    ): Database = Room.databaseBuilder(
        application,
        Database::class.java,
        SettingsDB.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideUserDao(
        database: Database
    ): UserDao = database.userDao()

    @Provides
    @Singleton
    fun provideLocalUserRepository(
        application: Application
    ): DatastoreRepository = DatastoreRepositoryImpl(application)

}