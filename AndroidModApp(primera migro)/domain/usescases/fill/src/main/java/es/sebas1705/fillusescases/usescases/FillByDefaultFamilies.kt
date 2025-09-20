package es.sebas1705.fillusescases.usescases
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

import android.util.Log
import es.sebas1705.files.repository.FileRepository
import es.sebas1705.mappers.toFamiliesEntity
import es.sebas1705.room.repository.DatabaseRepository

/**
 * Use case to fill the database with default families
 *
 * @param fileRepository [FileRepository]: Repository to get the default families
 * @param databaseRepository [DatabaseRepository]: Repository to insert the families
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class FillByDefaultFamilies(
    private val fileRepository: FileRepository,
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        onLoading: () -> Unit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val families = fileRepository.readDefaultBDFamilies()
        Log.d("FillUsesCases", "invoke: $families")
        families.forEach { databaseRepository.insertOrReplace(it.toFamiliesEntity()) }
        if(families.isEmpty()) onError("Empty Families")
        else onSuccess()
    }
}