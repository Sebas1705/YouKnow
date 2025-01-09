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

import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.enums.Languages
import es.sebas1705.youknow.core.classes.enums.Letter
import es.sebas1705.youknow.data.local.database.repository.DatabaseRepository
import es.sebas1705.youknow.domain.model.games.WordModel
import kotlin.collections.forEach

class GenerateWordPass(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        numFamilies: Int,
        letter: Letter,
        difficulty: Difficulty,
        onLoading: () -> Unit,
        onSuccess: (List<WordModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val words = databaseRepository.getWords(
            numFamilies,
            letter,
            Languages.ANY,
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

data class WordPassUsesCases(
    val generateWordPass: GenerateWordPass,
    val generateWheelWordPass: GenerateWheelWordPass,
    val insertWordPassList: InsertWordPassList
)