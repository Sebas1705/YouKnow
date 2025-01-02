package es.sebas1705.youknow.presentation.features.game.screens.wordpass.composables
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

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import es.sebas1705.youknow.core.composables.buttons.common.ITextButton
import es.sebas1705.youknow.core.composables.buttons.icon.IStandardIconButton
import es.sebas1705.youknow.core.composables.cards.IPrimaryCard
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.textfields.IOutlinedTextField
import es.sebas1705.youknow.core.composables.texts.Title
import es.sebas1705.youknow.core.composables.texts.TitleSurface
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.extensions.numerables.toViewList
import es.sebas1705.youknow.core.utlis.extensions.primitives.toReducedString
import es.sebas1705.youknow.presentation.features.game.viewmodels.WordPassMode
import es.sebas1705.youknow.presentation.features.game.viewmodels.WordPassState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun Running(
    windowState: WindowState = WindowState.default(),
    wordPassState: WordPassState = WordPassState.default(),
    onResponse: (String) -> Unit = { }
) {
    ApplyBack(
        backId = windowState.backFill
    ) {
        if (wordPassState.words.isEmpty()) {
            Title(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.error_loading_questions),
                color = MaterialTheme.colorScheme.error
            )
            return@ApplyBack
        }
        val word = wordPassState.words[wordPassState.actualWord]
        val definitions = word.definitions
        var definition by remember { mutableStateOf(definitions[0]) }
        val color = when (word.difficulty) {
            Difficulty.EASY -> Color.Green
            Difficulty.MEDIUM -> Color.Yellow
            Difficulty.HARD -> Color.Red
            else -> MaterialTheme.colorScheme.tertiary
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleSurface(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(MediumPadding)
                    .border(1.dp, color, MaterialTheme.shapes.small),
                text = word.toMoultedString(),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.2f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var response by remember { mutableStateOf("") }
                IOutlinedTextField(
                    value = response,
                    onValueChange = { response = it },
                    modifier = Modifier.fillMaxWidth(0.6f)
                )
                ITextButton(
                    onClick = { onResponse(response) },
                    label = stringResource(R.string.try_word),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.4f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IStandardIconButton(
                    imageVector = Icons.AutoMirrored.Filled.ArrowLeft,
                    contentDescription = stringResource(R.string.previous_definition),
                    onClick = {
                        definition =
                            definitions[(definitions.indexOf(definition) - 1).coerceAtLeast(0)]
                    }
                )
                Title(
                    text = definition,
                    style = MaterialTheme.typography.bodyMedium,
                )
                IStandardIconButton(
                    imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                    contentDescription = stringResource(R.string.next_definition),
                    onClick = {
                        definition =
                            definitions[(definitions.indexOf(definition) + 1).coerceAtMost(
                                definitions.size - 1
                            )]
                    }
                )

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
                    text = "Game points: ${wordPassState.points.toReducedString()}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Row {
                    val titles = wordPassState.words.toViewList(wordPassState.actualWord)
                    Title(
                        modifier = Modifier.padding(end = SmallestPadding),
                        text = titles[0],
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Title(
                        text = titles[1],
                        style = MaterialTheme.typography.titleMedium,
                        color = color
                    )
                    Title(
                        modifier = Modifier.padding(start = SmallestPadding),
                        text = titles[2],
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                if (wordPassState.mode == WordPassMode.SURVIVAL) Row {
                    (1..3).forEach {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Life",
                            tint = if (it <= wordPassState.lives) MaterialTheme.colorScheme.tertiary else Color.Gray
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
