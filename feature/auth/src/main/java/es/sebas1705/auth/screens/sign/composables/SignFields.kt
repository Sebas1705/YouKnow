package es.sebas1705.auth.screens.sign.composables


import android.media.SoundPool
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.designsystem.spacers.IVerSpacer
import es.sebas1705.designsystem.textfields.IEmailTextField
import es.sebas1705.designsystem.textfields.IPasswordTextField
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.designsystem.textfields.IOutlinedTextField
import es.sebas1705.feature.auth.R

@Composable
fun ColumnScope.SignField(
    modifier: Modifier = Modifier,
    userName: String,
    onUserNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    emailRepeat: String,
    onEmailRepeatChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordRepeat: String,
    onPasswordRepeatChange: (String) -> Unit,
    soundPool: Pair<SoundPool, Float>? = null,
) {
    val userNameText = stringResource(id = R.string.feature_auth_username)
    val emailText = stringResource(id = R.string.feature_auth_email)
    val passwordText = stringResource(id = R.string.feature_auth_password)
    val repeatText = stringResource(id = R.string.feature_auth_repeat)

    IOutlinedTextField(
        modifier = modifier,
        value = userName,
        onValueChange = onUserNameChange,
        label = userNameText,
        placeholder = userNameText,
        leadingIcon = Icons.Filled.Person to { },
    )
    IVerSpacer(height = SmallPadding)
    IEmailTextField(
        modifier = modifier,
        value = email,
        onValueChange = onEmailChange,
    )
    IVerSpacer(height = SmallPadding)
    IEmailTextField(
        modifier = modifier,
        value = emailRepeat,
        onValueChange = onEmailRepeatChange,
        label = "$repeatText $emailText",
        placeholder = "$repeatText $emailText",
    )
    IVerSpacer(height = SmallPadding)
    IPasswordTextField(
        modifier = modifier,
        value = password,
        onValueChange = onPasswordChange,
    )
    IVerSpacer(height = SmallPadding)
    IPasswordTextField(
        modifier = modifier,
        value = passwordRepeat,
        onValueChange = onPasswordRepeatChange,
        label = "$repeatText $passwordText",
        placeholder = "$repeatText $passwordText",
    )
}
