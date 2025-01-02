package es.sebas1705.youknow.presentation.features.home.viewmodels
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
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.R
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
 * ViewModel for [es.sebas1705.youknow.presentation.features.app.navigation.HomeNavigation] that will handle the logic of the screen.
 *
 * @param userUsesCases [UserUsesCases]: UseCase to get the user's data.
 *
 * @see MVIBaseViewModel
 * @see HiltViewModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class UserViewModel @Inject constructor(
    private val authUsesCases: AuthUsesCases,
    private val userUsesCases: UserUsesCases,
    private val application: Application
) : MVIBaseViewModel<UserState, UserIntent>() {

    private val context: Context = application.applicationContext

    override fun initState(): UserState = UserState.default()

    override fun intentHandler(intent: UserIntent) {
        when (intent) {
            is UserIntent.LoadActual -> loadActual()
            is UserIntent.ClearActual -> updateUi { it.copy(userModel = null, firebaseUser = null) }
            is UserIntent.AddCredits -> addCredits(intent)
            is UserIntent.GetUser -> getUser(intent)
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
            stopAndError(context.getString(R.string.user_not_logged), context::printTextInToast)
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
        intent: UserIntent.AddCredits
    ) = execute(Dispatchers.IO) {
        if (uiState.value.userModel == null)
            execute { context.printTextInToast(context.getString(R.string.user_not_logged)) }
        else userUsesCases.addCreditsToUser(
            uiState.value.userModel!!,
            intent.credits,
            onLoading = { startLoading() },
            onSuccess = { newCredits ->
                stopLoading()
                updateUi { it.copy(userModel = it.userModel?.copy(credits = newCredits)) }
            },
            onError = {
                stopAndError(it, context::printTextInToast)
            }
        )
    }

    private fun getUser(
        intent: UserIntent.GetUser
    ) = execute(Dispatchers.IO) {
        userUsesCases.getUser(
            intent.userId,
            onLoading = { startLoading() },
            onSuccess = { userModel ->
                stopLoading()
                updateUi { it.copy(infoUser = userModel) }
            },
            onError = { error ->
                stopAndError(error, context::printTextInToast)
            }
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
 * State for [UserViewModel] that will handle the user's data.
 *
 * @property userModel [UserModel]: User's data.
 * @property firebaseUser [FirebaseUser]: Firebase's user data.
 *
 * @see MVIBaseState
 * @see FirebaseUser
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class UserState(
    val isLoading: Boolean,
    val userModel: UserModel?,
    val firebaseUser: FirebaseUser?,
    val infoUser: UserModel?
) : MVIBaseState {
    companion object {

        /**
         * Default [UserState] with no user.
         *
         * @return [UserState]
         */
        fun default() = UserState(
            isLoading = false,
            userModel = null,
            firebaseUser = null,
            infoUser = null
        )
    }
}

/**
 * Intent for [UserViewModel].
 *
 * @see MVIBaseIntent
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface UserIntent : MVIBaseIntent {

    object LoadActual : UserIntent

    object ClearActual : UserIntent

    data class AddCredits(
        val credits: Int
    ) : UserIntent

    data class GetUser(
        val userId: String
    ) : UserIntent
}