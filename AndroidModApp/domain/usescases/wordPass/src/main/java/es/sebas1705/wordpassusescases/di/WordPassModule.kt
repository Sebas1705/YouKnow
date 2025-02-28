package es.sebas1705.wordpassusescases.di
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
import es.sebas1705.wordpassusescases.WordPassUsesCases
import es.sebas1705.wordpassusescases.usescases.GenerateWheelWordPass
import es.sebas1705.wordpassusescases.usescases.GenerateWordPass
import es.sebas1705.wordpassusescases.usescases.InsertWordPassList
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object WordPassModule {

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

}