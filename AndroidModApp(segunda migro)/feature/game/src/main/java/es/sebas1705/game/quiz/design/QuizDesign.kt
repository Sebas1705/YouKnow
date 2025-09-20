package es.sebas1705.game.quiz.design


import android.media.SoundPool
import androidx.compose.runtime.Composable
import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.QuizType
import es.sebas1705.common.games.quiz.QuizMode
import es.sebas1705.common.games.quiz.QuizStatus
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.game.quiz.composables.Custom
import es.sebas1705.game.quiz.composables.Finished
import es.sebas1705.game.quiz.composables.Running
import es.sebas1705.game.quiz.composables.SelectionMode
import es.sebas1705.game.quiz.viewmodel.QuizState
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog

/**
 * Design of the Mystery Number Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param quizState [QuizState]: State of the game.
 * @param onSelectMode ([QuizMode]) -> Unit: Function to select the game mode.
 * @param onResponseQuestion ([String]) -> Unit: Function to respond to the question.
 * @param onRestartGame () -> Unit: Function to restart the game.
 * @param onOutGame () -> Unit: Function to exit the game.
 * @param onStartGame ([Difficulty], [Category], [QuizType], [Int]) -> Unit: Function to start the game.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun QuizDesign(
    windowState: WindowState = WindowState.default(),
    quizState: QuizState = QuizState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onSelectMode: (QuizMode) -> Unit = { },
    onResponseQuestion: (String) -> Unit = { },
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { },
    onStartGame: (Difficulty, Category, QuizType, Int) -> Unit = { _, _, _, _ -> }
) {
    //Body:
    if (quizState.isLoading) LoadingDialog(windowState)

    when (quizState.status) {
        QuizStatus.SELECTION_MODE -> SelectionMode(windowState, soundPool, onSelectMode)
        QuizStatus.CUSTOM -> Custom(windowState, quizState, soundPool, onStartGame)
        QuizStatus.RUNNING -> Running(windowState, quizState, soundPool, onResponseQuestion)
        QuizStatus.FINISHED -> Finished(windowState, quizState, soundPool, onRestartGame, onOutGame)
    }
}

@UiModePreviews
@Composable
private fun QuizPreview() {
    AppTheme {
        QuizDesign()
    }
}
