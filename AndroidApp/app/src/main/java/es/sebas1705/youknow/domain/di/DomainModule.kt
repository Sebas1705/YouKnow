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
import es.sebas1705.youknow.domain.usecases.logs.AnalyticsUsesCases
import es.sebas1705.youknow.domain.usecases.logs.LogEvent
import es.sebas1705.youknow.domain.usecases.logs.SetUserProperty
import es.sebas1705.youknow.domain.usecases.social.ChangeCredits
import es.sebas1705.youknow.domain.usecases.social.ChatUsesCases
import es.sebas1705.youknow.domain.usecases.social.CreateGroup
import es.sebas1705.youknow.domain.usecases.social.GetFirebaseUser
import es.sebas1705.youknow.domain.usecases.social.GetUser
import es.sebas1705.youknow.domain.usecases.social.GroupUsesCases
import es.sebas1705.youknow.domain.usecases.social.IsUserLogged
import es.sebas1705.youknow.domain.usecases.social.JoinGroupAsLeader
import es.sebas1705.youknow.domain.usecases.social.JoinGroupAsMember
import es.sebas1705.youknow.domain.usecases.social.RemoveGroupsListener
import es.sebas1705.youknow.domain.usecases.social.RemoveMessagesListener
import es.sebas1705.youknow.domain.usecases.social.SaveUser
import es.sebas1705.youknow.domain.usecases.social.SendForgotPassword
import es.sebas1705.youknow.domain.usecases.social.SendMessage
import es.sebas1705.youknow.domain.usecases.social.SetGroupsListener
import es.sebas1705.youknow.domain.usecases.social.SetLogged
import es.sebas1705.youknow.domain.usecases.social.SetMessagesListener
import es.sebas1705.youknow.domain.usecases.social.SignGoogle
import es.sebas1705.youknow.domain.usecases.social.SignInEmailUser
import es.sebas1705.youknow.domain.usecases.social.SignOut
import es.sebas1705.youknow.domain.usecases.social.SignUpEmailUser
import es.sebas1705.youknow.domain.usecases.social.UserUsesCases
import es.sebas1705.youknow.domain.usecases.social.VerifyJustLogged
import es.sebas1705.youknow.domain.usecases.social.VerifyWasLogged
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
    fun provideAnalyticsUsesCases(
        analyticsRepository: AnalyticsRepository
    ): AnalyticsUsesCases = AnalyticsUsesCases(
        logEvent = LogEvent(analyticsRepository),
        setUserProperty = SetUserProperty(analyticsRepository)
    )

    @Provides
    @Singleton
    fun provideUserUsesCases(
        authenticationRepository: AuthenticationRepository,
        databaseRepository: DatabaseRepository,
        firestoreRepository: FirestoreRepository,
        realtimeRepository: RealtimeRepository
    ): UserUsesCases = UserUsesCases(
        signUpEmailUser = SignUpEmailUser(authenticationRepository),
        signInEmailUser = SignInEmailUser(authenticationRepository),
        signGoogle = SignGoogle(authenticationRepository),
        signOut = SignOut(authenticationRepository),
        saveUser = SaveUser(databaseRepository, firestoreRepository),
        getUser = GetUser(databaseRepository, firestoreRepository),
        setLogged = SetLogged(firestoreRepository),
        verifyWasLogged = VerifyWasLogged(databaseRepository, firestoreRepository),
        verifyJustLogged = VerifyJustLogged(firestoreRepository),
        sendForgotPassword = SendForgotPassword(authenticationRepository),
        getFirebaseUser = GetFirebaseUser(authenticationRepository),
        isUserLogged = IsUserLogged(authenticationRepository),
        changeCredits = ChangeCredits(databaseRepository, firestoreRepository),
        joinGroupAsMember = JoinGroupAsMember(firestoreRepository, databaseRepository, realtimeRepository),
        joinGroupAsLeader = JoinGroupAsLeader(firestoreRepository, databaseRepository)
    )

    @Provides
    @Singleton
    fun provideChatUsesCases(
        realtimeRepository: RealtimeRepository
    ): ChatUsesCases = ChatUsesCases(
        sendMessage = SendMessage(realtimeRepository),
        setMessagesListener = SetMessagesListener(realtimeRepository),
        removeMessagesListener = RemoveMessagesListener(realtimeRepository)
    )

    @Provides
    @Singleton
    fun provideGroupUsesCases(
        realtimeRepository: RealtimeRepository
    ): GroupUsesCases = GroupUsesCases(
        createGroup = CreateGroup(realtimeRepository),
        setGroupsListener = SetGroupsListener(realtimeRepository),
        removeGroupsListener = RemoveGroupsListener(realtimeRepository)
    )
}