package es.sebas1705.designsystem.spacers


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.Paddings.HugePadding
import es.sebas1705.ui.theme.AppTheme

/**
 * Horizontal spacer
 *
 * @param weight [Float]: Weight
 * @param width [Dp]: Width
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun RowScope.IHorSpacer(
    weight: Float? = null,
    width: Dp = HugePadding
) = Spacer(
    modifier =
    if (weight == null)
        Modifier
            .fillMaxWidth()
            .width(width)
    else Modifier
        .fillMaxWidth()
        .weight(weight)
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    Row {
        IHorSpacer(1f)
    }
}
