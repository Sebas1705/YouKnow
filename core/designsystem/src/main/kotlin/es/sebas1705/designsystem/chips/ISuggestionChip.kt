package es.sebas1705.designsystem.chips


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.ui.theme.OutlineThickness
import es.sebas1705.ui.theme.AppTheme

/**
 * Personalized suggestion chip
 *
 * @param onClick [() -> Unit]: On click
 * @param label [String]: Label
 * @param modifier [Modifier]: Modifier
 * @param enabled [Boolean]: Enabled
 * @param icon [ImageVector?]: Icon
 * @param interactionSource [MutableInteractionSource?]: Interaction source
 *
 * @since 1.0.0
 * @author Sebas1705 21/09/2025
 */
@Composable
fun ISuggestionChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    interactionSource: MutableInteractionSource? = null
) = SuggestionChip(
    onClick = onClick,
    label = { Text(label) },
    modifier = modifier,
    enabled = enabled,
    icon = { icon?.let { Icon(it, contentDescription = null) } },
    colors = SuggestionChipDefaults.suggestionChipColors(
        containerColor = MaterialTheme.colorScheme.surface,
        labelColor = MaterialTheme.colorScheme.onSurface,
        iconContentColor = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor = MaterialTheme.colorScheme.surface.disabled(),
        disabledLabelColor = MaterialTheme.colorScheme.onSurface.disabled(),
        disabledIconContentColor = MaterialTheme.colorScheme.onSurface.disabled()
    ),
    border = BorderStroke(
        OutlineThickness,
        MaterialTheme.colorScheme.onSurface
    ),
    interactionSource = interactionSource
)

@ComposablePreview
@Composable
private fun PreviewE() = AppTheme {
    ISuggestionChip(
        onClick = { },
        label = "Suggestion Chip",
        enabled = true,
        icon = Icons.Filled.Add
    )
}

@ComposablePreview
@Composable
private fun PreviewD() = AppTheme {
    ISuggestionChip(
        onClick = { },
        label = "Suggestion Chip",
        enabled = true,
        icon = Icons.Filled.Add
    )
}

