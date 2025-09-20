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

import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.mappers.toWordModel
import es.sebas1705.models.games.WordModel
import es.sebas1705.room.repository.DatabaseRepository
import es.sebas1705.common.games.wordpass.Letter

/**
 * Use case to generate a word pass for the wheel
 *
 * @param databaseRepository [DatabaseRepository]: Repository to get words
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class GenerateWheelWordPass(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        difficulty: Difficulty,
        languages: Languages,
        onLoading: () -> Unit,
        onSuccess: (List<WordModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val words: MutableList<WordModel> = mutableListOf()
        Letter.entries.forEach {
            if (it == Letter.ANY)
                return@forEach
            val word = databaseRepository.getWords(
                1,
                it,
                languages,
                difficulty,
            )
            if (word.isNotEmpty())
                words.add(word[0].toWordModel())
            else
                onError("No ${it.letter} words found")
        }
        onSuccess(words)
    }
}