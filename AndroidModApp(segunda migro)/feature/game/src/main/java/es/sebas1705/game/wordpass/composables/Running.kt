package es.sebas1705.game.wordpass.composables


import android.media.SoundPool
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.wordpass.WordPassMode
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.common.utlis.extensions.primitives.toReducedString
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.designsystem.buttons.icon.IStandardIconButton
import es.sebas1705.designsystem.cards.IPrimaryCard
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.texts.Title
import es.sebas1705.designsystem.texts.TitleSurface
import es.sebas1705.game.wordpass.viewmodel.WordPassState
import es.sebas1705.models.games.WordModel
import es.sebas1705.ui.theme.OutlineThickness
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.Paddings.SmallestPadding
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.gameBottomBarHeight
import es.sebas1705.youknow.core.composables.textfields.IOutlinedTextField
import es.sebas1705.feature.games.R

/**
 * Screen of the Word Pass Game.
 *
 * @param windowState [WindowState]: State of the window.
 * @param wordPassState [WordPassState]: State of the game.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onResponse (String) -> Unit: Function that will be called when the user responds to the game.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Running(
    windowState: WindowState = WindowState.default(),
    wordPassState: WordPassState = WordPassState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onResponse: (String) -> Unit = { }
) {
    ApplyBack(
        backId = windowState.backFill
    ) {
        if (wordPassState.words.isEmpty()) {
            Title(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.feature_game_error_loading_message),
                color = MaterialTheme.colorScheme.error
            )
            return@ApplyBack
        }
        val word = wordPassState.words[wordPassState.actualWord]
        var definition by rememberSaveable { mutableIntStateOf(0) }
        val color = when (word.difficulty) {
            Difficulty.EASY -> Color.Green
            Difficulty.MEDIUM -> Color.Yellow
            Difficulty.HARD -> Color.Red
            else -> MaterialTheme.colorScheme.tertiary
        }
        val lazyMod = if (windowState.isImeVisible) Modifier.imePadding() else Modifier.padding(
            SmallestPadding
        )
        LazyColumn(
            modifier = lazyMod
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TitleSurface(
                    modifier = Modifier
                        .padding(MediumPadding)
                        .border(OutlineThickness, color, MaterialTheme.shapes.small),
                    text = word.toMoultedString(),
                    textAlign = TextAlign.Center,
                    textStyle = when {
                        wordPassState.words[wordPassState.actualWord].word.length > 9 -> MaterialTheme.typography.titleMedium
                        wordPassState.words[wordPassState.actualWord].word.length > 5 -> MaterialTheme.typography.headlineMedium
                        else -> MaterialTheme.typography.displayMedium
                    }
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.2f),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var response by rememberSaveable { mutableStateOf("") }
                    IOutlinedTextField(
                        value = response,
                        onValueChange = { response = it },
                        label = stringResource(R.string.feature_game_guest_response),
                        placeholder = stringResource(R.string.feature_game_guest_response),
                        modifier = Modifier.fillMaxWidth(0.6f),
                        soundPool = soundPool
                    )
                    ITextButton(
                        onClick = {
                            onResponse(response)
                            definition = 0
                        },
                        label = stringResource(R.string.feature_game_try_word),
                        soundPool = soundPool
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.4f),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IStandardIconButton(
                        imageVector = Icons.AutoMirrored.Filled.ArrowLeft,
                        contentDescription = stringResource(R.string.feature_game_previous_definition),
                        onClick = {
                            definition = (definition - 1).coerceAtLeast(0)
                        },
                        soundPool = soundPool
                    )
                    Title(
                        modifier = Modifier.fillMaxWidth(0.7f),
                        text = word.definitions[definition],
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    IStandardIconButton(
                        imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                        contentDescription = stringResource(R.string.feature_game_next_definition),
                        onClick = {
                            definition = (definition + 1).coerceAtMost(word.definitions.size - 1)
                        },
                        soundPool = soundPool
                    )

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
                            text = "${stringResource(es.sebas1705.core.resources.R.string.core_resources_points)}: ${wordPassState.points.toReducedString()}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Row {
                            val titles = wordPassState.words.toViewList(wordPassState.actualWord)
                            Title(
                                modifier = Modifier.padding(end = SmallestPadding),
                                text = titles[0],
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Title(
                                text = titles[1],
                                style = MaterialTheme.typography.titleMedium,
                                color = color
                            )
                            Title(
                                modifier = Modifier.padding(start = SmallestPadding),
                                text = titles[2],
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        if (wordPassState.mode == WordPassMode.SURVIVAL) Row {
                            (1..3).forEach {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = stringResource(R.string.feature_game_lives),
                                    tint = if (it <= wordPassState.lives) MaterialTheme.colorScheme.tertiary else Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Transform a list of [WordModel] to a list of [String]
 *
 * @receiver [List]<[WordModel]>: list of words
 * @param actualWord: [Int]: actual word
 *
 * @return [List]<[String]>: list of words
 *
 * @see WordModel
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
private fun List<WordModel>.toViewList(actualWord: Int): List<String> {
    val previousWord = if (actualWord > 0) this[actualWord - 1].word else ""
    val currentWord = this[actualWord].word
    val nextWord = if (actualWord < this.size - 1) this[actualWord + 1].word else ""

    val previousString = if (previousWord.isNotEmpty()) "..${previousWord.first()}" else ""
    val currentString = currentWord.first().toString()
    val nextString = if (nextWord.isNotEmpty()) "${nextWord.first()}.." else ""

    return listOf(previousString, currentString, nextString)
}

@UiModePreviews
@Composable
private fun RunningPreview() {
    AppTheme {
        Running()
    }
}
