package es.sebas1705.game.mysterynumber.composables


import android.media.SoundPool
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.games.families.FamiliesMode
import es.sebas1705.common.games.mysterynumber.MysteryNumberMode
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.theme.SizeType
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.common.IFilledTonalButton
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.texts.Title
import es.sebas1705.ui.theme.Paddings.HugePadding
import es.sebas1705.ui.theme.Paddings.LargePadding
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.Paddings.SmallestPadding
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.feature.games.R

/**
 * Selection mode of the Mystery Number game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onSelectMode ([MysteryNumberMode]) -> Unit: Function to select the game mode.
 *
 * @since 1.0.0
 * @Author Sebas1705 12/09/2025
 */
@Composable
fun SelectionMode(
    windowState: WindowState = WindowState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onSelectMode: (MysteryNumberMode) -> Unit = { }
) {
    //Body:
    ApplyBack(
        backId = windowState.backEmpty
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Title(
                    modifier = Modifier.padding(bottom = windowState.heightFilter(
                        MediumPadding,
                        LargePadding,
                        HugePadding
                    )),
                    text = stringResource(id = R.string.feature_game_mode_title)
                )
            }

            if (windowState.widthType == SizeType.COMPACT)
                items(MysteryNumberMode.entries.size) {
                    val mode = MysteryNumberMode.entries[it]
                    IFilledTonalButton(
                        modifier = Modifier
                            .fillMaxWidth(
                                windowState.widthFilter(0.9f, 0.7f, 0.5f)
                            )
                            .padding(vertical = SmallestPadding),
                        onClick = { onSelectMode(mode) },
                        label = stringResource(id = mode.strRes),
                        imageVector = mode.icon,
                        soundPool = soundPool
                    )
                }
            else
                items(MysteryNumberMode.entries.size) { index ->
                    if (index % 2 != 0)
                        return@items
                    val mode1 = MysteryNumberMode.entries[index]
                    val mode2 = MysteryNumberMode.entries.getOrNull(index + 1)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement =
                        if (mode2 != null) Arrangement.SpaceEvenly
                        else Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val width = windowState.widthDp / 3
                        IFilledTonalButton(
                            onClick = { onSelectMode(mode1) },
                            label = stringResource(id = mode1.strRes),
                            imageVector = mode1.icon,
                            modifier = Modifier
                                .width(width)
                                .fillMaxHeight(0.25f),
                            soundPool = soundPool
                        )
                        mode2?.let {
                            IFilledTonalButton(
                                onClick = { onSelectMode(it) },
                                label = stringResource(id = it.strRes),
                                imageVector = it.icon,
                                modifier = Modifier
                                    .width(width)
                                    .fillMaxHeight(0.25f),
                                soundPool = soundPool
                            )
                        }
                    }
                    if(index != FamiliesMode.entries.size - 1 && index != FamiliesMode.entries.size - 2) {
                        Spacer(modifier = Modifier.height(MediumPadding))
                    }
                }
        }
    }
}

@UiModePreviews
@Composable
private fun SelectModePreview() {
    AppTheme {
        SelectionMode()
    }
}