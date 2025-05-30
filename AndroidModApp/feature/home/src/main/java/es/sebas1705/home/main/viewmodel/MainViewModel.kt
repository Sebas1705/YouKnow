package es.sebas1705.home.main.viewmodel
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
import es.sebas1705.common.mvi.MVIBaseViewModel
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.fillusescases.FillUsesCases
import es.sebas1705.news.NewsUsesCases
import es.sebas1705.user.UserUsesCases
import es.sebas1705.youknow.presentation.features.home.features.main.viewmodel.MainState
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel that will handle the ranking screen.
 *
 * @param userUsesCases [UserUsesCases]: UseCase to get the user's data.
 * @param newsUsesCases [NewsUsesCases]: UseCase to get the news.
 * @param application [Application]: Application to get the context.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val userUsesCases: UserUsesCases,
    private val newsUsesCases: NewsUsesCases,
    private val fillUsesCases: FillUsesCases,
    private val application: Application
) : MVIBaseViewModel<MainState, MainIntent>() {

    override fun initState(): MainState = MainState.default()

    override fun intentHandler(intent: MainIntent) {
        when (intent) {
            is MainIntent.GetRanking -> getRanking()
            is MainIntent.GetNews -> getNews()
            is MainIntent.RecreateGameDB -> recreateGameDB()
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

    private fun getNews() = execute(Dispatchers.IO) {
        newsUsesCases.getNews(
            onLoading = { startLoading() },
            onSuccess = { news ->
                stopLoading()
                updateUi { it.copy(news = news) }
            },
            onError = { error ->
                stopAndError(error, application::printTextInToast)
            }
        )
    }

    private fun recreateGameDB() = execute(Dispatchers.IO) {
        fillUsesCases.fillByDefaultWords(
            onLoading = { startLoading() },
            onSuccess = {
                execute(Dispatchers.IO) {
                    fillUsesCases.fillByDefaultFamilies(
                        onLoading = {},
                        onSuccess = {
                            execute(Dispatchers.IO) {
                                fillUsesCases.fillByDefaultQuestions(
                                    onLoading = {},
                                    onSuccess = {
                                        stopLoading()
                                    },
                                    onError = { error ->
                                        stopAndError(error, application::printTextInToast)
                                    }
                                )
                            }
                        },
                        onError = { error ->
                            stopAndError(error, application::printTextInToast)
                        }
                    )
                }
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






