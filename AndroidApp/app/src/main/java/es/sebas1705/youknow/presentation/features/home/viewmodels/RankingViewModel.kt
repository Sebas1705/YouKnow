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
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.domain.model.UserModel
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel that will handle the ranking screen.
 *
 * @see MVIBaseViewModel
 * @see HiltViewModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class RankingViewModel @Inject constructor(
    private val userUsesCases: UserUsesCases,
    private val application: Application
) : MVIBaseViewModel<RankingState, RankingIntent>() {

    override fun initState(): RankingState = RankingState.default()

    override fun intentHandler(intent: RankingIntent) {
        when (intent) {
            is RankingIntent.GetRanking -> getRanking()
        }
    }

    //Actions:
    private fun getRanking() = execute(Dispatchers.IO) {
        userUsesCases.getUserRanking(
            onLoading = { startLoading() },
            onSuccess = { ranking ->
                stopLoading()
                updateUi { it.copy(ranking = ranking) }
            },
            onError = { error ->
                stopAndError(error, application::printTextInToast)
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
 * State of the [RankingViewModel] that will handle the data of the screen.
 *
 * @param isLoading [Boolean]: Flag to indicate if the screen is loading.
 *
 * @see MVIBaseState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class RankingState(
    val isLoading: Boolean,
    val ranking: List<UserModel>
) : MVIBaseState {
    companion object {

        /**
         * Default state of the [RankingState].
         *
         * @return [RankingState]: Default state.
         */
        fun default() = RankingState(
            isLoading = false,
            ranking = emptyList()
        )
    }
}

/**
 * Sealed interface that represents the possible actions of the [es.sebas1705.youknow.presentation.features.home.features.chat.viewmodel.ChatViewModel].
 *
 * @property SendMessage [es.sebas1705.youknow.presentation.features.home.features.chat.viewmodel.ChatIntent]: Action to send a message to the global chat.
 * @property CreateGroup [es.sebas1705.youknow.presentation.features.home.features.chat.viewmodel.ChatIntent]: Action to create a group.
 * @property JoinGroup [es.sebas1705.youknow.presentation.features.home.features.chat.viewmodel.ChatIntent]: Action to join a group.
 * @property LoadSocial [es.sebas1705.youknow.presentation.features.home.features.chat.viewmodel.ChatIntent]: Action to load the social data.
 * @property ClearSocial [es.sebas1705.youknow.presentation.features.home.features.chat.viewmodel.ChatIntent]: Action to clear the social data.
 *
 * @see MVIBaseIntent
 * @see es.sebas1705.youknow.presentation.features.home.features.chat.viewmodel.ChatViewModel
 * @see SendMessage
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface RankingIntent : MVIBaseIntent {

    data object GetRanking : RankingIntent
}


