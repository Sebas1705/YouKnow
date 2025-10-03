package es.sebas1705.game.families.design


import android.media.SoundPool
import androidx.compose.runtime.Composable
import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.families.FamiliesMode
import es.sebas1705.common.games.families.FamiliesStatus
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.game.families.composables.Custom
import es.sebas1705.game.families.composables.Finished
import es.sebas1705.game.families.composables.Running
import es.sebas1705.game.families.composables.SelectionMode
import es.sebas1705.game.families.viewmodel.FamiliesState
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.designsystem.dialogs.LoadingDialog

/**
 * Design of the Mystery Number Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param familiesState [FamiliesState]: State of the game.
 * @param onSelectMode ([FamiliesMode]) -> Unit: Function to select the mode of the game.
 * @param onResponseQuestion (String) -> Unit: Function to respond to the question.
 * @param onRestartGame () -> Unit: Function to restart the game.
 * @param onOutGame () -> Unit: Function to exit the game.
 * @param onStartGame (Difficulty, Category, Int) -> Unit: Function to start the game.
 *
 * @since 1.0.0
 * @Author Sebas1705 21/09/2025
 */
@Composable
fun FamiliesDesign(
    windowState: WindowState = WindowState.default(),
    familiesState: FamiliesState = FamiliesState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onSelectMode: (FamiliesMode) -> Unit = { },
    onResponseQuestion: (String) -> Unit = { },
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { },
    onStartGame: (Difficulty, Category, Int) -> Unit = { _, _, _ -> }
) {
    //Body:
    if (familiesState.isLoading) LoadingDialog(windowState)

    when (familiesState.status) {
        FamiliesStatus.SELECTION_MODE -> SelectionMode(windowState, soundPool, onSelectMode)
        FamiliesStatus.CUSTOM -> Custom(windowState, soundPool, onStartGame)
        FamiliesStatus.RUNNING -> Running(windowState, familiesState, soundPool, onResponseQuestion)
        FamiliesStatus.FINISHED -> Finished(windowState, familiesState, soundPool, onRestartGame, onOutGame)
    }
}

@UiModePreviews
@Composable
private fun FamiliesPreview() {
    AppTheme {
        FamiliesDesign()
    }
}
