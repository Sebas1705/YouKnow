package es.sebas1705.game.quiz.composables


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
import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.quiz.QuizMode
import es.sebas1705.common.games.quiz.QuizStatus
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.common.utlis.extensions.primitives.toReducedString
import es.sebas1705.designsystem.buttons.common.IOutlinedButton
import es.sebas1705.designsystem.cards.IResumeCard
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.spacers.IVerSpacer
import es.sebas1705.game.quiz.viewmodel.QuizState
import es.sebas1705.models.games.QuestionModel
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.feature.games.R

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
 * @Author Sebas1705 12/09/2025
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
            val data = mutableMapOf(
                stringResource(id = R.string.feature_game_mode) to (stringResource(
                    quizState.mode?.strRes ?: es.sebas1705.core.resources.R.string.core_resources_any
                )),
                stringResource(id = es.sebas1705.core.resources.R.string.core_resources_points) + ":" to quizState.points.toReducedString(),
                stringResource(id = R.string.feature_game_corrects_answers) to quizState.correctAnswers.toString(),
                stringResource(id = R.string.feature_game_incorrect_answers) to (quizState.questions.size - quizState.correctAnswers).toString(),
                stringResource(id = R.string.feature_game_total_answers) to quizState.questions.size.toString(),
            )
            if (quizState.mode == QuizMode.SURVIVAL) {
                data[stringResource(id = R.string.feature_game_lives)] = quizState.lives.toString()
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
                soundPool = soundPool
            )

            IVerSpacer(height = SmallPadding)

            IOutlinedButton(
                onClick = onOutGame,
                label = stringResource(id = R.string.feature_game_out_game),
                imageVector = Icons.Filled.Output,
                soundPool = soundPool
            )
        }
    }
}

@UiModePreviews
@Composable
private fun FinishedPreview() {
    AppTheme {
        Finished()
    }
}
