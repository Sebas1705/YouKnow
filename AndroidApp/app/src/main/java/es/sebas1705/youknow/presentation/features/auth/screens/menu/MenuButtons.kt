package es.sebas1705.youknow.presentation.features.auth.screens.menu
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

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.R
import es.sebas1705.youknow.presentation.composables.CustomIconTextButton
import es.sebas1705.youknow.presentation.composables.CustomFilledButton
import es.sebas1705.youknow.presentation.composables.Spacers.SimpleSpacer

/**
 * Composable that contains the buttons to navigate to the Sign Up, Google Log In and Email Log In screens.
 *
 * @param buttonsModifier [Modifier]: Modifier to apply to the buttons.
 * @param onSignButtonAction () -> Unit: Function to navigate to the Sign Up screen.
 * @param onEmailLogButtonAction () -> Unit: Function to navigate to the Email Log In screen.
 * @param onGoogleLogButtonAction () -> Unit: Function to navigate to the Google Log In screen.
 *
 * @see CustomFilledButton
 * @see CustomIconTextButton
 * @see SimpleSpacer
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun MenuButtons(
    buttonsModifier: Modifier,
    onSignButtonAction: () -> Unit,
    onEmailLogButtonAction: () -> Unit,
    onGoogleLogButtonAction: () -> Unit,
){
    CustomFilledButton(
        modifier = buttonsModifier.height(48.dp),
        text = stringResource(id = R.string.sign_up),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        onClick = onSignButtonAction
    )
    SimpleSpacer()
    CustomIconTextButton(
        modifier = buttonsModifier.height(48.dp),
        text = stringResource(id = R.string.google_log),
        icon = painterResource(id = R.drawable.google),
        onClick = onGoogleLogButtonAction
    )
    SimpleSpacer()
    CustomIconTextButton(
        modifier = buttonsModifier.height(48.dp),
        text = stringResource(id = R.string.email_log),
        imageVector = Icons.Default.Email,
        onClick = onEmailLogButtonAction
    )
}