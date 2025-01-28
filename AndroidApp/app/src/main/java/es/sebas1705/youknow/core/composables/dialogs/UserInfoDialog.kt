package es.sebas1705.youknow.core.composables.dialogs
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

import android.media.SoundPool
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.ITextButton
import es.sebas1705.youknow.core.composables.cards.IResumeCard
import es.sebas1705.youknow.core.composables.texts.Title
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.domain.model.social.UserModel
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * User info dialog
 *
 * @param windowState [WindowState]: Window state
 * @param userModel [UserModel]: User model
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume
 * @param onDismiss [Function0<Unit>]: On dismiss
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun UserInfoDialog(
    windowState: WindowState = WindowState.default(),
    userModel: UserModel = UserModel.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onDismiss: () -> Unit = {}
) {
    IDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.fillMaxWidth(
            windowState.widthFilter(0.95f, 0.75f, 0.55f)
        ),
        dismissButton = {
            ITextButton(
                onClick = onDismiss,
                label = stringResource(R.string.dismiss),
                soundPool = soundPool
            )
        },
        icon = {
            val size = windowState.sizeFilter(100, 150, 200).dp
            if (userModel.photoUrl != null) AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userModel.photoUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.sign_user),
                contentDescription = stringResource(R.string.Profile),
                modifier = Modifier.size(size)
            )
            else Image(
                painter = painterResource(R.drawable.sign_user),
                contentDescription = stringResource(R.string.Profile),
                modifier = Modifier.size(size)
            )
        },
        title = {
            Title(
                text = userModel.nickName,
                style = windowState.widthFilter(
                    MaterialTheme.typography.titleLarge,
                    MaterialTheme.typography.headlineMedium,
                    MaterialTheme.typography.displaySmall
                )
            )
        },
        text = {
            IResumeCard(
                title = userModel.nickName,
                titlesValues = mapOf(
                    stringResource(R.string.points) to userModel.points.toString(),
                    stringResource(R.string.credits) to userModel.credits.toString(),
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    )
}

@UiModePreviews
@Composable
private fun Preview() {
    YouKnowTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            UserInfoDialog()
        }
    }
}