package es.sebas1705.youknow.presentation.features.auth.screens.log.composable
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
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.youknow.core.composables.spacers.IVerSpacer
import es.sebas1705.youknow.core.composables.textfields.IEmailTextField
import es.sebas1705.youknow.core.composables.textfields.IPasswordTextField
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding

/**
 * Component that contains the email and password fields
 *
 * @param email String: email value
 * @param password String: password value
 * @param onEmailChange (String) -> Unit: action to be executed when the email field changes
 * @param onPasswordChange (String) -> Unit: action to be executed when the password field changes
 * @param modifier Modifier: modifier to be applied to the fields
 * @param soundPool Pair<SoundPool, Float>: sound pool to play sounds
 *
 * @author: Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun ColumnScope.EmailAndPassFields(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    soundPool: Pair<SoundPool, Float>? = null,
) {
    IEmailTextField(
        modifier = modifier,
        value = email,
        onValueChange = onEmailChange,
        soundPool = soundPool
    )
    IVerSpacer(height = SmallPadding)
    IPasswordTextField(
        modifier = modifier,
        value = password,
        onValueChange = onPasswordChange,
        soundPool = soundPool
    )
}
