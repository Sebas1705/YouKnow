package es.sebas1705.designsystem.cards


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.AppTheme

/**
 * Gives any [Modifier] chain the app's "paper sticker" look: a border, a flat container fill and
 * an offset drop shadow drawn as a second copy of the shape behind it, so it reads as a cut-out
 * sticker rather than a Material elevation shadow.
 *
 * @param shape [Shape]: Outline the sticker is cut to.
 * @param container [Color]: Fill colour.
 * @param border [Color]: Stroke colour.
 * @param shadow [Color]: Colour of the offset shadow copy, defaults to [border].
 * @param shadowOffset [Dp]: How far down-right the shadow sits.
 * @param borderWidth [Dp]: Stroke width.
 *
 * @since 1.3.0
 * @author Sebas1705 30/09/2026
 */
fun Modifier.sticker(
    shape: Shape,
    container: Color,
    border: Color,
    shadow: Color = border,
    shadowOffset: Dp = 4.dp,
    borderWidth: Dp = 2.dp,
): Modifier = this
    .drawBehind {
        val outline = shape.createOutline(size, layoutDirection, this)
        val offset = shadowOffset.toPx()
        translate(offset, offset) { drawOutline(outline, shadow) }
    }
    .clip(shape)
    .background(container, shape)
    .border(borderWidth, border, shape)

/**
 * A sticker-styled container: see [Modifier.sticker]. This is the base building block behind the
 * game screens' cards and reused wherever the rest of the app wants the same hand-drawn feel
 * (Home, results, setup forms, …).
 *
 * @param modifier [Modifier]: Modifier.
 * @param container [Color]: Fill colour, defaults to the lowest surface container.
 * @param border [Color]: Stroke colour, defaults to the primary colour.
 * @param shadow [Color]: Shadow colour, defaults to [border].
 * @param shadowOffset [Dp]: Shadow offset.
 * @param shape [Shape]: Outline shape, defaults to the theme's large shape.
 * @param content [BoxScope.() -> Unit]: Content.
 *
 * @since 1.3.0
 * @author Sebas1705 30/09/2026
 */
@Composable
fun IStickerCard(
    modifier: Modifier = Modifier,
    container: Color = MaterialTheme.colorScheme.surfaceContainerLowest,
    border: Color = MaterialTheme.colorScheme.primary,
    shadow: Color = border,
    shadowOffset: Dp = 4.dp,
    shape: Shape = MaterialTheme.shapes.large,
    content: @Composable BoxScope.() -> Unit,
) = Box(
    modifier = modifier.sticker(shape, container, border, shadow, shadowOffset),
    content = content
)

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IStickerCard(
        modifier = Modifier
            .width(200.dp)
            .height(120.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text("Hello, World!")
    }
}
