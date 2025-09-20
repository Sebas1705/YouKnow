package es.sebas1705.game.wordpass.composables
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
import es.sebas1705.common.games.wordpass.WordPassMode
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.common.utlis.extensions.primitives.toReducedString
import es.sebas1705.designsystem.buttons.common.IOutlinedButton
import es.sebas1705.designsystem.cards.IResumeCard
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.YouKnowTheme
import es.sebas1705.youknow.feature.games.R
import es.sebas1705.game.wordpass.viewmodel.WordPassState

/**
 * Finished composable
 *
 * @param windowState WindowState
 * @param wordPassState WordPassState
 * @param soundPool Pair<SoundPool, Float>?
 * @param onRestartGame Function0<Unit>
 * @param onOutGame Function0<Unit>
 *
 * @since 1.0.0
 * @author Sebastian Ramiro Entrerrios Garcia
 */
@Composable
fun Finished(
    windowState: WindowState = WindowState.default(),
    wordPassState: WordPassState = WordPassState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
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
                val data = mutableMapOf(
                    stringResource(id = R.string.feature_game_mode) to (stringResource(
                        wordPassState.mode?.strRes ?: es.iberext.youknow.core.resources.R.string.core_resources_any
                    )),
                    stringResource(id = es.iberext.youknow.core.resources.R.string.core_resources_points) + ":" to wordPassState.points.toReducedString(),
                    stringResource(id = R.string.feature_game_corrects_answers) to wordPassState.correctAnswers.toString(),
                    stringResource(id = R.string.feature_game_incorrect_answers) to (wordPassState.words.size - wordPassState.correctAnswers).toString(),
                    stringResource(id = R.string.feature_game_total_answers) to wordPassState.words.size.toString(),
                )
                if (wordPassState.mode == WordPassMode.SURVIVAL) {
                    data[stringResource(id = R.string.feature_game_lives)] = wordPassState.lives.toString()
                }
                IResumeCard(
                    title = stringResource(R.string.feature_game_finished_title),
                    titlesValues = data.toMap(),
                    modifier = Modifier.padding(MediumPadding)
                )
            }

            item {
                IOutlinedButton(
                    onClick = onRestartGame,
                    label = stringResource(id = R.string.feature_game_restart_game),
                    imageVector = Icons.Filled.RestartAlt,
                    soundPool = soundPool
                )
            }

            item {
                Spacer(modifier = Modifier.height(SmallPadding))
            }

            item {
                IOutlinedButton(
                    onClick = onOutGame,
                    label = stringResource(id = R.string.feature_game_out_game),
                    imageVector = Icons.Filled.Output,
                    soundPool = soundPool
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
