package es.sebas1705.youknow.presentation.features.home.screens
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
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.IOutlinedButton
import es.sebas1705.youknow.core.composables.buttons.icon.IStandardIconButton
import es.sebas1705.youknow.core.composables.cards.IResumeCard
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.textfields.IOutlinedTextField
import es.sebas1705.youknow.core.composables.texts.Title
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.home.viewmodels.ProfileIntent
import es.sebas1705.youknow.presentation.features.home.viewmodels.ProfileState
import es.sebas1705.youknow.presentation.features.home.viewmodels.ProfileViewModel
import es.sebas1705.youknow.presentation.features.home.viewmodels.UserState
import es.sebas1705.youknow.presentation.features.home.viewmodels.UserViewModel
import es.sebas1705.youknow.presentation.features.home.windows.NickWindow
import es.sebas1705.youknow.presentation.features.home.windows.UrlRequestWindow
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Profile Screen that shows the user's data.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun ProfileScreen(
    windowState: WindowState,
    userState: UserState,
    userViewModel: UserViewModel
) {
    BackHandler {}
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val profileState by profileViewModel.uiState.collectAsStateWithLifecycle()

    ProfileDesign(windowState, userState, profileState, userViewModel, profileViewModel)
}

/**
 * Design of the Profile Screen. It shows the user's data.
 *
 * @param userViewModel [UserViewModel]: ViewModel of the Home Screen.
 * @param userState [State]<[UserState]>: State of the Home Screen.
 *
 * @see UserViewModel
 * @see UserState
 * @see [ProfileScreen]
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProfileDesign(
    windowState: WindowState = WindowState.default(),
    userState: UserState = UserState.default(),
    profileState: ProfileState = ProfileState.default(),
    userViewModel: UserViewModel? = null,
    profileViewModel: ProfileViewModel? = null
) {
    val user = userState.userModel
    var photoWindow by remember { mutableStateOf(false) }
    var nickWindow by remember { mutableStateOf(false) }
    var nickname by remember { mutableStateOf(user?.nickName ?: "") }

    ApplyBack(windowState.backEmpty) {
        if (userState.isLoading || profileState.isLoading)
            LoadingDialog(windowState)
        if (photoWindow) UrlRequestWindow(
            windowState,
            onConfirmButton = {
                photoWindow = false
                profileViewModel?.eventHandler(
                    ProfileIntent.ChangePhoto(
                        user?.firebaseId ?: "",
                        it
                    )
                )
            },
            onDismissAction = { photoWindow = false }
        )
        if (nickWindow) NickWindow(
            nickname = nickname,
            firebaseId = user?.firebaseId ?: "",
            onConfirm = {
                nickWindow = false
                profileViewModel?.eventHandler(
                    ProfileIntent.ChangeNickname(
                        user?.firebaseId ?: "",
                        it
                    )
                )
            },
            onDismiss = { nickWindow = false }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                val imageModifier = Modifier
                    .height(windowState.heightFilter(200.dp, 250.dp, 300.dp))
                    .padding(
                        end = SmallPadding,
                        bottom = MediumPadding,
                        start = SmallPadding,
                        top = SmallestPadding
                    )
                    .background(MaterialTheme.colorScheme.surface)
                    .border(3.dp, MaterialTheme.colorScheme.secondary)
                    .clickable(onClick = { photoWindow = true })
                if (user != null && user.photoUrl != null) AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(user.photoUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.sign_user),
                    contentDescription = stringResource(R.string.Profile),
                    contentScale = ContentScale.Fit,
                    modifier = imageModifier
                )
                else Image(
                    painter = painterResource(R.drawable.sign_user),
                    contentDescription = stringResource(R.string.Profile),
                    contentScale = ContentScale.Fit,
                    modifier = imageModifier
                )
            }

            item {
                Title(
                    text = stringResource(R.string.profile_title),
                    modifier = Modifier.padding(SmallPadding),
                    style = MaterialTheme.typography.displayMedium
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IOutlinedTextField(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(windowState.widthType.filter(0.6f, 0.4f, 0.3f))
                            .padding(end = SmallestPadding),
                        value = nickname,
                        label = stringResource(R.string.nickname),
                        onValueChange = { nickname = it },
                        placeholder = stringResource(R.string.nickname),
                    )
                    IStandardIconButton(
                        onClick = { nickWindow = true },
                        contentDescription = stringResource(R.string.nickname),
                        modifier = Modifier.fillMaxHeight(),
                        imageVector = Icons.Filled.Save,
                    )
                }
            }

            item {
                Title(
                    text = stringResource(R.string.user_data_title),
                    modifier = Modifier.padding(SmallPadding),
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            item {
                IResumeCard(
                    title = stringResource(R.string.user_data_title),
                    titlesValues = mapOf(
                        stringResource(R.string.email) to user?.email.toString(),
                        stringResource(R.string.firebase_id) to user?.firebaseId.toString(),
                        stringResource(R.string.credits) to user?.credits.toString(),
                        stringResource(R.string.points) to user?.points.toString(),
                    ),
                    modifier = Modifier
                        .fillMaxWidth(windowState.widthType.filter(0.8f, 0.7f, 0.6f))
                        .fillMaxWidth()
                        .padding(bottom = SmallPadding)
                )
            }

            item {
                IOutlinedButton(
                    onClick = {},
                    label = stringResource(R.string.delete_account),
                    modifier = Modifier
                        .fillMaxWidth(windowState.widthType.filter(0.8f, 0.7f, 0.6f))
                        .padding(vertical = MediumPadding),
                    imageVector = Icons.Filled.Delete,
                )
            }
        }
    }

}

/**
 * Preview of the [ProfileDesign] composable.
 *
 * @see [ProfileDesign]
 */
@UiModePreviews
@Composable
private fun ProfilePreview() {
    YouKnowTheme {
        ProfileDesign()
    }
}