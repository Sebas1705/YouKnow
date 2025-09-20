package es.sebas1705.designsystem.divider


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.OutlineThickness
import es.sebas1705.ui.theme.AppTheme

/**
 * Vertical divider
 *
 * @param modifier [Modifier]: Modifier
 * @param color [Color]: Color
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun IVerDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.outline
) = VerticalDivider(
    modifier = modifier,
    thickness = OutlineThickness,
    color = color
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IVerDivider()
}

