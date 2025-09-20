package es.sebas1705.designsystem.cards


import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.ui.theme.AppTheme

/**
 * Personalized elevated card
 *
 * @param modifier [Modifier]: Modifier
 * @param content [ColumnScope.() -> Unit]: Content
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
 */
@Composable
fun IElevatedCard(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit),
) = ElevatedCard(
    modifier = modifier,
    colors = CardDefaults.elevatedCardColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor = MaterialTheme.colorScheme.surface.disabled(),
        disabledContentColor = MaterialTheme.colorScheme.onSurface.disabled()
    ),
    content = content
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IElevatedCard(
        modifier = Modifier
            .width(200.dp)
            .height(400.dp)
    ) {
        Text("Hello, World!")
    }
}
