package es.sebas1705.survey.design
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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.common.states.WindowState
import es.sebas1705.survey.viewmodel.SurveyState
import es.sebas1705.youknow.domain.model.stats.SurveyModel

/**
 * Survey Design of the app.
 *
 * @param windowState [WindowState]: state of the window
 * @param soundPool [Pair]<[SoundPool], [Float]>: sound pool and volume
 * @param onBack () -> Unit: action to go back
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
 */
@Composable
fun SurveyDesign(
    windowState: WindowState = WindowState.default(),
    surveyState: SurveyState = SurveyState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onBack: () -> Unit = {}
) {
    val pagerState = rememberPagerState(initialPage = 0) { SurveyModel.PAGES_N }
    Scaffold(
        topBar = {

        }
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding)
        )
    }

}