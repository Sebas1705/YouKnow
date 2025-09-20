package es.sebas1705.auth.screens.log.viewmodel


import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.auth.AuthUsesCases
import es.sebas1705.common.mvi.MVIBaseViewModel
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.user.UserUsesCases
import es.sebas1705.feature.auth.R
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for Log Screen that will handle the authentication process.
 *
 * @param authUsesCases [AuthUsesCases]: UseCase to handle the authentication process.
 * @param userUsesCases [UserUsesCases]: UseCase to handle the user process.
 * @param application [Application]: Application context.
 *
 * @see MVIBaseViewModel
 * @see HiltViewModel
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@HiltViewModel
class LogViewModel @Inject constructor(
    private val authUsesCases: AuthUsesCases,
    private val userUsesCases: UserUsesCases,
    private val application: Application
) : MVIBaseViewModel<LogState, LogIntent>() {

    override fun initState(): LogState = LogState.default()

    override fun intentHandler(intent: LogIntent) {
        when (intent) {
            is LogIntent.SignInWithEmail -> signInEmailAction(intent)
            is LogIntent.SendForgotPassword -> sendForgotPassword(intent)
        }
    }

    //Actions:
    private fun signInEmailAction(
        intent: LogIntent.SignInWithEmail
    ) = execute(Dispatchers.IO) {
        authUsesCases.signInEmailUser(
            intent.email,
            intent.password,
            onLoading = { startLoading() },
            onSuccess = {
                execute(Dispatchers.IO) {
                    val user = authUsesCases.getFirebaseUser()
                    if (user != null && user.isEmailVerified) {
                        stopLoading()
                        execute(action = intent.onSuccess)
                    } else stopAndError(
                        application.getString(R.string.feature_auth_verify_email), intent.onError
                    )
                }
            },
            onError = { stopAndError(it, intent.onError) })
    }

    private fun sendForgotPassword(
        intent: LogIntent.SendForgotPassword
    ) = execute(Dispatchers.IO) {
        authUsesCases.sendForgotPassword(intent.email,
            onLoading = { startLoading() },
            onEmptySuccess = { stopLoading() },
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



