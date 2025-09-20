package es.sebas1705.auth.screens.log.composable


import android.media.SoundPool
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.feature.auth.R

/**
 * Component that contains the forgot password and wanna register buttons
 *
 * @param soundPool [Pair<SoundPool, Float>]: sound pool to play sounds
 * @param onForgotButton () -> Unit: action to be executed when the forgot password button is clicked
 * @param onRegisterButton () -> Unit: action to be executed when the wanna register button is clicked
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun ForgotAndRegisterButtons(
    soundPool: Pair<SoundPool, Float>? = null,
    onForgotButton: () -> Unit,
    onRegisterButton: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ITextButton(
            label = stringResource(id = R.string.feature_auth_forgot_password),
            onClick = onForgotButton,
            soundPool = soundPool
        )
        VerticalDivider(
            modifier = Modifier.height(30.dp),
            color = MaterialTheme.colorScheme.primary
        )
        ITextButton(
            label = stringResource(id = R.string.feature_auth_wanna_register),
            onClick = onRegisterButton,
            soundPool = soundPool
        )
    }
}