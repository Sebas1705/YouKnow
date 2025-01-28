package es.sebas1705.youknow.presentation.features.game.features.mysterynumber.viewmodel
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
import es.sebas1705.youknow.core.classes.enums.games.Difficulty
import es.sebas1705.youknow.core.classes.enums.games.mysterynumber.MysteryNumberMode
import es.sebas1705.youknow.core.classes.enums.games.mysterynumber.MysteryNumberStatus
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.domain.usecases.games.MysteryNumberUsesCases
import es.sebas1705.youknow.domain.usecases.user.AuthUsesCases
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for Quiz Screen that will decide the start destination of the app
 * depending on the user's state and the first time the app is opened.
 *
 * @param mysteryNumberUsesCases [MysteryNumberUsesCases]: UseCases for the MysteryNumber.
 * @param userUsesCases [UserUsesCases]: UseCases for the user.
 * @param authUsesCases [AuthUsesCases]: UseCases for the Auth.
 * @param application [Application]: Application context.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class MysteryNumberViewModel @Inject constructor(
    private val mysteryNumberUsesCases: MysteryNumberUsesCases,
    private val userUsesCases: UserUsesCases,
    private val authUsesCases: AuthUsesCases,
    private val application: Application
) : MVIBaseViewModel<MysteryNumberState, MysteryNumberIntent>() {

    override fun initState(): MysteryNumberState = MysteryNumberState.default()

    override fun intentHandler(intent: MysteryNumberIntent) {
        when (intent) {
            is MysteryNumberIntent.GenerateGame -> generateGame(intent)
            is MysteryNumberIntent.ResetGame -> resetGame()
            is MysteryNumberIntent.SelectMode -> selectMode(intent)
            is MysteryNumberIntent.Response -> response(intent)
            is MysteryNumberIntent.OutGame -> outGame(intent)
        }
    }


    //Actions:
    private fun generateGame(
        intent: MysteryNumberIntent.GenerateGame
    ) = execute(Dispatchers.IO) {
        mysteryNumberUsesCases.generateRandomNumber(
            difficulty = intent.difficulty,
            onLoading = { startLoading() },
            onSuccess = { number ->
                stopLoading()
                updateUi {
                    it.copy(
                        numberModel = number,
                        status = MysteryNumberStatus.RUNNING,
                        lives = intent.lives
                    )
                }
            }
        )
    }

    private fun resetGame() =
        updateUi {
            MysteryNumberState.default().copy(
                points = it.points
            )
        }

    private fun selectMode(
        intent: MysteryNumberIntent.SelectMode
    ) {
        if (intent.mysteryNumberMode != MysteryNumberMode.CUSTOM) {
            execute(Dispatchers.IO) {
                mysteryNumberUsesCases.generateRandomNumber(
                    difficulty = Difficulty.ANY,
                    onLoading = { startLoading() },
                    onSuccess = { number ->
                        stopLoading()
                        updateUi {
                            it.copy(
                                mode = intent.mysteryNumberMode,
                                lives = intent.mysteryNumberMode.lives,
                                numberModel = number,
                                status = MysteryNumberStatus.RUNNING
                            )
                        }
                    }
                )
            }
        } else updateUi {
            it.copy(
                mode = intent.mysteryNumberMode,
                status = MysteryNumberStatus.CUSTOM
            )
        }
    }

    private fun response(
        intent: MysteryNumberIntent.Response
    ) {
        val correct = intent.response == _uiState.value.numberModel.number
        val last = (!correct && _uiState.value.lives - 1 <= 0 || intent.response == -1)
        if (!correct) {
            val m =
                if (_uiState.value.numberModel.number > intent.response) application.getString(
                    R.string.greater
                ) else application.getString(
                    R.string.smaller
                )
            application.printTextInToast(application.getString(R.string.incorrect_number) + " $m")
        }
        updateUi {
            val multiPoints = it.mode?.multiPoints ?: 1.0
            val plus =
                if (it.mode == MysteryNumberMode.TIME_ATTACK) intent.time * 2 else _uiState.value.lives.toFloat()
            it.copy(
                status = if (last || correct) MysteryNumberStatus.FINISHED else MysteryNumberStatus.RUNNING,
                points = if (correct) it.points + (it.numberModel.difficulty.points * multiPoints * plus).toInt() else it.points,
                lives = if (correct) it.lives else it.lives - 1,
                timeRemaining = if (correct || last) intent.time else 0f
            )
        }
    }

    private fun outGame(
        intent: MysteryNumberIntent.OutGame
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
                Log.i("MysteryNumberViewModel", "User: $user")
                execute(Dispatchers.IO) {
                    userUsesCases.addPointsToUser(
                        user = user,
                        pointsToAdd = points,
                        onSuccess = {
                            Log.i("MysteryNumberViewModel", "Points added")
                            execute(Dispatchers.IO) {
                                userUsesCases.addCreditsToUser(
                                    user = user,
                                    creditsToAdd = points / (10..100).random(),
                                    onSuccess = {
                                        Log.i("MysteryNumberViewModel", "Credits added")
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





