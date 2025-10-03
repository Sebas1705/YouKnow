package es.sebas1705.designsystem.buttons.common


import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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

/**
 * Personalized filled tonal button
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
fun IFilledTonalButton(
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

    FilledTonalButton(
        onClick = {
            soundPool.play(Sounds.BUTTON, soundPool.second)
            onClick()
        },
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer.disabled(),
            disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer.disabled(),
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
    IFilledTonalButton(
        onClick = {},
        label = "Add",
        imageVector = Icons.Default.Add
    )
}

@ComposablePreview
@Composable
private fun PreviewD() = AppTheme {
    IFilledTonalButton(
        onClick = {},
        enabled = false,
        label = "Add",
        imageVector = Icons.Default.Add
    )
}
