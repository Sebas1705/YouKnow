package es.sebas1705.game.families.composables


import android.media.SoundPool
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.games.families.FamiliesMode
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.common.utlis.extensions.primitives.toReducedString
import es.sebas1705.designsystem.buttons.common.IOutlinedButton
import es.sebas1705.designsystem.cards.IResumeCard
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.spacers.IVerSpacer
import es.sebas1705.game.families.viewmodel.FamiliesState
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.feature.games.R

/**
 * Finished screen of the Families game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param familiesState [FamiliesState]: State of the game.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onRestartGame () -> Unit: Function to restart the game.
 * @param onOutGame () -> Unit: Function to exit the game.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun Finished(
    windowState: WindowState = WindowState.default(),
    familiesState: FamiliesState = FamiliesState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onRestartGame: () -> Unit = { },
    onOutGame: () -> Unit = { }
) {
    //Body:
    ApplyBack(
        backId = windowState.backEmpty
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val data = mutableMapOf(
                stringResource(id = R.string.feature_game_mode) to (stringResource(
                    familiesState.mode?.strRes ?: es.sebas1705.core.resources.R.string.core_resources_any
                )),
                stringResource(id = es.sebas1705.core.resources.R.string.core_resources_points) + ":" to familiesState.points.toReducedString(),
                stringResource(id = R.string.feature_game_corrects_answers) to familiesState.correctAnswers.toString(),
                stringResource(id = R.string.feature_game_incorrect_answers) to (familiesState.families.size - familiesState.correctAnswers).toString(),
                stringResource(id = R.string.feature_game_total_answers) to familiesState.families.size.toString(),
            )
            if (familiesState.mode == FamiliesMode.SURVIVAL) {
                data[stringResource(id = R.string.feature_game_lives)] = familiesState.lives.toString()
            }
            IResumeCard(
                title = stringResource(R.string.feature_game_finished_title),
                titlesValues = data.toMap(),
                modifier = Modifier.padding(MediumPadding)
            )

            IOutlinedButton(
                onClick = onRestartGame,
                label = stringResource(id = R.string.feature_game_restart_game),
                imageVector = Icons.Filled.RestartAlt,
                soundPool = soundPool,
            )

            IVerSpacer(height = SmallPadding)

            IOutlinedButton(
                onClick = onOutGame,
                label = stringResource(id = R.string.feature_game_out_game),
                modifier = Modifier,
                imageVector = Icons.Filled.Output,
                soundPool = soundPool,
            )
        }
    }
}

@UiModePreviews
@Composable
private fun FinishedPreview() {
    AppTheme {
        Finished()
    }
}
