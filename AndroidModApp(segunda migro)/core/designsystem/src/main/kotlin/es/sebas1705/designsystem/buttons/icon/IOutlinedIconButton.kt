package es.sebas1705.designsystem.buttons.icon


import android.content.Context
import android.media.SoundPool
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.designsystem.ComposableConstants.ICON_BUTTON_SOUND
import es.sebas1705.designsystem.ComposableConstants.LOOP_N
import es.sebas1705.designsystem.ComposableConstants.PRIORITY_SOUND
import es.sebas1705.designsystem.ComposableConstants.RATE
import es.sebas1705.ui.theme.OutlineThickness
import es.sebas1705.ui.theme.AppTheme

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
 * @param soundPool [Pair]<[SoundPool], [Float]>: Sound pool
 * @param soundRes [Int]: Sound resource
 * @param context [Context]: Context
 * @param soundId [Int]: Sound id
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
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
    soundPool: Pair<SoundPool, Float>? = null,
    soundRes: Int = ICON_BUTTON_SOUND,
    context: Context = LocalContext.current,
    soundId: Int? = remember { soundPool?.first?.load(context, soundRes, PRIORITY_SOUND) }
) = OutlinedIconButton(
    {
        soundPool?.first?.play(
            soundId ?: 0,
            soundPool.second,
            soundPool.second,
            PRIORITY_SOUND,
            LOOP_N,
            RATE
        )
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
