package es.sebas1705.designsystem.dialogs


import android.media.SoundPool
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.core.designsystem.R
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.designsystem.textfields.IEmailTextField

/**
 * Composable that displays a dialog to recover the password.
 *
 * @param modifier [Modifier]: Modifier to be applied to the dialog.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onConfirm [Function]: Function to be executed when the user confirms the dialog.
 * @param onDismiss [Function]: Function to be executed when the user dismisses the dialog.
 *
 * @see AlertDialog
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun ForgotPasswordDialog(
    modifier: Modifier = Modifier,
    soundPool: Pair<SoundPool, Float>? = null,
    onConfirm: (String) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }

    IDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            ITextButton(
                onClick = { onConfirm(email) },
                label = stringResource(R.string.core_designsystem_confirm),
                soundPool = soundPool
            )
        },
        modifier = modifier,
        dismissButton = {
            ITextButton(
                onClick = onDismiss,
                label = stringResource(R.string.core_designsystem_dismiss),
                soundPool = soundPool
            )
        },
        title = {
            IText(stringResource(R.string.core_designsystem_enter_email))
        },
        text = {
            IEmailTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                soundPool = soundPool
            )
        }
    )
}

@UiModePreviews
@Composable
private fun ForgotPasswordWindowPreview() {
    AppTheme{
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ForgotPasswordDialog()
        }
    }
}