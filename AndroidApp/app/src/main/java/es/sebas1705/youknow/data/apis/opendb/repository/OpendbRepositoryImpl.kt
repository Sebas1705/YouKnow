package es.sebas1705.youknow.data.apis.opendb.repository
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

import es.sebas1705.youknow.data.apis.opendb.OpendbApi
import es.sebas1705.youknow.data.apis.opendb.config.SettingsOpendb.TRIVIA_ENCODE_TEXT
import es.sebas1705.youknow.data.apis.opendb.dtos.ResponseOpendbDto
import javax.inject.Inject

/**
 * Trivia repository implementation
 *
 * @property opendbApi [OpendbApi]: Opentbd API service
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class OpendbRepositoryImpl @Inject constructor(
    private val opendbApi: OpendbApi
) : OpendbRepository {

    override suspend fun getQuestions(
        amount: Int,
        category: Int?,
        difficulty: String?,
        type: String?,
        encode: String?
    ): ResponseOpendbDto {
        return opendbApi.getTrivia(
            amount,
            category,
            difficulty,
            type,
            encode
        )
    }


    override suspend fun getTenRandomQuestions(): ResponseOpendbDto {
        return opendbApi.getTrivia(10, encode = TRIVIA_ENCODE_TEXT)
    }


}