package es.sebas1705.common.utlis.extensions.numerables
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
 * Transform a list of [T] to a map of [Int] and [Q]
 *
 * @receiver [List]<[T]>: list of data
 * @param dataTransform: (Int, T) -> Q: function to transform the data
 *
 * @return [Map]<[Int], [Q]>: map of data
 *
 * @see WordModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun <T, Q> List<T>.toMapIndexed(dataTransform: (Int, T) -> Q): Map<Int, Q> {
    return this.mapIndexed { index, t -> index to dataTransform(index, t) }.toMap()
}

/**
 * Transform a list of [WordModel] to a list of [String]
 *
 * @receiver [List]<[WordModel]>: list of words
 * @param actualWord: [Int]: actual word
 *
 * @return [List]<[String]>: list of words
 *
 * @see WordModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun List<WordModel>.toViewList(actualWord: Int): List<String> {
    val previousWord = if (actualWord > 0) this[actualWord - 1].word else ""
    val currentWord = this[actualWord].word
    val nextWord = if (actualWord < this.size - 1) this[actualWord + 1].word else ""

    val previousString = if (previousWord.isNotEmpty()) "..${previousWord.first()}" else ""
    val currentString = currentWord.first().toString()
    val nextString = if (nextWord.isNotEmpty()) "${nextWord.first()}.." else ""

    return listOf(previousString, currentString, nextString)
}