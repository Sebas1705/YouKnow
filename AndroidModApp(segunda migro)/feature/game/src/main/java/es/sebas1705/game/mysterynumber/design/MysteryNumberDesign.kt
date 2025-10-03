package es.sebas1705.game.mysterynumber.design


import android.media.SoundPool
import androidx.compose.runtime.Composable
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.mysterynumber.MysteryNumberMode
import es.sebas1705.common.games.mysterynumber.MysteryNumberStatus
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.game.mysterynumber.composables.Custom
import es.sebas1705.game.mysterynumber.composables.Finished
import es.sebas1705.game.mysterynumber.composables.Running
import es.sebas1705.game.mysterynumber.composables.SelectionMode
import es.sebas1705.game.mysterynumber.viewmodel.MysteryNumberState
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.designsystem.dialogs.LoadingDialog

/**
 * Design of the Mystery Number Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param mysteryNumberState [MysteryNumberState]: State of the game.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onSelectMode ([MysteryNumberMode]) -> Unit: Function to select the game mode.
 * @param onResponseNumber (Int, Float) -> Unit: Function to respond to the number.
 * @param onRestartGame () -> Unit: Function to restart the game.
 * @param onOutGame () -> Unit: Function to exit the game.
 * @param onStartGame (Difficulty, Int) -> Unit: Function to start the game.
 *
 * @since 1.0.0
 * @Author Sebas1705 12/09/2025
 */
@Composable
fun MysteryNumberDesign(
    windowState: WindowState = WindowState.default(),
    mysteryNumberState: MysteryNumberState = MysteryNumberState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onSelectMode: (MysteryNumberMode) -> Unit = { },
    onResponseNumber: (Int, Float) -> Unit = { _, _ -> },
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { },
    onStartGame: (Difficulty, Int) -> Unit = { _, _ -> }
) {
    //Body:
    if (mysteryNumberState.isLoading) LoadingDialog(windowState)

    when (mysteryNumberState.status) {
        MysteryNumberStatus.SELECTION_MODE -> SelectionMode(
            windowState,
            soundPool,
            onSelectMode
        )

        MysteryNumberStatus.CUSTOM -> Custom(
            windowState,
            soundPool,
            onStartGame
        )

        MysteryNumberStatus.RUNNING -> Running(
            windowState,
            mysteryNumberState,
            soundPool,
            onResponseNumber
        )

        MysteryNumberStatus.FINISHED -> Finished(
            windowState,
            mysteryNumberState,
            soundPool,
            onRestartGame,
            onOutGame
        )
    }
}

@UiModePreviews
@Composable
private fun MysteryNumberPreview() {
    AppTheme {
        MysteryNumberDesign()
    }
}