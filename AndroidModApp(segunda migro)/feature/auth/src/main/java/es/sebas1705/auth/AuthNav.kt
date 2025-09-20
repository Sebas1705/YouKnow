package es.sebas1705.auth


import android.media.SoundPool
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.sebas1705.common.states.WindowState
import es.sebas1705.auth.AuthScreens.LogScreen
import es.sebas1705.auth.AuthScreens.MenuScreen
import es.sebas1705.auth.AuthScreens.SignScreen
import es.sebas1705.auth.screens.log.LogScreen
import es.sebas1705.auth.screens.menu.MenuScreen
import es.sebas1705.auth.screens.sign.SignScreen

/**
 * Navigation for the Auth feature.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of SoundPool and volume.
 * @param toHomeNav [() -> Unit]: Function to navigate to the Home feature.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun AuthNav(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    toHomeNav: () -> Unit,
) {
    // NavController:
    val authNavController = rememberNavController()

    // Body:
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = authNavController,
        startDestination = MenuScreen
    ) {
        composable<MenuScreen> {
            MenuScreen(
                windowState,
                soundPool,
                toSignNav = {
                    authNavController.navigate(SignScreen)
                },
                toHomeNav,
                toLogNav = {
                    authNavController.navigate(LogScreen)
                }
            )
        }
        composable<LogScreen> {
            LogScreen(
                windowState,
                soundPool,
                toHomeNav,
                toSignNav = {
                    authNavController.navigate(SignScreen)
                }
            )
        }
        composable<SignScreen> {
            SignScreen(
                windowState,
                soundPool,
                toLogNav = {
                    authNavController.navigate(LogScreen)
                }
            )
        }
    }
}