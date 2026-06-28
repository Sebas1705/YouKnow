package es.sebas1705.designsystem.texts


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.common.utlis.extensions.composables.makeItalic
import es.sebas1705.designsystem.cards.IPrimaryCard
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.makeTitle

/**
 * Title surface
 *
 * @param text [String]: Text
 * @param modifier [Modifier]: Modifier
 * @param textAlign [TextAlign]: Text align
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun TitleSurface(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    textStyle: TextStyle = MaterialTheme.typography.displayMedium
) {
    IPrimaryCard(
        modifier = modifier
    ) {
        Title(
            text = text,
            style = textStyle
                .makeTitle()
                .makeItalic(),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(
                vertical = SmallPadding,
                horizontal = MediumPadding
            ),
            textAlign = textAlign
        )
    }
}

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    TitleSurface("Hello World")
}