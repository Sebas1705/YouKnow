package es.sebas1705.survey.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import es.sebas1705.designsystem.cards.IStickerCard
import es.sebas1705.designsystem.textfields.IOutlinedTextField
import es.sebas1705.feature.survey.R
import es.sebas1705.models.stats.Opinion
import es.sebas1705.survey.schema.SurveyQuestion
import es.sebas1705.survey.schema.SurveySection

/** A 1..5 star rating with an optional one-line comment, for one [SurveyQuestion]. */
@Composable
fun OpinionQuestionRow(
    question: SurveyQuestion,
    opinion: Opinion,
    onPointsChange: (Int) -> Unit,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = question.label,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Row(
            modifier = Modifier.padding(top = 4.dp, bottom = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            (1..5).forEach { star ->
                val filled = star <= opinion.points
                Icon(
                    imageVector = if (filled) Icons.Filled.Star else Icons.Filled.StarBorder,
                    contentDescription = star.toString(),
                    tint = if (filled) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.outline,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .clickable { onPointsChange(if (opinion.points == star) 0 else star) }
                        .padding(4.dp)
                )
            }
        }
        IOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = opinion.opinion,
            onValueChange = onTextChange,
            label = stringResource(R.string.feature_survey_opinion_comment),
            placeholder = stringResource(R.string.feature_survey_opinion_comment),
        )
    }
}

/** A titled group of [OpinionQuestionRow]s, one per [SurveySection]. */
@Composable
fun SurveySectionCard(
    section: SurveySection,
    opinions: Map<String, Opinion>,
    onPointsChange: (String, Int) -> Unit,
    onTextChange: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    IStickerCard(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = section.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            section.questions.forEach { question ->
                OpinionQuestionRow(
                    question = question,
                    opinion = opinions[question.key] ?: Opinion(0, ""),
                    onPointsChange = { points -> onPointsChange(question.key, points) },
                    onTextChange = { text -> onTextChange(question.key, text) },
                )
            }
        }
    }
}
