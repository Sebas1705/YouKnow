package es.sebas1705.youknow.presentation.features.home.viewmodels

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.core.utlis.extensions.primitives.isImageUrl
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel fon [es.sebas1705.youknow.presentation.features.home.navigation.ProfileScreen] that will handle the logic of the screen.
 *
 * @param application: Application to get the context
 *
 * @see MVIBaseViewModel
 * @see HiltViewModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userUsesCases: UserUsesCases,
    private val application: Application
) : MVIBaseViewModel<ProfileState, ProfileIntent>() {

    private val ctx = application.applicationContext
    override fun initState(): ProfileState = ProfileState.default()

    override fun intentHandler(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.ChangePhoto -> changePhoto(intent)
            is ProfileIntent.ChangeNickname -> changeNickname(intent)
        }
    }

    //Actions:
    private fun changePhoto(intent: ProfileIntent.ChangePhoto) = execute(Dispatchers.IO) {
        if (intent.urlPhoto.isImageUrl()) {
            userUsesCases.changePhotoToUser(
                intent.firebaseId,
                intent.urlPhoto,
                onLoading = { startLoading() },
                onEmptySuccess = { stopLoading() },
                onError = { stopAndError(it, ctx::printTextInToast) }
            )
        } else execute { ctx.printTextInToast(ctx.getString(R.string.invalid_url)) }
    }

    private fun changeNickname(intent: ProfileIntent.ChangeNickname) = execute(Dispatchers.IO) {
        userUsesCases.changeNicknameToUser(
            intent.firebaseId,
            intent.nickname,
            onLoading = { startLoading() },
            onEmptySuccess = { stopLoading() },
            onError = { stopAndError(it, ctx::printTextInToast) }
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

/**
 * State of the [ProfileViewModel] that will handle the UI changes
 *
 * @param isLoading: Boolean to show a loading spinner
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class ProfileState(
    val isLoading: Boolean
) : MVIBaseState {
    companion object {

        fun default() = ProfileState(
            isLoading = false
        )
    }
}

/**
 * Intents of the [ProfileViewModel] that will handle the actions of the screen
 *
 * @param ChangePhoto: Intent to change the user's photo
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
sealed interface ProfileIntent : MVIBaseIntent {

    /**
     * Intent to change the user's photo
     *
     * @param firebaseId: String with the user's firebase id
     * @param urlPhoto: String with the url of the new photo
     */
    data class ChangePhoto(
        val firebaseId: String,
        val urlPhoto: String
    ) : ProfileIntent

    /**
     * Intent to change the user's nickname
     *
     * @param firebaseId: String with the user's firebase id
     * @param nickname: String with the new nickname
     */
    data class ChangeNickname(
        val firebaseId: String,
        val nickname: String
    ) : ProfileIntent
}