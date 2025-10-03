package es.sebas1705.designsystem.cards


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.designsystem.buttons.icon.IStandardIconButton
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.Paddings.SmallestPadding
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.designsystem.divider.IHorDivider

/**
 * Personalized resume card
 *
 * @param title [String]: Title
 * @param titlesValues [Map<String, String>]: Titles and values
 * @param modifier [Modifier]: Modifier
 * @param imageVector [Triple<ImageVector,String,()->Unit>]: Image vector
 *
 * @since 1.0.0
 * @author Sebas1705 21/09/2025
 */
@Composable
fun IResumeCard(
    title: String,
    titlesValues: Map<String, String>,
    modifier: Modifier = Modifier,
    imageVector: Triple<ImageVector, String, () -> Unit>? = null,
) = IPrimaryCard(modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(
                start = SmallPadding,
                top = SmallestPadding
            )
        )
        imageVector?.let {
            IStandardIconButton(
                imageVector = it.first,
                contentDescription = it.second,
                onClick = it.third,
                modifier = Modifier.padding(
                    end = SmallPadding,
                    top = SmallestPadding
                )
            )
        }
    }

    for ((titleT, value) in titlesValues) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = titleT,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(SmallPadding)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(SmallPadding)
            )
        }
        if (title != titlesValues.keys.last()) IHorDivider(
            modifier = Modifier.padding(horizontal = SmallPadding),
            color = MaterialTheme.colorScheme.onSurface
        )
        else Spacer(modifier = Modifier.height(SmallestPadding))
    }
}

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IResumeCard(
        "Title",
        titlesValues = mapOf(
            "Title 1" to "Value 1",
            "Title 2" to "Value 2",
            "Title 3" to "Value 3",
        ),
        imageVector = Triple(
            Icons.Filled.Link,
            "Edit"
        ) {},
    )
}
