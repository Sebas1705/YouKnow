package es.sebas1705.auth.screens.menu


import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.auth.screens.menu.design.MenuDesign
import es.sebas1705.auth.screens.menu.viewmodel.MenuIntent
import es.sebas1705.auth.screens.menu.viewmodel.MenuViewModel
import es.sebas1705.common.states.WindowState

/**
 * Menu Screen that will show the user the options to sign in, log in or log in with Google.
 * The user will be able to navigate to the Sign In Screen, Log In Screen or Home Screen.
 * The user will be able to log in with Google.
 *
 * @param windowState [WindowState]: State of the window.
 * @param toSignNav () -> Unit: Function to navigate to the Sign In Screen.
 * @param toHomeNav () -> Unit: Function to navigate to the Home Screen.
 * @param toLogNav () -> Unit: Function to navigate to the Log In Screen.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun MenuScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    toSignNav: () -> Unit,
    toHomeNav: () -> Unit,
    toLogNav: () -> Unit,
) {
    //Local:
    val context = LocalContext.current

    //View Model:
    val menuViewModel: MenuViewModel = hiltViewModel()

    //State:
    val menuState by menuViewModel.uiState.collectAsStateWithLifecycle()

    //Body:
    MenuDesign(
        windowState,
        menuState,
        soundPool,
        onSignButtonAction = toSignNav,
        onEmailLogButtonAction = toLogNav,
        onGoogleLogButtonAction = {
            menuViewModel.eventHandler(MenuIntent.SignWithGoogle(context, toHomeNav))
        }
    )
}