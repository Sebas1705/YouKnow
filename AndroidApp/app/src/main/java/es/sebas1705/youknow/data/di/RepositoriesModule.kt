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
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.youknow.data.apis.opendb.OpendbApi
import es.sebas1705.youknow.data.apis.opendb.repository.OpendbRepository
import es.sebas1705.youknow.data.apis.opendb.repository.OpendbRepositoryImpl
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepository
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepositoryImpl
import es.sebas1705.youknow.data.firebase.authentication.repository.AuthenticationRepository
import es.sebas1705.youknow.data.firebase.authentication.repository.AuthenticationRepositoryImpl
import es.sebas1705.youknow.data.firebase.firestore.repository.FirestoreRepository
import es.sebas1705.youknow.data.firebase.firestore.repository.FirestoreRepositoryImpl
import es.sebas1705.youknow.data.firebase.realtime.repository.RealtimeRepository
import es.sebas1705.youknow.data.firebase.realtime.repository.RealtimeRepositoryImpl
import es.sebas1705.youknow.data.local.database.Database
import es.sebas1705.youknow.data.local.database.repository.DatabaseRepository
import es.sebas1705.youknow.data.local.database.repository.DatabaseRepositoryImpl
import es.sebas1705.youknow.data.local.datastore.repository.DatastoreRepository
import es.sebas1705.youknow.data.local.datastore.repository.DatastoreRepositoryImpl
import es.sebas1705.youknow.data.local.files.repository.FileRepository
import es.sebas1705.youknow.data.local.files.repository.FileRepositoryImpl
import javax.inject.Singleton

/**
 * Module to provide all the repositories of the data layer
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    /**
     * Provides [AnalyticsRepository] that is used to track events
     *
     * @param firebaseAnalytics [FirebaseAnalytics]: Firebase Analytics
     *
     * @return [AnalyticsRepository]
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideAnalyticsRepository(
        firebaseAnalytics: FirebaseAnalytics
    ): AnalyticsRepository = AnalyticsRepositoryImpl(firebaseAnalytics)

    /**
     * Provides [OpendbRepository] that is used to get the questions
     *
     * @param opendbApi [OpendbApi]: Opentbd API service
     *
     * @return [OpendbRepository]
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideOpendbRepository(
        opendbApi: OpendbApi
    ): OpendbRepository = OpendbRepositoryImpl(opendbApi)

    /**
     * Provides [AuthenticationRepository] that is used to manage the authentication
     *
     * @param firebaseAuth [FirebaseAuth]: Firebase Auth
     * @param analyticsRepository [AnalyticsRepository]: Analytics Repository
     *
     * @return [AuthenticationRepository]
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        analyticsRepository: AnalyticsRepository
    ): AuthenticationRepository =
        AuthenticationRepositoryImpl(firebaseAuth, analyticsRepository)

    /**
     * Provides [RealtimeRepository] that is used to manage the realtime database
     *
     * @param firebaseDatabase [FirebaseDatabase]: Firebase Database
     * @param analyticsRepository [AnalyticsRepository]: Analytics Repository
     *
     * @return [RealtimeRepository]
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideRealtimeRepository(
        firebaseDatabase: FirebaseDatabase,
        analyticsRepository: AnalyticsRepository
    ): RealtimeRepository = RealtimeRepositoryImpl(firebaseDatabase, analyticsRepository)

    /**
     * Provides [FirestoreRepository] that is used to manage the firestore database
     *
     * @param firebaseFirestore [FirebaseFirestore]: Firebase Firestore
     * @param analyticsRepository [AnalyticsRepository]: Analytics Repository
     *
     * @return [FirestoreRepository]
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideFirestoreRepository(
        firebaseFirestore: FirebaseFirestore,
        analyticsRepository: AnalyticsRepository
    ): FirestoreRepository = FirestoreRepositoryImpl(firebaseFirestore, analyticsRepository)

    /**
     * Provides [DatastoreRepository] that is used to manage the datastore
     *
     * @param application [Application]: Application
     *
     * @return [DatastoreRepository]
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideDatastoreRepository(
        application: Application
    ): DatastoreRepository = DatastoreRepositoryImpl(application)

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

    /**
     * Provides [FileRepository] that is used to manage the files
     *
     * @param application [Application]: Application
     * @param analyticsRepository [AnalyticsRepository]: Analytics Repository
     *
     * @return [FileRepository]
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideFileRepository(
        application: Application,
        analyticsRepository: AnalyticsRepository
    ): FileRepository = FileRepositoryImpl(application, analyticsRepository)

}