package es.sebas1705.designsystem.dialogs


import android.media.SoundPool
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import es.sebas1705.core.designsystem.R
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.youknow.core.composables.textfields.IOutlinedTextField

/**
 * Dialog to request a URL.
 *
 * @param modifier [Modifier]: Modifier to be applied to the dialog.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param windowState [WindowState]: State of the window.
 * @param onConfirmButton [Function1<String, Unit]: Function to be executed when the user confirms the dialog.
 * @param onDismissAction [Function0<Unit>]: Function to be executed when the user dismisses the dialog.
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun UrlRequestDialog(
    modifier: Modifier = Modifier,
    soundPool: Pair<SoundPool, Float>? = null,
    windowState: WindowState = WindowState.default(),
    onConfirmButton: (String) -> Unit = {},
    onDismissAction: () -> Unit = {}
) {
    val context = LocalContext.current
    var photoUrl by remember { mutableStateOf("") }

    IDialog(
        onDismissRequest = onDismissAction,
        confirmButton = {
            ITextButton(
                onClick = { onConfirmButton(photoUrl) },
                label = stringResource(R.string.core_designsystem_confirm),
                soundPool = soundPool
            )
        },
        modifier = modifier,
        dismissButton = {
            ITextButton(
                onClick = onDismissAction,
                label = stringResource(R.string.core_designsystem_dismiss),
                soundPool = soundPool
            )
        },
        title = {
            Image(
                painter = painterResource(es.sebas1705.core.resources.R.drawable.sign_user),
                contentDescription = stringResource(R.string.core_designsystem_profile),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
            )
        },
        text = {
            IOutlinedTextField(
                value = photoUrl,
                onValueChange = { photoUrl = it },
                label = stringResource(R.string.core_designsystem_url_photo),
                placeholder = stringResource(R.string.core_designsystem_url_photo),
                leadingIcon = Icons.Filled.Link to {},
                soundPool = soundPool,
            )
        }
    )
}

@UiModePreviews
@Composable
private fun UrlRequestWindowPreview() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            UrlRequestDialog()
        }
    }
}
