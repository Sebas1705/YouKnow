package es.sebas1705.youknow.data.local.files.repository
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

import es.sebas1705.youknow.domain.model.games.FamiliesModel
import es.sebas1705.youknow.domain.model.games.QuestionModel
import es.sebas1705.youknow.domain.model.games.WordModel

/**
 * Repository interface to write and read data from the files
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
interface FileRepository {

    /**
     * Read the default families from the file
     *
     * @return [List]<[FamiliesModel]>: List of families
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun readDefaultBDFamilies(): List<FamiliesModel>

    /**
     * Read the default questions from the file
     *
     * @return [List]<[QuestionModel]>: List of questions
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun readDefaultBDQuestions(): List<QuestionModel>

    /**
     * Read the default words from the file
     *
     * @return [List]<[WordModel]>: List of words
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun readDefaultBDWords(): List<WordModel>

}