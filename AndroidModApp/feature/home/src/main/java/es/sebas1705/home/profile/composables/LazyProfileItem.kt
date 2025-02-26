package es.sebas1705.home.profile.composables
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
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import es.sebas1705.common.states.WindowState
import es.sebas1705.designsystem.buttons.common.IOutlinedButton
import es.sebas1705.designsystem.cards.IResumeCard
import es.sebas1705.designsystem.divider.IHorDivider
import es.sebas1705.designsystem.texts.Title
import es.sebas1705.models.social.UserModel
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.core.composables.textfields.IOutlinedTextField
import es.sebas1705.youknow.feature.home.R

/**
 * LazyColumn item for the Profile Screen.
 *
 * @param windowState [WindowState]: State of the window.
 * @param userModel [UserModel]: User's data.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param nickname [String]: User's nickname.
 * @param onChangeNickname (String) -> Unit: Function to change the user's nickname.
 * @param onChangeNicknameDialog () -> Unit: Function to show the dialog to change the user's nickname.
 * @param onChangePhotoDialog () -> Unit: Function to show the dialog to change the user's photo.
 * @param onChangePassDialog () -> Unit: Function to show the dialog to change the user's password.
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun LazyProfileItem(
    windowState: WindowState = WindowState.default(),
    userModel: UserModel = UserModel.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    nickname: String = "",
    onChangeNickname: (String) -> Unit = {},
    onChangeNicknameDialog: () -> Unit = {},
    onChangePhotoDialog: () -> Unit = {},
    onChangePassDialog: () -> Unit = {},
) = Column(
    modifier = Modifier
        .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val width = windowState.widthFilter(0.8f, 0.7f, 0.5f)
    val imageModifier = Modifier
        .height(windowState.heightFilter(200.dp, 250.dp, 300.dp))
        .padding(
            end = SmallPadding,
            bottom = MediumPadding,
            start = SmallPadding,
            top = SmallPadding
        )
        .background(MaterialTheme.colorScheme.surface)
        .border(3.dp, MaterialTheme.colorScheme.secondary)
        .clickable(onClick = onChangePhotoDialog)
    if (userModel.photoUrl != null) AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(userModel.photoUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(es.iberext.youknow.core.resources.R.drawable.sign_user),
        contentDescription = stringResource(R.string.feature_home_Profile),
        contentScale = ContentScale.Fit,
        modifier = imageModifier
    )
    else Image(
        painter = painterResource(es.iberext.youknow.core.resources.R.drawable.sign_user),
        contentDescription = stringResource(R.string.feature_home_Profile),
        contentScale = ContentScale.Fit,
        modifier = imageModifier
    )
    IHorDivider(
        modifier = Modifier
            .fillMaxWidth(width + 0.1f)
            .fillMaxWidth()
            .padding(bottom = SmallPadding)
    )
    IOutlinedTextField(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(width)
            .padding(bottom = SmallPadding),
        value = nickname,
        label = stringResource(R.string.feature_home_user_nickname),
        onValueChange = onChangeNickname,
        placeholder = stringResource(R.string.feature_home_user_nickname),
        trailingIcon = Icons.Filled.Save to onChangeNicknameDialog,
        soundPool = soundPool
    )
    IHorDivider(
        modifier = Modifier
            .fillMaxWidth(width + 0.1f)
            .fillMaxWidth()
            .padding(bottom = MediumPadding)
    )
    Title(
        text = stringResource(R.string.feature_home_user_profile_title),
        modifier = Modifier.padding(bottom = SmallPadding),
        style = MaterialTheme.typography.displayMedium
    )
    IResumeCard(
        title = userModel.nickName,
        titlesValues = mapOf(
            stringResource(es.iberext.youknow.core.designsystem.R.string.core_designsystem_email) to userModel.email,
            stringResource(R.string.feature_home_user_firebase_id) to userModel.firebaseId,
            stringResource(es.iberext.youknow.core.resources.R.string.core_resources_credits) to userModel.credits.toString(),
            stringResource(es.iberext.youknow.core.resources.R.string.core_resources_points) to userModel.points.toString(),
        ),
        modifier = Modifier
            .fillMaxWidth(width)
            .fillMaxWidth()
            .padding(bottom = MediumPadding)
    )
    IOutlinedButton(
        onClick = onChangePassDialog,
        label = stringResource(R.string.feature_home_user_reset_password),
        modifier = Modifier
            .fillMaxWidth(width)
            .padding(bottom = SmallPadding),
        imageVector = Icons.Filled.Password,
        soundPool = soundPool
    )
}