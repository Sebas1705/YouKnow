package es.sebas1705.youknow.data.di
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
import es.sebas1705.youknow.data.apis.opendb.repository.OpendbRepositoryImpl
import es.sebas1705.youknow.data.apis.opendb.OpendbApi
import es.sebas1705.youknow.data.apis.opendb.repository.OpendbRepository
import es.sebas1705.youknow.data.apis.opendb.config.SettingsOpendb.TRIVIA_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module to provide api dependencies
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object ApisModule {

    @Provides
    @Singleton
    fun provideTriviaApiService(): OpendbApi {
        return Retrofit.Builder()
            .baseUrl(TRIVIA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpendbApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTriviaRepository(
        opendbApi: OpendbApi
    ): OpendbRepository = OpendbRepositoryImpl(opendbApi)

}