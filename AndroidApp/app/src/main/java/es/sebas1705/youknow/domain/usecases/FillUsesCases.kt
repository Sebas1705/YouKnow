package es.sebas1705.youknow.domain.usecases
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

import es.sebas1705.youknow.data.local.database.repository.DatabaseRepository
import es.sebas1705.youknow.data.local.files.repository.FileRepository

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
    suspend operator fun invoke() {
        val families = fileRepository.readDefaultBDFamilies()
        families.forEach { databaseRepository.insertOrReplace(it) }
    }
}

/**
 * Use case to fill the database with default questions
 *
 * @param fileRepository [FileRepository]: Repository to get the default questions
 * @param databaseRepository [DatabaseRepository]: Repository to insert the questions
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class FillByDefaultQuestions(
    private val fileRepository: FileRepository,
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke() {
        val questions = fileRepository.readDefaultBDQuestions()
        questions.forEach { databaseRepository.insertOrReplace(it) }
    }
}

/**
 * Use case to fill the database with default words
 *
 * @param fileRepository [FileRepository]: Repository to get the default words
 * @param databaseRepository [DatabaseRepository]: Repository to insert the words
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class FillByDefaultWords(
    private val fileRepository: FileRepository,
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke() {
        val words = fileRepository.readDefaultBDWords()
        words.forEach { databaseRepository.insertOrReplace(it) }
    }
}

/**
 * Use case to fill the database with default families, questions and words
 *
 * @param fillByDefaultFamilies [FillByDefaultFamilies]: Use case to fill the database with default families
 * @param fillByDefaultQuestions [FillByDefaultQuestions]: Use case to fill the database with default questions
 * @param fillByDefaultWords [FillByDefaultWords]: Use case to fill the database with default words
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class FillUsesCases(
    val fillByDefaultFamilies: FillByDefaultFamilies,
    val fillByDefaultQuestions: FillByDefaultQuestions,
    val fillByDefaultWords: FillByDefaultWords
)
