package es.sebas1705.designsystem.checkboxes


import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
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
 * Personalized checkbox
 *
 * @param checked [Boolean]: Checked
 * @param onCheckedChange [(Boolean) -> Unit]: On checked change
 * @param modifier [Modifier]: Modifier
 * @param enabled [Boolean]: Enabled
 * @param interactionSource [MutableInteractionSource]: Interaction source
 *
 * @since 1.0.0
 * @author Sebas1705 21/09/2025
 */
@Composable
fun ICheckBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
) {
    //Local:
    val context = LocalContext.current
    val soundPool = SoundPoolProvider.getSoundPool(context)
    Checkbox(
        checked = checked,
        onCheckedChange = {
            soundPool.play(Sounds.RADIO_BUTTON, soundPool.second)
            onCheckedChange(it)
        },
        modifier = modifier,
        enabled = enabled,
        colors = CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.primary,
            uncheckedColor = MaterialTheme.colorScheme.onSurface,
            checkmarkColor = MaterialTheme.colorScheme.onPrimary,
            disabledCheckedColor = MaterialTheme.colorScheme.primary.disabled(),
            disabledUncheckedColor = MaterialTheme.colorScheme.onSurface.disabled(),
            disabledIndeterminateColor = MaterialTheme.colorScheme.primary.disabled()
        ),
        interactionSource = interactionSource
    )
}

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    ICheckBox(
        checked = false,
        onCheckedChange = {},
        modifier = Modifier
            .width(24.dp)
            .height(24.dp)
    )
}
