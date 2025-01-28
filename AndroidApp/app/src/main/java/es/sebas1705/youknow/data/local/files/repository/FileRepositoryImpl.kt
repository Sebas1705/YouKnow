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

import android.app.Application
import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData
import es.sebas1705.youknow.data.firebase.analytics.config.Layer
import es.sebas1705.youknow.data.firebase.analytics.config.Repository
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepository
import es.sebas1705.youknow.data.local.files.config.SettingsFL
import es.sebas1705.youknow.data.local.files.json.FamiliesJson
import es.sebas1705.youknow.data.local.files.json.QuestionJson
import es.sebas1705.youknow.data.local.files.json.WordJson
import es.sebas1705.youknow.data.mappers.toFamiliesModel
import es.sebas1705.youknow.data.mappers.toQuestionModel
import es.sebas1705.youknow.data.mappers.toWordModel
import es.sebas1705.youknow.domain.model.games.FamiliesModel
import es.sebas1705.youknow.domain.model.games.QuestionModel
import es.sebas1705.youknow.domain.model.games.WordModel
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

    override suspend fun readDefaultBDFamilies(): List<FamiliesModel> {
        try {
            val json =
                application.assets.open(SettingsFL.FAMILIES_DEFAULT_BD_JSON).bufferedReader().use {
                    it.readText()
                }

            return Json.decodeFromString<List<FamiliesJson>>(json).map { it.toFamiliesModel() }
        } catch (ex: Exception) {
            analyticsRepository.logError(this, ex.message.toString())
            return emptyList()
        }
    }

    override suspend fun readDefaultBDQuestions(): List<QuestionModel> {
        try {
            val json =
                application.assets.open(SettingsFL.QUESTION_DEFAULT_BD_JSON).bufferedReader().use {
                    it.readText()
                }

            return Json.decodeFromString<List<QuestionJson>>(json).map { it.toQuestionModel() }
        } catch (ex: Exception) {
            analyticsRepository.logError(this, ex.message.toString())
            return emptyList()
        }
    }

    override suspend fun readDefaultBDWords(): List<WordModel> {
        try {
            val json =
                application.assets.open(SettingsFL.WORD_DEFAULT_BD_JSON).bufferedReader().use {
                    it.readText()
                }

            return Json.decodeFromString<List<WordJson>>(json).map { it.toWordModel() }
        } catch (ex: Exception) {
            analyticsRepository.logError(this, ex.message.toString())
            return emptyList()
        }
    }

}