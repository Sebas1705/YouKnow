package es.sebas1705.designsystem.textfields


import android.media.SoundPool
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import es.sebas1705.core.designsystem.R
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.youknow.core.composables.textfields.IOutlinedTextField

/**
 * Password text field
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
 * @author Sebas1705 12/09/2025
 */
@Composable
fun IPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    label: String = stringResource(R.string.core_designsystem_password),
    placeholder: String = stringResource(R.string.core_designsystem_password),
    soundPool: Pair<SoundPool, Float>? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }
    IOutlinedTextField(
        value,
        onValueChange,
        modifier,
        label,
        placeholder,
        enabled,
        readOnly,
        leadingIcon = Icons.Filled.Password to {},
        trailingIcon =
        (if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff) to
                { passwordVisible = !passwordVisible },
        isError = isError,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        soundPool = soundPool
    )
}

@ComposablePreview
@Composable
private fun PreviewE() = AppTheme {
    IPasswordTextField(
        "Value",
        {},
        enabled = true,
        isError = false
    )
}

@ComposablePreview
@Composable
private fun PreviewD() = AppTheme {
    IPasswordTextField(
        "Value",
        {},
        enabled = false,
        isError = false
    )
}