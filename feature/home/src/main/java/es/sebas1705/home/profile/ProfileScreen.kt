package es.sebas1705.home.profile


import android.media.SoundPool
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.home.navigation.viewmodel.HomeState
import es.sebas1705.home.profile.design.ProfileDesign
import es.sebas1705.feature.home.R
import es.sebas1705.youknow.presentation.features.home.features.profile.viewmodel.ProfileIntent
import es.sebas1705.youknow.presentation.features.home.features.profile.viewmodel.ProfileViewModel

/**
 * Profile Screen that shows the user's data.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun ProfileScreen(
    windowState: WindowState,
    homeState: HomeState,
    soundPool: Pair<SoundPool, Float>,
    onAuthNav: () -> Unit,
) {
    //Locals:
    val ctx = LocalContext.current
    BackHandler {}

    //ViewModel:
    val profileViewModel: ProfileViewModel = hiltViewModel()

    //State:
    val profileState by profileViewModel.uiState.collectAsStateWithLifecycle()

    //Photo picker: the system Photo Picker, no storage permission needed.
    val photoPicker = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) homeState.userModel?.let { userModel ->
            profileViewModel.eventHandler(ProfileIntent.UploadPhoto(userModel.firebaseId, uri))
        } ?: ctx.printTextInToast(ctx.getString(R.string.feature_home_user_not_logged))
    }

    //Body:
    ProfileDesign(
        windowState,
        profileState,
        homeState,
        soundPool,
        onLogout = {
            profileViewModel.eventHandler(ProfileIntent.SignOut)
            onAuthNav()
        },
        onPickPhoto = {
            photoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        },
        onChangeNickname = {
            homeState.userModel?.let { userModel ->
                profileViewModel.eventHandler(
                    ProfileIntent.ChangeNickname(
                        userModel.firebaseId,
                        it
                    )
                )
            } ?: ctx.printTextInToast(ctx.getString(R.string.feature_home_user_not_logged))
        },
        onChangePassword = {
            homeState.userModel?.let { userModel ->
                profileViewModel.eventHandler(
                    ProfileIntent.SendPasswordChanger(
                        userModel.email,
                    )
                )
            } ?: ctx.printTextInToast(ctx.getString(R.string.feature_home_user_not_logged))
        }
    )
}

