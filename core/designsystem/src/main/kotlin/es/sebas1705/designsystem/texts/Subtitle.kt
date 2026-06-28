package es.sebas1705.designsystem.texts


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.makeTitle

/**
 * Subtitle
 *
 * @param text [String]: Text
 * @param modifier [Modifier]: Modifier
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun Subtitle(
    text: String,
    modifier: Modifier = Modifier,
) = Text(
    modifier = modifier,
    text = text,
    style = MaterialTheme.typography.headlineSmall.makeTitle(),
    color = MaterialTheme.colorScheme.onSurface,
    textAlign = TextAlign.Center
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    Subtitle("Hello World")
}