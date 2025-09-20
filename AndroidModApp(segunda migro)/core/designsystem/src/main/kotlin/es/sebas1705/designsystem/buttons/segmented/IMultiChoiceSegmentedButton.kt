package es.sebas1705.designsystem.buttons.segmented


import android.content.Context
import android.media.SoundPool
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.designsystem.ComposableConstants.LOOP_N
import es.sebas1705.designsystem.ComposableConstants.NAV_BUTTON_SOUND
import es.sebas1705.designsystem.ComposableConstants.PRIORITY_SOUND
import es.sebas1705.designsystem.ComposableConstants.RATE
import es.sebas1705.ui.theme.Paddings.SmallestPadding
import es.sebas1705.ui.theme.AppTheme

/**
 * Personalized multi choice segmented button
 *
 * @param elements [List<Triple<String, ImageVector?, Boolean>>]: Elements
 * @param selectedElements [SnapshotStateList<Int>]: Selected elements
 * @param modifier [Modifier]: Modifier
 * @param soundPool [Pair]<[SoundPool], [Float]>: Sound pool
 * @param soundRes [Int]: Sound resource
 * @param context [Context]: Context
 * @param soundId [Int]: Sound id
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
 */
@Composable
fun IMultiChoiceSegmentedButton(
    elements: List<Triple<String, ImageVector?, Boolean>>,
    selectedElements: SnapshotStateList<Int>,
    modifier: Modifier = Modifier,
    soundPool: Pair<SoundPool, Float>? = null,
    soundRes: Int = NAV_BUTTON_SOUND,
    context: Context = LocalContext.current,
    soundId: Int? = remember { soundPool?.first?.load(context, soundRes, PRIORITY_SOUND) }
) = MultiChoiceSegmentedButtonRow(modifier) {
    elements.forEachIndexed { index, element ->
        val checked = index in selectedElements
        SegmentedButton(
            checked = checked,
            onCheckedChange = {
                soundPool?.first?.play(
                    soundId ?: 0,
                    soundPool.second,
                    soundPool.second,
                    PRIORITY_SOUND,
                    LOOP_N,
                    RATE
                )
                if (index in selectedElements) {
                    selectedElements.remove(index)
                } else {
                    selectedElements.add(index)
                }
            },
            shape = SegmentedButtonDefaults.itemShape(index, elements.size),
            enabled = element.third,
            colors = SegmentedButtonDefaults.colors(
                activeContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                activeContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                activeBorderColor = MaterialTheme.colorScheme.outline,
                inactiveContainerColor = Color.Transparent,
                inactiveContentColor = MaterialTheme.colorScheme.onSurface,
                inactiveBorderColor = MaterialTheme.colorScheme.outline,
                disabledActiveContainerColor = Color.Transparent,
                disabledActiveContentColor = MaterialTheme.colorScheme.onSurface.disabled(),
                disabledActiveBorderColor = MaterialTheme.colorScheme.secondaryContainer.disabled(),
                disabledInactiveContainerColor = Color.Transparent,
                disabledInactiveContentColor = MaterialTheme.colorScheme.onSurface.disabled(),
                disabledInactiveBorderColor = MaterialTheme.colorScheme.outline.disabled()
            ),
            icon = {
                if (checked) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = element.first
                    )
                } else if (element.second != null) {
                    Icon(
                        imageVector = element.second!!,
                        contentDescription = element.first
                    )
                }
            },
            label = {
                Text(
                    element.first,
                    modifier = Modifier.padding(start = SmallestPadding)
                )
            }
        )
    }
}

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    val selectedElements = remember { mutableStateListOf(0) }
    IMultiChoiceSegmentedButton(
        elements = listOf(
            Triple("Add", Icons.Default.Add, true),
            Triple("Check", Icons.Default.Check, false),
        ),
        selectedElements = selectedElements
    )
}
