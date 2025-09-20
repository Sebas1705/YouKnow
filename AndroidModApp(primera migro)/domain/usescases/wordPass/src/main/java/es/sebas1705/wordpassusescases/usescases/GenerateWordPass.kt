package es.sebas1705.wordpassusescases.usescases
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

import android.util.Log
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.mappers.toWordModel
import es.sebas1705.models.games.WordModel
import es.sebas1705.room.repository.DatabaseRepository
import es.sebas1705.common.games.wordpass.Letter

/**
 * Use case to generate a word pass
 *
 * @param databaseRepository [DatabaseRepository]: Repository to get words
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class GenerateWordPass(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        numFamilies: Int,
        letter: Letter,
        languages: Languages,
        difficulty: Difficulty,
        onLoading: () -> Unit,
        onSuccess: (List<WordModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val words = databaseRepository.getWords(
            numFamilies,
            letter,
            languages,
            difficulty,
        )
        Log.d(
            "GenerateWordPass", """
            |*Words same features* 
            |-Same letter: ${words.filter { it.letter == letter }.size} (${letter.name})
            |-Same language: ${words.filter { it.language == languages }.size} (${languages.name})
            |-Same difficulty: ${words.filter { it.difficulty == difficulty }.size} (${difficulty.name})
            |-Results: ${words.filter { it.letter == letter && it.language == languages && it.difficulty == difficulty }.size}/${words.size}
        """.trimMargin()
        )
        if (words.isEmpty())
            onError("No words found")
        else if (words.size < numFamilies)
            onError("Not enough words found")
        else
            onSuccess(words.map { it.toWordModel() })
    }
}