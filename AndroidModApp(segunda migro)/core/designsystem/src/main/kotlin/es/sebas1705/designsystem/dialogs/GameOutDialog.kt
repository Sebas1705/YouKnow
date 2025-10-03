package es.sebas1705.designsystem.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.core.designsystem.R
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.AppTheme

/**
 * Dialog that will be shown when the game is over.
 *
 * @param modifier [Modifier]: Modifier to be applied to the dialog.
 * @param onConfirm [Function]: Function to be executed when the user confirms the dialog.
 * @param onDismiss [Function]: Function to be executed when the user dismisses the dialog.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun GameOutDialog(
    modifier: Modifier = Modifier,
    onConfirm : () -> Unit = {},
    onDismiss : () -> Unit = {},
) = IDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
        ITextButton(
            onClick = onConfirm,
            label = stringResource(R.string.core_designsystem_confirm),
        )
    },
    modifier = modifier,
    dismissButton = {
        ITextButton(
            onClick = onDismiss,
            label = stringResource(R.string.core_designsystem_dismiss),
        )
    },
    icon = null,
    title = {
        IText(stringResource(id = R.string.core_designsystem_game_out_title))
    },
    text = {
        IText(stringResource(id = R.string.core_designsystem_game_out_body))
    }
)

@UiModePreviews
@Composable
private fun GameOutWindowPreview() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            GameOutDialog()
        }
    }
}

