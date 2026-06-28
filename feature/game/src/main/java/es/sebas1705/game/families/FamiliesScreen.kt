package es.sebas1705.game.families


import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.states.WindowState
import es.sebas1705.game.families.design.FamiliesDesign
import es.sebas1705.game.families.viewmodel.FamiliesIntent
import es.sebas1705.game.families.viewmodel.FamiliesViewModel

/**
 * Screen of the Mystery Number Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onOutGameNavigation () -> Unit: Function to navigate out of the game.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun FamiliesScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    onOutGameNavigation: () -> Unit
) {
    //ViewModel:
    val familiesViewModel: FamiliesViewModel = hiltViewModel()

    //State:
    val familiesState by familiesViewModel.uiState.collectAsStateWithLifecycle()

    //Effects:
    LaunchedEffect(Unit) {
        familiesViewModel.eventHandler(FamiliesIntent.ReadLanguages)
    }

    //Body:
    FamiliesDesign(
        windowState,
        familiesState,
        soundPool,
        onSelectMode = { mode ->
            familiesViewModel.eventHandler(FamiliesIntent.SelectMode(mode))
        },
        onResponseQuestion = { response ->
            familiesViewModel.eventHandler(FamiliesIntent.Response(response))
        },
        onRestartGame = {
            familiesViewModel.eventHandler(FamiliesIntent.ResetGame)
        },
        onOutGame = {
            familiesViewModel.eventHandler(FamiliesIntent.OutGame {
                onOutGameNavigation()
            })
        },
        onStartGame = { difficulty, category, numFamilies ->
            familiesViewModel.eventHandler(
                FamiliesIntent.GenerateGame(
                    category,
                    difficulty,
                    numFamilies
                )
            )
        }
    )
}
