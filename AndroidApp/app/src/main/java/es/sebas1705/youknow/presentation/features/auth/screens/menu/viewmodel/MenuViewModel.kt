package es.sebas1705.youknow.presentation.features.auth.screens.menu.viewmodel/*
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
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.domain.model.UserModel
import es.sebas1705.youknow.domain.usecases.user.AuthUsesCases
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for Menu Screen that will handle the authentication process.
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
class MenuViewModel @Inject constructor(
    private val authUsesCases: AuthUsesCases,
    private val userUsesCases: UserUsesCases,
    private val application: Application
) : MVIBaseViewModel<MenuState, MenuIntent>() {

    private val ctx = application.applicationContext

    override fun initState(): MenuState = MenuState.default()

    override fun intentHandler(intent: MenuIntent) {
        when (intent) {
            is MenuIntent.SignWithGoogle -> authWithGoogleAction(intent)
        }
    }

    //Actions:
    private fun authWithGoogleAction(
        intent: MenuIntent.SignWithGoogle
    ) = execute(Dispatchers.IO) {
        authUsesCases.signGoogle(ctx,
            onLoading = { startLoading() },
            onSuccess = { firebaseId ->
                execute(Dispatchers.IO) {
                    userUsesCases.containsUser(firebaseId, onSuccess = { wasLogged ->
                        if (wasLogged) execute(Dispatchers.IO) {
                            userUsesCases.setLoggedToUser(firebaseId, true, onEmptySuccess = {
                                stopLoading()
                                execute(action = intent.onSuccess)
                            }, onError = { stopAndError(it, ctx::printTextInToast) })
                        }
                        else execute(Dispatchers.IO) {
                            userUsesCases.saveUser(userModel = UserModel.newGoogleUser(authUsesCases.getFirebaseUser()!!),
                                onEmptySuccess = {
                                    stopLoading()
                                    execute(action = intent.onSuccess)
                                },
                                onError = { stopAndError(it, ctx::printTextInToast) })
                        }
                    }, onError = { stopAndError(it, ctx::printTextInToast) })
                }
            },
            onError = { stopAndError(it, ctx::printTextInToast) })
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
 * Data class that represents the state of the Menu Screen.
 *
 * @param isLoading [Boolean]: Loading state of the Menu Screen.
 *
 * @see MVIBaseState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class MenuState(
    val isLoading: Boolean
) : MVIBaseState {
    companion object {
        fun default() = MenuState(
            isLoading = false
        )
    }
}

/**
 * Sealed interface that represents the possible actions that can be performed in the Menu Screen.
 *
 * @see MVIBaseIntent
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface MenuIntent : MVIBaseIntent {

    /**
     * Intent associated with the authentication process with Google.
     *
     * @param onSuccess () -> Unit: Action to perform when the authentication is successful.
     *
     * @see MenuIntent
     */
    data class SignWithGoogle(
        val onSuccess: () -> Unit
    ) : MenuIntent

}