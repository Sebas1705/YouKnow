package es.sebas1705.game.mysterynumber.composables

import android.media.SoundPool
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import es.sebas1705.common.games.mysterynumber.MysteryNumberMode
import es.sebas1705.common.games.mysterynumber.Numbers
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.feature.games.R
import es.sebas1705.game.common.GameHud
import es.sebas1705.game.common.GameLoadError
import es.sebas1705.game.common.GamePage
import es.sebas1705.game.common.GamePrimaryButton
import es.sebas1705.game.common.GameSecondaryButton
import es.sebas1705.game.common.GameTag
import es.sebas1705.game.common.StickerCard
import es.sebas1705.game.common.tint
import es.sebas1705.game.mysterynumber.viewmodel.MysteryNumberState
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.makeTitle
import kotlinx.coroutines.delay

/** Seconds of a time attack round. */
private const val ROUND_TIME = 50f

/**
 * A running Mystery Number: the HUD, a card with the range and the number being built, the
 * higher/lower hint of the last try with the history of guesses, and a keypad that only offers the
 * steps that fit the range.
 *
 * @param windowState [WindowState]: State of the window.
 * @param mysteryNumberState [MysteryNumberState]: State of the game.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of SoundPool and volume.
 * @param onResponseNumber (Int, Float) -> Unit: Callback with the tried number and the time left.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun Running(
    windowState: WindowState = WindowState.default(),
    mysteryNumberState: MysteryNumberState = MysteryNumberState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onResponseNumber: (Int, Float) -> Unit = { _, _ -> }
) {
    val number = mysteryNumberState.numberModel
    if (number.number == -2) {
        GameLoadError(windowState)
        return
    }
    val max = number.difficulty.maxMysteryNumber
    var time by rememberSaveable { mutableFloatStateOf(ROUND_TIME) }
    var current by rememberSaveable(number) { mutableIntStateOf(0) }
    var adding by rememberSaveable { mutableStateOf(true) }
    if (mysteryNumberState.mode == MysteryNumberMode.TIME_ATTACK) {
        LaunchedEffect(number) {
            time = ROUND_TIME
            while (time > 0f) {
                delay(50)
                time -= 0.05f
            }
            onResponseNumber(-1, time)
        }
    }
    val scheme = MaterialTheme.colorScheme
    val lastGuess = mysteryNumberState.guesses.lastOrNull()

    GamePage(windowState, filled = true, verticalArrangement = Arrangement.SpaceBetween) {
        GameHud(
            points = mysteryNumberState.points,
            lives = mysteryNumberState.lives,
            timeLeft = if (mysteryNumberState.mode == MysteryNumberMode.TIME_ATTACK) time else null,
            timeTotal = ROUND_TIME
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StickerCard(
                modifier = Modifier.fillMaxWidth(),
                shadow = number.difficulty.tint(),
                shadowOffset = 6.dp,
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.feature_game_mystery_title),
                        style = MaterialTheme.typography.titleLarge.makeTitle(),
                        color = scheme.primary
                    )
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        GameTag(stringResource(number.difficulty.strRes), number.difficulty.tint())
                        GameTag("1 – $max", scheme.primary)
                    }
                    AnimatedContent(
                        targetState = current,
                        transitionSpec = {
                            (fadeIn() + scaleIn(initialScale = 0.85f, animationSpec = spring(Spring.DampingRatioMediumBouncy)))
                                .togetherWith(fadeOut())
                        },
                        label = "number"
                    ) { value ->
                        Text(
                            modifier = Modifier.padding(vertical = 12.dp),
                            text = value.toString(),
                            style = MaterialTheme.typography.displayLarge.makeTitle(),
                            color = scheme.tertiary
                        )
                    }
                    GuessHint(lastGuess, number.number)
                    if (mysteryNumberState.guesses.isNotEmpty()) GuessHistory(mysteryNumberState.guesses.takeLast(6), number.number)
                }
            }
            Spacer(Modifier.height(24.dp))
            val steps = Numbers.entries.filter { it.number <= max }
            steps.chunked(3).forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    row.forEach { step ->
                        GameSecondaryButton(
                            text = (if (adding) "+" else "−") + step.str,
                            onClick = {
                                current = (current + if (adding) step.number else -step.number).coerceIn(0, max)
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    repeat(3 - row.size) { Spacer(Modifier.weight(1f)) }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GameSecondaryButton(
                    text = if (adding) "+ / −" else "− / +",
                    onClick = { adding = !adding }
                )
                GameSecondaryButton(
                    text = "0",
                    icon = Icons.Filled.Refresh,
                    onClick = { current = 0 }
                )
                GamePrimaryButton(
                    text = stringResource(R.string.feature_game_try_number),
                    icon = Icons.Filled.Search,
                    onClick = { onResponseNumber(current, time) },
                    enabled = current > 0,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        // Keeps the keypad off the bottom illustrations.
        Spacer(Modifier.height(56.dp))
    }
}

/** "Higher!" or "Lower!" for the last try, or the prompt before the first one. */
@Composable
private fun GuessHint(lastGuess: Int?, secret: Int) {
    val scheme = MaterialTheme.colorScheme
    if (lastGuess == null || lastGuess == secret) {
        Text(
            text = stringResource(R.string.feature_game_guess_prompt),
            style = MaterialTheme.typography.bodyMedium,
            color = scheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        return
    }
    val higher = secret > lastGuess
    AnimatedContent(targetState = lastGuess to higher, label = "hint") { (guess, up) ->
        Row(
            modifier = Modifier
                .background(scheme.tertiary.copy(alpha = 0.14f), CircleShape)
                .border(1.5.dp, scheme.tertiary, CircleShape)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (up) Icons.Filled.ArrowUpward else Icons.Filled.ArrowDownward,
                contentDescription = null,
                tint = scheme.tertiary
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = stringResource(if (up) R.string.feature_game_higher else R.string.feature_game_lower) + "  ($guess)",
                style = MaterialTheme.typography.titleMedium.makeTitle(),
                color = scheme.tertiary
            )
        }
    }
}

/** The last guesses as small tags with the direction each one pointed to. */
@Composable
private fun GuessHistory(guesses: List<Int>, secret: Int) {
    val scheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier.padding(top = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.feature_game_your_guesses),
            style = MaterialTheme.typography.labelMedium,
            color = scheme.onSurfaceVariant
        )
        Row(
            modifier = Modifier.padding(top = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            guesses.forEach { guess ->
                val up = secret > guess
                Row(
                    modifier = Modifier
                        .background(scheme.primary.copy(alpha = 0.08f), CircleShape)
                        .padding(horizontal = 8.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = guess.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = scheme.primary
                    )
                    Icon(
                        imageVector = if (up) Icons.Filled.ArrowUpward else Icons.Filled.ArrowDownward,
                        contentDescription = null,
                        tint = if (guess == secret) Color.Unspecified else scheme.primary,
                        modifier = Modifier.size(14.dp)
                    )
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
