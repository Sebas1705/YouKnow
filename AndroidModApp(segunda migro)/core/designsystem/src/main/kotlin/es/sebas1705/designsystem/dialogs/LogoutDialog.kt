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
import es.sebas1705.designsystem.buttons.common.IFilledButton
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.AppTheme

/**
 * Composable that displays a dialog to log out.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onConfirm [Function]: Function to be executed when the user confirms the dialog.
 * @param onDismiss [Function]: Function to be executed when the user dismisses the dialog.
 *
 * @see AlertDialog
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun LogoutDialog(
    windowState: WindowState = WindowState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {}
) = IDialog(
    onDismissRequest = onDismiss,
    modifier = Modifier
        .fillMaxWidth(windowState.widthFilter(0.9f, 0.7f, 0.5f)),
    confirmButton = {
        IFilledButton(
            label = stringResource(R.string.core_designsystem_confirm),
            onClick = onConfirm,
            soundPool = soundPool
        )
    },
    dismissButton = {
        IFilledButton(
            label = stringResource(R.string.core_designsystem_dismiss),
            onClick = onDismiss,
            soundPool = soundPool
        )
    },
    title = {
        IText(stringResource(R.string.core_designsystem_logout))
    }
)

/**
 * Preview for [LogoutDialog]
 *
 * @see LogoutDialog
 */
@UiModePreviews
@Composable
private fun LogoutWindowPreview() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LogoutDialog()
        }
    }
}
