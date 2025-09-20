package es.sebas1705.auth.screens.menu.composables


import android.media.SoundPool
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.designsystem.buttons.common.IFilledTonalButton
import es.sebas1705.designsystem.buttons.common.IOutlinedButton
import es.sebas1705.designsystem.spacers.IVerSpacer
import es.sebas1705.feature.auth.R

/**
 * Composable that contains the buttons to navigate to the Sign Up, Google Log In and Email Log In screens.
 *
 * @param modifier [Modifier]: Modifier to apply to the buttons.
 * @param onSignButtonAction () -> Unit: Function to navigate to the Sign Up screen.
 * @param onEmailLogButtonAction () -> Unit: Function to navigate to the Email Log In screen.
 * @param onGoogleLogButtonAction () -> Unit: Function to navigate to the Google Log In screen.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun ColumnScope.MenuButtons(
    modifier: Modifier,
    soundPool: Pair<SoundPool, Float>?,
    onSignButtonAction: () -> Unit,
    onEmailLogButtonAction: () -> Unit,
    onGoogleLogButtonAction: () -> Unit,
) {
    IFilledTonalButton(
        modifier = modifier.height(48.dp),
        label = stringResource(id = R.string.feature_auth_sign_up),
        onClick = onSignButtonAction,
        soundPool = soundPool
    )
    IVerSpacer(height = SmallPadding)
    IOutlinedButton(
        modifier = modifier.height(48.dp),
        label = stringResource(id = R.string.feature_auth_google_log),
        imageResource = R.drawable.google,
        onClick = onGoogleLogButtonAction,
        soundPool = soundPool
    )
    IVerSpacer(height = SmallPadding)
    IOutlinedButton(
        modifier = modifier.height(48.dp),
        label = stringResource(id = R.string.feature_auth_email_log),
        imageVector = Icons.Default.Email,
        onClick = onEmailLogButtonAction,
        soundPool = soundPool
    )
}