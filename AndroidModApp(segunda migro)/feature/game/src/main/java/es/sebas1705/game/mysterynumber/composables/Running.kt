package es.sebas1705.game.mysterynumber.composables


import android.media.SoundPool
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.games.mysterynumber.MysteryNumberMode
import es.sebas1705.common.games.mysterynumber.Numbers
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.common.utlis.extensions.primitives.toReducedString
import es.sebas1705.designsystem.buttons.common.IFilledButton
import es.sebas1705.designsystem.cards.IPrimaryCard
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.texts.Subtitle
import es.sebas1705.designsystem.texts.Title
import es.sebas1705.designsystem.texts.TitleSurface
import es.sebas1705.game.mysterynumber.viewmodel.MysteryNumberState
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.Paddings.SmallestPadding
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.gameBottomBarHeight
import es.sebas1705.feature.games.R
import kotlinx.coroutines.delay

/**
 * Running mode of the Mystery Number game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param mysteryNumberState [MysteryNumberState]: State of the game.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onResponseNumber ([Int], [Float]) -> Unit: Function to respond to the number.
 *
 * @since 1.0.0
 * @Author Sebas1705 12/09/2025
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Running(
    windowState: WindowState = WindowState.default(),
    mysteryNumberState: MysteryNumberState = MysteryNumberState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onResponseNumber: (Int, Float) -> Unit = { _, _ -> }
) {
    //Body:
    ApplyBack(
        backId = windowState.backFill
    ) {
        if (mysteryNumberState.numberModel.number == -2) {
            Title(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.feature_game_error_loading_message),
                color = MaterialTheme.colorScheme.error
            )
            return@ApplyBack
        }
        var time by rememberSaveable { mutableFloatStateOf(15f) }
        val number = mysteryNumberState.numberModel
        var actualNumber by rememberSaveable { mutableIntStateOf(0) }
        var plus by rememberSaveable { mutableStateOf(true) }
        if (mysteryNumberState.mode == MysteryNumberMode.TIME_ATTACK) {
            LaunchedEffect(number) {
                time = 50f
                while (time > 0) {
                    delay(10)
                    time -= 0.01f
                }
                onResponseNumber(-1, time)
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TitleSurface(
                    modifier = Modifier
                        .padding(MediumPadding),
                    text = stringResource(R.string.feature_game_mystery_title)
                )
            }
            item {
                Subtitle(
                    text = stringResource(R.string.feature_game_difficulty)
                            + ": " + stringResource(number.difficulty.strRes)
                            + "\n" + stringResource(R.string.feature_game_range) + "1 - ${number.difficulty.maxMysteryNumber}"
                )
            }
            item {
                Title(
                    modifier = Modifier
                        .padding(MediumPadding),
                    text = actualNumber.toString(),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            item {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(
                        (if (windowState.isPortrait) windowState.widthDp else windowState.heightDp)
                                * 0.33f
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(
                            if (windowState.isPortrait) windowState.widthDp * 0.5f
                            else windowState.widthDp * 0.1f
                        )
                ) {
                    items(Numbers.entries.size) {
                        IFilledButton(
                            onClick = {
                                actualNumber += if (plus) Numbers.entries[it].number else -Numbers.entries[it].number
                                if (actualNumber < 0) actualNumber = 0
                                if (actualNumber > 1_000_000) actualNumber = 1_000_000
                            },
                            label = (if (plus) "+" else "-") + Numbers.entries[it].str,
                            modifier = Modifier.padding(SmallestPadding),
                            soundPool = soundPool
                        )
                    }
                }
            }
            item {
                IFilledButton(
                    onClick = {
                        plus = !plus
                    },
                    label = stringResource(R.string.feature_game_minus_plus),
                    modifier = Modifier.padding(SmallestPadding),
                    soundPool = soundPool
                )
                IFilledButton(
                    onClick = {
                        onResponseNumber(actualNumber, time)
                    },
                    label = stringResource(R.string.feature_game_try_number),
                    modifier = Modifier.padding(MediumPadding),
                    soundPool = soundPool
                )
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
                            text = "${stringResource(es.sebas1705.core.resources.R.string.core_resources_points)}: ${mysteryNumberState.points.toReducedString()}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Row {
                            Title(
                                text = "${mysteryNumberState.lives}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = stringResource(R.string.feature_game_lives),
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                        }
                        if (mysteryNumberState.mode == MysteryNumberMode.TIME_ATTACK) Row {
                            Title(
                                text = "${time.toInt()}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Icon(
                                imageVector = Icons.Filled.Timer,
                                contentDescription = stringResource(R.string.feature_game_time),
                                tint = MaterialTheme.colorScheme.tertiary
                            )
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
