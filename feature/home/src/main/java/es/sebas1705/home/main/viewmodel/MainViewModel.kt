package es.sebas1705.home.main.viewmodel


import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.common.classes.mvi.MVIBaseViewModel
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.feature.home.R
import es.sebas1705.fillusescases.FillUsesCases
import es.sebas1705.news.NewsUsesCases
import es.sebas1705.opendbusescases.OpendbUsesCases
import es.sebas1705.user.UserUsesCases
import es.sebas1705.home.main.viewmodel.MainState
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel that will handle the ranking screen.
 *
 * @param userUsesCases [UserUsesCases]: UseCase to get the user's data.
 * @param newsUsesCases [NewsUsesCases]: UseCase to get the news.
 * @param opendbUsesCases [OpendbUsesCases]: UseCase to download more questions from OpenTDB.
 * @param application [Application]: Application to get the context.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val userUsesCases: UserUsesCases,
    private val newsUsesCases: NewsUsesCases,
    private val fillUsesCases: FillUsesCases,
    private val opendbUsesCases: OpendbUsesCases,
    private val application: Application
) : MVIBaseViewModel<MainState, MainIntent>() {

    override fun initState(): MainState = MainState.default()

    override fun intentHandler(intent: MainIntent) {
        when (intent) {
            is MainIntent.GetRanking -> getRanking()
            is MainIntent.GetNews -> getNews()
            is MainIntent.RecreateGameDB -> recreateGameDB()
            is MainIntent.DownloadQuestions -> downloadQuestions()
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

    private fun downloadQuestions() = execute(Dispatchers.IO) {
        opendbUsesCases.downloadQuestions(
            onLoading = { startLoading() },
            onSuccess = { amount ->
                stopLoading()
                execute {
                    application.printTextInToast(
                        application.getString(R.string.feature_home_download_questions_success, amount)
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






