package es.sebas1705.opendbusescases.di
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
import es.sebas1705.opendbusescases.OpendbUsesCases
import es.sebas1705.opendbusescases.usescases.GetTriviaTenQuestions
import es.sebas1705.retrofit.opendb.repository.OpendbRepository
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object OpendbModule {

    /**
     * Function to provide opendb use cases
     *
     * @param opendbRepository [OpendbRepository]: Repository to access to the opendb api
     *
     * @return [OpendbUsesCases]: Use cases of the trivia
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideOpendbUsesCases(
        opendbRepository: OpendbRepository
    ): OpendbUsesCases = OpendbUsesCases(
        getTriviaTenQuestions = GetTriviaTenQuestions(opendbRepository)
    )
}