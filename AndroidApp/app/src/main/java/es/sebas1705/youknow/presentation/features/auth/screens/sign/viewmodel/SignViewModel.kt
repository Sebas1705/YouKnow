package es.sebas1705.youknow.presentation.features.auth.screens.sign.viewmodel/*
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

import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.domain.usecases.user.AuthUsesCases
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthIntent
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for Sign Screen that will handle the authentication process.
 *
 * @param authUsesCases [AuthUsesCases]: Use cases to handle the authentication process.
 * @param userUsesCases [UserUsesCases]: Use cases to handle the user process.
 *
 * @see MVIBaseViewModel
 * @see HiltViewModel
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

/**
 * Data class that represents the state of the Sign Screen.
 *
 * @param isLoading [Boolean]: Loading state of the Sign Screen.
 *
 * @see MVIBaseState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class SignState(
    val isLoading: Boolean
) : MVIBaseState {
    companion object {
        fun default() = SignState(
            isLoading = false
        )
    }
}

/**
 * Sealed interface that represents the possible actions that can be performed in the Sign Screen.
 *
 * @see MVIBaseIntent
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface SignIntent : MVIBaseIntent {

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
    ) : SignIntent

}