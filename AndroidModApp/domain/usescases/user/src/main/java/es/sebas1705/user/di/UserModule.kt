package es.sebas1705.user.di
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
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.realtime.repository.RealtimeRepository
import es.sebas1705.user.UserUsesCases
import es.sebas1705.user.usescases.AddCreditsToUser
import es.sebas1705.user.usescases.AddPointsToUser
import es.sebas1705.user.usescases.ChangeNicknameToUser
import es.sebas1705.user.usescases.ChangePhotoToUser
import es.sebas1705.user.usescases.ContainsUser
import es.sebas1705.user.usescases.DeleteDataUser
import es.sebas1705.user.usescases.GetUser
import es.sebas1705.user.usescases.GetUserByNickname
import es.sebas1705.user.usescases.GetUserRanking
import es.sebas1705.user.usescases.RemoveGroupToUser
import es.sebas1705.user.usescases.RemoveUserListener
import es.sebas1705.user.usescases.SaveUser
import es.sebas1705.user.usescases.SetGroupToUser
import es.sebas1705.user.usescases.SetUserListener
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object UserModule {

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

}