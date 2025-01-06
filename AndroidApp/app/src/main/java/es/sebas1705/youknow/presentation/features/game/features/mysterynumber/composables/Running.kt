package es.sebas1705.youknow.presentation.features.game.features.mysterynumber.composables
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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.IFilledButton
import es.sebas1705.youknow.core.composables.cards.IPrimaryCard
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.texts.Subtitle
import es.sebas1705.youknow.core.composables.texts.Title
import es.sebas1705.youknow.core.composables.texts.TitleSurface
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.extensions.primitives.toReducedString
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.viewmodel.MysteryNumberMode
import es.sebas1705.youknow.presentation.features.game.features.mysterynumber.viewmodel.MysteryNumberState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme
import es.sebas1705.youknow.presentation.ui.theme.gameBottomBarHeight
import kotlinx.coroutines.delay

enum class Numbers(val number: Int, val str: String) {
    ONE(1, "1"),
    TEN(10, "10"),
    HUNDRED(100, "100"),
    THOUSAND(1_000, "1K"),
    TEN_THOUSAND(10_000, "10K"),
    HUNDRED_THOUSAND(100_000, "100K")
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Running(
    windowState: WindowState = WindowState.default(),
    mysteryNumberState: MysteryNumberState = MysteryNumberState.default(),
    onResponseNumber: (Int, Float) -> Unit = { _, _ -> }
) {
    ApplyBack(
        backId = windowState.backFill
    ) {
        if (mysteryNumberState.number.number == -2) {
            Title(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.error_loading_number),
                color = MaterialTheme.colorScheme.error
            )
            return@ApplyBack
        }
        var time by rememberSaveable { mutableFloatStateOf(15f) }
        val number = mysteryNumberState.number
        var actualNumber by rememberSaveable { mutableIntStateOf(0) }
        var plus by rememberSaveable { mutableStateOf(true) }
        if (mysteryNumberState.mode == MysteryNumberMode.TIME_ATTACK) {
            LaunchedEffect(number) {
                time = 50f
                while (time > 0) {
                    delay(10)
                    time -= 0.01f
                }
                onResponseNumber(-1, time)
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TitleSurface(
                    modifier = Modifier
                        .padding(MediumPadding),
                    text = stringResource(R.string.mystery_title)
                )
            }
            item {
                Subtitle(
                    text = stringResource(R.string.difficulty)
                            + ": " + stringResource(number.difficulty.strRes)
                            + "\n" + stringResource(R.string.range) + "1 - ${number.difficulty.maxMysteryNumber}"
                )
            }
            item {
                Title(
                    modifier = Modifier
                        .padding(MediumPadding),
                    text = actualNumber.toString(),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            item {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(
                        (if (windowState.isPortrait) windowState.widthDp else windowState.heightDp)
                                * 0.33f
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(
                            if (windowState.isPortrait) windowState.widthDp * 0.5f
                            else windowState.widthDp * 0.1f
                        )
                ) {
                    items(Numbers.entries.size) {
                        IFilledButton(
                            onClick = {
                                actualNumber += if (plus) Numbers.entries[it].number else -Numbers.entries[it].number
                                if (actualNumber < 0) actualNumber = 0
                                if (actualNumber > 1_000_000) actualNumber = 1_000_000
                            },
                            label = (if (plus) "+" else "-") + Numbers.entries[it].str,
                            modifier = Modifier.padding(SmallestPadding)
                        )
                    }
                }
            }
            item {
                IFilledButton(
                    onClick = {
                        plus = !plus
                    },
                    label = stringResource(R.string.minus_plus),
                    modifier = Modifier.padding(SmallestPadding)
                )
                IFilledButton(
                    onClick = {
                        onResponseNumber(actualNumber, time)
                    },
                    label = stringResource(R.string.try_number),
                    modifier = Modifier.padding(MediumPadding)
                )
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
                            text = "${stringResource(R.string.points)}: ${mysteryNumberState.points.toReducedString()}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Row {
                            Title(
                                text = "${mysteryNumberState.lives}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = stringResource(R.string.lives),
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                        }
                        if (mysteryNumberState.mode == MysteryNumberMode.TIME_ATTACK) Row {
                            Title(
                                text = "${time.toInt()}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Icon(
                                imageVector = Icons.Filled.Timer,
                                contentDescription = stringResource(R.string.time),
                                tint = MaterialTheme.colorScheme.tertiary
                            )
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
