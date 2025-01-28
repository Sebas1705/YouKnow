package es.sebas1705.youknow.presentation.features.guide.viewmodel
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
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.domain.usecases.DatastoreUsesCases
import es.sebas1705.youknow.domain.usecases.FillUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for Guide Screen that will handle the first time the app is opened.
 * It will show the guide screen if it is the first time the app is opened.
 * If it is not the first time, it will navigate to the Home Screen.
 *
 * @param datastoreUsesCases [DatastoreUsesCases]: UseCase to check if the app is opened for the first time.
 *
 * @see MVIBaseViewModel
 * @see HiltViewModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class GuideViewModel @Inject constructor(
    private val datastoreUsesCases: DatastoreUsesCases,
    private val fillUsesCases: FillUsesCases
) : MVIBaseViewModel<GuideState, GuideIntent>() {

    override fun initState(): GuideState = GuideState.default()

    override fun intentHandler(intent: GuideIntent) {
        when (intent) {
            is GuideIntent.SaveFirstTime -> saveFirstTime()
            is GuideIntent.FillData -> fillData(intent)
        }
    }

    override fun onInit() {
        execute(Dispatchers.IO) {
            datastoreUsesCases.readFirstTime().collect { data ->
                updateUi { it.copy(firstTime = data) }
            }
        }
    }

    //Actions:
    private fun saveFirstTime() = execute(Dispatchers.IO) {
        datastoreUsesCases.saveFirstTime()
        updateUi { it.copy(firstTime = false) }
    }

    private fun fillData(
        intent: GuideIntent.FillData
    ) = execute(Dispatchers.IO) {
        updateUi { it.copy(isLoading = true) }
        fillUsesCases.fillByDefaultWords()
        fillUsesCases.fillByDefaultFamilies()
        fillUsesCases.fillByDefaultQuestions()
        updateUi { it.copy(isLoading = false) }
        execute { intent.onSuccess() }
    }

}






