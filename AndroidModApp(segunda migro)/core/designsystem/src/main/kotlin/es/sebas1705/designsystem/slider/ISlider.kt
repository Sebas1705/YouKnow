package es.sebas1705.designsystem.slider


import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.ui.theme.AppTheme

/**
 * ISlider
 *
 * @param value [Float]: Value
 * @param onValueChange [(Float) -> Unit]: On value change
 * @param modifier [Modifier]: Modifier
 * @param enabled [Boolean]: Enabled
 * @param valueRange [ClosedFloatingPointRange<Float>]: Value range
 * @param steps [Int]: Steps
 * @param onValueChangeFinished [(() -> Unit)?]: On value change finished
 * @param interactionSource [MutableInteractionSource]: Interaction source
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun ISlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) = Slider(
    value,
    onValueChange,
    modifier,
    enabled,
    valueRange,
    steps,
    onValueChangeFinished,
    colors = SliderDefaults.colors(
        //Thumb
        thumbColor = MaterialTheme.colorScheme.primary,
        disabledThumbColor = MaterialTheme.colorScheme.primary.disabled(),
        //End Thumb
        //Active track
        activeTrackColor = MaterialTheme.colorScheme.primary,
        disabledActiveTrackColor = MaterialTheme.colorScheme.primary.disabled(),
        //End Active track
        //Inactive track
        inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        disabledInactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer.disabled(),
        //End Inactive track
        //Active tick
        activeTickColor = MaterialTheme.colorScheme.onPrimary,
        disabledActiveTickColor = MaterialTheme.colorScheme.onPrimary.disabled(),
        //End Active tick
        //Inactive tick
        inactiveTickColor = MaterialTheme.colorScheme.onSecondaryContainer,
        disabledInactiveTickColor = MaterialTheme.colorScheme.onSecondaryContainer.disabled()
        //End Inactive tick
    ),
    interactionSource,
)


@ComposablePreview
@Composable
private fun PreviewE() = AppTheme {
    ISlider(
        0.5f,
        {},
        steps = 9
    )
}

@ComposablePreview
@Composable
private fun PreviewD() = AppTheme {
    ISlider(
        0.5f,
        {},
        enabled = false,
        steps = 9
    )
}
