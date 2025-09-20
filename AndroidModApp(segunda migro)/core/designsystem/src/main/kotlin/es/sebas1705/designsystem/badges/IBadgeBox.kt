package es.sebas1705.designsystem.badges


import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BadgedBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.designsystem.buttons.icon.IOutlinedIconButton

/**
 * Personalized badge box
 *
 * @param text [String]: Text to show
 * @param content [BoxScope.() -> Unit]: Content
 * @param modifier [Modifier]: Modifier
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun IBadgeBox(
    text: String,
    content: @Composable (BoxScope.() -> Unit),
    modifier: Modifier = Modifier,
) = BadgedBox(
    badge = { IBadge(text) },
    modifier = modifier,
    content = content
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IBadgeBox(
        text = "1",
        content = {
            IOutlinedIconButton(
                onClick = {},
                contentDescription = "Add",
                imageVector = Icons.Default.Add
            )
        }
    )
}

