package es.sebas1705.youknow.presentation.features.game.screens.families.composables
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

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.IFilledButton
import es.sebas1705.youknow.core.composables.cards.IPrimaryCard
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.texts.Title
import es.sebas1705.youknow.core.composables.texts.TitleSurface
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.extensions.primitives.toReducedString
import es.sebas1705.youknow.presentation.features.game.viewmodels.FamiliesMode
import es.sebas1705.youknow.presentation.features.game.viewmodels.FamiliesState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme
import kotlinx.coroutines.delay

@Composable
fun Running(
    windowState: WindowState = WindowState.default(),
    familiesState: FamiliesState = FamiliesState.default(),
    onResponseQuestion: (String) -> Unit = { }
) {
    ApplyBack(
        backId = windowState.backFill
    ) {
        if (familiesState.families.isEmpty()) {
            Title(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.error_loading_questions),
                color = MaterialTheme.colorScheme.error
            )
            return@ApplyBack
        }
        var time by remember { mutableFloatStateOf(15f) }
        val family = familiesState.families[familiesState.actualFamily]
        if (familiesState.mode == FamiliesMode.TIME_ATTACK) {
            LaunchedEffect(family) {
                time = 10f
                while (time > 0) {
                    delay(10)
                    time -= 0.01f
                }
                onResponseQuestion("TIME_OUT")
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val color = when (family.difficulty) {
                Difficulty.EASY -> Color.Green
                Difficulty.MEDIUM -> Color.Yellow
                Difficulty.HARD -> Color.Red
                else -> MaterialTheme.colorScheme.tertiary
            }
            TitleSurface(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(MediumPadding)
                    .border(1.dp, color, MaterialTheme.shapes.small),
                text = stringResource(R.string.families_game)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.6f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val answers = family.answers
                Log.i("Running", "answers: $answers")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    IFilledButton(
                        onClick = { onResponseQuestion(answers[0]) },
                        label = answers[0],
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .padding(SmallestPadding),
                    )
                    IFilledButton(
                        onClick = { onResponseQuestion(answers[1]) },
                        label = answers[1],
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .padding(SmallestPadding),
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    IFilledButton(
                        onClick = { onResponseQuestion(answers[2]) },
                        label = answers[2],
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .padding(SmallestPadding),
                    )
                    IFilledButton(
                        onClick = { onResponseQuestion(answers[3]) },
                        label = answers[3],
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .padding(SmallestPadding),
                    )
                }
            }
        }

        IPrimaryCard(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.075f)
                .align(Alignment.BottomCenter)
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
                    text = "Game points: ${familiesState.points.toReducedString()}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Title(
                    text = "Question: ${familiesState.actualFamily + 1}/${familiesState.families.size}",
                    style = MaterialTheme.typography.bodyMedium
                )
                if (familiesState.mode == FamiliesMode.SURVIVAL) Row {
                    (1..3).forEach {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Life",
                            tint = if (it <= familiesState.lives) MaterialTheme.colorScheme.tertiary else Color.Gray
                        )
                    }
                }
                else if (familiesState.mode == FamiliesMode.TIME_ATTACK) Row {
                    (1..3).forEach {
                        Icon(
                            imageVector = Icons.Filled.Timer,
                            contentDescription = "Time",
                            tint = if (it <= time / 3.33 + 1) MaterialTheme.colorScheme.tertiary else Color.Gray
                        )
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
