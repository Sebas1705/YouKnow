package es.sebas1705.guide
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
import es.sebas1705.common.states.WindowState
import es.sebas1705.guide.design.GuideDesign
import es.sebas1705.guide.viewmodel.GuideIntent
import es.sebas1705.guide.viewmodel.GuideViewModel

/**
 * Guide Screen that will show the user a guide of the app.
 * The user can navigate through the guide and start the app.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onSuccessNavigation () -> Unit: Function that will be called when the user finishes the guide.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun GuideScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    onSuccessNavigation: () -> Unit,
) {
    //ViewModel:
    val guideViewModel: GuideViewModel = hiltViewModel()

    //State:
    val guideState by guideViewModel.uiState.collectAsStateWithLifecycle()

    //Body:
    GuideDesign(
        windowState,
        guideState,
        soundPool,
        onSuccessNavigation = {
            guideViewModel.eventHandler(GuideIntent.ChargeData {
                onSuccessNavigation()
            })
        },
    )
}






