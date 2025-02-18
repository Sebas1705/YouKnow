package es.sebas1705.wordpassusescases
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

import es.sebas1705.wordpassusescases.usescases.GenerateWheelWordPass
import es.sebas1705.wordpassusescases.usescases.GenerateWordPass
import es.sebas1705.wordpassusescases.usescases.InsertWordPassList

/**
 * Use cases for the word pass game
 *
 * @property generateWordPass [GenerateWordPass]: Use case to generate a word pass
 * @property generateWheelWordPass [GenerateWheelWordPass]: Use case to generate a word pass for the wheel
 * @property insertWordPassList [InsertWordPassList]: Use case to insert a list of words
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class WordPassUsesCases(
    val generateWordPass: GenerateWordPass,
    val generateWheelWordPass: GenerateWheelWordPass,
    val insertWordPassList: InsertWordPassList
)