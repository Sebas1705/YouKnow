package es.sebas1705.designsystem.cards


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.ui.theme.OutlineThickness
import es.sebas1705.ui.theme.AppTheme

/**
 * Personalized outlined card
 *
 * @param modifier [Modifier]: Modifier
 * @param content [ColumnScope.() -> Unit]: Content
 *
 * @since 1.0.0
 * @author Sebas1705 21/09/2025
 */
@Composable
fun IOutlinedCard(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit),
) = OutlinedCard(
    modifier = modifier,
    colors = CardDefaults.elevatedCardColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor = MaterialTheme.colorScheme.surface.disabled(),
        disabledContentColor = MaterialTheme.colorScheme.onSurface.disabled()
    ),
    border = BorderStroke(OutlineThickness, MaterialTheme.colorScheme.outline),
    content = content
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IOutlinedCard(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
    ) {
        Text("Hello, World!")
    }
}
