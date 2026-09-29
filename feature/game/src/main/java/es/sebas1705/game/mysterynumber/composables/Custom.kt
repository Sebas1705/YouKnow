package es.sebas1705.game.mysterynumber.composables


import android.media.SoundPool
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.feature.games.R
import es.sebas1705.game.common.ChoiceChips
import es.sebas1705.game.common.CountSlider
import es.sebas1705.game.common.CustomSetupContent
import es.sebas1705.game.common.GamePage
import es.sebas1705.game.common.SetupSection
import es.sebas1705.game.common.tint
import es.sebas1705.ui.theme.AppTheme

private val LIVES_RANGE = 1..20

/**
 * Custom screen of the Mystery Number game: lives and difficulty (which sets the range).
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onStartGame (Difficulty, Int) -> Unit: Function to start the game.
 *
 * @since 1.0.0
 * @Author Sebas1705 21/09/2025
 */
@Composable
fun Custom(
    windowState: WindowState = WindowState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onStartGame: (Difficulty, Int) -> Unit = { _, _ -> }
) {
    var difficulty by rememberSaveable { mutableIntStateOf(Difficulty.EASY.ordinal) }
    val difficultyEnum = Difficulty.entries[difficulty]
    var lives by rememberSaveable { mutableIntStateOf(10) }

    GamePage(windowState, filled = false) {
        CustomSetupContent(
            title = stringResource(R.string.feature_game_mystery_title),
            illustration = es.sebas1705.core.resources.R.drawable.game_numbers,
            onStart = { onStartGame(difficultyEnum, lives) }
        ) {
            SetupSection(stringResource(R.string.feature_game_stat_lives), lives.toString()) {
                CountSlider(lives, LIVES_RANGE, { lives = it })
            }
            SetupSection(stringResource(R.string.feature_game_difficulty), null) {
                ChoiceChips(
                    options = Difficulty.entries,
                    selected = difficultyEnum,
                    label = { stringResource(it.strRes) },
                    onSelect = { difficulty = it.ordinal },
                    tint = { it.tint() }
                )
                if (difficultyEnum != Difficulty.ANY) Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "1 – ${difficultyEnum.maxMysteryNumber}",
                    style = MaterialTheme.typography.titleMedium,
                    color = difficultyEnum.tint()
                )
            }
        }
    }
}

@UiModePreviews
@Composable
private fun CustomPreview() {
    AppTheme {
        Custom()
    }
}
