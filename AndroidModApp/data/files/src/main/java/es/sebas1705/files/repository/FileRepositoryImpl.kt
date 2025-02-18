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

import android.app.Application
import es.sebas1705.common.managers.ClassLogData
import es.sebas1705.common.managers.Layer
import es.sebas1705.common.managers.Repository
import es.sebas1705.files.config.SettingsFL
import es.sebas1705.files.json.FamiliesJson
import es.sebas1705.files.json.QuestionJson
import es.sebas1705.files.json.WordJson
import es.sebas1705.analytics.repository.AnalyticsRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 * Class to represent the repository of the files
 *
 * @property application [Application]: Application
 * @property analyticsRepository [AnalyticsRepository]: Analytics repository
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class FileRepositoryImpl @Inject constructor(
    private val application: Application,
    private val analyticsRepository: AnalyticsRepository
) : FileRepository, ClassLogData {

    override val layer: Layer = Layer.Data
    override val repository: Repository = Repository.Files

    override suspend fun readDefaultBDFamilies(): List<FamiliesJson> {
        try {
            val json =
                application.assets.open(SettingsFL.FAMILIES_DEFAULT_BD_JSON).bufferedReader().use {
                    it.readText()
                }

            return Json.decodeFromString<List<FamiliesJson>>(json)
        } catch (ex: Exception) {
            analyticsRepository.logError(this, ex.message.toString())
            return emptyList()
        }
    }

    override suspend fun readDefaultBDQuestions(): List<QuestionJson> {
        try {
            val json =
                application.assets.open(SettingsFL.QUESTION_DEFAULT_BD_JSON).bufferedReader().use {
                    it.readText()
                }

            return Json.decodeFromString<List<QuestionJson>>(json)
        } catch (ex: Exception) {
            analyticsRepository.logError(this, ex.message.toString())
            return emptyList()
        }
    }

    override suspend fun readDefaultBDWords(): List<WordJson> {
        try {
            val json =
                application.assets.open(SettingsFL.WORD_DEFAULT_BD_JSON).bufferedReader().use {
                    it.readText()
                }

            return Json.decodeFromString<List<WordJson>>(json)
        } catch (ex: Exception) {
            analyticsRepository.logError(this, ex.message.toString())
            return emptyList()
        }
    }

}