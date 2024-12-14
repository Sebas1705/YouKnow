package es.sebas1705.youknow.presentation.features.home.windows
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.data.firebase.authentication.config.ProviderAuth
import es.sebas1705.youknow.domain.model.UserModel
import es.sebas1705.youknow.presentation.composables.InfoCard
import es.sebas1705.youknow.presentation.composables.InfoDialog
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun UserInfoWindow(
    userModel: UserModel? = null,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {}
) {
    var user: UserModel = if (userModel == null) UserModel(
        firebaseId = "TODO()",
        email = " TODO()",
        provider = ProviderAuth.EMAIL,
        nickName = "TODO()",
        photoUrl = null,
        groupId = "TODO()",
        points = 11111,
        credits = 11111,
        friends = emptyList()
    ) else userModel
    InfoDialog(
        modifier = modifier,
        title = {
            if (user.photoUrl != null) AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.photoUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.sign_user),
                contentDescription = stringResource(R.string.Profile),
                modifier = modifier.fillMaxWidth(0.35f)
            )
            else Image(
                painter = painterResource(R.drawable.sign_user),
                contentDescription = stringResource(R.string.Profile),
                modifier = modifier.fillMaxWidth(0.35f)
            )
        },
        body = {
            InfoCard(
                titlesValues = mapOf(
                    stringResource(R.string.nickname) to user.nickName,
                    stringResource(R.string.points) to user.points.toString(),
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
        },
        onDismiss = onDismiss
    )
}


/**
 * Preview for [LogoutWindow]
 *
 * @see LogoutWindow
 */
@UiModePreviews
@Composable
private fun UserInfoWindowPreview() {
    YouKnowTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            UserInfoWindow()
        }
    }
}