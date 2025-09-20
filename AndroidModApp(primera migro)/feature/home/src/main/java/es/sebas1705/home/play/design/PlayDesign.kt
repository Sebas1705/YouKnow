package es.sebas1705.home.play.design
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.ComposableConstants.GAME_BUTTON_SOUND
import es.sebas1705.designsystem.ComposableConstants.LOOP_N
import es.sebas1705.designsystem.ComposableConstants.PRIORITY_SOUND
import es.sebas1705.designsystem.ComposableConstants.RATE
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.texts.Title
import es.sebas1705.home.play.GameItem.Companion.games
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.YouKnowTheme

/**
 * Design of the Play Screen.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onGameNav (Int) -> Unit: The navigation to the game.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun PlayDesign(
    windowState: WindowState = WindowState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onGameNav: (Int) -> Unit = {}
) {
    //Body:
    ApplyBack(
        windowState.backFill
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.Center
        ) {
            items(games.size) {
                //Local:
                val context = LocalContext.current

                val soundId = remember {
                    soundPool?.first?.load(context, GAME_BUTTON_SOUND, PRIORITY_SOUND)
                }
                val game = games[it]
                Column(
                    modifier = Modifier
                        .padding(SmallPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(game.icon),
                        contentDescription = stringResource(game.strRes),
                        modifier = Modifier
                            .padding(SmallPadding)
                            .clickable {
                                soundPool?.first?.play(
                                    soundId ?: 0,
                                    soundPool.second,
                                    soundPool.second,
                                    PRIORITY_SOUND,
                                    LOOP_N,
                                    RATE
                                )
                                onGameNav(it)
                            }
                    )
                    Title(
                        text = stringResource(game.strRes),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}





@UiModePreviews
@Composable
private fun PlayPreview() {
    YouKnowTheme {
        PlayDesign()
    }
}