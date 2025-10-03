package es.sebas1705.designsystem.buttons.icon


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.core.resources.Sounds
import es.sebas1705.domain.providers.SoundPoolProvider
import es.sebas1705.domain.providers.play
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.OutlineThickness

/**
 * Personalized outlined icon button
 *
 * @param onClick [() -> Unit]: Click action
 * @param contentDescription [String]: Content description
 * @param modifier [Modifier]: Modifier
 * @param imageVector [ImageVector]: Image vector
 * @param imageResource [Int]: Image resource
 * @param enabled [Boolean]: Enabled
 * @param interactionSource [MutableInteractionSource]: Interaction source
 *
 * @since 1.0.0
 * @author Sebas1705 21/09/2025
 */
@Composable
fun IOutlinedIconButton(
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    imageResource: Int? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    ) {
    //Local:
    val context = LocalContext.current
    val soundPool = SoundPoolProvider.getSoundPool(context)
    OutlinedIconButton(
        onClick = {
            soundPool.play(Sounds.ICON_BUTTON, soundPool.second)
            onClick()
        },
        modifier,
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.surfaceVariant.disabled()
        ),
        enabled = enabled,
        interactionSource = interactionSource,
        border = BorderStroke(
            OutlineThickness,
            if (enabled) MaterialTheme.colorScheme.outline
            else MaterialTheme.colorScheme.outline.disabled()
        ),
        content = {
            if (imageVector != null)
                Icon(imageVector, contentDescription)
            else if (imageResource != null)
                Icon(painterResource(imageResource), contentDescription)
        }
    )
}

@ComposablePreview
@Composable
private fun PreviewE() = AppTheme {
    IOutlinedIconButton(
        onClick = {},
        contentDescription = "Add",
        imageVector = Icons.Default.Add
    )
}

@ComposablePreview
@Composable
private fun PreviewD() = AppTheme {
    IOutlinedIconButton(
        onClick = {},
        contentDescription = "Add",
        imageVector = Icons.Default.Add,
        enabled = false
    )
}
