package es.sebas1705.youknow.data.apis.opendb.dtos
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

import com.google.gson.annotations.SerializedName

/**
 * DTO to represent a response from the Opentbd API
 *
 * @property responseCode [Int]: Response code of the API
 * @property questionOpendbDtos [List]<[es.sebas1705.youknow.data.apis.opendb.dtos.QuestionOpendbDto]>: List of questions
 *
 * @see es.sebas1705.youknow.data.apis.opendb.dtos.QuestionOpendbDto
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class ResponseOpendbDto(
    val responseCode: Int,
    @SerializedName("results") val questionOpendbDtos: List<QuestionOpendbDto>
)