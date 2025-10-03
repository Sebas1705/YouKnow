package es.sebas1705.designsystem.dialogs

import android.media.SoundPool
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import es.sebas1705.core.designsystem.R
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.designsystem.cards.IResumeCard
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.designsystem.texts.Title
import es.sebas1705.models.social.UserModel

/**
 * User info dialog
 *
 * @param windowState [WindowState]: Window state
 * @param userModel [UserModel]: User model
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume
 * @param onDismiss [Function0<Unit>]: On dismiss
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun UserInfoDialog(
    windowState: WindowState = WindowState.default(),
    userModel: UserModel = UserModel.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onDismiss: () -> Unit = {}
) {
    IDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.fillMaxWidth(
            windowState.widthFilter(0.95f, 0.75f, 0.55f)
        ),
        dismissButton = {
            ITextButton(
                onClick = onDismiss,
                label = stringResource(R.string.core_designsystem_dismiss),
            )
        },
        icon = {
            val size = windowState.sizeFilter(100, 150, 200).dp
            if (userModel.photoUrl != null) AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userModel.photoUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(es.sebas1705.core.resources.R.drawable.sign_user),
                contentDescription = stringResource(R.string.core_designsystem_profile),
                modifier = Modifier.size(size)
            )
            else Image(
                painter = painterResource(es.sebas1705.core.resources.R.drawable.sign_user),
                contentDescription = stringResource(R.string.core_designsystem_profile),
                modifier = Modifier.size(size)
            )
        },
        title = {
            Title(
                text = userModel.nickName,
                style = windowState.widthFilter(
                    MaterialTheme.typography.titleLarge,
                    MaterialTheme.typography.headlineMedium,
                    MaterialTheme.typography.displaySmall
                )
            )
        },
        text = {
            IResumeCard(
                title = userModel.nickName,
                titlesValues = mapOf(
                    stringResource(es.sebas1705.core.resources.R.string.core_resources_points) to userModel.points.toString(),
                    stringResource(es.sebas1705.core.resources.R.string.core_resources_credits) to userModel.credits.toString(),
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    )
}

@UiModePreviews
@Composable
private fun Preview() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            UserInfoDialog()
        }
    }
}