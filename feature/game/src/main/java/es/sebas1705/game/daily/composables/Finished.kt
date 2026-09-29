package es.sebas1705.game.daily.composables

import android.media.SoundPool
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Output
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.feature.games.R
import es.sebas1705.game.common.GamePage
import es.sebas1705.game.common.GameResultContent
import es.sebas1705.game.common.ResultStat
import es.sebas1705.game.common.gamePalette
import es.sebas1705.game.daily.viewmodel.DailyChallengeState
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.models.games.QuestionModel

/**
 * End of the Daily Challenge: stars and headline from the share of right answers, the points, and
 * a single exit action — there is no restart, since everyone only gets one go at today's round.
 *
 * @param windowState [WindowState]: State of the window.
 * @param dailyChallengeState [DailyChallengeState]: State of the challenge.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of SoundPool and volume.
 * @param onOutGame () -> Unit: Callback to leave the challenge.
 *
 * @since 1.3.0
 * @author Sebas1705 30/09/2026
 */
@Composable
fun Finished(
    windowState: WindowState = WindowState.default(),
    dailyChallengeState: DailyChallengeState = DailyChallengeState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onOutGame: () -> Unit = { }
) {
    val total = dailyChallengeState.questions.size
    val wrong = total - dailyChallengeState.correctAnswers
    val ratio = if (total > 0) dailyChallengeState.correctAnswers / total.toFloat() else 0f
    val scheme = MaterialTheme.colorScheme
    val stats = listOf(
        ResultStat(stringResource(R.string.feature_game_stat_correct), dailyChallengeState.correctAnswers.toString(), gamePalette().success),
        ResultStat(stringResource(R.string.feature_game_stat_wrong), wrong.toString(), scheme.error),
    )
    GamePage(windowState, filled = false, verticalArrangement = Arrangement.Center) {
        GameResultContent(
            points = dailyChallengeState.points,
            ratio = ratio,
            modeName = stringResource(R.string.feature_game_daily_title),
            stats = stats,
            restartLabel = stringResource(R.string.feature_game_out_game),
            exitLabel = stringResource(R.string.feature_game_out_game),
            onRestart = onOutGame,
            onExit = onOutGame,
            restartIcon = Icons.Filled.Output,
            exitIcon = Icons.Filled.Output,
            // Only one exit action: everyone gets a single go at today's round, so there's no
            // separate "restart" to offer.
            showExit = false,
        )
    }
}

@UiModePreviews
@Composable
private fun FinishedPreview() {
    AppTheme {
        Finished(
            dailyChallengeState = DailyChallengeState.default().copy(
                questions = QuestionModel.defaultMultipleList(5),
                correctAnswers = 4,
                points = 120
            )
        )
    }
}
