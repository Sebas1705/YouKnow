package es.sebas1705.youknow.core.composables.textfields
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Email text field
 *
 * @param value [String]: Value
 * @param onValueChange [Function1<String, Unit]: On value change
 * @param modifier [Modifier]: Modifier
 * @param enabled [Boolean]: Enabled
 * @param readOnly [Boolean]: Read only
 * @param isError [Boolean]: Is error
 * @param label [String]: Label
 * @param placeholder [String]: Placeholder
 * @param soundPool [Pair]<[SoundPool], [Float]>: Sound pool
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun IEmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    label: String = stringResource(R.string.email),
    placeholder: String = stringResource(R.string.email),
    soundPool: Pair<SoundPool, Float>? = null
) = IOutlinedTextField(
    value,
    onValueChange,
    modifier,
    label,
    placeholder,
    enabled,
    readOnly,
    leadingIcon = Icons.Filled.Mail to {},
    isError = isError,
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    soundPool = soundPool
)


@IComposablePreview
@Composable
private fun PreviewE() = YouKnowTheme {
    IEmailTextField(
        "Value",
        {},
        enabled = true,
        isError = false
    )
}

@IComposablePreview
@Composable
private fun PreviewD() = YouKnowTheme {
    IEmailTextField(
        "Value",
        {},
        enabled = false,
        isError = false
    )
}