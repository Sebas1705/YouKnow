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
import androidx.credentials.CredentialManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.sebas1705.youknow.data.firebase.authentication.repository.AuthenticationRepositoryImpl
import es.sebas1705.youknow.data.apis.opendb.repository.OpendbRepositoryImpl
import es.sebas1705.youknow.data.apis.opendb.OpendbApi
import es.sebas1705.youknow.data.firebase.authentication.repository.AuthenticationRepository
import es.sebas1705.youknow.data.apis.opendb.repository.OpendbRepository
import es.sebas1705.youknow.data.firebase.firestore.repository.FirestoreRepositoryImpl
import es.sebas1705.youknow.data.firebase.realtime.repository.RealtimeRepository
import es.sebas1705.youknow.data.firebase.realtime.repository.RealtimeRepositoryImpl
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepositoryImpl
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepository
import es.sebas1705.youknow.data.firebase.firestore.repository.FirestoreRepository
import es.sebas1705.youknow.data.local.database.Database
import es.sebas1705.youknow.data.local.database.repository.DatabaseRepository
import es.sebas1705.youknow.data.local.database.repository.DatabaseRepositoryImpl
import es.sebas1705.youknow.data.local.datastore.repository.DatastoreRepository
import es.sebas1705.youknow.data.local.datastore.repository.DatastoreRepositoryImpl
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

    @Provides
    @Singleton
    fun provideAnalyticsRepository(
        firebaseAnalytics: FirebaseAnalytics
    ): AnalyticsRepository = AnalyticsRepositoryImpl(firebaseAnalytics)

    @Provides
    @Singleton
    fun provideOpendbRepository(
        opendbApi: OpendbApi
    ): OpendbRepository = OpendbRepositoryImpl(opendbApi)

    @Provides
    @Singleton
    fun provideAuthRepository(
        credentialManager: CredentialManager,
        firebaseAuth: FirebaseAuth,
        analyticsRepository: AnalyticsRepository
    ): AuthenticationRepository = AuthenticationRepositoryImpl(credentialManager,firebaseAuth, analyticsRepository)

    @Provides
    @Singleton
    fun provideRealtimeRepository(
        firebaseDatabase: FirebaseDatabase,
        analyticsRepository: AnalyticsRepository
    ): RealtimeRepository = RealtimeRepositoryImpl(firebaseDatabase, analyticsRepository)

    @Provides
    @Singleton
    fun provideFirestoreRepository(
        firebaseFirestore: FirebaseFirestore,
        analyticsRepository: AnalyticsRepository
    ): FirestoreRepository = FirestoreRepositoryImpl(firebaseFirestore, analyticsRepository)

    @Provides
    @Singleton
    fun provideDatastoreRepository(
        application: Application
    ): DatastoreRepository = DatastoreRepositoryImpl(application)

    @Provides
    @Singleton
    fun provideDatabaseRepository(
        database: Database,
        analyticsRepository: AnalyticsRepository
    ): DatabaseRepository = DatabaseRepositoryImpl(database, analyticsRepository)

}