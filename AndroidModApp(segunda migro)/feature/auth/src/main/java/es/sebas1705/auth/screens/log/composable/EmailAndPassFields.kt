package es.sebas1705.auth.screens.log.composable


import android.media.SoundPool
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.designsystem.spacers.IVerSpacer
import es.sebas1705.designsystem.textfields.IEmailTextField
import es.sebas1705.designsystem.textfields.IPasswordTextField

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
 * @author: Sebas1705 12/09/2025
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
