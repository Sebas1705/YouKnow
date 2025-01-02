package es.sebas1705.youknow.presentation.features.game.screens.mysterynumber.composables
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import es.sebas1705.youknow.presentation.features.game.viewmodels.MysteryNumberMode
import es.sebas1705.youknow.presentation.features.game.viewmodels.MysteryNumberState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun Finished(
    windowState: WindowState = WindowState.default(),
    mysteryNumberState: MysteryNumberState = MysteryNumberState.default(),
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { }
) {
    ApplyBack(
        backId = windowState.backEmpty
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title(
                text = stringResource(id = R.string.finished_title),
                modifier = Modifier.padding(bottom = MediumPadding)
            )
            val data = mutableMapOf<String, String>(
                stringResource(id = R.string.mode) to (stringResource(
                    mysteryNumberState.mode?.strRes ?: R.string.any
                )),
                stringResource(id = R.string.points) + ":" to mysteryNumberState.points.toReducedString(),
            )
            if (mysteryNumberState.mode == MysteryNumberMode.TIME_ATTACK) {
                data.put(
                    stringResource(id = R.string.remaining_time),
                    mysteryNumberState.lives.toString()
                )
            } else {
                data.put(stringResource(id = R.string.lives), mysteryNumberState.lives.toString())
            }

            IResumeCard(
                title = stringResource(id = R.string.finished_title),
                titlesValues = data.toMap(),
                modifier = Modifier.padding(MediumPadding)
            )

            IOutlinedButton(
                onClick = onRestartGame,
                label = stringResource(id = R.string.restart_game),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.2f),
                imageVector = Icons.Filled.RestartAlt,
            )

            IVerSpacer(height = SmallPadding)

            IOutlinedButton(
                onClick = onOutGame,
                label = stringResource(id = R.string.out_game),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.2f),
                imageVector = Icons.Filled.Output,
            )
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
