package es.sebas1705.game
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import es.sebas1705.common.states.WindowState
import es.sebas1705.designsystem.dialogs.GameOutDialog
import es.sebas1705.game.families.FamiliesScreen
import es.sebas1705.game.mysterynumber.MysteryNumberScreen
import es.sebas1705.game.quiz.QuizScreen
import es.sebas1705.game.wordpass.WordPassScreen

/**
 * Navigation for the game.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onMusicChange (Boolean) -> Unit: Function that will change the music.
 * @param game [GameScreens.Companion.GameItem]: The game to navigate to.
 * @param onOutGameNavigation () -> Unit: Function that will be called when the user wants to go out of the game.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun GameNav(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    onMusicChange: (Boolean) -> Unit,
    gameIndex: Int = 0,
    onOutGameNavigation: () -> Unit,
) {
    //Flags:
    var outFlag by rememberSaveable { mutableStateOf(false) }

    //Local:
    BackHandler { outFlag = true }
    DisposableEffect(Unit) {
        onMusicChange(false)
        onDispose { onMusicChange(true) }
    }

    //Body:
    if (outFlag) GameOutDialog(
        onConfirm = {
            outFlag = false
            onOutGameNavigation()
        },
        onDismiss = { outFlag = false },
        soundPool = soundPool,
    )

    when (gameIndex) {
        0 -> {
            MysteryNumberScreen(
                windowState,
                soundPool,
                onOutGameNavigation
            )
        }

        1 -> {
            QuizScreen(
                windowState,
                soundPool,
                onOutGameNavigation
            )
        }

        2 -> {
            WordPassScreen(
                windowState,
                soundPool,
                onOutGameNavigation
            )
        }

        else -> {
            FamiliesScreen(
                windowState,
                soundPool,
                onOutGameNavigation
            )
        }
    }
}






