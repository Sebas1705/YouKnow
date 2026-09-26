package es.sebas1705.game.mysterynumber.composables

import android.media.SoundPool
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.common.games.mysterynumber.MysteryNumberMode
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.feature.games.R
import es.sebas1705.game.common.GamePage
import es.sebas1705.game.common.GameResultContent
import es.sebas1705.game.common.ResultStat
import es.sebas1705.game.common.gamePalette
import es.sebas1705.game.mysterynumber.viewmodel.MysteryNumberState
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.makeTitle
import kotlin.math.roundToInt

/**
 * End of a Mystery Number game: the number revealed, and stars from whether it was found and how
 * many lives were left.
 *
 * @param windowState [WindowState]: State of the window.
 * @param mysteryNumberState [MysteryNumberState]: State of the game.
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
    mysteryNumberState: MysteryNumberState = MysteryNumberState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { }
) {
    val secret = mysteryNumberState.numberModel.number
    val found = mysteryNumberState.guesses.lastOrNull() == secret
    val maxLives = (mysteryNumberState.mode?.lives ?: 0).coerceAtLeast(1)
    val livesLeft = mysteryNumberState.lives.coerceAtLeast(0)
    // Found with most lives left is a great round; found at the last try still earns a star.
    val ratio = if (found) 0.4f + 0.6f * (livesLeft / maxLives.toFloat()) else 0f
    val scheme = MaterialTheme.colorScheme
    val stats = buildList {
        add(ResultStat(stringResource(R.string.feature_game_stat_attempts), mysteryNumberState.guesses.size.toString(), scheme.primary))
        add(ResultStat(stringResource(R.string.feature_game_stat_lives), livesLeft.toString(), if (found) gamePalette().success else scheme.error))
        if (mysteryNumberState.mode == MysteryNumberMode.TIME_ATTACK)
            add(ResultStat(stringResource(R.string.feature_game_stat_time), "${mysteryNumberState.timeRemaining.roundToInt().coerceAtLeast(0)}s", scheme.tertiary))
    }
    GamePage(windowState, filled = false, verticalArrangement = Arrangement.Center) {
        GameResultContent(
            points = mysteryNumberState.points,
            ratio = ratio,
            modeName = stringResource(mysteryNumberState.mode?.strRes ?: es.sebas1705.core.resources.R.string.core_resources_any),
            stats = stats,
            restartLabel = stringResource(R.string.feature_game_restart_game),
            exitLabel = stringResource(R.string.feature_game_out_game),
            onRestart = onRestartGame,
            onExit = onOutGame,
            restartIcon = Icons.Filled.RestartAlt,
            exitIcon = Icons.Filled.Output,
            extra = {
                if (secret >= 0) Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = stringResource(R.string.feature_game_number_was, secret),
                    style = MaterialTheme.typography.titleMedium.makeTitle(),
                    color = scheme.primary
                )
            }
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
