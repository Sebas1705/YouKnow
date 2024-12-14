package es.sebas1705.youknow.presentation.features.auth.viewmodels/*
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
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.domain.model.UserModel
import es.sebas1705.youknow.domain.usecases.user.AuthUsesCases
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
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
    private val authUsesCases: AuthUsesCases,
    private val userUsesCases: UserUsesCases,
    private val application: Application
) : MVIBaseViewModel<AuthState, AuthIntent>() {

    private val context = application.applicationContext

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
        authUsesCases.signInEmailUser(intent.email,
            intent.password,
            onLoading = { startLoading() },
            onSuccess = { firebaseId ->
                execute(Dispatchers.IO) {
                    userUsesCases.getLoggedFromUser(firebaseId, onSuccess = { logged ->
                        if (logged) stopAndError(
                            context.getString(R.string.already_logged), intent.onError
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
                                context.getString(R.string.verify_email), intent.onError
                            )
                        }
                    }, onError = { stopAndError(it, intent.onError) })
                }
            },
            onError = { stopAndError(it, intent.onError) })
    }

    private fun signUpEmailAction(
        intent: AuthIntent.SignUpWithEmail
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


    private fun signOut(
        intent: AuthIntent.SignOut
    ) = execute(Dispatchers.IO) {
        authUsesCases.signOut(onSuccess = { firebaseId ->
            execute(Dispatchers.IO) {
                userUsesCases.setLoggedToUser(firebaseId,
                    false,
                    onLoading = { startLoading() },
                    onEmptySuccess = {
                        stopLoading()
                        userUsesCases.removeUserListener()
                        execute(action = intent.onSuccess)
                    },
                    onError = { stopAndError(it, intent.onError) })
            }
        }, onError = { stopAndError(it, intent.onError) })
    }

    private fun authWithGoogle(
        intent: AuthIntent.SignWithGoogle
    ) = execute(Dispatchers.IO) {
        authUsesCases.signGoogle(intent.context,
            onLoading = { startLoading() },
            onSuccess = { firebaseId ->
                Log.d("Google sign", "Firebase id: $firebaseId")
                execute(Dispatchers.IO) {
                    userUsesCases.containsUser(firebaseId, onSuccess = { wasLogged ->
                        Log.d("Google sign", "Was logged: $wasLogged")
                        if (wasLogged) execute(Dispatchers.IO) {
                            userUsesCases.setLoggedToUser(firebaseId, true, onEmptySuccess = {
                                Log.d("Google sign", "Logged")
                                stopLoading()
                                execute(action = intent.onSuccess)
                            }, onError = { stopAndError(it, intent.onError) })
                        }
                        else execute(Dispatchers.IO) {
                            userUsesCases.saveUser(userModel = UserModel.newGoogleUser(authUsesCases.getFirebaseUser()!!),
                                onEmptySuccess = {
                                    Log.d("Google sign", "Saved")
                                    stopLoading()
                                    execute(action = intent.onSuccess)
                                },
                                onError = { stopAndError(it, intent.onError) })
                        }
                    }, onError = { stopAndError(it, intent.onError) })
                }
            },
            onError = { stopAndError(it, intent.onError) })
    }

    private fun sendForgotPassword(
        intent: AuthIntent.SendForgotPassword
    ) = execute(Dispatchers.IO) {
        authUsesCases.sendForgotPassword(intent.email,
            onLoading = { startLoading() },
            onEmptySuccess = { stopLoading() },
            onError = { stopAndError(it, intent.onError) }
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
        val onSuccess: () -> Unit, val onError: (String) -> Unit
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
        val context: Context, val onSuccess: () -> Unit, val onError: (String) -> Unit
    ) : AuthIntent

    /**
     * Intent associated with the send forgot password process.
     *
     * @param email [String]: Email to send the forgot password.
     *
     * @see AuthIntent
     */
    data class SendForgotPassword(
        val email: String, val onError: (String) -> Unit
    ) : AuthIntent

}