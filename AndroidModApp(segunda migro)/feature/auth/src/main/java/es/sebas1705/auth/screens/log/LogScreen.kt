package es.sebas1705.auth.screens.log


import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.auth.screens.log.design.LogDesign
import es.sebas1705.auth.screens.log.viewmodel.LogIntent
import es.sebas1705.auth.screens.log.viewmodel.LogViewModel
import es.sebas1705.common.states.WindowState

/**
 * Main composable for the Log screen. It contains the [LogDesign] composable, which is the main design of the screen.
 *
 * @param windowState [WindowState]: State of the window.
 * @param toHomeNav () -> Unit: Function to navigate to the home screen.
 * @param toSignNav () -> Unit: Function to navigate to the sign screen.
 *
 * @author: Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun LogScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    toHomeNav: () -> Unit,
    toSignNav: () -> Unit
) {

    //ViewModel:
    val logViewModel: LogViewModel = hiltViewModel()

    //State:
    val logState by logViewModel.uiState.collectAsStateWithLifecycle()

    //Body:
    LogDesign(
        windowState,
        logState,
        soundPool,
        onRegisterButton = toSignNav,
        onPasswordForgot = {
            logViewModel.eventHandler(LogIntent.SendForgotPassword(it))
        },
        onLoginButton = { email, password, onError ->
            logViewModel.eventHandler(
                LogIntent.SignInWithEmail(
                    email = email,
                    password = password,
                    onSuccess = toHomeNav,
                    onError = onError
                )
            )
        }
    )
}







