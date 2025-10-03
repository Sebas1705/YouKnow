package es.sebas1705.designsystem.surfaces

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.OutlineThickness
import es.sebas1705.ui.theme.AppTheme

/**
 * Primary surface
 *
 * @param modifier [Modifier]: Modifier
 * @param content [Composable]: Content
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun IPrimarySurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) = Surface(
    modifier = modifier,
    color = MaterialTheme.colorScheme.surfaceContainer,
    shape = MaterialTheme.shapes.medium,
    border = BorderStroke(OutlineThickness, MaterialTheme.colorScheme.primary),
    content = content
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IPrimarySurface(
        modifier = Modifier
            .width(200.dp)
            .height(400.dp)
    ) {
        Text("Hello, World!")
    }
}