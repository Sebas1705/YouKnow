package es.sebas1705.youknow.data.apis.opendb
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

import es.sebas1705.youknow.data.apis.opendb.dtos.ResponseOpendbDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface to manage the Opentbd API
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
interface OpendbApi {

    /**
     * Get trivia questions
     *
     * @param amount [Int]: Amount of questions to get
     * @param category [Int]: Category of the questions
     * @param difficulty [String]: Difficulty of the questions
     * @param type [String]: Type of the questions
     * @param encode [String]: Text to encode the URL
     *
     * @return [ResponseOpendbDto]: Response from the API
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @GET("api.php")
    suspend fun getTrivia(
        @Query("amount") amount: Int,
        @Query("category") category: Int? = null,
        @Query("difficulty") difficulty: String? = null,
        @Query("type") type: String? = null,
        @Query("encode") encode: String? = null
    ): ResponseOpendbDto

}

