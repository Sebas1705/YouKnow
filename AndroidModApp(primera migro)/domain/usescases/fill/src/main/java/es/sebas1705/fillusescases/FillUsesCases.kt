package es.sebas1705.fillusescases

import es.sebas1705.fillusescases.usescases.FillByDefaultFamilies
import es.sebas1705.fillusescases.usescases.FillByDefaultQuestions
import es.sebas1705.fillusescases.usescases.FillByDefaultWords

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

/**
 * Use case to fill the database with default families, questions and words
 *
 * @param fillByDefaultFamilies [FillByDefaultFamilies]: Use case to fill the database with default families
 * @param fillByDefaultQuestions [FillByDefaultQuestions]: Use case to fill the database with default questions
 * @param fillByDefaultWords [FillByDefaultWords]: Use case to fill the database with default words
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class FillUsesCases(
    val fillByDefaultFamilies: FillByDefaultFamilies,
    val fillByDefaultQuestions: FillByDefaultQuestions,
    val fillByDefaultWords: FillByDefaultWords
)