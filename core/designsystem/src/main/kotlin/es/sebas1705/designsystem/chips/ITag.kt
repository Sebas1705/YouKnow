package es.sebas1705.designsystem.chips


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.AppTheme

/**
 * A small pill-shaped label tinted with its own [color], e.g. a difficulty or a category next to
 * a headline. Lighter-weight than [es.sebas1705.designsystem.chips.IAssistChip]: it is not
 * clickable, just a coloured tag.
 *
 * @param text [String]: Label text.
 * @param color [Color]: Tint for the border, fill and text.
 * @param modifier [Modifier]: Modifier.
 *
 * @since 1.3.0
 * @author Sebas1705 30/09/2026
 */
@Composable
fun ITag(text: String, color: Color, modifier: Modifier = Modifier) = Text(
    modifier = modifier
        .background(color.copy(alpha = 0.14f), CircleShape)
        .border(1.dp, color, CircleShape)
        .padding(horizontal = 10.dp, vertical = 3.dp),
    text = text,
    style = MaterialTheme.typography.labelMedium,
    fontWeight = FontWeight.Bold,
    color = color
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    ITag("Easy", MaterialTheme.colorScheme.tertiary)
}
