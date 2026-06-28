package es.sebas1705.home.play


import android.media.SoundPool
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import es.sebas1705.common.states.WindowState
import es.sebas1705.home.play.design.PlayDesign

/**
 * Play Screen that will show the game to the user.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onGameNav (Int) -> Unit: The navigation to the game.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun PlayScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    onGameNav: (Int) -> Unit
) {
    //Local:
    BackHandler {}

    //Body:
    PlayDesign(
        windowState,
        soundPool,
        onGameNav
    )
}

