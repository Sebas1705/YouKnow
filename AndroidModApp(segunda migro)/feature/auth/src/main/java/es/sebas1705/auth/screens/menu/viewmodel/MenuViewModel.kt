package es.sebas1705.auth.screens.menu.viewmodel

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.auth.AuthUsesCases
import es.sebas1705.common.classes.mvi.MVIBaseViewModel
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.models.social.UserModel
import es.sebas1705.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val authUsesCases: AuthUsesCases,
    private val userUsesCases: UserUsesCases,
    private val application: Application
) : MVIBaseViewModel<MenuState, MenuIntent>() {

    override fun initState(): MenuState = MenuState.default()

    override fun intentHandler(intent: MenuIntent) {
        when (intent) {
            is MenuIntent.SignWithGoogle -> authWithGoogleAction(intent)
        }
    }

    private fun authWithGoogleAction(intent: MenuIntent.SignWithGoogle) = execute(Dispatchers.IO) {
        authUsesCases.signGoogle(
            onLoading = { startLoading() },
            onSuccess = { firebaseId ->
                execute(Dispatchers.IO) {
                    userUsesCases.containsUser(firebaseId, onSuccess = { wasLogged ->
                        if (!wasLogged) execute(Dispatchers.IO) {
                            userUsesCases.saveUser(
                                userModel = UserModel.newGoogleUser(authUsesCases.getFirebaseUser()!!),
                                onEmptySuccess = { stopLoading(); execute(action = intent.onSuccess) },
                                onError = { stopAndError(it, application::printTextInToast) })
                        } else {
                            stopLoading()
                            execute(action = intent.onSuccess)
                        }
                    }, onError = { stopAndError(it, application::printTextInToast) })
                }
            },
            onError = { stopAndError(it, application::printTextInToast) })
    }

    private fun startLoading() { updateUi { it.copy(isLoading = true) } }
    private fun stopLoading() { updateUi { it.copy(isLoading = false) } }
    private fun stopAndError(error: String, onError: (String) -> Unit) {
        stopLoading(); execute { onError(error) }
    }
}
