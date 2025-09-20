package es.sebas1705.designsystem.divider


import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.OutlineThickness
import es.sebas1705.ui.theme.AppTheme

/**
 * Horizontal divider
 *
 * @param modifier [Modifier]: Modifier
 * @param color [Color]: Color
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun IHorDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.outline
) = HorizontalDivider(
    modifier = modifier,
    thickness = OutlineThickness,
    color = color
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IHorDivider()
}

