package es.sebas1705.designsystem.states


import android.content.res.Configuration
import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.theme.SizeType
import es.sebas1705.common.utlis.extensions.primitives.toDp

/**
 * Remember the state of the window
 *
 * @return MutableState<WindowState>
 *
 * @see WindowState
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun rememberWindowState(): MutableState<WindowState> {

    val windowState = remember { mutableStateOf(WindowState.default()) }

    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val height = view.height.toDp(view.context)
            val width = view.width.toDp(view.context)
            val portrait =
                view.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
            windowState.value = WindowState(
                widthDp = width,
                heightDp = height,
                widthType = SizeType.getWidth(width),
                heightType = SizeType.getHeight(height),
                isImeVisible = ViewCompat.getRootWindowInsets(view)
                    ?.isVisible(WindowInsetsCompat.Type.ime()) != false,
                isPortrait = portrait,
                backFill = if (portrait)
                    es.sebas1705.core.resources.R.drawable.back_portrait_fill
                else es.sebas1705.core.resources.R.drawable.back_landscape_fill,
                backEmpty = if (portrait)
                    es.sebas1705.core.resources.R.drawable.back_portrait_empty
                else es.sebas1705.core.resources.R.drawable.back_landscape_empty
            )
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
    return windowState
}