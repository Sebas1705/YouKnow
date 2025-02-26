package es.sebas1705.survey.viewmodel
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

import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.common.mvi.MVIBaseViewModel
import es.sebas1705.survey.SurveyUsesCases
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val surveyUsesCases: SurveyUsesCases
) : MVIBaseViewModel<SurveyState, SurveyIntent>() {

    override fun initState(): SurveyState = SurveyState.default()

    override fun intentHandler(intent: SurveyIntent) {
        when (intent) {
            else -> {}
        }
    }

    //Actions:
    //Privates:
    private fun startLoading() {
        updateUi { it.copy(isLoading = true) }
    }

    private fun stopLoading() {
        updateUi { it.copy(isLoading = false) }
    }

    private fun stopAndError(error: String, onError: (String) -> Unit) {
        stopLoading()
        execute { onError(error) }
    }
}