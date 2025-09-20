package es.sebas1705.models.games
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
import es.sebas1705.common.games.wordpass.Letter

/**
 * Model class to represent the word game
 *
 * @param word [String]: Word to guess
 * @param definitions [List]<[String]>: List of definitions
 * @param letter [Letter]: Letter to guess
 * @param language [Languages]: Language of the question
 * @param difficulty [Difficulty]: Difficulty of the question
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class WordModel(
    val word: String,
    val definitions: List<String>,
    val letter: Letter,
    val language: Languages,
    val difficulty: Difficulty
) {
    companion object {
        fun defaultList(index: Int): List<WordModel> = (1..index).map {
            WordModel(
                "W--W--W",
                listOf("Definition 1", "Definition 2", "Definition 3"),
                Letter.W,
                Languages.EN,
                Difficulty.entries.random()
            )
        }
    }

    fun toMoultedString(): String {
        return this.word.map { char ->
            if (char.lowercase()[0] == this.letter.letter) char
            else '_'
        }.joinToString("")
    }

}
