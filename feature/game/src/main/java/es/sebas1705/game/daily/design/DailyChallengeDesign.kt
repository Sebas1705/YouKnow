package es.sebas1705.game.daily.design


import android.media.SoundPool
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.dialogs.LoadingDialog
import es.sebas1705.feature.games.R
import es.sebas1705.game.common.GamePage
import es.sebas1705.game.common.GamePrimaryButton
import es.sebas1705.game.daily.composables.Finished
import es.sebas1705.game.daily.composables.Running
import es.sebas1705.game.daily.viewmodel.DailyChallengeState
import es.sebas1705.game.daily.viewmodel.DailyChallengeStatus
import es.sebas1705.designsystem.texts.Title
import es.sebas1705.ui.theme.AppTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh

/**
 * Design of the Daily Challenge Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param dailyChallengeState [DailyChallengeState]: State of the challenge.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onRetry () -> Unit: Function to retry fetching today's challenge after an error.
 * @param onResponse (String) -> Unit: Function to respond to a question.
 * @param onOutGame () -> Unit: Function to exit the challenge.
 *
 * @since 1.3.0
 * @author Sebas1705 30/09/2026
 */
@Composable
fun DailyChallengeDesign(
    windowState: WindowState = WindowState.default(),
    dailyChallengeState: DailyChallengeState = DailyChallengeState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onRetry: () -> Unit = {},
    onResponse: (String) -> Unit = {},
    onOutGame: () -> Unit = {}
) {
    when {
        dailyChallengeState.loadError != null -> GamePage(
            windowState, filled = true, verticalArrangement = Arrangement.Center
        ) {
            Title(
                text = dailyChallengeState.loadError.orEmpty(),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.headlineSmall
            )
            GamePrimaryButton(
                text = stringResource(R.string.feature_game_daily_retry),
                icon = Icons.Filled.Refresh,
                onClick = onRetry
            )
        }

        dailyChallengeState.status == DailyChallengeStatus.LOADING -> LoadingDialog(windowState)

        dailyChallengeState.status == DailyChallengeStatus.RUNNING -> Running(
            windowState, dailyChallengeState, soundPool, onResponse
        )

        else -> Finished(windowState, dailyChallengeState, soundPool, onOutGame)
    }
}

@UiModePreviews
@Composable
private fun DailyChallengeDesignPreview() {
    AppTheme {
        DailyChallengeDesign()
    }
}
