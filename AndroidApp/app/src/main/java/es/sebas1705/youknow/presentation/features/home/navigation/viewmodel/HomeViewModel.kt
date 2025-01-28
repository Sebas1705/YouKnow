package es.sebas1705.youknow.presentation.features.home.navigation.viewmodel
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
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.domain.usecases.user.AuthUsesCases
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for HomeNav that will handle the logic of the screen.
 *
 * @param authUsesCases [AuthUsesCases]: UseCase to get the user's data.
 * @param userUsesCases [UserUsesCases]: UseCase to get the user's data.
 * @param application [Application]: Application context.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authUsesCases: AuthUsesCases,
    private val userUsesCases: UserUsesCases,
    private val application: Application
) : MVIBaseViewModel<HomeState, HomeIntent>() {

    override fun initState(): HomeState = HomeState.default()

    override fun intentHandler(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadActual -> loadActual()
            is HomeIntent.ClearActual -> updateUi { it.copy(userModel = null, firebaseUser = null) }
            is HomeIntent.AddCredits -> addCredits(intent)
            is HomeIntent.GetUser -> getUser(intent)
            is HomeIntent.SignOut -> signOut(intent)
        }
    }

    override fun onInit() {
        super.onInit()
        loadActual()
    }

    //Actions:
    private fun loadActual() = execute(Dispatchers.IO) {
        val firebaseUser = authUsesCases.getFirebaseUser()
        if (firebaseUser == null)
            stopAndError(
                application.getString(R.string.user_not_logged),
                application::printTextInToast
            )
        else {
            updateUi { it.copy(firebaseUser = firebaseUser) }
            userUsesCases.setUserListener(
                firebaseUser.uid,
                onDataChange = { userModel ->
                    updateUi { it.copy(userModel = userModel) }
                },
            )
        }
    }

    private fun clearActual() = execute(Dispatchers.IO) {
        userUsesCases.removeUserListener()
        updateUi { it.copy(userModel = null, firebaseUser = null) }
    }

    private fun addCredits(
        intent: HomeIntent.AddCredits
    ) = execute(Dispatchers.IO) {
        if (uiState.value.userModel == null)
            execute { application.printTextInToast(application.getString(R.string.user_not_logged)) }
        else userUsesCases.addCreditsToUser(
            uiState.value.userModel!!,
            intent.credits,
            onLoading = { startLoading() },
            onSuccess = { newCredits ->
                stopLoading()
                updateUi { it.copy(userModel = it.userModel?.copy(credits = newCredits)) }
            },
            onError = {
                stopAndError(it, application::printTextInToast)
            }
        )
    }

    private fun getUser(
        intent: HomeIntent.GetUser
    ) = execute(Dispatchers.IO) {
        if (_uiState.value.infoUsers.containsKey(intent.firebaseId).not()) {
            Log.d("HomeViewModel", "User not caught")
            userUsesCases.getUser(
                intent.firebaseId,
                onLoading = { startLoading() },
                onSuccess = { userModel ->
                    stopLoading()
                    updateUi {
                        val infoUsers = it.infoUsers.toMutableMap()
                        infoUsers[userModel.firebaseId] = userModel
                        it.copy(infoUsers = infoUsers)
                    }
                },
                onError = { error ->
                    stopAndError(error, application::printTextInToast)
                }
            )
        }
    }

    private fun signOut(
        intent: HomeIntent.SignOut
    ) = execute(Dispatchers.IO) {
        authUsesCases.signOut(onSuccess = { firebaseId ->
            execute(Dispatchers.IO) {
                userUsesCases.setLoggedToUser(firebaseId,
                    false,
                    onLoading = { startLoading() },
                    onEmptySuccess = {
                        stopLoading()
                        userUsesCases.removeUserListener()
                    },
                    onError = { stopAndError(it, application::printTextInToast) })
            }
        }, onError = { stopAndError(it, application::printTextInToast) })
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