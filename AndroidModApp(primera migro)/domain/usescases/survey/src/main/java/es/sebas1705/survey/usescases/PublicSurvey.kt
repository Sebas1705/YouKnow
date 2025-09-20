package es.sebas1705.survey.usescases
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

import es.sebas1705.common.utlis.extensions.types.catcher
import es.sebas1705.mappers.toSurveyDocument
import es.sebas1705.mappers.toSurveyEntity
import es.sebas1705.room.repository.DatabaseRepository
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.youknow.domain.model.stats.SurveyModel

/**
 * Use case to public a survey
 *
 * @param firestoreRepository [FirestoreRepository]: repository to public the survey
 * @param databaseRepository [DatabaseRepository]: repository to insert the survey
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class PublicSurvey(
    private val firestoreRepository: FirestoreRepository,
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        surveyModel: SurveyModel,
        onLoading: () -> Unit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            onLoading()
            databaseRepository.insertOrReplace(surveyModel.toSurveyEntity())
            firestoreRepository.publicNewSurvey(
                surveyModel.toSurveyDocument()
            ).catcher(
                onEmptySuccess = onSuccess,
                onError = onError
            )
        } catch (e: Exception) {
            onError(e.message.toString())
        }
    }
}