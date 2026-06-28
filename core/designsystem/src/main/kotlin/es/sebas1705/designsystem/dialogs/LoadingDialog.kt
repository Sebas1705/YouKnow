package es.sebas1705.designsystem.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import es.sebas1705.core.designsystem.R
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.designsystem.lottie.IRawLottieAnimation

/**
 * Loading dialog
 *
 * @param windowState [WindowState]: Window state
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun LoadingDialog(
    windowState: WindowState = WindowState.default()
) = Popup(
    alignment = Alignment.Center,
) {
    IRawLottieAnimation(
        R.raw.lottie_load_2,
        modifier = Modifier
            .size(
                windowState.sizeFilter(
                    150.dp,
                    200.dp,
                    250.dp
                )
            )
    )
}

@UiModePreviews
@Composable
private fun Preview() = AppTheme {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center,
        ) {
            LoadingDialog()
        }
    }
}
