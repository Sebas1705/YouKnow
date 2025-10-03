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
import es.sebas1705.core.designsystem.R
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.AppTheme

/**
 * Composable that shows a dialog with a reload message.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair<SoundPool, Float>]: Pair of SoundPool and volume to play a sound when the dialog is shown.
 * @param onConfirm () -> Unit: Function to be executed when the confirm button is clicked.
 *
 * @see AlertDialog
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun ReloadDialog(
    windowState: WindowState = WindowState.default(),
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {}
) = IDialog(
    confirmButton = {
        ITextButton(
            label = stringResource(R.string.core_designsystem_confirm),
            onClick = onConfirm,
        )
    },
    onDismissRequest = onDismiss,
    dismissButton = {
        ITextButton(
            label = stringResource(R.string.core_designsystem_dismiss),
            onClick = onDismiss,
        )
    },
    modifier = Modifier.fillMaxWidth(0.9f),
    text = {
        IText(stringResource(R.string.core_designsystem_reload_text))
    }
)

@UiModePreviews
@Composable
private fun Preview() = AppTheme {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ReloadDialog()
    }
}
