package es.sebas1705.home.profile.design


import android.media.SoundPool
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.fab.IFAB
import es.sebas1705.designsystem.dialogs.LogoutDialog
import es.sebas1705.designsystem.dialogs.NickDialog
import es.sebas1705.designsystem.dialogs.ResetPasswordDialog
import es.sebas1705.designsystem.dialogs.UrlRequestDialog
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.home.navigation.viewmodel.HomeState
import es.sebas1705.home.profile.composables.LazyProfileItem
import es.sebas1705.models.social.UserModel
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.designsystem.dialogs.LoadingDialog
import es.sebas1705.youknow.presentation.features.home.features.profile.viewmodel.ProfileState

/**
 * Design of the Profile Screen. It shows the user's data.
 *
 * @param windowState [WindowState]: State of the window.
 * @param profileState [ProfileState]: State of the Profile Screen.
 * @param homeState [HomeState]: State of the Home Screen.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onLogout [Function0<Unit>]: Function that will be called when the user wants to logout.
 * @param onChangePhoto [(String) -> Unit]: Function that will be called when the user wants to change the photo.
 * @param onChangeNickname [(String) -> Unit]: Function that will be called when the user wants to change the nickname.
 * @param onChangePassword [Function0<Unit>]: Function that will be called when the user wants to change the password.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun ProfileDesign(
    windowState: WindowState = WindowState.default(),
    profileState: ProfileState = ProfileState.default(),
    homeState: HomeState = HomeState.defaultWithUser(),
    soundPool: Pair<SoundPool, Float>? = null,
    onLogout: () -> Unit = {},
    onChangePhoto: (String) -> Unit = {},
    onChangeNickname: (String) -> Unit = {},
    onChangePassword: () -> Unit = {}
) {
    //State:
    var nickname by remember { mutableStateOf(homeState.userModel?.nickName ?: "") }

    //Flag:
    var changePhotoDialog by remember { mutableStateOf(false) }
    var changeNicknameDialog by remember { mutableStateOf(false) }
    var changePassDialog by remember { mutableStateOf(false) }
    var signOutDialog by remember { mutableStateOf(false) }

    //Body:
    ApplyBack(
        backId = windowState.backFill,
        modifier = Modifier.fillMaxSize()
    ) {
        if (profileState.isLoading)
            LoadingDialog(windowState)
        else if (signOutDialog)
            LogoutDialog(
                windowState,
                onConfirm = {
                    signOutDialog = false
                    onLogout()
                },
                onDismiss = {
                    signOutDialog = false
                },
                soundPool = soundPool
            )
        else if (changePhotoDialog) UrlRequestDialog(
            windowState = windowState,
            onConfirmButton = {
                changePhotoDialog = false
                onChangePhoto(it)
            },
            onDismissAction = { changePhotoDialog = false },
            soundPool = soundPool
        )
        else if (changeNicknameDialog) NickDialog(
            nickname = nickname,
            firebaseId = homeState.userModel?.firebaseId ?: "",
            onConfirm = {
                changeNicknameDialog = false
                onChangeNickname(it)
            },
            onDismiss = { changeNicknameDialog = false },
            soundPool = soundPool
        )
        else if (changePassDialog)
            ResetPasswordDialog(
                email = homeState.userModel?.email ?: "",
                soundPool = soundPool,
                windowState,
                onConfirm = {
                    changePassDialog = false
                    onChangePassword()
                },
                onDismiss = {
                    changePassDialog = false
                }
            )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                LazyProfileItem(
                    windowState = windowState,
                    userModel = homeState.userModel ?: UserModel.default(),
                    soundPool = soundPool,
                    nickname = nickname,
                    onChangeNickname = { nickname = it },
                    onChangeNicknameDialog = { changeNicknameDialog = true },
                    onChangePhotoDialog = { changePhotoDialog = true },
                    onChangePassDialog = { changePassDialog = true }
                )
            }
        }

        IFAB(
            onClick = { signOutDialog = true },
            contentDescription = stringResource(es.sebas1705.core.designsystem.R.string.core_designsystem_logout),
            imageVector = Icons.AutoMirrored.Filled.Logout,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = MediumPadding, bottom = MediumPadding),
            soundPool = soundPool
        )
    }
}

/**
 * Preview of the [ProfileDesign] composable.
 *
 * @see [ProfileDesign]
 */
@UiModePreviews
@Composable
private fun ProfilePreview() {
    AppTheme {
        ProfileDesign()
    }
}