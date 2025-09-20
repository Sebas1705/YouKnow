package es.sebas1705.retrofit.opendb.repository
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

import es.sebas1705.retrofit.opendb.config.SettingsOpendb.TRIVIA_ENCODE_TEXT
import es.sebas1705.retrofit.opendb.dtos.ResponseOpendbDto

/**
 * Repository interface to get trivia questions
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
interface OpendbRepository {

    /**
     * Get a list of trivia questions
     *
     * @param amount [Int]: Number of questions to get
     * @param category [Int]?: Category of the questions
     * @param difficulty [String]?: Difficulty of the questions
     * @param type [String]?: Type of the questions
     * @param encode [String]?: Encode of the questions
     *
     * @return [ResponseOpendbDto] with the list of questions
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun getQuestions(
        amount: Int,
        category: Int? = null,
        difficulty: String? = null,
        type: String? = null,
        encode: String? = TRIVIA_ENCODE_TEXT
    ): ResponseOpendbDto

    /**
     * Get a list of 10 random trivia questions
     *
     * @return [ResponseOpendbDto] with the list of questions
     *
     * @see ResponseOpendbDto
     */
    suspend fun getTenRandomQuestions(): ResponseOpendbDto
}