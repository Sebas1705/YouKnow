package es.sebas1705.designsystem.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.core.designsystem.R
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.designsystem.textfields.IPasswordTextField
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.AppTheme

/**
 * Composable that asks the signed-in user to type their password again, before a sensitive
 * operation such as deleting the account. The password only lives in this composable's state until
 * [onConfirm]; it is never logged or stored anywhere else.
 *
 * @param windowState [WindowState]: State of the window.
 * @param errorMessage [String]: Why the previous attempt failed, e.g. a wrong password; `null` for none.
 * @param onConfirm [Function]: Function to be executed with the typed password.
 * @param onDismiss [Function]: Function to be executed when the user dismisses the dialog.
 *
 * @see AlertDialog
 *
 * @author Sebas1705 11/11/2026
 * @since 1.6.0
 */
@Composable
fun PasswordConfirmDialog(
    windowState: WindowState = WindowState.default(),
    errorMessage: String? = null,
    onConfirm: (String) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    var password by rememberSaveable { mutableStateOf("") }

    IDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            ITextButton(
                onClick = { onConfirm(password) },
                label = stringResource(R.string.core_designsystem_confirm),
            )
        },
        modifier = Modifier
            .fillMaxWidth(windowState.widthFilter(0.9f, 0.7f, 0.5f)),
        dismissButton = {
            ITextButton(
                onClick = onDismiss,
                label = stringResource(R.string.core_designsystem_dismiss),
            )
        },
        title = {
            IText(stringResource(R.string.core_designsystem_confirm_password_title))
        },
        text = {
            Column {
                IText(
                    stringResource(R.string.core_designsystem_confirm_password_text),
                    textAlign = TextAlign.Justify,
                )
                IPasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    value = password,
                    onValueChange = { password = it },
                    isError = errorMessage != null,
                )
                if (errorMessage != null) IText(
                    errorMessage,
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }
    )
}

@UiModePreviews
@Composable
private fun PasswordConfirmWindowPreview() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PasswordConfirmDialog()
        }
    }
}

@UiModePreviews
@Composable
private fun PasswordConfirmErrorWindowPreview() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PasswordConfirmDialog(errorMessage = "That password is not right.")
        }
    }
}
