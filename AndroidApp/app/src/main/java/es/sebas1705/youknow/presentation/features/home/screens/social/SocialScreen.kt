package es.sebas1705.youknow.presentation.features.home.screens.social
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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.printTextInToast
import es.sebas1705.youknow.presentation.composables.ApplyBack
import es.sebas1705.youknow.presentation.features.home.screens.social.composables.Chat
import es.sebas1705.youknow.presentation.features.home.screens.social.composables.Group
import es.sebas1705.youknow.presentation.features.home.screens.social.composables.GroupsList
import es.sebas1705.youknow.presentation.features.home.screens.social.composables.IconsBar
import es.sebas1705.youknow.presentation.features.home.viewmodels.HomeState
import es.sebas1705.youknow.presentation.features.home.viewmodels.SocialIntent
import es.sebas1705.youknow.presentation.features.home.viewmodels.SocialState
import es.sebas1705.youknow.presentation.features.home.viewmodels.SocialViewModel
import es.sebas1705.youknow.presentation.features.home.viewmodels.UserViewModel
import es.sebas1705.youknow.presentation.ui.classes.WindowState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Social Screen that will show the chat and group options.
 * It will show the chat by default and the group options if the user wants to.
 * The user can send messages in the chat and join groups.
 *
 * @see SocialViewModel
 * @see SocialState
 * @see SocialDesign
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun SocialScreen(
    userViewModel: UserViewModel,
    windowState: WindowState,
    homeState: HomeState
) {

    //Locals:
    var context = LocalContext.current

    val socialViewModel: SocialViewModel = hiltViewModel()
    val socialState by socialViewModel.uiState.collectAsStateWithLifecycle()
    socialViewModel.eventHandler(SocialIntent.LoadGlobalChat)
    homeState.userModel?.let {
        socialViewModel.eventHandler(SocialIntent.LoadGroups(it.firebaseId))
    } ?: context.printTextInToast("User not found, restart the app")

    SocialDesign(
        socialViewModel = socialViewModel,
        homeState = homeState,
        socialState = socialState,
        messageSender = { message ->
            homeState.userModel?.let{
                socialViewModel.eventHandler(SocialIntent.SendMessage(message,it))
            }?: context.printTextInToast("User not found, restart the app")
        },
        groupCreator = { name, description ->
            homeState.userModel?.let{
                socialViewModel.eventHandler(SocialIntent.CreateGroup(name, description, it.firebaseId))
            }?: context.printTextInToast("User not found, restart the app")
        }
    )
}

/**
 * Design of the Social Screen that will show the chat and group options.
 * It will show the chat by default and the group options if the user wants to.
 * The user can send messages in the chat and join groups.
 *
 * @param socialViewModel [SocialViewModel]: ViewModel to handle the logic of the screen.
 * @param socialState [SocialState]: State of the screen.
 *
 * @see SocialViewModel
 * @see SocialState
 * @see Chat
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun SocialDesign(
    socialViewModel: SocialViewModel? = null,
    homeState: HomeState = HomeState.default(),
    socialState: SocialState? = SocialState.default(),
    messageSender: (String) -> Unit = {},
    groupCreator: (String, String) -> Unit = { _, _ -> }
) {

    //States:
    var chatPage by remember { mutableStateOf(false) }


    ApplyBack(
        R.drawable.back_portrait_empty,
    ) {
        Column(
            modifier = Modifier.padding(top = MediumPadding),
        ) {
            IconsBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.8f),
                isGroup = !chatPage,
                changeToGroup = { chatPage = false },
                changeToChat = { chatPage = true },
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(20f)
                    .padding(horizontal = SmallPadding)
                    .padding(bottom = MediumPadding),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.primaryContainer,
                border = BorderStroke(
                    width = 4.dp,
                    color = MaterialTheme.colorScheme.primary,
                ),
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(SmallPadding),
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                ) {
                    when {
                        chatPage -> Chat(socialState?.chatGlobal!!, messageSender)
                        socialState?.myGroup != null -> Group(
                            socialState.myGroup,
                            socialState.myGroup.leaderUID == homeState.userModel!!.firebaseId
                        )
                        else -> GroupsList(socialState?.groups!!, {}, groupCreator)
                    }
                }
            }
        }
    }
}



/**
 * Preview of the Social Screen.
 *
 * @see SocialDesign
 */
@UiModePreviews
@Composable
private fun SocialPreview() {
    YouKnowTheme {
        SocialDesign()
    }
}





