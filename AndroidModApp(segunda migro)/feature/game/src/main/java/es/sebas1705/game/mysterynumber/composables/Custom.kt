package es.sebas1705.game.mysterynumber.composables


import android.media.SoundPool
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Start
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.common.IOutlinedButton
import es.sebas1705.designsystem.buttons.radio.IRadioButton
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.spacers.PaddingSpacers.MediumSpacer
import es.sebas1705.designsystem.spacers.PaddingSpacers.SmallSpacer
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.designsystem.texts.TitleSurface
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.feature.games.R

/**
 * Custom screen of the Mystery Number game.
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
    //States:
    val difficulty = rememberSaveable { mutableIntStateOf(0) }
    val difficultyEnum = Difficulty.entries[difficulty.intValue]
    var lives by rememberSaveable { mutableIntStateOf(10) }
    val titleStyle = windowState.heightType.filter(
        MaterialTheme.typography.titleSmall,
        MaterialTheme.typography.titleLarge,
        MaterialTheme.typography.headlineMedium
    )

    //Body:
    ApplyBack(
        backId = windowState.backEmpty
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                MediumSpacer()
                TitleSurface(stringResource(R.string.feature_game_custom_title))
                MediumSpacer()
            }

            item {
                IText(
                    text = stringResource(R.string.feature_game_lives) +
                            " $lives",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = titleStyle
                )
                SmallSpacer()
                Slider(
                    modifier = Modifier
                        .fillMaxWidth(windowState.widthType.filter(0.9f, 0.7f, 0.5f)),
                    value = lives / 100f,
                    onValueChange = { lives = (it * 100).toInt() },
                    steps = 99,
                )
                MediumSpacer()
            }

            item {
                IText(
                    text = stringResource(R.string.feature_game_difficulty) +
                            ": " + stringResource(difficultyEnum.strRes),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = titleStyle
                )
                SmallSpacer()
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    item {
                        if (windowState.isPortrait) Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Difficulty.entries.forEach {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    IRadioButton(
                                        selected = difficulty.intValue == it.ordinal,
                                        onClick = { difficulty.intValue = it.ordinal },
                                        soundPool = soundPool
                                    )
                                    IText(
                                        text = stringResource(it.strRes),
                                        color = MaterialTheme.colorScheme.onBackground,
                                        style = MaterialTheme.typography.titleSmall,
                                    )
                                }
                            }
                        }
                        else Row {
                            Difficulty.entries.forEach {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    IRadioButton(
                                        selected = difficulty.intValue == it.ordinal,
                                        onClick = { difficulty.intValue = it.ordinal },
                                        soundPool = soundPool
                                    )
                                    IText(
                                        text = stringResource(it.strRes),
                                        color = MaterialTheme.colorScheme.onBackground,
                                        style = MaterialTheme.typography.titleSmall,
                                    )
                                }
                            }
                        }
                    }
                }
                MediumSpacer()
            }
            item {
                IOutlinedButton(
                    onClick = { onStartGame(difficultyEnum, lives) },
                    label = stringResource(R.string.feature_game_start_game),
                    imageVector = Icons.Filled.Start,
                    soundPool = soundPool
                )
                MediumSpacer()
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

