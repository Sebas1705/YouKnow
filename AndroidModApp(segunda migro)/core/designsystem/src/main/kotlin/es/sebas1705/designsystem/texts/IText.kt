package es.sebas1705.designsystem.texts


import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.AppTheme

/**
 * IText
 *
 * @param text [String]: Text
 * @param modifier [Modifier]: Modifier
 * @param style [TextStyle]: Style
 * @param textAlign [TextAlign]: Text align
 * @param color [Color]: Color
 * @param overflow [TextOverflow]: Overflow
 * @param minLines [Int]: Min lines
 * @param maxLines [Int]: Max lines
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun IText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = LocalContentColor.current,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE
) = Text(
    modifier = modifier,
    text = text,
    textAlign = textAlign,
    style = style,
    color = color,
    overflow = overflow,
    maxLines = maxLines,
    minLines = minLines
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IText("Hello World")
}