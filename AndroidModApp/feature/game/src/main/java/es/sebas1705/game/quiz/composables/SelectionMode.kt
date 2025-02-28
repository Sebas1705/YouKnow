package es.sebas1705.game.quiz.composables
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.games.families.FamiliesMode
import es.sebas1705.common.games.quiz.QuizMode
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.theme.SizeType
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.common.IFilledTonalButton
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.texts.Title
import es.sebas1705.ui.theme.Paddings.HugePadding
import es.sebas1705.ui.theme.Paddings.LargePadding
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.Paddings.SmallestPadding
import es.sebas1705.ui.theme.YouKnowTheme
import es.sebas1705.youknow.feature.games.R

/**
 * Selection mode of the Quiz game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onSelectMode ([QuizMode]) -> Unit: Function to select the game mode.
 *
 * @since 1.0.0
 * @Author Sebastián Ramiro Entrerrios García
 */
@Composable
fun SelectionMode(
    windowState: WindowState = WindowState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onSelectMode: (QuizMode) -> Unit = { }
) {
    //Body:
    ApplyBack(
        backId = windowState.backEmpty
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Title(
                    modifier = Modifier.padding(bottom = windowState.heightFilter(
                        MediumPadding,
                        LargePadding,
                        HugePadding
                    )),
                    text = stringResource(id = R.string.feature_game_mode_title)
                )
            }

            if (windowState.widthType == SizeType.COMPACT)
                items(QuizMode.entries.size) {
                    val mode = QuizMode.entries[it]
                    IFilledTonalButton(
                        modifier = Modifier
                            .fillMaxWidth(
                                windowState.widthFilter(0.9f, 0.7f, 0.5f)
                            )
                            .padding(vertical = SmallestPadding),
                        onClick = { onSelectMode(mode) },
                        label = stringResource(id = mode.strRes),
                        imageVector = mode.icon,
                        soundPool = soundPool
                    )
                }
            else
                items(QuizMode.entries.size) { index ->
                    if (index % 2 != 0)
                        return@items
                    val mode1 = QuizMode.entries[index]
                    val mode2 = QuizMode.entries.getOrNull(index + 1)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement =
                        if (mode2 != null) Arrangement.SpaceEvenly
                        else Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val width = windowState.widthDp / 3
                        IFilledTonalButton(
                            onClick = { onSelectMode(mode1) },
                            label = stringResource(id = mode1.strRes),
                            imageVector = mode1.icon,
                            modifier = Modifier
                                .width(width)
                                .fillMaxHeight(0.25f),
                            soundPool = soundPool
                        )
                        mode2?.let {
                            IFilledTonalButton(
                                onClick = { onSelectMode(it) },
                                label = stringResource(id = it.strRes),
                                imageVector = it.icon,
                                modifier = Modifier
                                    .width(width)
                                    .fillMaxHeight(0.25f),
                                soundPool = soundPool
                            )
                        }
                    }
                    if(index != FamiliesMode.entries.size - 1 && index != FamiliesMode.entries.size - 2) {
                        Spacer(modifier = Modifier.height(MediumPadding))
                    }
                }
        }
    }
}

@UiModePreviews
@Composable
private fun SelectModePreview() {
    YouKnowTheme {
        SelectionMode()
    }
}