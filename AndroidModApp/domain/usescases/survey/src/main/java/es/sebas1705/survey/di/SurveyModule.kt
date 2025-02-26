package es.sebas1705.survey.di
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
import es.sebas1705.room.repository.DatabaseRepository
import es.sebas1705.survey.SurveyUsesCases
import es.sebas1705.survey.usescases.GetActualSurvey
import es.sebas1705.survey.usescases.GetAllSurveys
import es.sebas1705.survey.usescases.PublicSurvey
import es.sebas1705.firestore.repository.FirestoreRepository
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object SurveyModule {

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