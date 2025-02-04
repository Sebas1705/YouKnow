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
import es.sebas1705.youknow.data.local.database.repository.DatabaseRepository
import es.sebas1705.youknow.data.local.datastore.repository.DatastoreRepository
import es.sebas1705.youknow.data.local.files.repository.FileRepository
import es.sebas1705.youknow.domain.usecases.games.FamiliesUsesCases
import es.sebas1705.youknow.domain.usecases.games.FillByDefaultFamilies
import es.sebas1705.youknow.domain.usecases.games.FillByDefaultQuestions
import es.sebas1705.youknow.domain.usecases.games.FillByDefaultWords
import es.sebas1705.youknow.domain.usecases.games.FillUsesCases
import es.sebas1705.youknow.domain.usecases.games.GenerateFamilies
import es.sebas1705.youknow.domain.usecases.games.GenerateQuestionList
import es.sebas1705.youknow.domain.usecases.games.GenerateRandomNumber
import es.sebas1705.youknow.domain.usecases.games.GenerateWheelWordPass
import es.sebas1705.youknow.domain.usecases.games.GenerateWordPass
import es.sebas1705.youknow.domain.usecases.games.GetTriviaTenQuestions
import es.sebas1705.youknow.domain.usecases.games.InsertFamiliesList
import es.sebas1705.youknow.domain.usecases.games.InsertQuestionList
import es.sebas1705.youknow.domain.usecases.games.InsertWordPassList
import es.sebas1705.youknow.domain.usecases.games.MysteryNumberUsesCases
import es.sebas1705.youknow.domain.usecases.games.QuizUsesCases
import es.sebas1705.youknow.domain.usecases.games.TriviaUsesCases
import es.sebas1705.youknow.domain.usecases.games.WordPassUsesCases
import es.sebas1705.youknow.domain.usecases.logs.AnalyticsUsesCases
import es.sebas1705.youknow.domain.usecases.logs.GetActualSurvey
import es.sebas1705.youknow.domain.usecases.logs.GetAllSurveys
import es.sebas1705.youknow.domain.usecases.logs.LogEvent
import es.sebas1705.youknow.domain.usecases.logs.PublicSurvey
import es.sebas1705.youknow.domain.usecases.logs.SetUserProperty
import es.sebas1705.youknow.domain.usecases.logs.SurveyUsesCases
import es.sebas1705.youknow.domain.usecases.social.ChatUsesCases
import es.sebas1705.youknow.domain.usecases.social.CreateGroup
import es.sebas1705.youknow.domain.usecases.social.GetNews
import es.sebas1705.youknow.domain.usecases.social.GroupUsesCases
import es.sebas1705.youknow.domain.usecases.social.NewsUsesCases
import es.sebas1705.youknow.domain.usecases.social.RemoveGroup
import es.sebas1705.youknow.domain.usecases.social.RemoveGroupsListener
import es.sebas1705.youknow.domain.usecases.social.RemoveMessagesListener
import es.sebas1705.youknow.domain.usecases.social.SendMessage
import es.sebas1705.youknow.domain.usecases.social.SetGroupsListener
import es.sebas1705.youknow.domain.usecases.social.SetMessagesListener
import es.sebas1705.youknow.domain.usecases.ui.DatastoreUsesCases
import es.sebas1705.youknow.domain.usecases.ui.ReadAppContrast
import es.sebas1705.youknow.domain.usecases.ui.ReadFirstTime
import es.sebas1705.youknow.domain.usecases.ui.ReadGameLanguage
import es.sebas1705.youknow.domain.usecases.ui.ReadMusicVolume
import es.sebas1705.youknow.domain.usecases.ui.ReadSoundVolume
import es.sebas1705.youknow.domain.usecases.ui.SaveAppContrast
import es.sebas1705.youknow.domain.usecases.ui.SaveFirstTime
import es.sebas1705.youknow.domain.usecases.ui.SaveGameLanguage
import es.sebas1705.youknow.domain.usecases.ui.SaveMusicVolume
import es.sebas1705.youknow.domain.usecases.ui.SaveSoundVolume
import es.sebas1705.youknow.domain.usecases.user.AddCreditsToUser
import es.sebas1705.youknow.domain.usecases.user.AddPointsToUser
import es.sebas1705.youknow.domain.usecases.user.AuthUsesCases
import es.sebas1705.youknow.domain.usecases.user.ChangeNicknameToUser
import es.sebas1705.youknow.domain.usecases.user.ChangePhotoToUser
import es.sebas1705.youknow.domain.usecases.user.ContainsUser
import es.sebas1705.youknow.domain.usecases.user.DeleteDataUser
import es.sebas1705.youknow.domain.usecases.user.GetFirebaseUser
import es.sebas1705.youknow.domain.usecases.user.GetUser
import es.sebas1705.youknow.domain.usecases.user.GetUserByNickname
import es.sebas1705.youknow.domain.usecases.user.GetUserRanking
import es.sebas1705.youknow.domain.usecases.user.RemoveGroupToUser
import es.sebas1705.youknow.domain.usecases.user.RemoveUserListener
import es.sebas1705.youknow.domain.usecases.user.SaveUser
import es.sebas1705.youknow.domain.usecases.user.SendForgotPassword
import es.sebas1705.youknow.domain.usecases.user.SetGroupToUser
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

    /**
     * Function to provide datastore use cases
     *
     * @param datastoreRepository [DatastoreRepository]: Repository to access to the datastore
     *
     * @return [DatastoreUsesCases]: Use cases of the datastore
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        datastoreRepository: DatastoreRepository
    ): DatastoreUsesCases = DatastoreUsesCases(
        readFirstTime = ReadFirstTime(datastoreRepository),
        saveFirstTime = SaveFirstTime(datastoreRepository),
        readMusicVolume = ReadMusicVolume(datastoreRepository),
        saveMusicVolume = SaveMusicVolume(datastoreRepository),
        readSoundVolume = ReadSoundVolume(datastoreRepository),
        saveSoundVolume = SaveSoundVolume(datastoreRepository),
        readAppContrast = ReadAppContrast(datastoreRepository),
        saveAppContrast = SaveAppContrast(datastoreRepository),
        readGameLanguage = ReadGameLanguage(datastoreRepository),
        saveGameLanguage = SaveGameLanguage(datastoreRepository)
    )

    /**
     * Function to provide opendb use cases
     *
     * @param opendbRepository [OpendbRepository]: Repository to access to the opendb api
     *
     * @return [TriviaUsesCases]: Use cases of the trivia
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideTriviaRepositoryUsesCases(
        opendbRepository: OpendbRepository
    ): TriviaUsesCases = TriviaUsesCases(
        getTriviaTenQuestions = GetTriviaTenQuestions(opendbRepository)
    )

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

    /**
     * Function to provide user use cases
     *
     * @param firestoreRepository [FirestoreRepository]: Repository to access to the firestore
     * @param realtimeRepository [RealtimeRepository]: Repository to access to the realtime database
     *
     * @return [UserUsesCases]: Use cases of the user
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideUserUsesCases(
        firestoreRepository: FirestoreRepository,
        realtimeRepository: RealtimeRepository
    ): UserUsesCases = UserUsesCases(
        addCreditsToUser = AddCreditsToUser(firestoreRepository),
        addPointsToUser = AddPointsToUser(firestoreRepository),
        containsUser = ContainsUser(firestoreRepository),
        getUser = GetUser(firestoreRepository),
        saveUser = SaveUser(firestoreRepository),
        setGroupToUser = SetGroupToUser(firestoreRepository, realtimeRepository),
        removeGroupToUser = RemoveGroupToUser(firestoreRepository, realtimeRepository),
        setUserListener = SetUserListener(firestoreRepository),
        removeUserListener = RemoveUserListener(firestoreRepository),
        changePhotoToUser = ChangePhotoToUser(firestoreRepository),
        changeNicknameToUser = ChangeNicknameToUser(firestoreRepository),
        getUserRanking = GetUserRanking(firestoreRepository),
        getUserByNickname = GetUserByNickname(firestoreRepository),
        deleteDataUser = DeleteDataUser(firestoreRepository)
    )

    /**
     * Function to provide authentication use cases
     *
     * @param authenticationRepository [AuthenticationRepository]: Repository to access to the authentication
     *
     * @return [AuthUsesCases]: Use cases of the authentication
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
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

    /**
     * Function to provide chat use cases
     *
     * @param realtimeRepository [RealtimeRepository]: Repository to access to the realtime database
     *
     * @return [ChatUsesCases]: Use cases of the chat
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideChatUsesCases(
        realtimeRepository: RealtimeRepository
    ): ChatUsesCases = ChatUsesCases(
        sendMessage = SendMessage(realtimeRepository),
        setMessagesListener = SetMessagesListener(realtimeRepository),
        removeMessagesListener = RemoveMessagesListener(realtimeRepository)
    )

    /**
     * Function to provide group use cases
     *
     * @param realtimeRepository [RealtimeRepository]: Repository to access to the realtime database
     *
     * @return [GroupUsesCases]: Use cases of the group
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
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

    /**
     * Function to provide quiz use cases
     *
     * @param databaseRepository [DatabaseRepository]: Repository to access to the database
     *
     * @return [QuizUsesCases]: Use cases of the quiz
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideQuizUsesCases(
        databaseRepository: DatabaseRepository
    ): QuizUsesCases = QuizUsesCases(
        generateQuestionList = GenerateQuestionList(databaseRepository),
        insertQuestionList = InsertQuestionList(databaseRepository)
    )

    /**
     * Function to provide mystery number use cases
     *
     * @return [MysteryNumberUsesCases]: Use cases of the mystery number
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideMysteryNumberUsesCases(
    ): MysteryNumberUsesCases = MysteryNumberUsesCases(
        generateRandomNumber = GenerateRandomNumber()
    )

    /**
     * Function to provide families use cases
     *
     * @param databaseRepository [DatabaseRepository]: Repository to access to the database
     *
     * @return [FamiliesUsesCases]: Use cases of the families
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideFamiliesUsesCases(
        databaseRepository: DatabaseRepository
    ): FamiliesUsesCases = FamiliesUsesCases(
        generateFamilies = GenerateFamilies(databaseRepository),
        insertFamiliesList = InsertFamiliesList(databaseRepository)
    )

    /**
     * Function to provide word pass use cases
     *
     * @param databaseRepository [DatabaseRepository]: Repository to access to the database
     *
     * @return [WordPassUsesCases]: Use cases of the word pass
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideWordPassUsesCases(
        databaseRepository: DatabaseRepository
    ): WordPassUsesCases = WordPassUsesCases(
        generateWordPass = GenerateWordPass(databaseRepository),
        generateWheelWordPass = GenerateWheelWordPass(databaseRepository),
        insertWordPassList = InsertWordPassList(databaseRepository)
    )

    /**
     * Function to provide fill use cases
     *
     * @param fileRepository [FileRepository]: Repository to access to the files
     * @param databaseRepository [DatabaseRepository]: Repository to access to the database
     *
     * @return [FillUsesCases]: Use cases of the fill
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideFillUsesCases(
        fileRepository: FileRepository,
        databaseRepository: DatabaseRepository
    ): FillUsesCases = FillUsesCases(
        fillByDefaultFamilies = FillByDefaultFamilies(fileRepository, databaseRepository),
        fillByDefaultQuestions = FillByDefaultQuestions(fileRepository, databaseRepository),
        fillByDefaultWords = FillByDefaultWords(fileRepository, databaseRepository)
    )

    /**
     * Function to provide news use cases
     *
     * @param firestoreRepository [FirestoreRepository]: Repository to access to the firestore
     *
     * @return [NewsUsesCases]: Use cases of the news
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideNewUsesCases(
        firestoreRepository: FirestoreRepository
    ): NewsUsesCases = NewsUsesCases(
        getNews = GetNews(firestoreRepository)
    )

    /**
     * Function to provide survey use cases
     *
     * @param firestoreRepository [FirestoreRepository]: Repository to access to the firestore
     * @param databaseRepository [DatabaseRepository]: Repository to access to the database
     *
     * @return [SurveyUsesCases]: Use cases of the survey
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideSurveyUsesCases(
        firestoreRepository: FirestoreRepository,
        databaseRepository: DatabaseRepository
    ): SurveyUsesCases = SurveyUsesCases(
        publicSurvey = PublicSurvey(firestoreRepository, databaseRepository),
        getActualSurvey = GetActualSurvey(firestoreRepository, databaseRepository),
        getAllSurveys = GetAllSurveys(firestoreRepository),
    )
}