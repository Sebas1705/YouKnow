package es.sebas1705.retrofit.opendb.config
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

import es.sebas1705.retrofit.opendb.config.SettingsOpendb.TRIVIA_BASE_URL
import es.sebas1705.retrofit.opendb.config.SettingsOpendb.TRIVIA_ENCODE_TEXT
import es.sebas1705.retrofit.opendb.config.SettingsOpendb.TRIVIA_RESPONSE_EXAMPLE
import es.sebas1705.retrofit.opendb.dtos.QuestionOpendbDto
import es.sebas1705.retrofit.opendb.dtos.ResponseOpendbDto

/**
 * Settings of the Opentbd API
 *
 * @property TRIVIA_BASE_URL [String]: Base URL of the Opentbd API
 * @property TRIVIA_ENCODE_TEXT [String]: Text to encode the URL
 * @property TRIVIA_RESPONSE_EXAMPLE [ResponseOpendbDto]: Example of a response from the API
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
object SettingsOpendb {
    const val TRIVIA_BASE_URL = "https://opentdb.com/"
    const val TRIVIA_ENCODE_TEXT = "url3986"

    val TRIVIA_RESPONSE_EXAMPLE = ResponseOpendbDto(
        0,
        (1..10).map {
            QuestionOpendbDto(
                "General Knowledge",
                "hey",
                "Easy",
                listOf("1", "2", "3"),
                "HelloWorld",
                "URL"
            )
        }
    )
}