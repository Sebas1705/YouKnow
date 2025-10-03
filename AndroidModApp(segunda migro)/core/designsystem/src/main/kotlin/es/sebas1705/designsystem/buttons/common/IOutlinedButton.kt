package es.sebas1705.designsystem.buttons.common


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.core.resources.Sounds
import es.sebas1705.domain.providers.SoundPoolProvider
import es.sebas1705.domain.providers.play
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.OutlineThickness

/**
 * Personalized outlined button
 *
 * @param onClick [() -> Unit]: Click action
 * @param label [String]: Label
 * @param modifier [Modifier]: Modifier
 * @param imageVector [ImageVector]: Image vector
 * @param imageResource [Int]: Image resource
 * @param enabled [Boolean]: Enabled
 * @param interactionSource [MutableInteractionSource]: Interaction source
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun IOutlinedButton(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    imageResource: Int? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
) {
    //Local:
    val context = LocalContext.current
    val soundPool = SoundPoolProvider.getSoundPool(context)

    OutlinedButton(
        onClick = {
            soundPool.play(Sounds.BUTTON, soundPool.second)
            onClick()
        },
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.surface.disabled(),
            disabledContentColor = MaterialTheme.colorScheme.primary.disabled(),
        ),
        border = BorderStroke(
            OutlineThickness,
            if (enabled) MaterialTheme.colorScheme.outline
            else MaterialTheme.colorScheme.outline.disabled()
        ),
        interactionSource = interactionSource,
        content = {
            val modifierC = Modifier.padding(end = 8.dp)
            if (imageVector != null)
                Icon(imageVector, label, modifierC)
            else if (imageResource != null)
                Icon(painterResource(imageResource), label, modifierC)
            Text(label)
        }
    )
}

@ComposablePreview
@Composable
private fun PreviewE() = AppTheme {
    IOutlinedButton(
        onClick = {},
        label = "Add",
        imageVector = Icons.Default.Add
    )
}

@ComposablePreview
@Composable
private fun PreviewD() = AppTheme {
    IOutlinedButton(
        onClick = {},
        enabled = false,
        label = "Add",
        imageVector = Icons.Default.Add
    )
}


