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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.games.families.FamiliesMode
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.common.utlis.extensions.primitives.toReducedString
import es.sebas1705.designsystem.buttons.common.IOutlinedButton
import es.sebas1705.designsystem.cards.IResumeCard
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.spacers.IVerSpacer
import es.sebas1705.game.families.viewmodel.FamiliesState
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.YouKnowTheme
import es.sebas1705.youknow.feature.games.R

/**
 * Finished screen of the Families game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param familiesState [FamiliesState]: State of the game.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onRestartGame () -> Unit: Function to restart the game.
 * @param onOutGame () -> Unit: Function to exit the game.
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun Finished(
    windowState: WindowState = WindowState.default(),
    familiesState: FamiliesState = FamiliesState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { }
) {
    //Body:
    ApplyBack(
        backId = windowState.backEmpty
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val data = mutableMapOf(
                stringResource(id = R.string.feature_game_mode) to (stringResource(
                    familiesState.mode?.strRes ?: es.iberext.youknow.core.resources.R.string.core_resources_any
                )),
                stringResource(id = es.iberext.youknow.core.resources.R.string.core_resources_points) + ":" to familiesState.points.toReducedString(),
                stringResource(id = R.string.feature_game_corrects_answers) to familiesState.correctAnswers.toString(),
                stringResource(id = R.string.feature_game_incorrect_answers) to (familiesState.families.size - familiesState.correctAnswers).toString(),
                stringResource(id = R.string.feature_game_total_answers) to familiesState.families.size.toString(),
            )
            if (familiesState.mode == FamiliesMode.SURVIVAL) {
                data[stringResource(id = R.string.feature_game_lives)] = familiesState.lives.toString()
            }
            IResumeCard(
                title = stringResource(R.string.feature_game_finished_title),
                titlesValues = data.toMap(),
                modifier = Modifier.padding(MediumPadding)
            )

            IOutlinedButton(
                onClick = onRestartGame,
                label = stringResource(id = R.string.feature_game_restart_game),
                imageVector = Icons.Filled.RestartAlt,
                soundPool = soundPool,
            )

            IVerSpacer(height = SmallPadding)

            IOutlinedButton(
                onClick = onOutGame,
                label = stringResource(id = R.string.feature_game_out_game),
                modifier = Modifier,
                imageVector = Icons.Filled.Output,
                soundPool = soundPool,
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
