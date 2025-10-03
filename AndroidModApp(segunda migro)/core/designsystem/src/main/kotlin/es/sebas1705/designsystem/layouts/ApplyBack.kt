package es.sebas1705.designsystem.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.AppTheme

/**
 * Apply back
 *
 * @param backId [Int]: Back id
 * @param modifier [Modifier]: Modifier
 * @param paddingValues [PaddingValues]: Padding values
 * @param applyCondition [Boolean]: Apply condition
 * @param content [BoxScope.() -> Unit]: Content
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun ApplyBack(
    backId: Int,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    applyCondition: Boolean = true,
    content: @Composable BoxScope.() -> Unit
) = Box(
    modifier = modifier
        .fillMaxSize()
        .padding(paddingValues)
) {
    if (applyCondition) Image(
        painter = painterResource(id = backId),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
    content()
}

@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    ApplyBack(
        backId = es.sebas1705.core.resources.R.drawable.back_landscape_fill,
        modifier = Modifier
            .height(500.dp)
            .width(200.dp)
    ) {

    }
}
