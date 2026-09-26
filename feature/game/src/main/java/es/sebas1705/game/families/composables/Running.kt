package es.sebas1705.game.families.composables

import android.media.SoundPool
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import es.sebas1705.common.games.families.FamiliesMode
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.feature.games.R
import es.sebas1705.game.common.AnswerOption
import es.sebas1705.game.common.AnswerState
import es.sebas1705.game.common.GameHud
import es.sebas1705.game.common.GameLoadError
import es.sebas1705.game.common.GamePage
import es.sebas1705.game.common.GameTag
import es.sebas1705.game.common.StickerCard
import es.sebas1705.game.common.rememberAnswerReveal
import es.sebas1705.game.common.tint
import es.sebas1705.game.families.viewmodel.FamiliesState
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.makeTitle
import kotlinx.coroutines.delay

/** Seconds per family in time attack. */
private const val FAMILY_TIME = 15f

/**
 * A running Families round: the HUD, the "which one doesn't belong?" card with its difficulty and
 * category, and the four words as a 2×2 grid. The chosen word shows green or red for a moment.
 *
 * @param windowState [WindowState]: State of the window.
 * @param familiesState [FamiliesState]: State of the game.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of SoundPool and volume.
 * @param onResponseQuestion (String) -> Unit: Callback with the chosen word ("TIME_OUT" on timeout).
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun Running(
    windowState: WindowState = WindowState.default(),
    familiesState: FamiliesState = FamiliesState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onResponseQuestion: (String) -> Unit = { }
) {
    if (familiesState.families.isEmpty()) {
        GameLoadError(windowState)
        return
    }
    val index = familiesState.actualFamily.coerceAtMost(familiesState.families.lastIndex)
    val family = familiesState.families[index]
    val (stateOf, choose) = rememberAnswerReveal(index, family.correctAnswer, onResponseQuestion)
    val revealing by rememberUpdatedState(stateOf(family.correctAnswer) != AnswerState.IDLE)

    // One countdown per family (it used to run once for the whole game).
    var time by remember(index) { mutableFloatStateOf(FAMILY_TIME) }
    if (familiesState.mode == FamiliesMode.TIME_ATTACK) {
        LaunchedEffect(index) {
            while (time > 0f) {
                delay(50)
                if (!revealing) time -= 0.05f
            }
            if (!revealing) onResponseQuestion("TIME_OUT")
        }
    }

    GamePage(windowState, filled = true, verticalArrangement = Arrangement.SpaceBetween) {
        GameHud(
            points = familiesState.points,
            round = index + 1,
            rounds = familiesState.families.size,
            lives = if (familiesState.mode == FamiliesMode.SURVIVAL) familiesState.lives else null,
            maxLives = 3,
            timeLeft = if (familiesState.mode == FamiliesMode.TIME_ATTACK) time else null,
            timeTotal = FAMILY_TIME
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StickerCard(
                modifier = Modifier.fillMaxWidth(),
                shadow = family.difficulty.tint(),
                shadowOffset = 6.dp,
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        GameTag(stringResource(family.difficulty.strRes), family.difficulty.tint())
                        GameTag(stringResource(family.category.strRes), MaterialTheme.colorScheme.primary)
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 4.dp),
                        text = stringResource(R.string.feature_game_families_game),
                        style = MaterialTheme.typography.headlineSmall.makeTitle(),
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(Modifier.height(28.dp))
            family.answers.chunked(2).forEach { pair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 7.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    pair.forEach { word ->
                        AnswerOption(
                            text = word,
                            state = stateOf(word),
                            onClick = { choose(word) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
        // Keeps the words off the bottom illustrations.
        Spacer(Modifier.height(72.dp))
    }
}

@UiModePreviews
@Composable
private fun RunningPreview() {
    AppTheme {
        Running()
    }
}
