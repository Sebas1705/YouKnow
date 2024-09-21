package es.sebas1705.youknowapp.presentation.screens.home.windows

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.common.Previews
import es.sebas1705.youknowapp.ui.theme.YouKnowTheme

@Composable
fun LogoutWindow(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { },
        title = {
            Text(
                text = stringResource(R.string.alertTitle),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        },
        confirmButton = {
            Button(onClick = onLogout) {
                Text(text = stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text(text = stringResource(R.string.dismiss))
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true
        )
    )
}
@Previews
@Composable
private fun LogoutWindowPreview() {
    YouKnowTheme{
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LogoutWindow()
        }
    }
}
