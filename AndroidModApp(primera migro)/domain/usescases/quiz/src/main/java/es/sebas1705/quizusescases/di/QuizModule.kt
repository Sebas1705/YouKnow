package es.sebas1705.quizusescases.di
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
import es.sebas1705.quizusescases.QuizUsesCases
import es.sebas1705.quizusescases.usescases.GenerateQuestionList
import es.sebas1705.quizusescases.usescases.InsertQuestionList
import es.sebas1705.room.repository.DatabaseRepository
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object QuizModule {
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

}