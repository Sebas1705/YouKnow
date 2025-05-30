package es.sebas1705.youknow.di
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
import javax.inject.Singleton

/**
 * Module to provide firebase dependencies
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    /**
     * Provides [FirebaseAnalytics] that is used to track events
     *
     * @param application [Application]: Application
     *
     * @return [FirebaseAnalytics]
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideFirebaseAnalytics(
        application: Application
    ): FirebaseAnalytics = FirebaseAnalytics.getInstance(application)

    /**
     * Provides [FirebaseAuth] that is used to manage the authentication
     *
     * @return [FirebaseAuth]
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Provides [FirebaseDatabase] that is used to manage the realtime database
     *
     * @return [FirebaseDatabase]
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideFirebaseRealtime(): FirebaseDatabase = FirebaseDatabase.getInstance()

    /**
     * Provides [FirebaseFirestore] that is used to manage the firestore database
     *
     * @return [FirebaseFirestore]
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideFirestoreFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

}