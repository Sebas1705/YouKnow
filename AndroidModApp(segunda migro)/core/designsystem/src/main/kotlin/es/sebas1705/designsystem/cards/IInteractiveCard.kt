package es.sebas1705.designsystem.cards


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.makeBold
import es.sebas1705.designsystem.buttons.icon.IStandardIconButton
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.AppTheme

/**
 * Personalized interactive card
 *
 * @param title [String]: Title
 * @param modifier [Modifier]: Modifier
 * @param subtitle [String]: Subtitle
 * @param buttons [RowScope.() -> Unit]: Buttons
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
 */
@Composable
fun IInteractiveCard(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String = "",
    buttons: @Composable RowScope.() -> Unit,
) = IPrimaryCard(modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SmallPadding),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.makeBold(),
            modifier = Modifier
                .padding(SmallPadding),
        )

        if (subtitle.isNotEmpty()) Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(SmallPadding),
        )

        buttons()
    }
}

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IInteractiveCard(
        title = "Title",
        subtitle = "Subtitle",
        buttons = {
            IStandardIconButton(
                imageVector = Icons.Default.Link,
                contentDescription = "Link",
                onClick = {}
            )
        }
    )
}
