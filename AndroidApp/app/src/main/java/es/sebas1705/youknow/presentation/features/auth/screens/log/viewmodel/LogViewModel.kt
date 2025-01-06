package es.sebas1705.youknow.presentation.features.auth.screens.log.viewmodel/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.domain.usecases.user.AuthUsesCases
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
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
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class LogViewModel @Inject constructor(
    private val authUsesCases: AuthUsesCases,
    private val userUsesCases: UserUsesCases,
    private val application: Application
) : MVIBaseViewModel<LogState, LogIntent>() {

    private val ctx = application.applicationContext

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
            onSuccess = { firebaseId ->
                execute(Dispatchers.IO) {
                    userUsesCases.getLoggedFromUser(firebaseId, onSuccess = { logged ->
                        if (logged) stopAndError(
                            ctx.getString(R.string.already_logged), intent.onError
                        )
                        else {
                            val user = authUsesCases.getFirebaseUser()
                            if (user != null && user.isEmailVerified) {
                                execute(Dispatchers.IO) {
                                    userUsesCases.setLoggedToUser(
                                        firebaseId,
                                        true,
                                        onEmptySuccess = {
                                            stopLoading()
                                            execute(action = intent.onSuccess)
                                        },
                                        onError = { stopAndError(it, intent.onError) })
                                }
                            } else stopAndError(
                                ctx.getString(R.string.verify_email), intent.onError
                            )
                        }
                    }, onError = { stopAndError(it, intent.onError) })
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
 * Data class that represents the state of the Log Screen.
 *
 * @param isLoading [Boolean]: Loading state of the Log Screen.
 *
 * @see MVIBaseState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class LogState(
    val isLoading: Boolean
) : MVIBaseState {
    companion object {
        fun default() = LogState(
            isLoading = false
        )
    }
}

/**
 * Sealed interface that represents the possible actions that can be performed in the Log Screen.
 *
 * @see MVIBaseIntent
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface LogIntent : MVIBaseIntent {

    /**
     * Intent associated with the sign in process with email.
     *
     * @param email [String]: Email to authenticate.
     * @param password [String]: Password to authenticate.
     * @param onSuccess () -> Unit: Action to perform when the authentication is successful.
     * @param onError (String) -> Unit: Action to perform when the authentication fails.
     *
     * @see LogIntent
     */
    data class SignInWithEmail(
        val email: String,
        val password: String,
        val onSuccess: () -> Unit,
        val onError: (String) -> Unit
    ) : LogIntent

    /**
     * Intent associated with the send forgot password process.
     *
     * @param email [String]: Email to send the forgot password.
     *
     * @see LogIntent
     */
    data class SendForgotPassword(
        val email: String
    ) : LogIntent

}