package es.sebas1705.game.mysterynumber


import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.states.WindowState
import es.sebas1705.game.mysterynumber.design.MysteryNumberDesign
import es.sebas1705.game.mysterynumber.viewmodel.MysteryNumberIntent
import es.sebas1705.game.mysterynumber.viewmodel.MysteryNumberViewModel

/**
 * Screen of the Mystery Number Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onOutGameNavigation () -> Unit: Function to exit the game.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun MysteryNumberScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    onOutGameNavigation: () -> Unit
) {
    //ViewModel:
    val mysteryNumberViewModel: MysteryNumberViewModel = hiltViewModel()

    //State:
    val mysteryNumberState by mysteryNumberViewModel.uiState.collectAsStateWithLifecycle()

    //Body:
    MysteryNumberDesign(
        windowState,
        mysteryNumberState,
        soundPool,
        onSelectMode = { mode ->
            mysteryNumberViewModel.eventHandler(MysteryNumberIntent.SelectMode(mode))
        },
        onResponseNumber = { response, time ->
            mysteryNumberViewModel.eventHandler(
                MysteryNumberIntent.Response(
                    response,
                    time
                )
            )
        },
        onRestartGame = {
            mysteryNumberViewModel.eventHandler(MysteryNumberIntent.ResetGame)
        },
        onOutGame = {
            mysteryNumberViewModel.eventHandler(MysteryNumberIntent.OutGame {
                onOutGameNavigation()
            })
        },
        onStartGame = { difficulty, lives ->
            mysteryNumberViewModel.eventHandler(MysteryNumberIntent.GenerateGame(difficulty, lives))
        }
    )
}


