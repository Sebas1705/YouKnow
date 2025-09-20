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
import es.sebas1705.youknow.core.composables.textfields.IOutlinedTextField
import es.sebas1705.feature.auth.R

/**
 * Sign Field that will allow the user to sign up.
 * It will show a form with username, email and password fields.
 * The email and password fields will be repeated.
 *
 * @param modifier [Modifier]: Modifier for the fields.
 * @param userName [String]: Username of the user.
 * @param onUserNameChange (String) -> Unit: Function to handle the username change.
 * @param email [String]: Email of the user.
 * @param onEmailChange (String) -> Unit: Function to handle the email change.
 * @param emailRepeat [String]: Repeat email of the user.
 * @param onEmailRepeatChange (String) -> Unit: Function to handle the repeat email change.
 * @param password [String]: Password of the user.
 * @param onPasswordChange (String) -> Unit: Function to handle the password change.
 * @param passwordRepeat [String]: Repeat password of the user.
 * @param onPasswordRepeatChange (String) -> Unit: Function to handle the repeat password change.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
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
    //Texts:
    val userNameText = stringResource(id = R.string.feature_auth_username)
    val emailText = stringResource(id = R.string.feature_auth_email)
    val passwordText = stringResource(id = R.string.feature_auth_password)
    val repeatText = stringResource(id = R.string.feature_auth_repeat)

    //Fields:
    IOutlinedTextField(
        modifier = modifier,
        value = userName,
        onValueChange = onUserNameChange,
        label = userNameText,
        placeholder = userNameText,
        leadingIcon = Icons.Filled.Person to { },
        soundPool = soundPool
    )
    IVerSpacer(height = SmallPadding)
    IEmailTextField(
        modifier = modifier,
        value = email,
        onValueChange = onEmailChange,
        soundPool = soundPool
    )
    IVerSpacer(height = SmallPadding)
    IEmailTextField(
        modifier = modifier,
        value = emailRepeat,
        onValueChange = onEmailRepeatChange,
        label = "$repeatText $emailText",
        placeholder = "$repeatText $emailText",
        soundPool = soundPool
    )
    IVerSpacer(height = SmallPadding)
    IPasswordTextField(
        modifier = modifier,
        value = password,
        onValueChange = onPasswordChange,
        soundPool = soundPool
    )
    IVerSpacer(height = SmallPadding)
    IPasswordTextField(
        modifier = modifier,
        value = passwordRepeat,
        onValueChange = onPasswordRepeatChange,
        label = "$repeatText $passwordText",
        placeholder = "$repeatText $passwordText",
        soundPool = soundPool
    )
}