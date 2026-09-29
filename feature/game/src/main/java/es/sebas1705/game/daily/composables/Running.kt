package es.sebas1705.game.daily.composables

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import es.sebas1705.common.games.QuizType
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.cards.IStickerCard
import es.sebas1705.designsystem.chips.ITag
import es.sebas1705.feature.games.R
import es.sebas1705.game.common.AnswerBadges
import es.sebas1705.game.common.AnswerOption
import es.sebas1705.game.common.GameHud
import es.sebas1705.game.common.GameLoadError
import es.sebas1705.game.common.GamePage
import es.sebas1705.game.common.rememberAnswerReveal
import es.sebas1705.game.common.tint
import es.sebas1705.game.daily.viewmodel.DailyChallengeState
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.makeTitle
import es.sebas1705.models.games.QuestionModel

/**
 * A running Daily Challenge: the HUD and one shared question at a time, answered the same way as
 * a Quiz round. No timer, no lives — it is a short, once-a-day round everyone gets identically.
 *
 * @param windowState [WindowState]: State of the window.
 * @param dailyChallengeState [DailyChallengeState]: State of the challenge.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of SoundPool and volume.
 * @param onResponse (String) -> Unit: Callback with the chosen answer.
 *
 * @since 1.3.0
 * @author Sebas1705 30/09/2026
 */
@Composable
fun Running(
    windowState: WindowState = WindowState.default(),
    dailyChallengeState: DailyChallengeState = DailyChallengeState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onResponse: (String) -> Unit = { }
) {
    if (dailyChallengeState.questions.isEmpty()) {
        GameLoadError(windowState)
        return
    }
    val index = dailyChallengeState.actualQuestion.coerceAtMost(dailyChallengeState.questions.lastIndex)
    val question = dailyChallengeState.questions[index]
    val (stateOf, choose) = rememberAnswerReveal(index, question.correctAnswer, onResponse)

    GamePage(windowState, filled = true, verticalArrangement = Arrangement.SpaceBetween) {
        GameHud(
            points = dailyChallengeState.points,
            round = index + 1,
            rounds = dailyChallengeState.questions.size,
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IStickerCard(
                modifier = Modifier.fillMaxWidth(),
                shadow = question.difficulty.tint(),
                shadowOffset = 6.dp,
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Column(Modifier.padding(20.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        ITag(stringResource(question.difficulty.strRes), question.difficulty.tint())
                        ITag(stringResource(question.category.strRes), MaterialTheme.colorScheme.primary)
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
        Running(
            dailyChallengeState = DailyChallengeState.default().copy(
                questions = QuestionModel.defaultMultipleList(5)
            )
        )
    }
}
