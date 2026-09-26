package es.sebas1705.game.quiz.composables

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
import es.sebas1705.common.games.QuizType
import es.sebas1705.common.games.quiz.QuizMode
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.game.common.AnswerBadges
import es.sebas1705.game.common.AnswerOption
import es.sebas1705.game.common.AnswerState
import es.sebas1705.game.common.GameHud
import es.sebas1705.game.common.GameLoadError
import es.sebas1705.game.common.GamePage
import es.sebas1705.game.common.GameTag
import es.sebas1705.game.common.StickerCard
import es.sebas1705.game.common.rememberAnswerReveal
import es.sebas1705.game.common.tint
import es.sebas1705.game.quiz.viewmodel.QuizState
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.makeTitle
import kotlinx.coroutines.delay

/** Seconds per question in time attack. */
private const val QUESTION_TIME = 15f

/**
 * A running Quiz: the HUD (points, progress, lives or clock), the question card with its difficulty
 * and category, and the answers. The chosen answer shows green or red for a moment before the next
 * question.
 *
 * @param windowState [WindowState]: State of the window.
 * @param quizState [QuizState]: State of the quiz.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of SoundPool and volume.
 * @param onResponseQuestion (String) -> Unit: Callback with the chosen answer ("TIME_OUT" on timeout).
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun Running(
    windowState: WindowState = WindowState.default(),
    quizState: QuizState = QuizState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onResponseQuestion: (String) -> Unit = { }
) {
    if (quizState.questions.isEmpty()) {
        GameLoadError(windowState)
        return
    }
    val index = quizState.actualQuestion.coerceAtMost(quizState.questions.lastIndex)
    val question = quizState.questions[index]
    val (stateOf, choose) = rememberAnswerReveal(index, question.correctAnswer, onResponseQuestion)
    val revealing by rememberUpdatedState(stateOf(question.correctAnswer) != AnswerState.IDLE)

    // One countdown per question (it used to run once for the whole game).
    var time by remember(index) { mutableFloatStateOf(QUESTION_TIME) }
    if (quizState.mode == QuizMode.TIME_ATTACK) {
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
            points = quizState.points,
            round = index + 1,
            rounds = quizState.questions.size,
            lives = if (quizState.mode == QuizMode.SURVIVAL) quizState.lives else null,
            maxLives = 3,
            timeLeft = if (quizState.mode == QuizMode.TIME_ATTACK) time else null,
            timeTotal = QUESTION_TIME
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        StickerCard(
            modifier = Modifier.fillMaxWidth(),
            shadow = question.difficulty.tint(),
            shadowOffset = 6.dp,
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Column(Modifier.padding(20.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    GameTag(stringResource(question.difficulty.strRes), question.difficulty.tint())
                    GameTag(stringResource(question.category.strRes), MaterialTheme.colorScheme.primary)
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp),
                    text = question.question,
                    style = (if (question.question.length > 80) MaterialTheme.typography.titleLarge
                    else MaterialTheme.typography.headlineSmall).makeTitle(),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(Modifier.height(28.dp))
        val answers = question.answers
        if (question.quizType == QuizType.BOOLEAN) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                answers.forEach { answer ->
                    AnswerOption(
                        text = answer,
                        state = stateOf(answer),
                        onClick = { choose(answer) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                answers.forEachIndexed { i, answer ->
                    AnswerOption(
                        text = answer,
                        badge = AnswerBadges.getOrNull(i),
                        state = stateOf(answer),
                        onClick = { choose(answer) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        }
        // Keeps the question block off the bottom illustrations.
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
