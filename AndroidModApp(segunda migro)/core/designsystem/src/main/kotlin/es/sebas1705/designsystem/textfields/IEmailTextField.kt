package es.sebas1705.designsystem.textfields


import android.media.SoundPool
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import es.sebas1705.core.designsystem.R
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.youknow.core.composables.textfields.IOutlinedTextField

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
 * @author Sebas1705 12/09/2025
 */
@Composable
fun IEmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    label: String = stringResource(R.string.core_designsystem_email),
    placeholder: String = stringResource(R.string.core_designsystem_email),
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


@ComposablePreview
@Composable
private fun PreviewE() = AppTheme {
    IEmailTextField(
        "Value",
        {},
        enabled = true,
        isError = false
    )
}

@ComposablePreview
@Composable
private fun PreviewD() = AppTheme {
    IEmailTextField(
        "Value",
        {},
        enabled = false,
        isError = false
    )
}