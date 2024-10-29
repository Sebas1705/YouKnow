package es.sebas1705.youknow.domain.di
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
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepository
import es.sebas1705.youknow.data.firebase.authentication.repository.AuthenticationRepository
import es.sebas1705.youknow.data.local.datastore.repository.DatastoreRepository
import es.sebas1705.youknow.data.firebase.realtime.repository.RealtimeRepository
import es.sebas1705.youknow.data.apis.opendb.repository.OpendbRepository
import es.sebas1705.youknow.data.firebase.firestore.repository.FirestoreRepository
import es.sebas1705.youknow.data.local.database.repository.DatabaseRepository
import es.sebas1705.youknow.domain.usecases.*
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        datastoreRepository: DatastoreRepository
    ): DatastoreUsesCases = DatastoreUsesCases(
        readFirstTime = ReadFirstTime(datastoreRepository),
        saveFirstTime = SaveFirstTime(datastoreRepository),
        readAppVolume = ReadAppVolume(datastoreRepository),
        saveAppVolume = SaveAppVolume(datastoreRepository),
        readAppContrast = ReadAppContrast(datastoreRepository),
        saveAppContrast = SaveAppContrast(datastoreRepository)
    )

    @Provides
    @Singleton
    fun provideTriviaRepositoryUsesCases(
        opendbRepository: OpendbRepository
    ): TriviaUsesCases = TriviaUsesCases(
        getTriviaTenQuestions = GetTriviaTenQuestions(opendbRepository)
    )

    @Provides
    @Singleton
    fun provideAuthUsesCases(
        authenticationRepository: AuthenticationRepository
    ): AuthenticationUsesCases = AuthenticationUsesCases(
        signUpWithEmail = SignUpWithEmail(authenticationRepository),
        signInWithEmail = SignInWithEmail(authenticationRepository),
        signWithGoogle = SignWithGoogle(authenticationRepository),
        signOut = SignOut(authenticationRepository),
        isUserLogged = IsUserLogged(authenticationRepository),
        getCurrentUser = GetCurrentUser(authenticationRepository),
        sendForgotPassword = SendForgotPassword(authenticationRepository)
    )

    @Provides
    @Singleton
    fun provideAnalyticsUsesCases(
        analyticsRepository: AnalyticsRepository
    ): AnalyticsUsesCases = AnalyticsUsesCases(
        logEvent = LogEvent(analyticsRepository),
        setUserProperty = SetUserProperty(analyticsRepository)
    )

    @Provides
    @Singleton
    fun provideRealtimeUsesCases(
        realtimeRepository: RealtimeRepository
    ): RealtimeUsesCases = RealtimeUsesCases(
        addMessageToGlobalChat = AddMessageToGlobalChat(realtimeRepository),
        getMessagesFromGlobalChat = GetMessagesFromGlobalChat(realtimeRepository)
    )

    /*
    @Provides
    @Singleton
    fun provideFirestoreUsesCases(
        firestoreRepository: FirestoreRepository
    ): FirestoreUsesCases = FirestoreUsesCases(
        saveUser = SaveUser(firestoreRepository),
        getUser = GetUser(firestoreRepository)
    )*/

    @Provides
    @Singleton
    fun provideUserUsesCases(
        authenticationRepository: AuthenticationRepository,
        databaseRepository: DatabaseRepository,
        firestoreRepository: FirestoreRepository,
        analyticsRepository: AnalyticsRepository
    ): UserUsesCases = UserUsesCases(
        signUpEmailUser = SignUpEmailUser(authenticationRepository),
        saveUser = SaveUser(databaseRepository, firestoreRepository),
        getUser = GetUser()
    )
}