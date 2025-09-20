package es.sebas1705.game.families.composables


import android.media.SoundPool
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.families.FamiliesMode
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.common.utlis.extensions.primitives.toReducedString
import es.sebas1705.designsystem.buttons.common.IFilledButton
import es.sebas1705.designsystem.cards.IPrimaryCard
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.texts.Title
import es.sebas1705.designsystem.texts.TitleSurface
import es.sebas1705.game.families.viewmodel.FamiliesState
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.Paddings.SmallestPadding
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.gameBottomBarHeight
import es.sebas1705.feature.games.R
import kotlinx.coroutines.delay

/**
 * Running screen of the Families game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param familiesState [FamiliesState]: State of the game.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onResponseQuestion (String) -> Unit: Function to respond to the question.
 *
 * @since 1.0.0
 * @Author Sebastián Ramiro Entrerrios
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Running(
    windowState: WindowState = WindowState.default(),
    familiesState: FamiliesState = FamiliesState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onResponseQuestion: (String) -> Unit = { }
) {
    //Body:
    ApplyBack(
        backId = windowState.backFill
    ) {
        if (familiesState.families.isEmpty()) {
            Title(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.feature_game_error_loading_message),
                color = MaterialTheme.colorScheme.error
            )
            return@ApplyBack
        }
        var time by rememberSaveable { mutableFloatStateOf(15f) }
        val family = familiesState.families[familiesState.actualFamily]
        if (familiesState.mode == FamiliesMode.TIME_ATTACK) {
            LaunchedEffect(family) {
                while (time > 0) {
                    delay(10)
                    time -= 0.01f
                }
                onResponseQuestion("TIME_OUT")
            }
        }
        val color = when (family.difficulty) {
            Difficulty.EASY -> Color.Green
            Difficulty.MEDIUM -> Color.Yellow
            Difficulty.HARD -> Color.Red
            else -> MaterialTheme.colorScheme.tertiary
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TitleSurface(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(MediumPadding)
                        .border(1.dp, color, MaterialTheme.shapes.small),
                    text = stringResource(R.string.feature_game_families_game),
                    textStyle = windowState.widthFilter(
                        MaterialTheme.typography.headlineMedium,
                        MaterialTheme.typography.displaySmall,
                        MaterialTheme.typography.displayLarge
                    )
                )
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val answers = family.answers
                    Log.i("Running", "answers: $answers")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        IFilledButton(
                            onClick = { onResponseQuestion(answers[0]) },
                            label = answers[0],
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .padding(SmallestPadding),
                            soundPool = soundPool
                        )
                        IFilledButton(
                            onClick = { onResponseQuestion(answers[1]) },
                            label = answers[1],
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .padding(SmallestPadding),
                            soundPool = soundPool
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        IFilledButton(
                            onClick = { onResponseQuestion(answers[2]) },
                            label = answers[2],
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .padding(SmallestPadding),
                            soundPool = soundPool
                        )
                        IFilledButton(
                            onClick = { onResponseQuestion(answers[3]) },
                            label = answers[3],
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .padding(SmallestPadding),
                            soundPool = soundPool
                        )
                    }
                }
            }
            stickyHeader {
                IPrimaryCard(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(gameBottomBarHeight)
                        .padding(bottom = SmallestPadding)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(SmallestPadding),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Title(
                            text = "${stringResource(es.sebas1705.core.resources.R.string.core_resources_points)}: ${familiesState.points.toReducedString()}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Title(
                            text = "${familiesState.actualFamily + 1}/${familiesState.families.size}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        if (familiesState.mode == FamiliesMode.SURVIVAL) Row {
                            (1..3).forEach {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = stringResource(R.string.feature_game_lives),
                                    tint = if (it <= familiesState.lives) MaterialTheme.colorScheme.tertiary else Color.Gray
                                )
                            }
                        }
                        else if (familiesState.mode == FamiliesMode.TIME_ATTACK) Row {
                            (1..3).forEach {
                                Icon(
                                    imageVector = Icons.Filled.Timer,
                                    contentDescription = stringResource(R.string.feature_game_time),
                                    tint = if (it <= time / 5) MaterialTheme.colorScheme.tertiary else Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@UiModePreviews
@Composable
private fun RunningPreview() {
    AppTheme {
        Running()
    }
}
