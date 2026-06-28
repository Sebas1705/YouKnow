package es.sebas1705.home.chat


import android.media.SoundPool
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.home.chat.design.ChatDesign
import es.sebas1705.home.chat.viewmodel.ChatIntent
import es.sebas1705.home.chat.viewmodel.ChatState
import es.sebas1705.home.chat.viewmodel.ChatViewModel
import es.sebas1705.feature.home.R
import es.sebas1705.home.navigation.viewmodel.HomeState

/**
 * Social Screen that will show the chat and group options.
 * It will show the chat by default and the group options if the user wants to.
 * The user can send messages in the chat and join groups.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param homeState [HomeState]: The state of the home.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun ChatScreen(
    windowState: WindowState,
    homeState: HomeState,
    soundPool: Pair<SoundPool, Float>
) {
    //Locals:
    val ctx = LocalContext.current
    BackHandler {}

    //ViewModel:
    val chatViewModel: ChatViewModel = hiltViewModel()

    //State:
    val chatState: ChatState by chatViewModel.uiState.collectAsStateWithLifecycle()

    //Effects:
    LaunchedEffect(Unit) {
        chatViewModel.eventHandler(ChatIntent.LoadChat)
    }

    //Body:
    ChatDesign(
        windowState,
        chatState,
        homeState,
        soundPool,
        messageSender = { message ->
            homeState.userModel?.let {
                chatViewModel.eventHandler(ChatIntent.SendMessage(message, it))
            } ?: ctx.printTextInToast(ctx.getString(R.string.feature_home_user_not_logged))
        }
    )
}







