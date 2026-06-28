package es.sebas1705.designsystem.buttons.fab

import android.content.Context
import android.media.SoundPool
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.core.resources.Sounds
import es.sebas1705.domain.providers.SoundPoolProvider
import es.sebas1705.domain.providers.play
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.TonalElevation

/**
 * Personalized small FAB
 *
 * @param onClick [() -> Unit]: Click action
 * @param contentDescription [String]: Content description
 * @param modifier [Modifier]: Modifier
 * @param imageVector [ImageVector]: Image vector
 * @param imageResource [Int]: Image resource
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
fun ISmallFAB(
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    imageResource: Int? = null,
    interactionSource: MutableInteractionSource? = null,
) {
    //Local:
    val context = LocalContext.current
    val soundPool = SoundPoolProvider.getSoundPool(context)
    SmallFloatingActionButton(
        onClick = {
            soundPool.play(Sounds.FAB_BUTTON, soundPool.second)
            onClick()
        },
        modifier,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = TonalElevation.Level5,
            pressedElevation = TonalElevation.Level4,
            focusedElevation = TonalElevation.Level5,
            hoveredElevation = TonalElevation.Level5,
        ),
        interactionSource = interactionSource,
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
private fun Preview() = AppTheme {
    ISmallFAB(
        onClick = {},
        contentDescription = "Add",
        imageVector = Icons.Default.Add
    )
}
