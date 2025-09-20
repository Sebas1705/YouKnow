package es.sebas1705.home.profile


import android.media.SoundPool
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
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
        onChangePhoto = { photo ->
            homeState.userModel?.let { userModel ->
                profileViewModel.eventHandler(
                    ProfileIntent.ChangePhoto(
                        userModel.firebaseId,
                        photo
                    )
                )
            } ?: ctx.printTextInToast(ctx.getString(R.string.feature_home_user_not_logged))
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

