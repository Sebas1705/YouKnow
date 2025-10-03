package es.sebas1705.youknow.presentation.features.home.features.profile.viewmodel


import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.common.classes.mvi.MVIBaseViewModel
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.common.utlis.extensions.primitives.isImageUrl
import es.sebas1705.user.UserUsesCases
import es.sebas1705.feature.home.R
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel fon [ProfileScreen] that will handle the logic of the screen.
 *
 * @param authUsesCases: UseCase to get the user's data
 * @param userUsesCases: UseCase to get the user's data
 * @param application: Application to get the context
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUsesCases: AuthUsesCases,
    private val userUsesCases: UserUsesCases,
    private val application: Application
) : MVIBaseViewModel<ProfileState, ProfileIntent>() {

    override fun initState(): ProfileState = ProfileState.default()

    override fun intentHandler(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.ChangePhoto -> changePhoto(intent)
            is ProfileIntent.ChangeNickname -> changeNickname(intent)
            is ProfileIntent.SendPasswordChanger -> sendPasswordChanger(intent)
            is ProfileIntent.SignOut -> signOut()
        }
    }

    //Actions:
    private fun changePhoto(
        intent: ProfileIntent.ChangePhoto
    ) = execute(Dispatchers.IO) {
        if (intent.urlPhoto.isImageUrl()) {
            userUsesCases.changePhotoToUser(
                intent.firebaseId,
                intent.urlPhoto,
                onLoading = { startLoading() },
                onEmptySuccess = { stopLoading() },
                onError = { stopAndError(it, application::printTextInToast) }
            )
        } else execute { application.printTextInToast(application.getString(R.string.feature_home_user_invalid_url)) }
    }

    private fun changeNickname(
        intent: ProfileIntent.ChangeNickname
    ) = execute(Dispatchers.IO) {
        userUsesCases.changeNicknameToUser(
            intent.firebaseId,
            intent.nickname,
            onLoading = { startLoading() },
            onEmptySuccess = { stopLoading() },
            onError = { stopAndError(it, application::printTextInToast) }
        )
    }

    private fun sendPasswordChanger(
        intent: ProfileIntent.SendPasswordChanger
    ) = execute(Dispatchers.IO) {
        authUsesCases.sendForgotPassword(
            intent.email,
            onLoading = { startLoading() },
            onEmptySuccess = { stopLoading() },
            onError = { stopAndError(it, application::printTextInToast) }
        )
    }

    private fun signOut() = execute(Dispatchers.IO) {
        authUsesCases.signOut(
            onSuccess = { firebaseId ->
                execute(Dispatchers.IO) {
                    stopLoading()
                    userUsesCases.removeUserListener()
                }
            },
            onError = { stopAndError(it, application::printTextInToast) }
        )
    }

    //Privates:
    private fun startLoading() {
        updateUi { it.copy(isLoading = true) }
    }

    private fun stopLoading() {
        updateUi { it.copy(isLoading = false) }
    }

    private fun stopAndError(error: String, onError: (String) -> Unit) {
        stopLoading()
        execute { onError(error) }
    }
}