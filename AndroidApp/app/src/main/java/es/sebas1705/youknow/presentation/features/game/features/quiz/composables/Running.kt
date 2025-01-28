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
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.enums.games.Difficulty
import es.sebas1705.youknow.core.classes.enums.games.quiz.QuizMode
import es.sebas1705.youknow.core.classes.enums.games.quiz.QuizType
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.IOutlinedButton
import es.sebas1705.youknow.core.composables.cards.IPrimaryCard
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.texts.Title
import es.sebas1705.youknow.core.composables.texts.TitleSurface
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.extensions.primitives.toReducedString
import es.sebas1705.youknow.presentation.features.game.features.quiz.viewmodel.QuizState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme
import es.sebas1705.youknow.presentation.ui.theme.gameBottomBarHeight
import kotlinx.coroutines.delay

/**
 * Running of the Quiz game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param quizState [QuizState]: State of the game.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onResponseQuestion ([String]) -> Unit: Function to respond to the question.
 *
 * @since 1.0.0
 * @Author Sebastián Ramiro Entrerrios García
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Running(
    windowState: WindowState = WindowState.default(),
    quizState: QuizState = QuizState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onResponseQuestion: (String) -> Unit = { }
) {
    //Body:
    ApplyBack(
        backId = windowState.backFill
    ) {
        if (quizState.questions.isEmpty()) {
            Title(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.error_loading_questions),
                color = MaterialTheme.colorScheme.error
            )
            return@ApplyBack
        }
        var time by rememberSaveable { mutableFloatStateOf(15f) }
        val question = quizState.questions[quizState.actualQuestion]
        if (quizState.mode == QuizMode.TIME_ATTACK) {
            LaunchedEffect(question) {
                while (time > 0) {
                    delay(10)
                    time -= 0.01f
                }
                onResponseQuestion("TIME_OUT")
            }
        }
        val color = when (question.difficulty) {
            Difficulty.EASY -> Color.Green
            Difficulty.MEDIUM -> Color.Yellow
            Difficulty.HARD -> Color.Red
            else -> MaterialTheme.colorScheme.tertiary
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TitleSurface(
                    modifier = Modifier
                        .padding(MediumPadding)
                        .border(1.dp, color, MaterialTheme.shapes.small),
                    text = question.question,
                    textAlign = TextAlign.Justify
                )
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val answers = question.answers
                    if (question.quizType == QuizType.BOOLEAN) {
                        IOutlinedButton(
                            onClick = { onResponseQuestion(answers[0]) },
                            label = answers[0],
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(SmallestPadding),
                            soundPool = soundPool
                        )
                        IOutlinedButton(
                            onClick = { onResponseQuestion(answers[1]) },
                            label = answers[1],
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(SmallestPadding),
                            soundPool = soundPool
                        )
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            IOutlinedButton(
                                onClick = { onResponseQuestion(answers[0]) },
                                label = answers[0],
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .padding(SmallestPadding),
                                soundPool = soundPool
                            )
                            IOutlinedButton(
                                onClick = { onResponseQuestion(answers[1]) },
                                label = answers[1],
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .padding(SmallestPadding),
                                soundPool = soundPool
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            IOutlinedButton(
                                onClick = { onResponseQuestion(answers[2]) },
                                label = answers[2],
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .padding(SmallestPadding),
                                soundPool = soundPool
                            )
                            IOutlinedButton(
                                onClick = { onResponseQuestion(answers[3]) },
                                label = answers[3],
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .padding(SmallestPadding),
                                soundPool = soundPool
                            )
                        }
                    }
                }
            }

            stickyHeader {
                IPrimaryCard(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(gameBottomBarHeight)
                        .padding(bottom = SmallestPadding)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(SmallestPadding),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Title(
                            text = "${stringResource(R.string.points)}: ${quizState.points.toReducedString()}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Title(
                            text = "${quizState.actualQuestion + 1}/${quizState.questions.size}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        if (quizState.mode == QuizMode.SURVIVAL) Row {
                            (1..3).forEach {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = stringResource(R.string.lives),
                                    tint = if (it <= quizState.lives) MaterialTheme.colorScheme.tertiary else Color.Gray
                                )
                            }
                        }
                        else if (quizState.mode == QuizMode.TIME_ATTACK) Row {
                            (0..2).forEach {
                                Icon(
                                    imageVector = Icons.Filled.Timer,
                                    contentDescription = stringResource(R.string.time),
                                    tint = if (it <= time / 5) MaterialTheme.colorScheme.tertiary else Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@UiModePreviews
@Composable
private fun RunningPreview() {
    YouKnowTheme {
        Running()
    }
}
