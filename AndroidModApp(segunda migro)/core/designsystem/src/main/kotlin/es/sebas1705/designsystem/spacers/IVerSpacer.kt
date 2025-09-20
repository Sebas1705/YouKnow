package es.sebas1705.designsystem.spacers


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
 * @param height [Dp]: Height
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun ColumnScope.IVerSpacer(
    weight: Float? = null,
    height: Dp = HugePadding
) = Spacer(
    modifier =
    if (weight == null)
        Modifier
            .fillMaxWidth()
            .height(height)
    else Modifier
        .fillMaxWidth()
        .weight(weight)
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    Column {
        IVerSpacer(1f)
    }
}

