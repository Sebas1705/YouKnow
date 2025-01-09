package es.sebas1705.youknow.presentation.features.home.features.chat
/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.presentation.features.home.features.chat.design.ChatDesign
import es.sebas1705.youknow.presentation.features.home.features.chat.viewmodel.ChatIntent
import es.sebas1705.youknow.presentation.features.home.features.chat.viewmodel.ChatState
import es.sebas1705.youknow.presentation.features.home.features.chat.viewmodel.ChatViewModel
import es.sebas1705.youknow.presentation.features.home.navigation.viewmodel.HomeState
import es.sebas1705.youknow.R

/**
 * Social Screen that will show the chat and group options.
 * It will show the chat by default and the group options if the user wants to.
 * The user can send messages in the chat and join groups.
 *
 * @see ChatViewModel
 * @see ChatState
 * @see ChatDesign
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun ChatScreen(
    windowState: WindowState,
    homeState: HomeState
) {
    //Locals:
    val ctx = LocalContext.current
    BackHandler {}

    //ViewModel:
    val chatViewModel: ChatViewModel = hiltViewModel()

    //State:
    val chatState: ChatState by chatViewModel.uiState.collectAsStateWithLifecycle()

    chatViewModel.eventHandler(ChatIntent.LoadChat)

    ChatDesign(
        windowState,
        chatState,
        homeState,
        messageSender = { message ->
            homeState.userModel?.let {
                chatViewModel.eventHandler(ChatIntent.SendMessage(message, it))
            } ?: ctx.printTextInToast(ctx.getString(R.string.user_not_logged))
        }
    )
}







