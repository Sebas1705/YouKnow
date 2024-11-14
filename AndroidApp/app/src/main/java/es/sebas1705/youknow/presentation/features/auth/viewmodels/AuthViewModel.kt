package es.sebas1705.youknow.presentation.features.auth.viewmodels
/*
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
import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.core.classes.MVIBaseIntent
import es.sebas1705.youknow.core.classes.MVIBaseState
import es.sebas1705.youknow.core.classes.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.printTextInToast
import es.sebas1705.youknow.domain.usecases.social.UserUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for Auth Screen that will handle the authentication process.
 *
 * @param userUsesCases [UserUsesCases]: UseCase to handle the authentication process.
 *
 * @see MVIBaseViewModel
 * @see HiltViewModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userUsesCases: UserUsesCases,
    private val application: Application
) : MVIBaseViewModel<AuthState, AuthIntent>() {

    override fun initState(): AuthState = AuthState.default()

    override fun intentHandler(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.SignInWithEmail -> signInEmailAction(intent)
            is AuthIntent.SignUpWithEmail -> signUpEmailAction(intent)
            is AuthIntent.SignOut -> signOut(intent)
            is AuthIntent.SignWithGoogle -> authWithGoogle(intent)
            is AuthIntent.SendForgotPassword -> sendForgotPassword(intent)
        }
    }

    //Actions:
    private fun signInEmailAction(
        intent: AuthIntent.SignInWithEmail
    ) = execute(Dispatchers.IO) {
        userUsesCases.signInEmailUser(
            intent.email,
            intent.password,
            onLoading = { startLoading() },
            onSuccess = { firebaseId ->
                execute(Dispatchers.IO) {
                    if(userUsesCases.verifyJustLogged(firebaseId)){
                        stopLoading()
                        execute{ application.applicationContext.printTextInToast("You are already logged in other dispositive, please log out first from the other dispositive") }
                        return@execute
                    }
                    val user = userUsesCases.getFirebaseUser()
                    if(user != null && user.isEmailVerified){
                        userUsesCases.setLogged(firebaseId, true)
                        userUsesCases.getUser(firebaseId, true)
                        stopLoading()
                        execute(action = intent.onSuccess)
                    }else{
                        stopLoading()
                        execute{ application.applicationContext.printTextInToast("Verify your email") }
                    }

                }
            },
            onError = {
                stopLoading()
                execute { intent.onError(it) }
            }
        )
    }

    private fun signUpEmailAction(
        intent: AuthIntent.SignUpWithEmail
    ) = execute(Dispatchers.IO) {
        userUsesCases.signUpEmailUser(
            intent.nickname,
            intent.email,
            intent.password,
            onLoading = { startLoading() },
            onSuccess = { user ->
                execute(Dispatchers.IO) {
                    userUsesCases.saveUser(user)
                    stopLoading()
                    execute(action = intent.onSuccess)
                }
            },
            onError = {
                stopLoading()
                execute { intent.onError(it) }
            }
        )
    }


    private fun signOut(
        intent: AuthIntent.SignOut
    ) = execute(Dispatchers.IO) {
        userUsesCases.signOut(
            onSuccess = { firebaseId ->
                execute(Dispatchers.IO) {
                    userUsesCases.setLogged(firebaseId, false)
                }
                execute(action = intent.onSuccess)
            },
            onError = { execute { intent.onError(it) } }
        )
    }

    private fun authWithGoogle(
        intent: AuthIntent.SignWithGoogle
    ) = execute(Dispatchers.IO) {
        userUsesCases.signGoogle(
            intent.context,
            onLoading = { startLoading() },
            onSuccess = { firebaseId ->
                execute(Dispatchers.IO) {
                    val wasLogged = userUsesCases.verifyWasLogged(firebaseId)
                    if(wasLogged)
                        userUsesCases.setLogged(firebaseId, true)
                    else
                        userUsesCases.saveUser(userUsesCases.signGoogle.createNewGoogleUser())
                    stopLoading()
                    execute(action = intent.onSuccess)
                }
            },
            onError = {
                stopLoading()
                execute { intent.onError(it) }
            }
        )
    }

    private fun sendForgotPassword(
        intent: AuthIntent.SendForgotPassword
    ) = execute(Dispatchers.IO) {
        userUsesCases.sendForgotPassword(
            intent.email,
            onLoading = { startLoading() },
            onSuccess = { stopLoading() },
            onError = { stopLoading() }
        )
    }


    //Privates:
    private fun startLoading() {
        updateUi { it.copy(isLoading = true) }
    }

    private fun stopLoading() {
        updateUi { it.copy(isLoading = false) }
    }
}

/**
 * Data class that represents the state of the Auth Screen.
 *
 * @param isLoading [Boolean]: Loading state of the Auth Screen.
 *
 * @see MVIBaseState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class AuthState(
    val isLoading: Boolean
) : MVIBaseState {
    companion object {
        fun default() = AuthState(
            isLoading = false
        )
    }
}

/**
 * Sealed interface that represents the possible actions that can be performed in the Auth Screen.
 *
 * @see MVIBaseIntent
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface AuthIntent : MVIBaseIntent {

    /**
     * Intent associated with the sign in process with email.
     *
     * @param email [String]: Email to authenticate.
     * @param password [String]: Password to authenticate.
     * @param onSuccess () -> Unit: Action to perform when the authentication is successful.
     * @param onError (String) -> Unit: Action to perform when the authentication fails.
     *
     * @see AuthIntent
     */
    data class SignInWithEmail(
        val email: String,
        val password: String,
        val onSuccess: () -> Unit,
        val onError: (String) -> Unit
    ) : AuthIntent

    /**
     * Intent associated with the sign in process with email.
     *
     * @param email [String]: Email to authenticate.
     * @param password [String]: Password to authenticate.
     * @param nickname [String]: Nickname of the user.
     * @param onSuccess () -> Unit: Action to perform when the authentication is successful.
     * @param onError (String) -> Unit: Action to perform when the authentication fails.
     *
     * @see AuthIntent
     */
    data class SignUpWithEmail(
        val email: String,
        val password: String,
        val nickname: String,
        val onSuccess: () -> Unit,
        val onError: (String) -> Unit
    ) : AuthIntent

    /**
     * Intent associated with the sign out process.
     *
     * @param onSuccess () -> Unit: Action to perform when the sign out is successful.
     * @param onError (String) -> Unit: Action to perform when the sign out fails.
     *
     * @see AuthIntent
     */
    data class SignOut(
        val onSuccess: () -> Unit,
        val onError: (String) -> Unit
    ) : AuthIntent

    /**
     * Intent associated with the authentication process with Google.
     *
     * @param context [Context]: Context of the app.
     * @param onSuccess () -> Unit: Action to perform when the authentication is successful.
     * @param onError (String) -> Unit: Action to perform when the authentication fails.
     *
     * @see AuthIntent
     */
    data class SignWithGoogle(
        val context: Context,
        val onSuccess: () -> Unit,
        val onError: (String) -> Unit
    ) : AuthIntent

    /**
     * Intent associated with the send forgot password process.
     *
     * @param email [String]: Email to send the forgot password.
     *
     * @see AuthIntent
     */
    data class SendForgotPassword(
        val email: String
    ) : AuthIntent

}