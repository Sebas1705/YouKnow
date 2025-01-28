package es.sebas1705.youknow.domain.usecases.games
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

import es.sebas1705.youknow.core.classes.enums.games.Difficulty
import es.sebas1705.youknow.core.classes.enums.games.Languages
import es.sebas1705.youknow.core.classes.enums.games.wordpass.Letter
import es.sebas1705.youknow.data.local.database.repository.DatabaseRepository
import es.sebas1705.youknow.domain.model.games.WordModel

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
        if (words.isEmpty())
            onError("No words found")
        else if (words.size < numFamilies)
            onError("Not enough words found")
        else
            onSuccess(words)
    }
}

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
        onLoading: () -> Unit,
        onSuccess: (List<WordModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val words: MutableList<WordModel> = mutableListOf()
        Letter.entries.forEach {
            val word = databaseRepository.getWords(
                1,
                it,
                Languages.ANY,
                difficulty,
            )
            if (word.isNotEmpty())
                words.add(word[0])
            else
                onError("No ${it.letter} words found")
        }
        onSuccess(words)
    }
}

/**
 * Use case to insert a list of words
 *
 * @param databaseRepository [DatabaseRepository]: Repository to insert words
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class InsertWordPassList(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        families: List<WordModel>,
        onLoading: () -> Unit,
        onSuccess: () -> Unit
    ) {
        onLoading()
        families.forEach {
            databaseRepository.insertOrReplace(it)
        }
        onSuccess()
    }
}

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