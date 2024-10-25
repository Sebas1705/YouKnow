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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.composables.ApplyBack
import es.sebas1705.youknow.presentation.features.home.viewmodels.HomeState
import es.sebas1705.youknow.presentation.features.home.viewmodels.HomeViewModel
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Profile Screen that shows the user's data.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun ProfileScreen(
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val homeState = homeViewModel.uiState.collectAsStateWithLifecycle()
    ProfileDesign(homeViewModel,homeState)
}

/**
 * Design of the Profile Screen. It shows the user's data.
 *
 * @param homeViewModel [HomeViewModel]: ViewModel of the Home Screen.
 * @param homeState [State]<[HomeState]>: State of the Home Screen.
 *
 * @see HomeViewModel
 * @see HomeState
 * @see [ProfileScreen]
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun ProfileDesign(
    homeViewModel: HomeViewModel? = null,
    homeState: State<HomeState>? = null,
) {
    val user = homeState?.value?.user

    ApplyBack(
        R.drawable.back_portrait_empty
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ){
            Column{
                val imageModifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                user?.photoUrl?.let{
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(user.photoUrl)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.sign_user),
                        contentDescription = stringResource(R.string.Profile),
                        contentScale = ContentScale.Crop,
                        modifier = imageModifier
                    )
                } ?: Image(
                    painter = painterResource(R.drawable.sign_user),
                    contentDescription = stringResource(R.string.Profile),
                    modifier = imageModifier
                )

                Text(
                    text = "User data",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Text(
                    text = "Name: ${user?.displayName?:"Null"}\n" +
                            "Email: ${user?.email?:"Null"}\n" +
                            "Phone: ${user?.phoneNumber?:"Null"}\n" +
                            "Provider: ${user?.providerId?:"Null"}\n" +
                            "Uid: ${user?.uid?:"Null"}\n" +
                            "Photo: ${user?.photoUrl?:"Null"}\n" +
                            "EmailVerified: ${user?.isEmailVerified?:"Null"}\n" +
                            "ProviderData: ${user?.providerData?:"Null"}\n",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
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