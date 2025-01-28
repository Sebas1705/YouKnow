package es.sebas1705.youknow.presentation.features.game.features.quiz.composables
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
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.enums.games.Category
import es.sebas1705.youknow.core.classes.enums.games.Difficulty
import es.sebas1705.youknow.core.classes.enums.games.quiz.QuizType
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.IOutlinedButton
import es.sebas1705.youknow.core.composables.buttons.radio.IRadioButton
import es.sebas1705.youknow.core.composables.buttons.segmented.ISingleChoiceSegmentedButton
import es.sebas1705.youknow.core.composables.extras.DropdownList
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.spacers.PaddingSpacers.MediumSpacer
import es.sebas1705.youknow.core.composables.spacers.PaddingSpacers.SmallSpacer
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.composables.texts.TitleSurface
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.game.features.quiz.viewmodel.QuizState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Custom mode of the Quiz game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param quizState [QuizState]: State of the game.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onStartGame ([Difficulty], [Category], [QuizType], [Int]) -> Unit: Function to start the game.
 *
 * @since 1.0.0
 * @Author Sebastián Ramiro Entrerrios
 */
@Composable
fun Custom(
    windowState: WindowState = WindowState.default(),
    quizState: QuizState = QuizState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onStartGame: (Difficulty, Category, QuizType, Int) -> Unit = { _, _, _, _ -> }
) {
    //Local:
    val ctx = LocalContext.current

    //States:
    val difficulty = rememberSaveable { mutableIntStateOf(Difficulty.EASY.ordinal) }
    val difficultyEnum = Difficulty.entries[difficulty.intValue]
    var category by rememberSaveable { mutableIntStateOf(Category.GENERAL_KNOWLEDGE.ordinal) }
    val categoryEnum = Category.entries[category]
    val quizType = rememberSaveable { mutableIntStateOf(QuizType.MULTIPLE.ordinal) }
    val quizTypeEnum = QuizType.entries[quizType.intValue]
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
                .padding(horizontal = SmallPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                MediumSpacer()
                TitleSurface(stringResource(R.string.custom_title))
                MediumSpacer()
            }
            item {
                IText(
                    text = stringResource(R.string.num_questions) +
                            " $numQuestions",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = titleStyle
                )
                SmallSpacer()
                Slider(
                    modifier = Modifier
                        .fillMaxWidth(windowState.widthType.filter(0.8f, 0.6f, 0.4f)),
                    value = numQuestions / 100f,
                    onValueChange = { numQuestions = (it * 100).toInt() },
                    steps = 99,
                )
                MediumSpacer()
            }

            item {
                IText(
                    text = stringResource(R.string.type) +
                            ": " + stringResource(quizTypeEnum.strRes),
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
                            QuizType.entries.forEach {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    IRadioButton(
                                        selected = quizType.intValue == it.ordinal,
                                        onClick = { quizType.intValue = it.ordinal },
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
                            QuizType.entries.forEach {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    IRadioButton(
                                        selected = quizType.intValue == it.ordinal,
                                        onClick = { quizType.intValue = it.ordinal },
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
                    text = stringResource(R.string.difficulty) +
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
                        ISingleChoiceSegmentedButton(
                            elements = Difficulty.entries.map {
                                Triple(
                                    stringResource(it.strRes),
                                    null,
                                    true
                                )
                            },
                            selectedElement = difficulty,
                            soundPool = soundPool
                        )
                    }
                }
                MediumSpacer()
            }
            item {
                IText(
                    text = stringResource(R.string.category),
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
                        category = Category.entries.indexOfFirst { ctx.getString(it.strRes) == s }
                    },
                    soundPool = soundPool
                ) { onChanged ->
                    Category.entries.forEach {
                        DropdownMenuItem(
                            text = { Text(stringResource(it.strRes)) },
                            onClick = { onChanged(it.strRes) },
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
                    onClick = {
                        onStartGame(
                            difficultyEnum,
                            categoryEnum,
                            quizTypeEnum,
                            numQuestions
                        )
                    },
                    label = stringResource(R.string.start_game),
                    imageVector = Icons.Filled.Start,
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



