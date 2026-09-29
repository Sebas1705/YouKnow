package es.sebas1705.game.daily


import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.states.WindowState
import es.sebas1705.game.daily.design.DailyChallengeDesign
import es.sebas1705.game.daily.viewmodel.DailyChallengeIntent
import es.sebas1705.game.daily.viewmodel.DailyChallengeViewModel

/**
 * Screen of the Daily Challenge Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onOutGameNavigation [Function]: Function to navigate to the previous screen.
 *
 * @author Sebas1705 30/09/2026
 * @since 1.3.0
 */
@Composable
fun DailyChallengeScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    onOutGameNavigation: () -> Unit
) {
    //ViewModel:
    val dailyChallengeViewModel: DailyChallengeViewModel = hiltViewModel()

    //State:
    val dailyChallengeState by dailyChallengeViewModel.uiState.collectAsStateWithLifecycle()

    //Body:
    DailyChallengeDesign(
        windowState,
        dailyChallengeState,
        soundPool,
        onRetry = {
            dailyChallengeViewModel.eventHandler(DailyChallengeIntent.LoadChallenge)
        },
        onResponse = { response ->
            dailyChallengeViewModel.eventHandler(DailyChallengeIntent.Response(response))
        },
        onOutGame = {
            dailyChallengeViewModel.eventHandler(DailyChallengeIntent.OutGame {
                onOutGameNavigation()
            })
        }
    )
}
