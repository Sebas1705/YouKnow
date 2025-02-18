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

import es.sebas1705.mappers.toWordEntity
import es.sebas1705.models.games.WordModel
import es.sebas1705.room.repository.DatabaseRepository

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
            databaseRepository.insertOrReplace(it.toWordEntity())
        }
        onSuccess()
    }
}