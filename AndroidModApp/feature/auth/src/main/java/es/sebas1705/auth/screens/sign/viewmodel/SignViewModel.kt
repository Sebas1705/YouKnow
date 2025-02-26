package es.sebas1705.youknow.presentation.features.auth.screens.sign.viewmodel


import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.domain.usecases.user.AuthUsesCases
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for Sign Screen that will handle the authentication process.
 *
 * @param authUsesCases [AuthUsesCases]: Use cases to handle the authentication process.
 * @param userUsesCases [UserUsesCases]: Use cases to handle the user process.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class SignViewModel @Inject constructor(
    private val authUsesCases: AuthUsesCases,
    private val userUsesCases: UserUsesCases
) : MVIBaseViewModel<SignState, SignIntent>() {

    override fun initState(): SignState = SignState.default()

    override fun intentHandler(intent: SignIntent) {
        when (intent) {
            is SignIntent.SignUpWithEmail -> signUpEmailAction(intent)
        }
    }

    //Actions:
    private fun signUpEmailAction(
        intent: SignIntent.SignUpWithEmail
    ) = execute(Dispatchers.IO) {
        authUsesCases.signUpEmailUser(intent.nickname,
            intent.email,
            intent.password,
            onLoading = { startLoading() },
            onSuccess = { user ->
                execute(Dispatchers.IO) {
                    userUsesCases.saveUser(userModel = user,
                        onLoading = { startLoading() },
                        onEmptySuccess = {
                            stopLoading()
                            execute(action = intent.onSuccess)
                        },
                        onError = { stopAndError(it, intent.onError) })
                }
            },
            onError = { stopAndError(it, intent.onError) })
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

