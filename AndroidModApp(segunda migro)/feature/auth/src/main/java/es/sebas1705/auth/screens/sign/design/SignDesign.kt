package es.sebas1705.auth.screens.sign.design


import android.media.SoundPool
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import es.sebas1705.auth.screens.sign.viewmodel.SignState
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.ComposableConstants.LOOP_N
import es.sebas1705.designsystem.ComposableConstants.PRIORITY_SOUND
import es.sebas1705.designsystem.ComposableConstants.RATE
import es.sebas1705.designsystem.buttons.common.IFilledButton
import es.sebas1705.designsystem.dialogs.ErrorInfoDialog
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.spacers.IVerSpacer
import es.sebas1705.designsystem.texts.TitleSurface
import es.sebas1705.ui.theme.Paddings.HugePadding
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.feature.auth.R
import es.sebas1705.auth.screens.sign.composables.SignField

/**
 * Main Design of the SignScreen.
 *
 * @param windowState [WindowState]: State of the window.
 * @param signState [SignState]: State of the Sign Screen.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onSignButtonAction (email: String, pass: String, nickname: String, onError: (String) -> Unit) -> Unit: Function to sign in.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun SignDesign(
    windowState: WindowState = WindowState.default(),
    signState: SignState = SignState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onSignButtonAction: (email: String, pass: String, nickname: String, onError: (String) -> Unit) -> Unit = { _, _, _, _ -> }
) {

    //Locals:
    val keyboard = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current

    //Texts:
    val defaultErrorText = stringResource(id = R.string.feature_auth_login_error)
    val notMatchErrorText = stringResource(id = R.string.feature_auth_any_not_match)
    val bothText = stringResource(id = R.string.feature_auth_both)
    val emailText = stringResource(id = R.string.feature_auth_email)
    val passwordText = stringResource(id = R.string.feature_auth_password)

    //States:
    var email by rememberSaveable { mutableStateOf("") }
    var emailRepeat by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordRepeat by rememberSaveable { mutableStateOf("") }
    var userName by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf(defaultErrorText) }
    val errorSound = remember { soundPool?.first?.load(ctx, R.raw.sound_lose, PRIORITY_SOUND) }

    //Flags:
    var errorFlag by rememberSaveable { mutableStateOf(false) }

    //Actions:
    val activateError: (String) -> Unit = { error = it; errorFlag = true }

    //Body:
    ApplyBack(
        windowState.backFill
    ) {
        //Dialogs:
        if (signState.isLoading)
            LoadingDialog(windowState)
        else if (errorFlag) {
            ErrorInfoDialog(
                errorText = error,
                onConfirm = { errorFlag = false },
                soundPool = soundPool
            )
        }

        //Body:
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MediumPadding)
                .imePadding(),
        ) {
            item {
                Column(
                    modifier = windowState.heightType.filter(
                        Modifier.fillMaxWidth(),
                        Modifier
                            .height(windowState.heightDp)
                            .fillMaxWidth(),
                        Modifier
                            .height(windowState.heightDp)
                            .fillParentMaxWidth()
                    ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TitleSurface(text = stringResource(id = R.string.feature_auth_signup))
                    IVerSpacer(height = HugePadding)
                    SignField(
                        modifier = Modifier
                            .fillMaxWidth(windowState.widthType.filter(1f, 0.8f, 0.6f)),
                        userName = userName,
                        onUserNameChange = { userName = it },
                        email = email,
                        onEmailChange = { email = it },
                        emailRepeat = emailRepeat,
                        onEmailRepeatChange = { emailRepeat = it },
                        password = password,
                        onPasswordChange = { password = it },
                        passwordRepeat = passwordRepeat,
                        onPasswordRepeatChange = { passwordRepeat = it },
                        soundPool = soundPool
                    )
                    IVerSpacer(height = MediumPadding)
                    IFilledButton(
                        label = stringResource(id = R.string.feature_auth_signup),
                        modifier = Modifier,
                        onClick = {
                            keyboard?.hide()
                            if (email == emailRepeat && password == passwordRepeat)
                                onSignButtonAction(email, password, userName, activateError)
                            else {
                                val who = when {
                                    email != emailRepeat && password != passwordRepeat -> bothText
                                    email != emailRepeat -> emailText
                                    else -> passwordText
                                }
                                soundPool?.let {
                                    it.first.play(
                                        errorSound ?: 0,
                                        it.second,
                                        it.second,
                                        PRIORITY_SOUND,
                                        LOOP_N,
                                        RATE
                                    )
                                }
                                activateError("$notMatchErrorText ($who)")
                            }
                        },
                        soundPool = soundPool
                    )
                }
            }
        }
    }
}

@UiModePreviews
@Composable
private fun SignPreview() {
    AppTheme {
        SignDesign()
    }
}