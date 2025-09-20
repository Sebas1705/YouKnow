package es.sebas1705.networkerror.design



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.lottie.IRawLottieAnimation
import es.sebas1705.designsystem.spacers.IVerSpacer
import es.sebas1705.designsystem.texts.Subtitle
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.feature.networkerror.R

/**
 * Design of the Network Error Screen.
 *
 * @param windowState [WindowState]: the state of the window
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun NetworkErrorDesign(
    windowState: WindowState = WindowState.default()
) {
    //Body:
    ApplyBack(
        backId = windowState.backFill,
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IRawLottieAnimation(
                rawRes = R.raw.lottie_connection,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.5f)
            )
            IVerSpacer(height = SmallPadding)
            Subtitle(
                text = stringResource(id = R.string.feature_networkerror_connection_lost)
            )
        }
    }
}

@UiModePreviews
@Composable
private fun NetworkErrorPreview() {
    AppTheme {
        NetworkErrorDesign()
    }
}

