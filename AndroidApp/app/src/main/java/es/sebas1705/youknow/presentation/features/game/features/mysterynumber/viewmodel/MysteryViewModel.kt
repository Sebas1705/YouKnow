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
import android.content.Context
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.ui.graphics.vector.ImageVector
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.domain.model.games.NumberModel
import es.sebas1705.youknow.domain.usecases.DatastoreUsesCases
import es.sebas1705.youknow.domain.usecases.games.MysteryNumberUsesCases
import es.sebas1705.youknow.domain.usecases.user.AuthUsesCases
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for Quiz Screen that will decide the start destination of the app
 * depending on the user's state and the first time the app is opened.
 *
 * @param userUsesCases [UserUsesCases]: UseCases for the user.
 * @param datastoreUsesCases [DatastoreUsesCases]: UseCases for the Datastore.
 *
 * @see MVIBaseViewModel
 * @see HiltViewModel
 * @see UserUsesCases
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

    private val ctx: Context = application.applicationContext

    override fun initState(): MysteryNumberState = MysteryNumberState.default()

    override fun intentHandler(intent: MysteryNumberIntent) {
        when (intent) {
            is MysteryNumberIntent.GenerateGame -> generateGame(intent)
            is MysteryNumberIntent.ResetGame -> resetGame(intent)
            is MysteryNumberIntent.SelectMode -> selectMode(intent)
            is MysteryNumberIntent.Response -> responseNumber(intent)
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
                        number = number,
                        status = MysteryNumberStatus.RUNNING,
                        lives = intent.lives
                    )
                }
            }
        )
    }


    private fun resetGame(intent: MysteryNumberIntent.ResetGame) =
        updateUi {
            MysteryNumberState.default().copy(
                points = it.points
            )
        }

    private fun selectMode(
        intent: MysteryNumberIntent.SelectMode
    ) {
        updateUi {
            it.copy(
                mode = intent.mysteryNumberMode,
                status = if (intent.mysteryNumberMode == MysteryNumberMode.CUSTOM) MysteryNumberStatus.CUSTOM else MysteryNumberStatus.SELECTION_MODE
            )
        }
    }

    private fun responseNumber(
        intent: MysteryNumberIntent.Response
    ) {
        val correct = intent.response == intent.mysteryNumberState.number.number
        val last = (!correct && intent.mysteryNumberState.lives - 1 <= 0 || intent.response == -1)
        if (!correct) {
            val m =
                if (intent.mysteryNumberState.number.number > intent.response) ctx.getString(R.string.greater) else ctx.getString(
                    R.string.smaller
                )
            ctx.printTextInToast(ctx.getString(R.string.incorrect_number) + " $m")
        }
        updateUi {
            val multiPoints = it.mode?.multiPoints ?: 1.0
            val plus =
                if (it.mode == MysteryNumberMode.TIME_ATTACK) intent.time * 2 else intent.mysteryNumberState.lives.toFloat()
            it.copy(
                status = if (last || correct) MysteryNumberStatus.FINISHED else MysteryNumberStatus.RUNNING,
                points = if (correct) it.points + (it.number.difficulty.points * multiPoints * plus).toInt() else it.points,
                lives = if (correct) it.lives else it.lives - 1,
                timeRemaining = if (correct || last) intent.time else 0f
            )
        }
    }

    private fun outGame(
        intent: MysteryNumberIntent.OutGame
    ) = addPointsAndCredits(intent.points, intent.onSuccess)


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
                                        stopAndError(error, ctx::printTextInToast)
                                    }
                                )
                            }
                        },
                        onError = { error ->
                            stopAndError(error, ctx::printTextInToast)
                        }
                    )
                }
            },
            onError = { error ->
                stopAndError(error, ctx::printTextInToast)
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

/**
 * Data class that represents the state of the MysteryNumber Screen.
 *
 * @param isLoading [Boolean]: Flag that indicates if the screen is loading.
 *
 * @see MVIBaseState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class MysteryNumberState(
    var isLoading: Boolean,
    var points: Int,
    var lives: Int,
    var timeRemaining: Float,
    var number: NumberModel,
    var status: MysteryNumberStatus,
    var mode: MysteryNumberMode?
) : MVIBaseState {

    companion object {
        /**
         * Default state of the MysteryNumber Screen.
         *
         * @return [MysteryNumberState]: Default state of the MysteryNumber Screen.
         */
        fun default() = MysteryNumberState(
            isLoading = false,
            points = 0,
            lives = 20,
            timeRemaining = 0f,
            number = NumberModel(-1, Difficulty.ANY),
            status = MysteryNumberStatus.SELECTION_MODE,
            mode = null
        )
    }
}

/**
 * Sealed interface that represents the possible intents of the MysteryNumber Screen.
 *
 *
 * @see MVIBaseIntent
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface MysteryNumberIntent : MVIBaseIntent {

    data class GenerateGame(
        val difficulty: Difficulty,
        val lives: Int
    ) : MysteryNumberIntent

    data object ResetGame : MysteryNumberIntent

    data class SelectMode(
        val mysteryNumberMode: MysteryNumberMode
    ) : MysteryNumberIntent

    data class Response(
        val response: Int,
        val time: Float,
        val mysteryNumberState: MysteryNumberState
    ) : MysteryNumberIntent

    data class OutGame(
        val points: Int,
        val onSuccess: () -> Unit
    ) : MysteryNumberIntent
}

enum class MysteryNumberStatus {
    SELECTION_MODE,
    CUSTOM,
    RUNNING,
    FINISHED
}

enum class MysteryNumberMode(
    val strRes: Int,
    val icon: ImageVector,
    val lives: Int,
    val multiPoints: Double
) {
    TIME_ATTACK(R.string.time_attack, Icons.Filled.HourglassBottom, 100, 1.2),
    ALEATORY(R.string.aleatory, Icons.Filled.Casino, 20, 1.0),
    CUSTOM(R.string.custom, Icons.Filled.DashboardCustomize, 20, 1.0),
}

