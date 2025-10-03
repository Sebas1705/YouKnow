package es.sebas1705.designsystem.chips


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.ui.theme.OutlineThickness
import es.sebas1705.ui.theme.AppTheme

/**
 * Personalized filter chip
 *
 * @param onClick [() -> Unit]: On click
 * @param selected [Boolean]: Selected
 * @param label [String]: Label
 * @param modifier [Modifier]: Modifier
 * @param enabled [Boolean]: Enabled
 * @param leadingIcon [ImageVector?]: Leading icon
 * @param trailingIcon [ImageVector?]: Trailing icon
 * @param interactionSource [MutableInteractionSource?]: Interaction source
 *
 * @since 1.0.0
 * @author Sebas1705 21/09/2025
 */
@Composable
fun IFilterChip(
    onClick: () -> Unit,
    selected: Boolean,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    interactionSource: MutableInteractionSource? = null
) = FilterChip(
    onClick = onClick,
    selected = selected,
    label = { Text(label) },
    modifier = modifier,
    enabled = enabled,
    leadingIcon = { leadingIcon?.let { Icon(it, contentDescription = null) } },
    trailingIcon = { trailingIcon?.let { Icon(it, contentDescription = null) } },
    colors = FilterChipDefaults.filterChipColors(
        containerColor = MaterialTheme.colorScheme.surface,
        labelColor = MaterialTheme.colorScheme.onSurface,
        iconColor = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor = MaterialTheme.colorScheme.surface.disabled(),
        disabledLabelColor = MaterialTheme.colorScheme.onSurface.disabled(),
        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurface.disabled(),
        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface.disabled(),
        selectedContainerColor = MaterialTheme.colorScheme.primary,
        disabledSelectedContainerColor = MaterialTheme.colorScheme.primary.disabled(),
        selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
        selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
        selectedTrailingIconColor = MaterialTheme.colorScheme.onPrimary
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
    IFilterChip(
        onClick = { },
        selected = true,
        label = "Filter Chip",
        enabled = true,
        leadingIcon = Icons.Filled.Add
    )
}

@ComposablePreview
@Composable
private fun PreviewD() = AppTheme {
    IFilterChip(
        onClick = { },
        selected = false,
        label = "Filter Chip",
        enabled = true,
        trailingIcon = Icons.Filled.Add
    )
}

