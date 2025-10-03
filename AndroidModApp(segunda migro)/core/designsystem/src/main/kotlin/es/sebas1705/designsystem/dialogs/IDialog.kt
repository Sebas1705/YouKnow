package es.sebas1705.designsystem.dialogs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.AppTheme

/**
 * Personalized dialog
 *
 * @param modifier [Modifier]: Modifier
 * @param onDismissRequest [() -> Unit]: On dismiss request
 * @param confirmButton [() -> Unit]: Confirm button
 * @param dismissButton [(() -> Unit)?]: Dismiss button
 * @param icon [(() -> Unit)?]: Icon
 * @param title [(() -> Unit)?]: Title
 * @param text [(() -> Unit)?]: Text
 *
 * @since 1.0.0
 * @author Sebas1705 21/09/2025
 */
@Composable
fun IDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    confirmButton: @Composable () -> Unit = {},
    dismissButton: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
) = AlertDialog(
    onDismissRequest = onDismissRequest,
    confirmButton = confirmButton,
    modifier = modifier,
    dismissButton = dismissButton,
    icon = icon,
    title = title,
    text = text,
    containerColor = MaterialTheme.colorScheme.background,
    iconContentColor = MaterialTheme.colorScheme.secondary,
    titleContentColor = MaterialTheme.colorScheme.primary,
    textContentColor = MaterialTheme.colorScheme.onBackground,
    properties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
        usePlatformDefaultWidth = true
    )
)


@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    IDialog(
        modifier = Modifier,
        onDismissRequest = {},
        confirmButton = { ITextButton({}, "Confirm") },
        dismissButton = { ITextButton({}, "Dismiss") },
        icon = { Icon(Icons.Default.Info, contentDescription = null) },
        title = { IText("Hello, World!") },
        text = { IText("This is a dialog") },
    )
}

