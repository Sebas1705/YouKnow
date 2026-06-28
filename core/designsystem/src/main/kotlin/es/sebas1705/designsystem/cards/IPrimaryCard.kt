package es.sebas1705.designsystem.cards


import androidx.compose.foundation.BorderStroke
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
import es.sebas1705.ui.theme.OutlineThickness
import es.sebas1705.ui.theme.AppTheme

/**
 * Personalized primary card
 *
 * @param modifier [Modifier]: Modifier
 * @param content [ColumnScope.() -> Unit]: Content
 *
 * @since 1.0.0
 * @author Sebas1705 21/09/2025
 */
@Composable
fun IPrimaryCard(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit),
) = Card(
    modifier = modifier,
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary
    ),
    border = BorderStroke(OutlineThickness, MaterialTheme.colorScheme.primary),
    content = content
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IPrimaryCard(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
    ) {
        Text("Hello, World!")
    }
}

