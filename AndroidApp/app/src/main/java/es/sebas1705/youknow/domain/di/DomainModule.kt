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
import es.sebas1705.youknow.data.apis.opendb.repository.OpendbRepository
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepository
import es.sebas1705.youknow.data.firebase.authentication.repository.AuthenticationRepository
import es.sebas1705.youknow.data.firebase.firestore.repository.FirestoreRepository
import es.sebas1705.youknow.data.firebase.realtime.repository.RealtimeRepository
import es.sebas1705.youknow.data.local.datastore.repository.DatastoreRepository
import es.sebas1705.youknow.domain.usecases.*
import es.sebas1705.youknow.domain.usecases.games.FamiliesUsesCases
import es.sebas1705.youknow.domain.usecases.games.GenerateFamilies
import es.sebas1705.youknow.domain.usecases.games.GenerateQuestionList
import es.sebas1705.youknow.domain.usecases.games.GenerateRandomNumber
import es.sebas1705.youknow.domain.usecases.games.GenerateWordPass
import es.sebas1705.youknow.domain.usecases.games.MysteryNumberUsesCases
import es.sebas1705.youknow.domain.usecases.games.QuizUsesCases
import es.sebas1705.youknow.domain.usecases.games.WordPassUsesCases
import es.sebas1705.youknow.domain.usecases.logs.AnalyticsUsesCases
import es.sebas1705.youknow.domain.usecases.logs.LogEvent
import es.sebas1705.youknow.domain.usecases.logs.SetUserProperty
import es.sebas1705.youknow.domain.usecases.social.ChatUsesCases
import es.sebas1705.youknow.domain.usecases.social.CreateGroup
import es.sebas1705.youknow.domain.usecases.social.GroupUsesCases
import es.sebas1705.youknow.domain.usecases.social.RemoveGroup
import es.sebas1705.youknow.domain.usecases.social.RemoveGroupsListener
import es.sebas1705.youknow.domain.usecases.social.RemoveMessagesListener
import es.sebas1705.youknow.domain.usecases.social.SendMessage
import es.sebas1705.youknow.domain.usecases.social.SetGroupsListener
import es.sebas1705.youknow.domain.usecases.social.SetMessagesListener
import es.sebas1705.youknow.domain.usecases.user.AddCreditsToUser
import es.sebas1705.youknow.domain.usecases.user.AddPointsToUser
import es.sebas1705.youknow.domain.usecases.user.AuthUsesCases
import es.sebas1705.youknow.domain.usecases.user.ChangeNicknameToUser
import es.sebas1705.youknow.domain.usecases.user.ChangePhotoToUser
import es.sebas1705.youknow.domain.usecases.user.ContainsUser
import es.sebas1705.youknow.domain.usecases.user.GetFirebaseUser
import es.sebas1705.youknow.domain.usecases.user.GetLoggedFromUser
import es.sebas1705.youknow.domain.usecases.user.GetUser
import es.sebas1705.youknow.domain.usecases.user.GetUserByNickname
import es.sebas1705.youknow.domain.usecases.user.GetUserRanking
import es.sebas1705.youknow.domain.usecases.user.RemoveGroupToUser
import es.sebas1705.youknow.domain.usecases.user.RemoveUserListener
import es.sebas1705.youknow.domain.usecases.user.SaveUser
import es.sebas1705.youknow.domain.usecases.user.SendForgotPassword
import es.sebas1705.youknow.domain.usecases.user.SetGroupToUser
import es.sebas1705.youknow.domain.usecases.user.SetLoggedToUser
import es.sebas1705.youknow.domain.usecases.user.SetUserListener
import es.sebas1705.youknow.domain.usecases.user.SignGoogle
import es.sebas1705.youknow.domain.usecases.user.SignInEmailUser
import es.sebas1705.youknow.domain.usecases.user.SignOut
import es.sebas1705.youknow.domain.usecases.user.SignUpEmailUser
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
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
        firestoreRepository: FirestoreRepository,
        realtimeRepository: RealtimeRepository
    ): UserUsesCases = UserUsesCases(
        addCreditsToUser = AddCreditsToUser(firestoreRepository),
        addPointsToUser = AddPointsToUser(firestoreRepository),
        containsUser = ContainsUser(firestoreRepository),
        getLoggedFromUser = GetLoggedFromUser(firestoreRepository),
        getUser = GetUser(firestoreRepository),
        saveUser = SaveUser(firestoreRepository),
        setGroupToUser = SetGroupToUser(firestoreRepository, realtimeRepository),
        removeGroupToUser = RemoveGroupToUser(firestoreRepository, realtimeRepository),
        setLoggedToUser = SetLoggedToUser(firestoreRepository),
        setUserListener = SetUserListener(firestoreRepository),
        removeUserListener = RemoveUserListener(firestoreRepository),
        changePhotoToUser = ChangePhotoToUser(firestoreRepository),
        changeNicknameToUser = ChangeNicknameToUser(firestoreRepository),
        getUserRanking = GetUserRanking(firestoreRepository),
        getUserByNickname = GetUserByNickname(firestoreRepository)
    )

    @Provides
    @Singleton
    fun provideAuthUsesCases(
        authenticationRepository: AuthenticationRepository
    ): AuthUsesCases = AuthUsesCases(
        getFirebaseUser = GetFirebaseUser(authenticationRepository),
        sendForgotPassword = SendForgotPassword(authenticationRepository),
        signInEmailUser = SignInEmailUser(authenticationRepository),
        signGoogle = SignGoogle(authenticationRepository),
        signOut = SignOut(authenticationRepository),
        signUpEmailUser = SignUpEmailUser(authenticationRepository)
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
        removeGroup = RemoveGroup(realtimeRepository),
        setGroupsListener = SetGroupsListener(realtimeRepository),
        removeGroupsListener = RemoveGroupsListener(realtimeRepository)
    )

    @Provides
    @Singleton
    fun provideQuizUsesCases(
    ): QuizUsesCases = QuizUsesCases(
        generateQuestionList = GenerateQuestionList()
    )

    @Provides
    @Singleton
    fun provideMysteryNumberUsesCases(
    ): MysteryNumberUsesCases = MysteryNumberUsesCases(
        generateRandomNumber = GenerateRandomNumber()
    )

    @Provides
    @Singleton
    fun provideFamiliesUsesCases(
    ): FamiliesUsesCases = FamiliesUsesCases(
        generateFamilies = GenerateFamilies()
    )

    @Provides
    @Singleton
    fun provideWordPassUsesCases(
    ): WordPassUsesCases = WordPassUsesCases(
        generateWordPass = GenerateWordPass()
    )
}