package es.sebas1705.game.wordpass.composables

import android.media.SoundPool
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.sebas1705.common.games.wordpass.WordPassMode
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.textfields.IOutlinedTextField
import es.sebas1705.feature.games.R
import es.sebas1705.game.common.GameHud
import es.sebas1705.game.common.GameLoadError
import es.sebas1705.game.common.GamePage
import es.sebas1705.game.common.GamePrimaryButton
import es.sebas1705.game.common.GameTag
import es.sebas1705.game.common.LetterStatus
import es.sebas1705.game.common.LetterWheel
import es.sebas1705.game.common.StickerCard
import es.sebas1705.game.common.tint
import es.sebas1705.game.wordpass.viewmodel.WordPassState
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.makeTitle

/**
 * A running Word-Pass: the HUD, the letter wheel (green guessed, red missed, the current letter
 * pulsing) with the word's pattern inside, the definition card, and the answer field. While the
 * keyboard is up the wheel folds into a single line so the definition stays visible.
 *
 * @param windowState [WindowState]: State of the window.
 * @param wordPassState [WordPassState]: State of the game.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of SoundPool and volume.
 * @param onResponse (String) -> Unit: Callback with the typed word.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun Running(
    windowState: WindowState = WindowState.default(),
    wordPassState: WordPassState = WordPassState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onResponse: (String) -> Unit = { }
) {
    if (wordPassState.words.isEmpty()) {
        GameLoadError(windowState)
        return
    }
    val index = wordPassState.actualWord.coerceAtMost(wordPassState.words.lastIndex)
    val word = wordPassState.words[index]
    var definition by rememberSaveable(index) { mutableIntStateOf(0) }
    var response by rememberSaveable(index) { mutableStateOf("") }
    val scheme = MaterialTheme.colorScheme
    val submit = {
        if (response.isNotBlank()) onResponse(response.trim())
    }

    GamePage(
        windowState = windowState,
        filled = true,
        modifier = Modifier.imePadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        GameHud(
            points = wordPassState.points,
            round = index + 1,
            rounds = wordPassState.words.size,
            lives = if (wordPassState.mode == WordPassMode.SURVIVAL) wordPassState.lives else null,
            maxLives = 3
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val statuses = wordPassState.words.indices.map { i ->
                when {
                    i < wordPassState.results.size -> if (wordPassState.results[i]) LetterStatus.CORRECT else LetterStatus.WRONG
                    i == index -> LetterStatus.CURRENT
                    else -> LetterStatus.PENDING
                }
            }
            if (!windowState.isImeVisible) {
                LetterWheel(
                    letters = wordPassState.words.map { it.letter.letter },
                    statuses = statuses,
                    modifier = Modifier.fillMaxWidth(),
                    maxSize = 300.dp
                ) {
                    WordPattern(word.letter.letter, word.toMoultedString())
                }
            } else {
                WordPattern(word.letter.letter, word.toMoultedString())
            }
            Spacer(Modifier.height(20.dp))
            StickerCard(
                modifier = Modifier.fillMaxWidth(),
                shadow = word.difficulty.tint(),
                shadowOffset = 6.dp,
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        GameTag(stringResource(word.difficulty.strRes), word.difficulty.tint())
                        if (word.definitions.size > 1) GameTag(
                            stringResource(R.string.feature_game_definition_of, definition + 1, word.definitions.size),
                            scheme.primary
                        )
                    }
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { definition = (definition - 1).coerceAtLeast(0) },
                            enabled = definition > 0
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowLeft, stringResource(R.string.feature_game_previous_definition))
                        }
                        AnimatedContent(
                            targetState = definition,
                            transitionSpec = { fadeIn() togetherWith fadeOut() },
                            modifier = Modifier.weight(1f),
                            label = "definition"
                        ) { shown ->
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = word.definitions.getOrElse(shown) { "" },
                                style = MaterialTheme.typography.titleMedium,
                                color = scheme.onSurface,
                                textAlign = TextAlign.Center
                            )
                        }
                        IconButton(
                            onClick = { definition = (definition + 1).coerceAtMost(word.definitions.size - 1) },
                            enabled = definition < word.definitions.size - 1
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowRight, stringResource(R.string.feature_game_next_definition))
                        }
                    }
                }
            }
            Spacer(Modifier.height(18.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IOutlinedTextField(
                    value = response,
                    onValueChange = { response = it },
                    modifier = Modifier.weight(1f),
                    label = stringResource(R.string.feature_game_guest_response),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrectEnabled = false,
                        imeAction = ImeAction.Send
                    ),
                    keyboardActions = KeyboardActions(onSend = { submit() })
                )
                GamePrimaryButton(
                    text = stringResource(R.string.feature_game_try_word),
                    icon = Icons.AutoMirrored.Filled.Send,
                    onClick = submit,
                    enabled = response.isNotBlank()
                )
            }
        }
        // Keeps the answer row off the bottom illustrations.
        Spacer(Modifier.height(if (windowState.isImeVisible) 8.dp else 48.dp))
    }
}

/** The current letter and the word's pattern ("A____a____"), animated when the word changes. */
@Composable
private fun WordPattern(letter: Char, pattern: String) {
    val scheme = MaterialTheme.colorScheme
    AnimatedContent(
        targetState = letter to pattern,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "pattern"
    ) { (shownLetter, shownPattern) ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = shownLetter.uppercase(),
                style = MaterialTheme.typography.displayMedium.makeTitle(),
                color = scheme.tertiary
            )
            Text(
                text = shownPattern,
                style = MaterialTheme.typography.titleMedium.makeTitle().copy(letterSpacing = 3.sp),
                color = scheme.primary,
                textAlign = TextAlign.Center
            )
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
