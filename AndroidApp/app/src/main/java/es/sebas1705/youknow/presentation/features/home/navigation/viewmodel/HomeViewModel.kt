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

import android.annotation.SuppressLint
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
 * ViewModel for [HomeNav] that will handle the logic of the screen.
 *
 * @param authUsesCases [AuthUsesCases]: UseCase to get the user's data.
 * @param userUsesCases [UserUsesCases]: UseCase to get the user's data.
 * @param application [Application]: Application context.
 *
 * @see MVIBaseViewModel
 * @see HiltViewModel
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

    @SuppressLint("StaticFieldLeak")
    private val ctx: Context = application.applicationContext

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
            stopAndError(ctx.getString(R.string.user_not_logged), ctx::printTextInToast)
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
            execute { ctx.printTextInToast(ctx.getString(R.string.user_not_logged)) }
        else userUsesCases.addCreditsToUser(
            uiState.value.userModel!!,
            intent.credits,
            onLoading = { startLoading() },
            onSuccess = { newCredits ->
                stopLoading()
                updateUi { it.copy(userModel = it.userModel?.copy(credits = newCredits)) }
            },
            onError = {
                stopAndError(it, ctx::printTextInToast)
            }
        )
    }

    private fun getUser(
        intent: HomeIntent.GetUser
    ) = execute(Dispatchers.IO) {
        userUsesCases.getUser(
            intent.userId,
            onLoading = { startLoading() },
            onSuccess = { userModel ->
                stopLoading()
                updateUi { it.copy(infoUser = userModel) }
            },
            onError = { error ->
                stopAndError(error, ctx::printTextInToast)
            }
        )
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
                    onError = { stopAndError(it, ctx::printTextInToast) })
            }
        }, onError = { stopAndError(it, ctx::printTextInToast) })
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
 * State for [HomeViewModel] that will handle the user's data.
 *
 * @property isLoading [Boolean]: If the screen is loading.
 * @property userModel [UserModel]: User's data.
 * @property firebaseUser [FirebaseUser]: Firebase user's data.
 * @property infoUser [UserModel]: User's data.
 *
 * @see MVIBaseState
 * @see UserModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class HomeState(
    val isLoading: Boolean,
    val userModel: UserModel?,
    val firebaseUser: FirebaseUser?,
    val infoUser: UserModel?
) : MVIBaseState {
    companion object {

        /**
         * Default [HomeState] with no user.
         *
         * @return [HomeState]
         */
        fun default() = HomeState(
            isLoading = false,
            userModel = null,
            firebaseUser = null,
            infoUser = null
        )

        /**
         * Default [HomeState] with user.
         *
         * @return [HomeState]
         */
        fun defaultWithUser() = HomeState(
            isLoading = false,
            userModel = UserModel.default(),
            firebaseUser = null,
            infoUser = null
        )
    }
}

/**
 * Intent for [HomeViewModel].
 *
 * @see MVIBaseIntent
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface HomeIntent : MVIBaseIntent {

    object LoadActual : HomeIntent

    object ClearActual : HomeIntent

    data class AddCredits(
        val credits: Int
    ) : HomeIntent

    data class GetUser(
        val userId: String
    ) : HomeIntent

    data object SignOut : HomeIntent
}