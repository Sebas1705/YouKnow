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

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.spacers.IVerSpacer
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthIntent
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthState
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthViewModel
import es.sebas1705.youknow.presentation.ui.theme.Paddings.LargePadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Menu Screen that will show the user the options to sign in, log in or log in with Google.
 * The user will be able to navigate to the Sign In Screen, Log In Screen or Home Screen.
 * The user will be able to log in with Google.
 *
 * @param windowState [WindowState]: State of the window.
 * @param authViewModel [AuthViewModel]: ViewModel for the Auth Screen.
 * @param toSignNav () -> Unit: Function to navigate to the Sign In Screen.
 * @param toHomeNav () -> Unit: Function to navigate to the Home Screen.
 * @param toLogNav () -> Unit: Function to navigate to the Log In Screen.
 *
 * @see AuthViewModel
 * @see WindowState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun MenuScreen(
    windowState: WindowState,
    authState: AuthState,
    authViewModel: AuthViewModel,
    toSignNav: () -> Unit,
    toHomeNav: () -> Unit,
    toLogNav: () -> Unit,
) {

    val context = LocalContext.current

    MenuDesign(
        windowState,
        authState,
        onSignButtonAction = toSignNav,
        onEmailLogButtonAction = toLogNav,
        onGoogleLogButtonAction = {
            authViewModel.eventHandler(
                AuthIntent.SignWithGoogle(
                    context,
                    onSuccess = toHomeNav,
                    onError = { Log.e("Google sign", "Failure for $it") }
                )
            )
        }
    )
}

/**
 * Design of the Menu Screen.
 *
 * @param windowState [WindowState]: State of the window.
 * @param onSignButtonAction () -> Unit: Function to navigate to the Sign In Screen.
 * @param onEmailLogButtonAction () -> Unit: Function to navigate to the Log In Screen.
 * @param onGoogleLogButtonAction () -> Unit: Function to log in with Google.
 *
 * @see WindowState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun MenuDesign(
    windowState: WindowState = WindowState.default(),
    authState: AuthState = AuthState.default(),
    onSignButtonAction: () -> Unit = {},
    onEmailLogButtonAction: () -> Unit = {},
    onGoogleLogButtonAction: () -> Unit = {},
) {

    ApplyBack(
        if (windowState.isPortrait) R.drawable.back_portrait_fill
        else R.drawable.back_landscape_fill,
    ) {
        if (authState.isLoading) LoadingDialog(windowState)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = LargePadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IVerSpacer(0.4f)
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            IVerSpacer(0.2f)
            Text(
                text = stringResource(id = R.string.initial_text),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            IVerSpacer(0.2f)
            MenuButtons(
                buttonsModifier = Modifier
                    .fillMaxWidth(windowState.widthType.filter(1f, 0.8f, 0.6f)),
                onSignButtonAction = onSignButtonAction,
                onEmailLogButtonAction = onEmailLogButtonAction,
                onGoogleLogButtonAction = onGoogleLogButtonAction
            )
            IVerSpacer(0.7f)
        }
    }
}

/**
 * Preview of the [MenuScreen].
 *
 * @see MenuScreen
 */
@UiModePreviews
@Composable
private fun MenuPreview() {
    YouKnowTheme {
        MenuDesign()
    }
}