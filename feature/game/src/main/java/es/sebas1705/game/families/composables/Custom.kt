package es.sebas1705.game.families.composables


import android.media.SoundPool
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.extras.DropdownList
import es.sebas1705.feature.games.R
import es.sebas1705.game.common.ChoiceChips
import es.sebas1705.game.common.CountSlider
import es.sebas1705.game.common.CustomSetupContent
import es.sebas1705.game.common.GamePage
import es.sebas1705.game.common.SetupSection
import es.sebas1705.game.common.tint
import es.sebas1705.ui.theme.AppTheme

/** Open Trivia DB returns at most 50 questions per request. */
private val QUESTIONS_RANGE = 1..50

/**
 * Custom screen of the Families game: number of questions, difficulty and category.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onStartGame (Difficulty, Category, Int) -> Unit: Function to start the game.
 *
 * @since 1.0.0
 * @Author Sebas1705 21/09/2025
 */
@Composable
fun Custom(
    windowState: WindowState = WindowState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onStartGame: (Difficulty, Category, Int) -> Unit = { _, _, _ -> }
) {
    val ctx = LocalContext.current
    var difficulty by rememberSaveable { mutableIntStateOf(Difficulty.EASY.ordinal) }
    val difficultyEnum = Difficulty.entries[difficulty]
    var category by rememberSaveable { mutableIntStateOf(Category.GENERAL_KNOWLEDGE.ordinal) }
    val categoryEnum = Category.entries[category]
    var numQuestions by rememberSaveable { mutableIntStateOf(10) }

    GamePage(windowState, filled = false) {
        CustomSetupContent(
            title = stringResource(R.string.feature_game_title_families),
            illustration = es.sebas1705.core.resources.R.drawable.game_family,
            onStart = { onStartGame(difficultyEnum, categoryEnum, numQuestions) }
        ) {
            SetupSection(stringResource(R.string.feature_game_setup_questions), numQuestions.toString()) {
                CountSlider(numQuestions, QUESTIONS_RANGE, { numQuestions = it })
            }
            SetupSection(stringResource(R.string.feature_game_difficulty), null) {
                ChoiceChips(
                    options = Difficulty.entries,
                    selected = difficultyEnum,
                    label = { stringResource(it.strRes) },
                    onSelect = { difficulty = it.ordinal },
                    tint = { it.tint() }
                )
            }
            SetupSection(stringResource(R.string.feature_game_category), null) {
                DropdownList(
                    modifier = Modifier.fillMaxWidth(),
                    valueRes = categoryEnum.strRes,
                    onValueChange = { s ->
                        category = Category.entries.indexOfFirst { ctx.getString(it.strRes) == s }
                            .coerceAtLeast(0)
                    },
                ) { onChanged ->
                    Category.entries.forEach {
                        DropdownMenuItem(
                            text = { Text(stringResource(it.strRes)) },
                            onClick = { onChanged(it.strRes) },
                            colors = MenuDefaults.itemColors(
                                textColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
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
