package es.sebas1705.youknow.presentation.features.app.viewmodels
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
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.core.classes.MVIBaseIntent
import es.sebas1705.youknow.core.classes.MVIBaseState
import es.sebas1705.youknow.core.classes.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.printTextInToast
import es.sebas1705.youknow.domain.usecases.DatastoreUsesCases
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
    private val application: Application
) : MVIBaseViewModel<GuideState, GuideIntent>() {

    override fun initState(): GuideState = GuideState.default()

    override fun intentHandler(intent: GuideIntent) {
        when (intent) {
            is GuideIntent.SaveFirstTime -> saveFirstTime()
        }
    }

    override fun onViewModelInit() {
        execute(Dispatchers.IO) {
            datastoreUsesCases.readFirstTime().collect { data ->
                updateUi { it.copy(firstTime = data) }
            }
        }
    }

    //Actions:
    private fun saveFirstTime() {
        execute(Dispatchers.IO) {
            datastoreUsesCases.saveFirstTime()
            updateUi { it.copy(firstTime = false) }
        }
    }
}

/**
 * State for Guide Screen that will handle the first time the app is opened.
 *
 * @property firstTime [Boolean]: Flag that indicates if it is the first time the app is opened.
 *
 * @see MVIBaseState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class GuideState(
    val firstTime: Boolean
) : MVIBaseState {
    companion object {

        /**
         * Default state for Guide Screen.
         *
         * @return [GuideState]
         */
        fun default() = GuideState(
            firstTime = true
        )
    }
}

/**
 * Sealed class that represents the possible intents for Guide Screen.
 *
 * @see MVIBaseIntent
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface GuideIntent : MVIBaseIntent {
    data object SaveFirstTime : GuideIntent
}



