package es.sebas1705.files.repository
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

import es.sebas1705.files.json.FamiliesJson
import es.sebas1705.files.json.QuestionJson
import es.sebas1705.files.json.WordJson

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
     * @return [List]<[FamiliesJson]>: List of families
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun readDefaultBDFamilies(): List<FamiliesJson>

    /**
     * Read the default questions from the file
     *
     * @return [List]<[QuestionJson]>: List of questions
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun readDefaultBDQuestions(): List<QuestionJson>

    /**
     * Read the default words from the file
     *
     * @return [List]<[WordJson]>: List of words
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun readDefaultBDWords(): List<WordJson>

}