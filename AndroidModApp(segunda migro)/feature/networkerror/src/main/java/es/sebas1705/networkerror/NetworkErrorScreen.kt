package es.sebas1705.networkerror


import androidx.compose.runtime.Composable
import es.sebas1705.common.states.WindowState
import es.sebas1705.networkerror.design.NetworkErrorDesign

/**
 * Screen to show when there is a network error.
 *
 * @param windowState [WindowState]: the state of the window
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun NetworkErrorScreen(
    windowState: WindowState
) {
    //Body:
    NetworkErrorDesign(windowState)
}

