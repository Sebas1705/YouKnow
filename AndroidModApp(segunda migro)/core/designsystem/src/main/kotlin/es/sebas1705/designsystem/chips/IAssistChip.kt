package es.sebas1705.designsystem.chips


import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.ui.theme.AppTheme

/**
 * Personalized assist chip
 *
 * @param onClick [() -> Unit]: On click
 * @param label [String]: Label
 * @param modifier [Modifier]: Modifier
 * @param enabled [Boolean]: Enabled
 * @param leadingIcon [ImageVector?]: Leading icon
 * @param trailingIcon [ImageVector?]: Trailing icon
 * @param interactionSource [MutableInteractionSource?]: Interaction source
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
 */
@Composable
fun IAssistChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    interactionSource: MutableInteractionSource? = null
) = AssistChip(
    onClick = onClick,
    label = { Text(label) },
    modifier = modifier,
    enabled = enabled,
    leadingIcon = { leadingIcon?.let { Icon(it, contentDescription = null) } },
    trailingIcon = { trailingIcon?.let { Icon(it, contentDescription = null) } },
    colors = AssistChipDefaults.assistChipColors(
        containerColor = MaterialTheme.colorScheme.surface,
        labelColor = MaterialTheme.colorScheme.onSurface,
        leadingIconContentColor = MaterialTheme.colorScheme.primary,
        trailingIconContentColor = MaterialTheme.colorScheme.primary,
        disabledContainerColor = MaterialTheme.colorScheme.surface.disabled(),
        disabledLabelColor = MaterialTheme.colorScheme.onSurface.disabled(),
        disabledLeadingIconContentColor = MaterialTheme.colorScheme.primary.disabled(),
        disabledTrailingIconContentColor = MaterialTheme.colorScheme.primary.disabled(),
    ),
    interactionSource = interactionSource
)

@ComposablePreview
@Composable
private fun PreviewE() = AppTheme {
    IAssistChip(
        onClick = { },
        label = "Assist Chip",
        enabled = true,
        leadingIcon = Icons.Filled.Add
    )
}

@ComposablePreview
@Composable
private fun PreviewD() = AppTheme {
    IAssistChip(
        onClick = { },
        label = "Assist Chip",
        enabled = false,
        trailingIcon = Icons.Filled.Add
    )
}

