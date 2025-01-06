package es.sebas1705.youknow.presentation.features.game.features.wordpass.composables
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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.IOutlinedButton
import es.sebas1705.youknow.core.composables.cards.IResumeCard
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.spacers.IVerSpacer
import es.sebas1705.youknow.core.composables.texts.Title
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.extensions.primitives.toReducedString
import es.sebas1705.youknow.presentation.features.game.features.wordpass.viewmodel.WordPassMode
import es.sebas1705.youknow.presentation.features.game.features.wordpass.viewmodel.WordPassState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun Finished(
    windowState: WindowState = WindowState.default(),
    wordPassState: WordPassState = WordPassState.default(),
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { }
) {
    ApplyBack(
        backId = windowState.backEmpty
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                val data = mutableMapOf<String, String>(
                    stringResource(id = R.string.mode) to (stringResource(
                        wordPassState.mode?.strRes ?: R.string.any
                    )),
                    stringResource(id = R.string.points) + ":" to wordPassState.points.toReducedString(),
                    stringResource(id = R.string.corrects_answers) to wordPassState.correctAnswers.toString(),
                    stringResource(id = R.string.incorrect_answers) to (wordPassState.words.size - wordPassState.correctAnswers).toString(),
                    stringResource(id = R.string.total_answers) to wordPassState.words.size.toString(),
                )
                if (wordPassState.mode == WordPassMode.SURVIVAL) {
                    data.put(stringResource(id = R.string.lives), wordPassState.lives.toString())
                }
                IResumeCard(
                    title = stringResource(R.string.finished_title),
                    titlesValues = data.toMap(),
                    modifier = Modifier.padding(MediumPadding)
                )
            }

            item {
                IOutlinedButton(
                    onClick = onRestartGame,
                    label = stringResource(id = R.string.restart_game),
                    imageVector = Icons.Filled.RestartAlt,
                )
            }

            item {
                Spacer(modifier = Modifier.height(SmallPadding))
            }

            item {
                IOutlinedButton(
                    onClick = onOutGame,
                    label = stringResource(id = R.string.out_game),
                    imageVector = Icons.Filled.Output,
                )
            }
        }
    }
}

@UiModePreviews
@Composable
private fun FinishedPreview() {
    YouKnowTheme {
        Finished()
    }
}
