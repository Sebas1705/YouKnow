package es.sebas1705.game.wordpass.composables


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
import es.sebas1705.common.games.wordpass.WordPassMode
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
 * SelectionMode composable
 *
 * @param windowState WindowState
 * @param soundPool Pair<SoundPool, Float>?
 * @param onSelectMode Function1<WordPassMode, Unit>
 *
 * @since 1.0.0
 * @author Sebastian Ramiro Entrerrios Garcia
 */
@Composable
fun SelectionMode(
    windowState: WindowState = WindowState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onSelectMode: (WordPassMode) -> Unit = { }
) {
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
                    modifier = Modifier.padding(
                        bottom = windowState.heightFilter(
                            MediumPadding,
                            LargePadding,
                            HugePadding
                        )
                    ),
                    text = stringResource(id = R.string.feature_game_mode_title)
                )
            }

            if (windowState.widthType == SizeType.COMPACT)
                items(WordPassMode.entries.size) {
                    val mode = WordPassMode.entries[it]
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
                items(WordPassMode.entries.size) { wordPassMode ->
                    if (wordPassMode % 2 != 0)
                        return@items
                    val mode1 = WordPassMode.entries[wordPassMode]
                    val mode2 = WordPassMode.entries.getOrNull(wordPassMode + 1)
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
                    if (wordPassMode != FamiliesMode.entries.size - 1 && wordPassMode != FamiliesMode.entries.size - 2) {
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