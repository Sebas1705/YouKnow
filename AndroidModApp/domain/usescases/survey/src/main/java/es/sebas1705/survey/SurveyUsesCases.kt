package es.sebas1705.survey
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

import es.sebas1705.survey.usescases.GetActualSurvey
import es.sebas1705.survey.usescases.GetAllSurveys
import es.sebas1705.survey.usescases.PublicSurvey

/**
 * Survey use cases
 *
 * @param publicSurvey [PublicSurvey]: use case to public a survey
 * @param getActualSurvey [GetActualSurvey]: use case to get the actual survey
 * @param getAllSurveys [GetAllSurveys]: use case to get all surveys
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class SurveyUsesCases(
    val publicSurvey: PublicSurvey,
    val getActualSurvey: GetActualSurvey,
    val getAllSurveys: GetAllSurveys
)