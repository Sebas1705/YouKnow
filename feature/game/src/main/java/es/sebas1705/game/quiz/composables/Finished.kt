package es.sebas1705.game.quiz.composables

import android.media.SoundPool
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.quiz.QuizMode
import es.sebas1705.common.games.quiz.QuizStatus
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.feature.games.R
import es.sebas1705.game.common.GamePage
import es.sebas1705.game.common.GameResultContent
import es.sebas1705.game.common.ResultStat
import es.sebas1705.game.common.gamePalette
import es.sebas1705.game.quiz.viewmodel.QuizState
import es.sebas1705.models.games.QuestionModel
import es.sebas1705.ui.theme.AppTheme
import kotlin.math.roundToInt

/**
 * End of a Quiz: stars and headline from the share of right answers, the points, and the stats.
 *
 * @param windowState [WindowState]: State of the window.
 * @param quizState [QuizState]: State of the quiz.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of SoundPool and volume.
 * @param onRestartGame () -> Unit: Callback to restart the game.
 * @param onOutGame () -> Unit: Callback to leave the game.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun Finished(
    windowState: WindowState = WindowState.default(),
    quizState: QuizState = QuizState(
        mode = QuizMode.SURVIVAL,
        points = 2000,
        correctAnswers = 7,
        questions = QuestionModel.defaultMultipleList(20),
        status = QuizStatus.FINISHED,
        isLoading = false,
        actualQuestion = 10,
        lives = 1,
        languages = Languages.ANY
    ),
    soundPool: Pair<SoundPool, Float>? = null,
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { }
) {
    // actualQuestion moves past the last answered question, so it counts the answers given.
    val answered = quizState.actualQuestion.coerceIn(quizState.correctAnswers, quizState.questions.size)
    val wrong = answered - quizState.correctAnswers
    val ratio = if (answered > 0) quizState.correctAnswers / answered.toFloat() else 0f
    val scheme = MaterialTheme.colorScheme
    val stats = buildList {
        add(ResultStat(stringResource(R.string.feature_game_stat_correct), quizState.correctAnswers.toString(), gamePalette().success))
        add(ResultStat(stringResource(R.string.feature_game_stat_wrong), wrong.toString(), scheme.error))
        add(ResultStat(stringResource(R.string.feature_game_stat_accuracy), "${(ratio * 100).roundToInt()}%", scheme.primary))
        if (quizState.mode == QuizMode.SURVIVAL)
            add(ResultStat(stringResource(R.string.feature_game_stat_lives), quizState.lives.coerceAtLeast(0).toString(), scheme.tertiary))
    }
    GamePage(windowState, filled = false, verticalArrangement = Arrangement.Center) {
        GameResultContent(
            points = quizState.points,
            ratio = ratio,
            modeName = stringResource(quizState.mode?.strRes ?: es.sebas1705.core.resources.R.string.core_resources_any),
            stats = stats,
            restartLabel = stringResource(R.string.feature_game_restart_game),
            exitLabel = stringResource(R.string.feature_game_out_game),
            onRestart = onRestartGame,
            onExit = onOutGame,
            restartIcon = Icons.Filled.RestartAlt,
            exitIcon = Icons.Filled.Output
        )
    }
}

@UiModePreviews
@Composable
private fun FinishedPreview() {
    AppTheme {
        Finished()
    }
}
