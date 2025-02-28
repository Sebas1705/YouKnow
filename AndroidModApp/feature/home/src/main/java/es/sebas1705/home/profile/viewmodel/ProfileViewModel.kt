package es.sebas1705.youknow.presentation.features.home.features.profile.viewmodel
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
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.auth.AuthUsesCases
import es.sebas1705.common.mvi.MVIBaseViewModel
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.common.utlis.extensions.primitives.isImageUrl
import es.sebas1705.user.UserUsesCases
import es.sebas1705.youknow.feature.home.R
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel fon [ProfileScreen] that will handle the logic of the screen.
 *
 * @param authUsesCases: UseCase to get the user's data
 * @param userUsesCases: UseCase to get the user's data
 * @param application: Application to get the context
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUsesCases: AuthUsesCases,
    private val userUsesCases: UserUsesCases,
    private val application: Application
) : MVIBaseViewModel<ProfileState, ProfileIntent>() {

    override fun initState(): ProfileState = ProfileState.default()

    override fun intentHandler(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.ChangePhoto -> changePhoto(intent)
            is ProfileIntent.ChangeNickname -> changeNickname(intent)
            is ProfileIntent.SendPasswordChanger -> sendPasswordChanger(intent)
            is ProfileIntent.SignOut -> signOut()
        }
    }

    //Actions:
    private fun changePhoto(
        intent: ProfileIntent.ChangePhoto
    ) = execute(Dispatchers.IO) {
        if (intent.urlPhoto.isImageUrl()) {
            userUsesCases.changePhotoToUser(
                intent.firebaseId,
                intent.urlPhoto,
                onLoading = { startLoading() },
                onEmptySuccess = { stopLoading() },
                onError = { stopAndError(it, application::printTextInToast) }
            )
        } else execute { application.printTextInToast(application.getString(R.string.feature_home_user_invalid_url)) }
    }

    private fun changeNickname(
        intent: ProfileIntent.ChangeNickname
    ) = execute(Dispatchers.IO) {
        userUsesCases.changeNicknameToUser(
            intent.firebaseId,
            intent.nickname,
            onLoading = { startLoading() },
            onEmptySuccess = { stopLoading() },
            onError = { stopAndError(it, application::printTextInToast) }
        )
    }

    private fun sendPasswordChanger(
        intent: ProfileIntent.SendPasswordChanger
    ) = execute(Dispatchers.IO) {
        authUsesCases.sendForgotPassword(
            intent.email,
            onLoading = { startLoading() },
            onEmptySuccess = { stopLoading() },
            onError = { stopAndError(it, application::printTextInToast) }
        )
    }

    private fun signOut() = execute(Dispatchers.IO) {
        authUsesCases.signOut(
            onSuccess = { firebaseId ->
                execute(Dispatchers.IO) {
                    stopLoading()
                    userUsesCases.removeUserListener()
                }
            },
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