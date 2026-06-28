package es.sebas1705.auth.screens.log.composable


import android.media.SoundPool
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.designsystem.spacers.IVerSpacer
import es.sebas1705.designsystem.textfields.IEmailTextField
import es.sebas1705.designsystem.textfields.IPasswordTextField

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
    )
    IVerSpacer(height = SmallPadding)
    IPasswordTextField(
        modifier = modifier,
        value = password,
        onValueChange = onPasswordChange,
    )
}
