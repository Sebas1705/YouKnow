package es.sebas1705.home.chat.composables


import android.media.SoundPool
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.models.social.MessageModel
import es.sebas1705.ui.theme.Paddings.SmallestPadding
import es.sebas1705.ui.theme.AppTheme

/**
 * Chat composable that will show the messages of the chat.
 * The user can send messages in the chat.
 * The messages will be shown in a list.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param firebaseId String: The firebase id of the user.
 * @param messageModels List<MessageModel>: The list of messages.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun Chat(
    windowState: WindowState = WindowState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    firebaseId: String = "",
    messageModels: List<MessageModel> = (1..10).map {
        MessageModel(
            text = "Message $it",
            time = System.currentTimeMillis(),
            authorId = it.toString(),
            authorName = "Author $it",
        )
    }
) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(messageModels.size) {
        lazyListState.scrollToItem(
            if (messageModels.isEmpty()) 0
            else messageModels.size - 1
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = SmallestPadding),
            state = lazyListState,
        ) {
            messageModels.sortedBy { it.time }.forEach {
                item {
                    MessageCard(
                        messageModel = it,
                        isCurrentUser = it.authorId == firebaseId
                    )
                }
            }
        }
    }
}

@UiModePreviews
@Composable
private fun ChatPreview() {
    AppTheme {
        Chat()
    }
}