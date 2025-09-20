package es.sebas1705.designsystem.dialogs


import android.media.SoundPool
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import es.sebas1705.core.designsystem.R
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.AppTheme

/**
 * Composable that displays a dialog to recover the password.
 *
 * @param email [String]: Email to be displayed in the dialog.
 * @param soundPool [Pair<SoundPool, Float>]: Pair of SoundPool and volume to play sounds.
 * @param windowState [WindowState]: State of the window.
 * @param onConfirm [Function]: Function to be executed when the user confirms the dialog.
 * @param onDismiss [Function]: Function to be executed when the user dismisses the dialog.
 *
 * @see AlertDialog
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun ResetPasswordDialog(
    email: String,
    soundPool: Pair<SoundPool, Float>? = null,
    windowState: WindowState = WindowState.default(),
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {}
) = IDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
        ITextButton(
            onClick = onConfirm,
            label = stringResource(R.string.core_designsystem_confirm),
            soundPool = soundPool
        )
    },
    modifier = Modifier
        .fillMaxWidth(windowState.widthFilter(0.9f, 0.7f, 0.5f)),
    dismissButton = {
        ITextButton(
            onClick = onDismiss,
            label = stringResource(R.string.core_designsystem_dismiss),
            soundPool = soundPool
        )
    },
    icon = null,
    title = {
        IText(stringResource(R.string.core_designsystem_reset_password))
    },
    text = {
        IText(
            stringResource(R.string.core_designsystem_reset_password_text) + email + "?",
            textAlign = TextAlign.Justify
        )
    }
)

@UiModePreviews
@Composable
private fun ForgotPasswordWindowPreview() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ResetPasswordDialog("Unknown")
        }
    }
}
