package es.sebas1705.game.families.viewmodel


import android.app.Application
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.families.FamiliesMode
import es.sebas1705.common.games.families.FamiliesStatus
import es.sebas1705.common.classes.mvi.MVIBaseViewModel
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.familiesusescases.FamiliesUsesCases
import es.sebas1705.settings.SettingUsesCases
import es.sebas1705.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for Families Screen that will decide the start destination of the app
 * depending on the user's state and the first time the app is opened.
 *
 * @param familiesUsesCases [FamiliesUsesCases]: Use cases for the Families game.
 * @param userUsesCases [UserUsesCases]: Use cases for the User.
 * @param authUsesCases [AuthUsesCases]: Use cases for the Auth.
 * @param settingUsesCases [SettingUsesCases]: Use cases for the Settings.
 * @param application [Application]: Application context.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@HiltViewModel
class FamiliesViewModel @Inject constructor(
    private val familiesUsesCases: FamiliesUsesCases,
    private val userUsesCases: UserUsesCases,
    private val authUsesCases: AuthUsesCases,
    private val settingUsesCases: SettingUsesCases,
    private val application: Application
) : MVIBaseViewModel<FamiliesState, FamiliesIntent>() {

    override fun initState(): FamiliesState = FamiliesState.default()

    override fun intentHandler(intent: FamiliesIntent) {
        when (intent) {
            is FamiliesIntent.ReadLanguages -> readLanguages()
            is FamiliesIntent.GenerateGame -> generateGame(intent)
            is FamiliesIntent.ResetGame -> resetGame()
            is FamiliesIntent.SelectMode -> selectMode(intent)
            is FamiliesIntent.Response -> responseFamily(intent)
            is FamiliesIntent.OutGame -> outGame(intent)
        }
    }


    //Actions:
    private fun readLanguages() = execute(Dispatchers.IO) {
        settingUsesCases.readGameLanguage().collect { languages ->
            updateUi {
                it.copy(languages = languages)
            }
        }
    }

    private fun generateGame(
        intent: FamiliesIntent.GenerateGame
    ) = execute(Dispatchers.IO) {
        familiesUsesCases.generateFamilies(
            intent.numFamilies,
            intent.category,
            intent.difficulty,
            _uiState.value.languages,
            onLoading = { startLoading() },
            onSuccess = { families ->
                val familiesShuffled = families.map { family ->
                    family.copy(
                        answers = family.answers.shuffled()
                    )
                }
                stopLoading()
                updateUi {
                    it.copy(
                        families = familiesShuffled,
                        status = FamiliesStatus.RUNNING,
                        mode = FamiliesMode.CUSTOM
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
            FamiliesState.default()
        }
    }

    private fun selectMode(
        intent: FamiliesIntent.SelectMode
    ) {
        if (intent.familiesMode != FamiliesMode.CUSTOM)
            execute(Dispatchers.IO) {
                familiesUsesCases.generateFamilies(
                    intent.familiesMode.numFamilies,
                    Category.ANY,
                    Difficulty.ANY,
                    _uiState.value.languages,
                    onLoading = { startLoading() },
                    onSuccess = { families ->
                        val familiesShuffled = families.map { family ->
                            family.copy(
                                answers = family.answers.shuffled()
                            )
                        }
                        stopLoading()
                        updateUi {
                            it.copy(
                                mode = intent.familiesMode,
                                status = FamiliesStatus.RUNNING,
                                families = familiesShuffled,
                            )
                        }
                    },
                    onError = { error ->
                        stopAndError(error, application::printTextInToast)
                    }
                )
            }
        else updateUi {
            it.copy(
                mode = intent.familiesMode,
                status = FamiliesStatus.CUSTOM
            )
        }
    }

    private fun responseFamily(
        intent: FamiliesIntent.Response
    ) {
        val state = _uiState.value
        val family = state.families[state.actualFamily]
        val correct = intent.response == family.correctAnswer
        //Last: If last family or no lives left
        val last =
            (state.actualFamily + 1 == state.families.size) or (!correct && state.mode == FamiliesMode.SURVIVAL && state.lives - 1 <= 0)
        //Family Points: Difficulty points * Multiplier of mode
        val familyPoints =
            (if (correct) 1 else 0) * (family.difficulty.points * (state.mode?.multiPoints
                ?: 1.0)).toInt()
        //Buff: Correct answers / Total families
        val buff = state.correctAnswers / state.families.size.toFloat()
        updateUi {
            it.copy(
                status = if (last) FamiliesStatus.FINISHED else FamiliesStatus.RUNNING,
                points = it.points + familyPoints + ((it.points + familyPoints) * (if (last) buff else 0f)).toInt(),
                actualFamily = it.actualFamily + 1,
                correctAnswers = it.correctAnswers + (if (correct) 1 else 0),
                lives = it.lives - (if (correct) 0 else 1)
            )
        }
    }

    private fun outGame(
        intent: FamiliesIntent.OutGame
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
                Log.i("FamiliesViewModel", "User: $user")
                execute(Dispatchers.IO) {
                    userUsesCases.addPointsToUser(
                        user = user,
                        pointsToAdd = points,
                        onSuccess = {
                            Log.i("FamiliesViewModel", "Points added")
                            execute(Dispatchers.IO) {
                                userUsesCases.addCreditsToUser(
                                    user = user,
                                    creditsToAdd = points / (10..100).random(),
                                    onSuccess = {
                                        Log.i("FamiliesViewModel", "Credits added")
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