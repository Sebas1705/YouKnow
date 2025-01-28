package es.sebas1705.youknow.presentation.features.home.features.play
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
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.presentation.features.home.features.play.design.PlayDesign

/**
 * Play Screen that will show the game to the user.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onGameNav (Int) -> Unit: The navigation to the game.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun PlayScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    onGameNav: (Int) -> Unit
) {
    //Local:
    BackHandler {}

    //Body:
    PlayDesign(
        windowState,
        soundPool,
        onGameNav
    )
}

