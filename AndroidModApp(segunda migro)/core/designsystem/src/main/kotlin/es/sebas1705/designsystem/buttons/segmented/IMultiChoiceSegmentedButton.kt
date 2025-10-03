package es.sebas1705.designsystem.buttons.segmented

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
import es.sebas1705.core.resources.Sounds
import es.sebas1705.domain.providers.SoundPoolProvider
import es.sebas1705.domain.providers.play
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.Paddings.SmallestPadding

/**
 * Personalized multi choice segmented button
 *
 * @param elements [List<Triple<String, ImageVector?, Boolean>>]: Elements
 * @param selectedElements [SnapshotStateList<Int>]: Selected elements
 * @param modifier [Modifier]: Modifier
 *
 * @since 1.0.0
 * @author Sebas1705 21/09/2025
 */
@Composable
fun IMultiChoiceSegmentedButton(
    elements: List<Triple<String, ImageVector?, Boolean>>,
    selectedElements: SnapshotStateList<Int>,
    modifier: Modifier = Modifier,
) {
    //Local:
    val context = LocalContext.current
    val soundPool = SoundPoolProvider.getSoundPool(context)
    MultiChoiceSegmentedButtonRow(modifier) {
        elements.forEachIndexed { index, element ->
            val checked = index in selectedElements
            SegmentedButton(
                checked = checked,
                onCheckedChange = {
                    soundPool.play(Sounds.NAV_BUTTON, soundPool.second)
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
                    if (checked)
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = element.first
                        )
                    else if (element.second != null)
                        Icon(
                            imageVector = element.second!!,
                            contentDescription = element.first
                        )
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
