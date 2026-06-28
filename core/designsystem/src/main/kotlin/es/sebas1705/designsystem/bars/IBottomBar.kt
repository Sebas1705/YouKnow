package es.sebas1705.designsystem.bars


import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.TonalElevation
import es.sebas1705.ui.theme.AppTheme

/**
 * Personalized bottom bar
 *
 * @param modifier [Modifier]: Modifier
 * @param content [RowScope.() -> Unit]: Content
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun IBottomBar(
    modifier: Modifier = Modifier,
    content: @Composable (RowScope.() -> Unit),
) = BottomAppBar(
    modifier = modifier,
    containerColor = MaterialTheme.colorScheme.surfaceContainer,
    contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
    tonalElevation = TonalElevation.Level2,
    content = content
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IBottomBar {
        IText("Hola mundo")
    }
}