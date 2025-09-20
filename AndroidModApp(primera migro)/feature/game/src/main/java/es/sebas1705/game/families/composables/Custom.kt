package es.sebas1705.game.families.composables
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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Start
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.common.IOutlinedButton
import es.sebas1705.designsystem.buttons.radio.IRadioButton
import es.sebas1705.designsystem.extras.DropdownList
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.spacers.PaddingSpacers.MediumSpacer
import es.sebas1705.designsystem.spacers.PaddingSpacers.SmallSpacer
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.designsystem.texts.TitleSurface
import es.sebas1705.ui.theme.YouKnowTheme
import es.sebas1705.youknow.feature.games.R

/**
 * Custom screen of the Families game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onStartGame (Difficulty, Category, Int) -> Unit: Function to start the game.
 *
 * @since 1.0.0
 * @Author Sebastián Ramiro Entrerrios
 */
@Composable
fun Custom(
    windowState: WindowState = WindowState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onStartGame: (Difficulty, Category, Int) -> Unit = { _, _, _ -> }
) {
    //Local:
    val ctx = LocalContext.current

    //States:
    var difficulty by rememberSaveable { mutableIntStateOf(Difficulty.EASY.ordinal) }
    val difficultyEnum = Difficulty.entries[difficulty]
    val category = rememberSaveable { mutableIntStateOf(Category.GENERAL_KNOWLEDGE.ordinal) }
    val categoryEnum = Category.entries[category.intValue]
    var numQuestions by rememberSaveable { mutableIntStateOf(10) }
    val titleStyle = windowState.heightType.filter(
        MaterialTheme.typography.titleSmall,
        MaterialTheme.typography.titleLarge,
        MaterialTheme.typography.headlineMedium
    )

    //Body:
    ApplyBack(
        backId = windowState.backEmpty
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                MediumSpacer()
                TitleSurface(stringResource(R.string.feature_game_custom_title))
                MediumSpacer()
            }

            item {
                IText(
                    text = stringResource(R.string.feature_game_num_questions) +
                            " $numQuestions",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = titleStyle
                )
                SmallSpacer()
                Slider(
                    modifier = Modifier
                        .fillMaxWidth(windowState.widthType.filter(0.9f, 0.7f, 0.5f)),
                    value = numQuestions / 100f,
                    onValueChange = { numQuestions = (it * 100).toInt() },
                    steps = 99,
                )
                MediumSpacer()
            }
            item {
                IText(
                    text = stringResource(R.string.feature_game_difficulty) +
                            ": " + stringResource(difficultyEnum.strRes),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = titleStyle
                )
                SmallSpacer()
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    item {
                        if (windowState.isPortrait) Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Difficulty.entries.forEach {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    IRadioButton(
                                        selected = difficulty == it.ordinal,
                                        onClick = { difficulty = it.ordinal },
                                        soundPool = soundPool
                                    )
                                    IText(
                                        text = stringResource(it.strRes),
                                        color = MaterialTheme.colorScheme.onBackground,
                                        style = MaterialTheme.typography.titleSmall,
                                    )
                                }
                            }
                        }
                        else Row {
                            Difficulty.entries.forEach {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    IRadioButton(
                                        selected = difficulty == it.ordinal,
                                        onClick = { difficulty = it.ordinal },
                                        soundPool = soundPool
                                    )
                                    IText(
                                        text = stringResource(it.strRes),
                                        color = MaterialTheme.colorScheme.onBackground,
                                        style = MaterialTheme.typography.titleSmall,
                                    )
                                }
                            }
                        }
                    }
                }
                MediumSpacer()
            }
            item {
                IText(
                    text = stringResource(R.string.feature_game_category),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = titleStyle
                )
                SmallSpacer()
                DropdownList(
                    modifier = Modifier.fillMaxWidth(
                        windowState.widthFilter(0.9f, 0.7f, 0.5f)
                    ),
                    valueRes = categoryEnum.strRes,
                    onValueChange = { s ->
                        category.intValue =
                            Category.entries.indexOfFirst { ctx.getString(it.strRes) == s }
                    },
                    soundPool = soundPool
                ) { onChanged ->
                    Category.entries.forEach {
                        DropdownMenuItem(
                            text = { Text(stringResource(it.strRes)) },
                            onClick = {
                                onChanged(it.strRes)
                            },
                            colors = MenuDefaults.itemColors(
                                textColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
                MediumSpacer()
            }
            item {
                IOutlinedButton(
                    label = stringResource(R.string.feature_game_start_game),
                    imageVector = Icons.Filled.Start,
                    onClick = { onStartGame(difficultyEnum, categoryEnum, numQuestions) },
                    soundPool = soundPool
                )
                MediumSpacer()
            }
        }
    }
}

@UiModePreviews
@Composable
private fun CustomPreview() {
    YouKnowTheme {
        Custom()
    }
}