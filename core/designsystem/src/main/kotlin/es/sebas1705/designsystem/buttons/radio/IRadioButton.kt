package es.sebas1705.designsystem.buttons.radio

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.core.resources.Sounds
import es.sebas1705.domain.providers.SoundPoolProvider
import es.sebas1705.domain.providers.play
import es.sebas1705.ui.theme.AppTheme

/**
 * Personalized radio button
 *
 * @param selected [Boolean]: Selected state
 * @param modifier [Modifier]: Modifier
 * @param enabled [Boolean]: Enabled
 * @param interactionSource [MutableInteractionSource]: Interaction source
 * @param onClick [() -> Unit]: Click action
 *
 * @since 1.0.0
 * @author Sebas1705 21/09/2025
 */
@Composable
fun IRadioButton(
    selected: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    onClick: (() -> Unit)? = null,
) {
    //Local:
    val context = LocalContext.current
    val soundPool = SoundPoolProvider.getSoundPool(context)
    RadioButton(
        selected = selected,
        onClick = {
            soundPool.play(Sounds.RADIO_BUTTON, soundPool.second)
            onClick?.invoke()
        },
        modifier = modifier,
        enabled = enabled,
        colors = RadioButtonDefaults.colors(
            selectedColor = MaterialTheme.colorScheme.primary,
            unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledSelectedColor = MaterialTheme.colorScheme.primary.disabled(),
            disabledUnselectedColor = MaterialTheme.colorScheme.onSurfaceVariant.disabled()
        ),
        interactionSource = interactionSource
    )
}

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IRadioButton(
        selected = true,
        modifier = Modifier
            .width(24.dp)
            .height(24.dp)
    )
}
