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

import es.sebas1705.youknow.core.classes.enums.Category
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.enums.Languages
import es.sebas1705.youknow.data.local.database.repository.DatabaseRepository
import es.sebas1705.youknow.domain.model.games.FamiliesModel

class GenerateFamilies(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        numFamilies: Int,
        category: Category,
        difficulty: Difficulty,
        onLoading: () -> Unit,
        onSuccess: (List<FamiliesModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val families = databaseRepository.getFamilies(
            numFamilies,
            category,
            Languages.ANY,
            difficulty
        )
        if (families.isEmpty())
            onError("No families found")
        else if (families.size < numFamilies)
            onError("Not enough families found")
        else
            onSuccess(families)
    }
}

class InsertFamiliesList(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        families: List<FamiliesModel>,
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

data class FamiliesUsesCases(
    val generateFamilies: GenerateFamilies,
    val insertFamiliesList: InsertFamiliesList
)