package es.sebas1705.splash


import androidx.compose.runtime.Composable
import es.sebas1705.common.states.WindowState
import es.sebas1705.youknow.presentation.features.splash.design.SplashDesign

/**
 * Splash Screen of the application
 *
 * @param windowState [WindowState]: the state of the window
 *
 * @author Sebastian Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun SplashScreen(
    windowState: WindowState
) {
    SplashDesign(windowState)
}