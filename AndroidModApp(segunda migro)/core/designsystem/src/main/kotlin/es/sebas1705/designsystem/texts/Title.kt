package es.sebas1705.designsystem.texts


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.makeTitle

/**
 * Title
 *
 * @param text [String]: Text
 * @param modifier [Modifier]: Modifier
 * @param style [TextStyle]: Style
 * @param color [Color]: Color
 * @param textAlign [TextAlign]: Text align
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.displayLarge,
    color: Color = MaterialTheme.colorScheme.primary,
    textAlign: TextAlign = TextAlign.Center
) = IText(
    modifier = modifier,
    text = text,
    style = style.makeTitle(),
    color = color,
    textAlign = textAlign,
    overflow = TextOverflow.Ellipsis
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    Title("Hello World")
}