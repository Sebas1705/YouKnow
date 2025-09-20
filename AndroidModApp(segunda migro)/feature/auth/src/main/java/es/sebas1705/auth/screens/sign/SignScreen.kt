package es.sebas1705.auth.screens.sign


import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.states.WindowState
import es.sebas1705.auth.screens.sign.design.SignDesign
import es.sebas1705.auth.screens.sign.viewmodel.SignIntent
import es.sebas1705.auth.screens.sign.viewmodel.SignState
import es.sebas1705.auth.screens.sign.viewmodel.SignViewModel

/**
 * Sign Screen that will allow the user to sign up or log in.
 * It will show a form with email and password fields.
 * If the user is not registered, it will allow the user to sign up.
 *
 * @param windowState [WindowState]: State of the window.
 * @param toLogNav () -> Unit: Function to navigate to the Log Screen.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun SignScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    toLogNav: () -> Unit,
) {
    //ViewModel:
    val signViewModel: SignViewModel = hiltViewModel()

    //State:
    val signState: SignState by signViewModel.uiState.collectAsStateWithLifecycle()

    //Body:
    SignDesign(
        windowState,
        signState,
        soundPool,
        onSignButtonAction = { email, pass, nick, onError ->
            signViewModel.eventHandler(
                SignIntent.SignUpWithEmail(
                    email = email,
                    password = pass,
                    nickname = nick,
                    onSuccess = toLogNav,
                    onError = onError
                )
            )
        }
    )
}

