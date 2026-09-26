package es.sebas1705.game.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.common.states.WindowState
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.texts.Title
import es.sebas1705.feature.games.R

/**
 * Page of a game: the app's background ([filled] for the play screens, the lighter one for mode
 * selection and results) with a scrollable, centred column no wider than a phone.
 */
@Composable
fun GamePage(
    windowState: WindowState,
    filled: Boolean,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable ColumnScope.() -> Unit,
) = ApplyBack(backId = if (filled) windowState.backFill else windowState.backEmpty) {
    // The column is at least as tall as the screen so [verticalArrangement] can spread the
    // content, and it still scrolls when the content is taller (small screens, keyboard up).
    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 560.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .heightIn(min = maxHeight)
                .padding(horizontal = 20.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = verticalArrangement,
            content = content
        )
    }
}

/** Shown when a game could not load its data. */
@Composable
fun GameLoadError(windowState: WindowState) = GamePage(windowState, filled = true, verticalArrangement = Arrangement.Center) {
    Title(
        text = stringResource(R.string.feature_game_error_loading_message),
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.headlineSmall
    )
}

/**
 * A [ModeOption] for one of the games' mode enums, described by its name (SURVIVAL, TIME_ATTACK,
 * ALEATORY, CUSTOM, FIRE_WHEEL) with its round count, lives and points multiplier as tags.
 */
@Composable
fun modeOption(
    key: String,
    nameRes: Int,
    icon: ImageVector,
    multiplier: Double,
    rounds: Int? = null,
    lives: Int? = null,
): ModeOption {
    val description = stringResource(
        when (key) {
            "SURVIVAL" -> R.string.feature_game_mode_desc_survival
            "TIME_ATTACK" -> R.string.feature_game_mode_desc_time_attack
            "CUSTOM" -> R.string.feature_game_mode_desc_custom
            "FIRE_WHEEL" -> R.string.feature_game_mode_desc_fire_wheel
            else -> R.string.feature_game_mode_desc_aleatory
        }
    )
    val details = buildList {
        if (rounds != null && rounds > 0) add(stringResource(R.string.feature_game_mode_rounds, rounds))
        if (lives != null && lives > 0) add(stringResource(R.string.feature_game_mode_lives, lives))
        if (multiplier > 0.0) add(stringResource(R.string.feature_game_mode_multiplier, multiplier.asMultiplier()))
    }
    return ModeOption(stringResource(nameRes), icon, description, details)
}

/** The letters used as badges for multiple-choice answers. */
val AnswerBadges = listOf("A", "B", "C", "D", "E", "F")
