package es.sebas1705.designsystem.lottie

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import es.sebas1705.core.designsystem.R
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.AppTheme

/**
 * Raw lottie animation
 *
 * @param rawRes [Int]: Raw res
 * @param modifier [Modifier]: Modifier
 * @param iterations [Int]: Iterations
 * @param contentScale [ContentScale]: Content scale
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun IRawLottieAnimation(
    rawRes: Int,
    modifier: Modifier = Modifier,
    iterations: Int = LottieConstants.IterateForever,
    contentScale: ContentScale = ContentScale.Crop
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(rawRes)
    )
    LottieAnimation(
        composition = composition,
        iterations = iterations,
        contentScale = contentScale,
        modifier = modifier
    )
}


@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IRawLottieAnimation(
        rawRes = R.raw.lottie_load_1,
        modifier = Modifier
            .height(400.dp)
            .width(400.dp),
    )
}
