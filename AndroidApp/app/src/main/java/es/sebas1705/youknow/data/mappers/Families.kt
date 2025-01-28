package es.sebas1705.youknow.data.mappers
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

import es.sebas1705.youknow.core.classes.enums.games.Category
import es.sebas1705.youknow.core.classes.enums.games.Difficulty
import es.sebas1705.youknow.core.classes.enums.games.Languages
import es.sebas1705.youknow.data.local.database.entities.FamiliesEntity
import es.sebas1705.youknow.data.local.files.json.FamiliesJson
import es.sebas1705.youknow.domain.model.games.FamiliesModel

//ENTITY

/**
 * Mapper function
 *
 * @receiver [FamiliesEntity]: The object to convert
 *
 * @return [FamiliesModel]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun FamiliesEntity.toFamiliesModel() = FamiliesModel(
    answers,
    correctAnswer,
    category,
    language,
    difficulty
)

/**
 * Mapper function
 *
 * @receiver [FamiliesEntity]: The object to convert
 *
 * @return [FamiliesJson]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun FamiliesEntity.toFamiliesJson() = FamiliesJson(
    answers,
    correctAnswer,
    category.ordinal,
    language.ordinal,
    difficulty.ordinal
)

//JSON

/**
 * Mapper function
 *
 * @receiver [FamiliesJson]: The object to convert
 *
 * @return [FamiliesEntity]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun FamiliesJson.toFamiliesEntity() = FamiliesEntity(
    answers,
    correctAnswer,
    Category.entries[category],
    Languages.entries[language],
    Difficulty.entries[difficulty]
)

/**
 * Mapper function
 *
 * @receiver [FamiliesJson]: The object to convert
 *
 * @return [FamiliesModel]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun FamiliesJson.toFamiliesModel() = FamiliesModel(
    answers,
    correctAnswer,
    Category.entries[category],
    Languages.entries[language],
    Difficulty.entries[difficulty]
)

//MODEL

/**
 * Mapper function
 *
 * @receiver [FamiliesModel]: The object to convert
 *
 * @return [FamiliesEntity]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun FamiliesModel.toFamiliesEntity() = FamiliesEntity(
    answers,
    correctAnswer,
    category,
    language,
    difficulty
)

/**
 * Mapper function
 *
 * @receiver [FamiliesModel]: The object to convert
 *
 * @return [FamiliesJson]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun FamiliesModel.toFamiliesJson() = FamiliesJson(
    answers,
    correctAnswer,
    category.ordinal,
    language.ordinal,
    difficulty.ordinal
)