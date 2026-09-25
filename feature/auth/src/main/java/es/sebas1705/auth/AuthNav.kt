package es.sebas1705.auth


import android.media.SoundPool
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
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
    // Back stack:
    val authBackStack = rememberNavBackStack(MenuScreen)

    // Body:
    NavDisplay(
        modifier = Modifier.fillMaxSize(),
        backStack = authBackStack,
        entryDecorators = listOf(rememberSaveableStateHolderNavEntryDecorator()),
        entryProvider = entryProvider {
            entry<MenuScreen> {
                MenuScreen(
                    windowState,
                    soundPool,
                    toSignNav = {
                        authBackStack.add(SignScreen)
                    },
                    toHomeNav,
                    toLogNav = {
                        authBackStack.add(LogScreen)
                    }
                )
            }
            entry<LogScreen> {
                LogScreen(
                    windowState,
                    soundPool,
                    toHomeNav,
                    toSignNav = {
                        authBackStack.add(SignScreen)
                    }
                )
            }
            entry<SignScreen> {
                SignScreen(
                    windowState,
                    soundPool,
                    toLogNav = {
                        authBackStack.add(LogScreen)
                    }
                )
            }
        }
    )
}
