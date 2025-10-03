package es.sebas1705.designsystem.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
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
 * Composable that shows an AlertDialog with an error message.
 *
 * @param modifier [Modifier]: Modifier to be applied to the composable.
 * @param errorText [String]: Error message to be shown.
 * @param onConfirm () -> Unit: Function to be executed when the confirm button is clicked.
 *
 * @see AlertDialog
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun ErrorInfoDialog(
    modifier: Modifier = Modifier,
    errorText: String,
    onConfirm: () -> Unit = {},
) = IDialog(
    confirmButton = {
        ITextButton(
            label = stringResource(R.string.core_designsystem_confirm),
            onClick = onConfirm,
        )
    },
    modifier = modifier,
    title = {
        IText(stringResource(R.string.core_designsystem_title_error))
    },
    text = {
        IText(errorText)
    }
)

/**
 * Preview for [ErrorInfoDialog]
 *
 * @see ErrorInfoDialog
 */
@UiModePreviews
@Composable
private fun Preview() = AppTheme {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ErrorInfoDialog(errorText = "Error")
    }
}
