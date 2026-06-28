package es.sebas1705.designsystem.badges


import androidx.compose.material3.Badge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.AppTheme

/**
 * Personalized badge
 *
 * @param text [String]: Text to show
 * @param modifier [Modifier]: Modifier
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun IBadge(
    text: String,
    modifier: Modifier = Modifier,
) = Badge(
    modifier = modifier,
    containerColor = MaterialTheme.colorScheme.error,
    contentColor = MaterialTheme.colorScheme.onError,
    content = {
        Text(text = text)
    }
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IBadge(
        text = "999+"
    )
}