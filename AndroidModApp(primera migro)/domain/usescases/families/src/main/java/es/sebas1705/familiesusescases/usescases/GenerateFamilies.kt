package es.sebas1705.familiesusescases.usescases

import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.mappers.toFamiliesModel
import es.sebas1705.models.games.FamiliesModel
import es.sebas1705.room.repository.DatabaseRepository

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
 * Use case to generate families
 *
 * @param databaseRepository [DatabaseRepository]: Repository to get families
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class GenerateFamilies(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        numFamilies: Int,
        category: Category,
        difficulty: Difficulty,
        languages: Languages,
        onLoading: () -> Unit,
        onSuccess: (List<FamiliesModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val families = databaseRepository.getFamilies(
            numFamilies,
            category,
            languages,
            difficulty
        )
        if (families.isEmpty())
            onError("No families found")
        else if (families.size < numFamilies)
            onError("Not enough families found")
        else
            onSuccess(families.map { it.toFamiliesModel() })
    }
}