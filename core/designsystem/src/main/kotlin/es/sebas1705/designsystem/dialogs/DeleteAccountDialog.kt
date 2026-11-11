package es.sebas1705.designsystem.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import es.sebas1705.core.designsystem.R
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.AppTheme

/**
 * Composable that asks the user to confirm that they want to delete their account for good, saying
 * what is deleted. When a previous attempt failed, [errorMessage] explains why and the confirm
 * button retries.
 *
 * @param windowState [WindowState]: State of the window.
 * @param errorMessage [String]: Why the previous attempt failed, shown under the text; `null` for none.
 * @param onConfirm [Function]: Function to be executed when the user confirms the dialog.
 * @param onDismiss [Function]: Function to be executed when the user dismisses the dialog.
 *
 * @see AlertDialog
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun DeleteAccountDialog(
    windowState: WindowState = WindowState.default(),
    errorMessage: String? = null,
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {}
) = IDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
        ITextButton(
            onClick = onConfirm,
            label = stringResource(R.string.core_designsystem_delete_account_confirm),
        )
    },
    modifier = Modifier
        .fillMaxWidth(windowState.widthFilter(0.9f,0.7f,0.5f)),
    dismissButton = {
        ITextButton(
            onClick = onDismiss,
            label = stringResource(R.string.core_designsystem_dismiss),
        )
    },
    title = {
        IText(stringResource(R.string.core_designsystem_delete_account))
    },
    text = {
        Column {
            IText(
                stringResource(R.string.core_designsystem_delete_account_text),
                textAlign = TextAlign.Justify,
            )
            if (errorMessage != null) IText(
                errorMessage,
                modifier = Modifier.padding(top = 12.dp),
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Justify,
            )
        }
    }
)

@UiModePreviews
@Composable
private fun DeleteAccountWindowPreview() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            DeleteAccountDialog()
        }
    }
}

@UiModePreviews
@Composable
private fun DeleteAccountErrorWindowPreview() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            DeleteAccountDialog(errorMessage = "Your data was deleted but the account was not. Try again.")
        }
    }
}
