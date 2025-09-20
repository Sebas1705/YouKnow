package es.sebas1705.game.wordpass.design


import android.media.SoundPool
import androidx.compose.runtime.Composable
import es.sebas1705.common.games.wordpass.WordPassMode
import es.sebas1705.common.games.wordpass.WordPassStatus
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.game.wordpass.composables.Finished
import es.sebas1705.game.wordpass.composables.Running
import es.sebas1705.game.wordpass.composables.SelectionMode
import es.sebas1705.game.wordpass.viewmodel.WordPassState
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog

/**
 * Design of the Mystery Number Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param wordPassState [WordPassState]: State of the game.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onSelectMode (WordPassMode) -> Unit: Function that will be called when the user selects a mode.
 * @param onResponse (String) -> Unit: Function that will be called when the user responds to the game.
 * @param onRestartGame () -> Unit: Function that will be called when the user wants to restart the game.
 * @param onOutGame () -> Unit: Function that will be called when the user wants to go out of the game.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun WordPassDesign(
    windowState: WindowState = WindowState.default(),
    wordPassState: WordPassState = WordPassState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onSelectMode: (WordPassMode) -> Unit = { },
    onResponse: (String) -> Unit = { },
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { }
) {
    //Body:
    if (wordPassState.isLoading) LoadingDialog(windowState)

    when (wordPassState.status) {
        WordPassStatus.SELECTION_MODE -> SelectionMode(windowState, soundPool, onSelectMode)
        WordPassStatus.RUNNING -> Running(windowState, wordPassState, soundPool, onResponse)
        WordPassStatus.FINISHED -> Finished(windowState, wordPassState, soundPool, onRestartGame, onOutGame)
    }
}

@UiModePreviews
@Composable
private fun WordPassPreview() {
    AppTheme {
        WordPassDesign()
    }
}