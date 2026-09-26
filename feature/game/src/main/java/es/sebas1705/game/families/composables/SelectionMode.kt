package es.sebas1705.game.families.composables

import android.media.SoundPool
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.feature.games.R
import es.sebas1705.game.common.GamePage
import es.sebas1705.game.common.ModeSelectionContent
import es.sebas1705.game.common.modeOption
import es.sebas1705.common.games.families.FamiliesMode
import es.sebas1705.ui.theme.AppTheme

/**
 * Mode selection of Families: the game's illustration and one card per mode, with what it is, how
 * many families it has and its points multiplier.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of SoundPool and volume.
 * @param onSelectMode (FamiliesMode) -> Unit: Callback when a mode is selected.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun SelectionMode(
    windowState: WindowState = WindowState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onSelectMode: (FamiliesMode) -> Unit = { }
) {
    val modes = FamiliesMode.entries
    val options = modes.map {
        modeOption(it.name, it.strRes, it.icon, it.multiPoints, rounds = it.numFamilies)
    }
    GamePage(windowState, filled = false, verticalArrangement = Arrangement.Center) {
        ModeSelectionContent(
            title = stringResource(R.string.feature_game_title_families),
            illustration = es.sebas1705.core.resources.R.drawable.game_family,
            options = options,
            onSelect = { onSelectMode(modes[it]) }
        )
    }
}

@UiModePreviews
@Composable
private fun SelectionModePreview() {
    AppTheme {
        SelectionMode()
    }
}
