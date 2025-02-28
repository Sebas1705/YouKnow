package es.sebas1705.mappers
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
import es.sebas1705.files.json.WordJson
import es.sebas1705.models.games.WordModel
import es.sebas1705.room.entities.WordEntity
import es.sebas1705.common.games.wordpass.Letter

//ENTITY

/**
 * Mapper function
 *
 * @receiver [WordEntity]: The object to convert
 *
 * @return [WordModel]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun WordEntity.toWordModel() = WordModel(
    word,
    definitions,
    letter,
    language,
    difficulty
)

/**
 * Mapper function
 *
 * @receiver [WordEntity]: The object to convert
 *
 * @return [WordJson]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun WordEntity.toWordJson() = WordJson(
    word,
    definitions,
    letter.ordinal,
    language.ordinal,
    difficulty.ordinal
)

//JSON

/**
 * Mapper function
 *
 * @receiver [WordJson]: The object to convert
 *
 * @return [WordEntity]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun WordJson.toWordEntity() = WordEntity(
    word,
    definitions,
    Letter.entries[letter],
    Languages.entries[language],
    Difficulty.entries[difficulty]
)

/**
 * Mapper function
 *
 * @receiver [WordJson]: The object to convert
 *
 * @return [WordModel]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun WordJson.toWordModel() = WordModel(
    word,
    definitions,
    Letter.entries[letter],
    Languages.entries[language],
    Difficulty.entries[difficulty]
)

//MODEL

/**
 * Mapper function
 *
 * @receiver [WordModel]: The object to convert
 *
 * @return [WordEntity]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun WordModel.toWordEntity() = WordEntity(
    word,
    definitions,
    letter,
    language,
    difficulty
)

/**
 * Mapper function
 *
 * @receiver [WordModel]: The object to convert
 *
 * @return [WordJson]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun WordModel.toWordJson() = WordJson(
    word,
    definitions,
    letter.ordinal,
    language.ordinal,
    difficulty.ordinal
)