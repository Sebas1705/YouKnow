package es.sebas1705.designsystem.cards


import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.ui.theme.AppTheme

/**
 * Personalized filled card
 *
 * @param modifier [Modifier]: Modifier
 * @param content [ColumnScope.() -> Unit]: Content
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
 */
@Composable
fun IFilledCard(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit),
) = Card(
    modifier = modifier,
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer.disabled(),
        disabledContentColor = MaterialTheme.colorScheme.onSurface.disabled()
    ),
    content = content
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IFilledCard(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
    ) {
        Text("Hello, World!")
    }
}
