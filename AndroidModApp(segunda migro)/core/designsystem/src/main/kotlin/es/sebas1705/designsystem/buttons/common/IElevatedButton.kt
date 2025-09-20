package es.sebas1705.designsystem.buttons.common


import android.content.Context
import android.media.SoundPool
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.designsystem.ComposableConstants.BUTTON_SOUND
import es.sebas1705.designsystem.ComposableConstants.LOOP_N
import es.sebas1705.designsystem.ComposableConstants.PRIORITY_SOUND
import es.sebas1705.designsystem.ComposableConstants.RATE
import es.sebas1705.ui.theme.AppTheme

/**
 * Personalized elevated button
 *
 * @param onClick [() -> Unit]: Click action
 * @param label [String]: Label
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
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun IElevatedButton(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    imageResource: Int? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    soundPool: Pair<SoundPool, Float>? = null,
    soundRes: Int = BUTTON_SOUND,
    context: Context = LocalContext.current,
    soundId: Int? = remember { soundPool?.first?.load(context, soundRes, PRIORITY_SOUND) }
) = ElevatedButton(
    onClick = {
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
    modifier = modifier,
    enabled = enabled,
    colors = ButtonDefaults.elevatedButtonColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary,
        disabledContainerColor = MaterialTheme.colorScheme.surface.disabled(),
        disabledContentColor = MaterialTheme.colorScheme.primary.disabled()
    ),
    interactionSource = interactionSource,
    content = {
        val modifierC = Modifier.padding(end = 8.dp)
        if (imageVector != null)
            Icon(imageVector, label, modifierC)
        else if (imageResource != null)
            Icon(painterResource(imageResource), label, modifierC)
        Text(label)
    },
)

@ComposablePreview
@Composable
private fun PreviewE() = AppTheme {
    IElevatedButton(
        onClick = {},
        label = "Add",
    )
}

@ComposablePreview
@Composable
private fun PreviewD() = AppTheme {
    IElevatedButton(
        onClick = {},
        enabled = false,
        label = "Add",
        imageVector = Icons.Filled.Image
    )
}