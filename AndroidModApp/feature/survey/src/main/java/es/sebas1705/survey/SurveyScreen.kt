package es.sebas1705.youknow.presentation.features.survey
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

import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.presentation.features.survey.design.SurveyDesign
import es.sebas1705.youknow.presentation.features.survey.viewmodel.SurveyViewModel

/**
 * Survey Screen of the app.
 *
 * @param windowState [WindowState]: state of the window
 * @param soundPool [Pair]<[SoundPool], [Float]>: sound pool and volume
 * @param onBack () -> Unit: action to go back
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
 */
@Composable
fun SurveyScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    onBack: () -> Unit
) {
    //Viewmodel:
    val surveyViewModel: SurveyViewModel = hiltViewModel()

    //State:
    val surveyState by surveyViewModel.uiState.collectAsStateWithLifecycle()

    //Body:
    SurveyDesign(
        windowState,
        surveyState,
        soundPool,
        onBack
    )
}