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

import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.core.classes.MVIBaseIntent
import es.sebas1705.youknow.core.classes.MVIBaseState
import es.sebas1705.youknow.core.classes.MVIBaseViewModel
import es.sebas1705.youknow.domain.usecases.AuthenticationUsesCases
import javax.inject.Inject

/**
 * ViewModel for [es.sebas1705.youknow.presentation.features.home.navigation.HomeNav] that will handle the user's data and the navigation to other screens.
 *
 * @param authenticationUsesCases [AuthenticationUsesCases]: UseCase to get the current user.
 *
 * @see MVIBaseViewModel
 * @see HiltViewModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authenticationUsesCases: AuthenticationUsesCases
) : MVIBaseViewModel<HomeState, HomeIntent>() {

    override fun initState(): HomeState = HomeState.default()

    override fun intentHandler(intent: HomeIntent) {
        when(intent){
            else -> {}
        }
    }

    override fun onViewModelInit() {
        authenticationUsesCases.getCurrentUser().let { user ->
            updateUi { it.copy(user = user) }
        }
    }
}

/**
 * State for [HomeViewModel] that will handle the user's data.
 *
 * @property user [FirebaseUser]: The current user.
 *
 * @see MVIBaseState
 * @see FirebaseUser
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class HomeState(
    val user: FirebaseUser?
) : MVIBaseState {
    companion object {

        /**
         * Default [HomeState] with no user.
         *
         * @return [HomeState]
         */
        fun default() = HomeState(
            user = null
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
sealed interface HomeIntent : MVIBaseIntent