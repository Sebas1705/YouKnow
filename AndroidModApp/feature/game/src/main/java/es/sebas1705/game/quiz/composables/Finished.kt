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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.enums.games.Languages
import es.sebas1705.youknow.core.classes.enums.games.quiz.QuizMode
import es.sebas1705.youknow.core.classes.enums.games.quiz.QuizStatus
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.IOutlinedButton
import es.sebas1705.youknow.core.composables.cards.IResumeCard
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.spacers.IVerSpacer
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.extensions.primitives.toReducedString
import es.sebas1705.youknow.domain.model.games.QuestionModel
import es.sebas1705.youknow.presentation.features.game.features.quiz.viewmodel.QuizState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Finished state of the Quiz game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param quizState [QuizState]: State of the game.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onRestartGame () -> Unit: Function to restart the game.
 * @param onOutGame () -> Unit: Function to exit the game.
 *
 * @since 1.0.0
 * @Author Sebastián Ramiro Entrerrios García
 */
@Composable
fun Finished(
    windowState: WindowState = WindowState.default(),
    quizState: QuizState = QuizState(
        mode = QuizMode.SURVIVAL,
        points = 2000,
        correctAnswers = 10,
        questions = QuestionModel.defaultMultipleList(20),
        status = QuizStatus.FINISHED,
        isLoading = false,
        actualQuestion = 20,
        lives = 3,
        languages = Languages.ANY
    ),
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
            val data = mutableMapOf<String, String>(
                stringResource(id = R.string.mode) to (stringResource(
                    quizState.mode?.strRes ?: R.string.any
                )),
                stringResource(id = R.string.points) + ":" to quizState.points.toReducedString(),
                stringResource(id = R.string.corrects_answers) to quizState.correctAnswers.toString(),
                stringResource(id = R.string.incorrect_answers) to (quizState.questions.size - quizState.correctAnswers).toString(),
                stringResource(id = R.string.total_answers) to quizState.questions.size.toString(),
            )
            if (quizState.mode == QuizMode.SURVIVAL) {
                data[stringResource(id = R.string.lives)] = quizState.lives.toString()
            }
            IResumeCard(
                title = stringResource(R.string.finished_title),
                titlesValues = data.toMap(),
                modifier = Modifier.padding(MediumPadding)
            )

            IOutlinedButton(
                onClick = onRestartGame,
                label = stringResource(id = R.string.restart_game),
                imageVector = Icons.Filled.RestartAlt,
                soundPool = soundPool
            )

            IVerSpacer(height = SmallPadding)

            IOutlinedButton(
                onClick = onOutGame,
                label = stringResource(id = R.string.out_game),
                imageVector = Icons.Filled.Output,
                soundPool = soundPool
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
