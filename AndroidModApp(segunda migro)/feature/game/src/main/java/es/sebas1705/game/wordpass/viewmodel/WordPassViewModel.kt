package es.sebas1705.game.wordpass.viewmodel


import android.app.Application
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.common.games.wordpass.Letter
import es.sebas1705.common.games.wordpass.WordPassMode
import es.sebas1705.common.games.wordpass.WordPassStatus
import es.sebas1705.common.classes.mvi.MVIBaseViewModel
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.common.utlis.extensions.primitives.normalizeString
import es.sebas1705.settings.SettingUsesCases
import es.sebas1705.user.UserUsesCases
import es.sebas1705.wordpassusescases.WordPassUsesCases
import es.sebas1705.feature.games.R
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for WordPass Screen that will decide the start destination of the app
 * depending on the user's state and the first time the app is opened.
 *
 * @param wordPassUsesCases [WordPassUsesCases]: UseCases for the WordPass game.
 * @param userUsesCases [UserUsesCases]: UseCases for the user.
 * @param authUsesCases [AuthUsesCases]: UseCases for the Auth.
 * @param settingUsesCases [SettingUsesCases]: UseCases for the Settings.
 * @param application [Application]: Application context.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@HiltViewModel
class WordPassViewModel @Inject constructor(
    private val wordPassUsesCases: WordPassUsesCases,
    private val userUsesCases: UserUsesCases,
    private val authUsesCases: AuthUsesCases,
    private val settingUsesCases: SettingUsesCases,
    private val application: Application
) : MVIBaseViewModel<WordPassState, WordPassIntent>() {

    override fun initState(): WordPassState = WordPassState.default()

    override fun intentHandler(intent: WordPassIntent) {
        when (intent) {
            is WordPassIntent.ReadLanguages -> readLanguages()
            is WordPassIntent.GenerateGame -> generateGame(intent)
            is WordPassIntent.ResetGame -> resetGame()
            is WordPassIntent.SelectMode -> selectMode(intent)
            is WordPassIntent.Response -> response(intent)
            is WordPassIntent.OutGame -> outGame(intent)
        }
    }


    //Actions:
    private fun readLanguages() = execute(Dispatchers.IO) {
        settingUsesCases.readGameLanguage().collect { language ->
            updateUi {
                it.copy(languages = language)
            }
        }
    }

    private fun generateGame(
        intent: WordPassIntent.GenerateGame
    ) = execute(Dispatchers.IO) {
        if (intent.wordPassMode == WordPassMode.FIRE_WHEEL)
            wordPassUsesCases.generateWheelWordPass(
                intent.difficulty,
                _uiState.value.languages,
                onLoading = { startLoading() },
                onSuccess = { words ->
                    stopLoading()
                    updateUi {
                        it.copy(
                            words = words,
                            status = WordPassStatus.RUNNING
                        )
                    }
                },
                onError = { error ->
                    stopAndError(error, application::printTextInToast)
                }
            )
        else
            wordPassUsesCases.generateWordPass(
                intent.numWords,
                Letter.ANY,
                _uiState.value.languages,
                intent.difficulty,
                onLoading = { startLoading() },
                onSuccess = { words ->
                    stopLoading()
                    updateUi {
                        it.copy(
                            words = words,
                            status = WordPassStatus.RUNNING
                        )
                    }
                },
                onError = { error ->
                    stopAndError(error, application::printTextInToast)
                }
            )
    }


    private fun resetGame() = addPointsAndCredits(_uiState.value.points) {
        updateUi {
            WordPassState.default()
        }
    }

    private fun selectMode(
        intent: WordPassIntent.SelectMode
    ) {
        updateUi {
            it.copy(
                mode = intent.wordPassMode
            )
        }
    }

    private fun response(
        intent: WordPassIntent.Response
    ) {
        val state = _uiState.value
        val word = state.words[state.actualWord]
        val correct = intent.response.normalizeString() == word.word.normalizeString()
        //Last: last word or no lives left
        val last =
            (state.actualWord + 1 == state.words.size) or (!correct && state.mode == WordPassMode.SURVIVAL && state.lives - 1 <= 0)
        //Points: word points * mode multiplier
        val wordPoints = (if (correct) 1 else 0) *
                (word.difficulty.points * (state.mode?.multiPoints ?: 1.0)).toInt()
        //Buff: correct answers / total words
        val buff =
            state.correctAnswers / state.words.size.toFloat()
        if (!correct)
            application.printTextInToast("${application.getString(R.string.feature_game_correct_response)}${word.word}")
        updateUi {
            it.copy(
                status = if (last) WordPassStatus.FINISHED else WordPassStatus.RUNNING,
                points = it.points + wordPoints + ((it.points + wordPoints) * (if (last) buff else 0f)).toInt(),
                actualWord = it.actualWord + 1,
                correctAnswers = it.correctAnswers + (if (correct) 1 else 0),
                lives = it.lives - (if (correct) 0 else 1)
            )
        }
    }

    private fun outGame(
        intent: WordPassIntent.OutGame
    ) = addPointsAndCredits(_uiState.value.points, intent.onSuccess)


    //Privates:
    private fun addPointsAndCredits(
        points: Int,
        onSuccess: () -> Unit
    ) = execute(Dispatchers.IO) {
        userUsesCases.getUser(
            firebaseId = authUsesCases.getFirebaseUser()!!.uid,
            onLoading = { startLoading() },
            onSuccess = { user ->
                Log.i("WordPassViewModel", "User: $user")
                execute(Dispatchers.IO) {
                    userUsesCases.addPointsToUser(
                        user = user,
                        pointsToAdd = points,
                        onSuccess = {
                            Log.i("WordPassViewModel", "Points added")
                            execute(Dispatchers.IO) {
                                userUsesCases.addCreditsToUser(
                                    user = user,
                                    creditsToAdd = points / (10..100).random(),
                                    onSuccess = {
                                        Log.i("WordPassViewModel", "Credits added")
                                        stopLoading()
                                        execute {
                                            onSuccess()
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
            },
            onError = { error ->
                stopAndError(error, application::printTextInToast)
            }
        )
    }


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







