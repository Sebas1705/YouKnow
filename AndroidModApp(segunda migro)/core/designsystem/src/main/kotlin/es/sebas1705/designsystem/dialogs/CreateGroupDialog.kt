package es.sebas1705.designsystem.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.core.designsystem.R
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.designsystem.textfields.IOutlinedTextField

/**
 * Create group dialog
 *
 * @param modifier [Modifier]: Modifier
 * @param onConfirm [Function2<String, String, Unit>]: On confirm
 * @param onDismiss [Function0<Unit>]: On dismiss
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun CreateGroupDialog(
    modifier: Modifier = Modifier,
    onConfirm: (String, String) -> Unit = { _, _ -> },
    onDismiss: () -> Unit = {}
) {
    val context = LocalContext.current
    var groupName by remember { mutableStateOf("") }
    var groupDescription by remember { mutableStateOf("") }

    IDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            ITextButton(
                onClick = {
                    if (groupName.isNotEmpty() && groupDescription.isNotEmpty())
                        onConfirm(groupName, groupDescription)
                    else context.printTextInToast("Please fill all the fields")
                },
                label = "${stringResource(R.string.core_designsystem_confirm)} (2000 ${stringResource(
                    es.sebas1705.core.resources.R.string.core_resources_credits)})",
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
            IText(stringResource(R.string.core_designsystem_titleCreateGroup))
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                IOutlinedTextField(
                    value = groupName,
                    onValueChange = { groupName = it },
                    label = stringResource(R.string.core_designsystem_groupName),
                    placeholder = stringResource(R.string.core_designsystem_groupName),
                    modifier = Modifier.fillMaxWidth(),
                )
                IOutlinedTextField(
                    value = groupDescription,
                    onValueChange = { groupDescription = it },
                    label = stringResource(R.string.core_designsystem_groupDescription),
                    placeholder = stringResource(R.string.core_designsystem_groupDescription),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    )
}

@UiModePreviews
@Composable
private fun CreateGroupPreview() = AppTheme {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CreateGroupDialog()
    }
}

