package es.sebas1705.designsystem.buttons.segmented

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.core.resources.Sounds
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.domain.providers.SoundPoolProvider
import es.sebas1705.domain.providers.play
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.Paddings.SmallestPadding

/**
 * Personalized single choice segmented button
 *
 * @param elements [List<Triple<String, ImageVector?, Boolean>]: Elements
 * @param selectedElement [MutableState<Int>]: Selected element
 * @param modifier [Modifier]: Modifier
 *
 * @since 1.0.0
 * @author Sebas1705 21/09/2025
 */
@Composable
fun ISingleChoiceSegmentedButton(
    elements: List<Triple<String, ImageVector?, Boolean>>,
    selectedElement: MutableState<Int>,
    modifier: Modifier = Modifier,
) {
    //Local:
    val context = LocalContext.current
    val soundPool = SoundPoolProvider.getSoundPool(context)
    SingleChoiceSegmentedButtonRow(modifier) {
        elements.forEachIndexed { index, element ->
            val selected = index == selectedElement.value
            SegmentedButton(
                selected = selected,
                onClick = {
                    soundPool.play(Sounds.NAV_BUTTON, soundPool.second)
                    selectedElement.value = index
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
                    if (selected)
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
                    IText(
                        element.first,
                        modifier = Modifier.padding(start = SmallestPadding),
                        maxLines = 1
                    )
                }
            )
        }
    }
}

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    val selectedElement = remember { mutableIntStateOf(0) }
    ISingleChoiceSegmentedButton(
        elements = listOf(
            Triple("Add", Icons.Default.Add, true),
            Triple("Check", Icons.Default.Check, false),
        ),
        selectedElement = selectedElement
    )
}
