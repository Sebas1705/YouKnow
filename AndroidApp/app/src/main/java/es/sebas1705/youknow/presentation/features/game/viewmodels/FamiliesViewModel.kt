package es.sebas1705.youknow.presentation.features.game.viewmodels
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
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.ui.graphics.vector.ImageVector
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.enums.Category
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.domain.model.games.FamiliesModel
import es.sebas1705.youknow.domain.usecases.DatastoreUsesCases
import es.sebas1705.youknow.domain.usecases.games.FamiliesUsesCases
import es.sebas1705.youknow.domain.usecases.user.AuthUsesCases
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for Families Screen that will decide the start destination of the app
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
class FamiliesViewModel @Inject constructor(
    private val familiesUsesCases: FamiliesUsesCases,
    private val userUsesCases: UserUsesCases,
    private val authUsesCases: AuthUsesCases,
    private val application: Application
) : MVIBaseViewModel<FamiliesState, FamiliesIntent>() {

    private val ctx: Context = application.applicationContext

    override fun initState(): FamiliesState = FamiliesState.default()

    override fun intentHandler(intent: FamiliesIntent) {
        when (intent) {
            is FamiliesIntent.GenerateGame -> generateGame(intent)
            is FamiliesIntent.ResetGame -> resetGame(intent)
            is FamiliesIntent.SelectMode -> selectMode(intent)
            is FamiliesIntent.Response -> responseFamily(intent)
            is FamiliesIntent.OutGame -> outGame(intent)
        }
    }


    //Actions:
    private fun generateGame(
        intent: FamiliesIntent.GenerateGame
    ) = execute(Dispatchers.IO) {
        familiesUsesCases.generateFamilies(
            intent.numFamilies,
            intent.category,
            intent.difficulty,
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
                        status = FamiliesStatus.RUNNING
                    )
                }
            },
            onError = { error ->
                stopAndError(error, ctx::printTextInToast)
            }
        )
    }


    private fun resetGame(intent: FamiliesIntent.ResetGame) = addPointsAndCredits(intent.points) {
        updateUi {
            FamiliesState.default()
        }
    }

    private fun selectMode(
        intent: FamiliesIntent.SelectMode
    ) {
        updateUi {
            it.copy(
                mode = intent.familiesMode,
                status = if (intent.familiesMode == FamiliesMode.CUSTOM)
                    FamiliesStatus.CUSTOM
                else
                    FamiliesStatus.SELECTION_MODE,
                numFamily = intent.familiesMode.numFamilies
            )
        }
    }

    private fun responseFamily(
        intent: FamiliesIntent.Response
    ) {
        val family = intent.familiesState.families[intent.familiesState.actualFamily]
        val correct = intent.response == family.correctAnswer
        val last =
            (intent.familiesState.actualFamily + 1 == intent.familiesState.families.size) or (!correct && intent.familiesState.mode == FamiliesMode.SURVIVAL && intent.familiesState.lives - 1 == 0)
        val pointsToAdd =
            (family.difficulty.points * (intent.familiesState.mode?.multiPoints ?: 1.0)).toInt()
        val buff =
            intent.familiesState.correctAnswers / intent.familiesState.families.size.toFloat()
        updateUi {
            it.copy(
                status = if (last) FamiliesStatus.FINISHED else FamiliesStatus.RUNNING,
                points = it.points + (pointsToAdd * (if (last) buff else 1f)).toInt(),
                actualFamily = it.actualFamily + 1,
                correctAnswers = if (correct) it.correctAnswers + 1 else it.correctAnswers,
                lives = if (correct) it.lives else it.lives - 1
            )
        }
    }

    private fun outGame(
        intent: FamiliesIntent.OutGame
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
 * Data class that represents the state of the Families Screen.
 *
 * @param isLoading [Boolean]: Flag that indicates if the screen is loading.
 *
 * @see MVIBaseState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class FamiliesState(
    var isLoading: Boolean,
    var numFamily: Int,
    var actualFamily: Int,
    var points: Int,
    var correctAnswers: Int,
    var lives: Int,
    var families: List<FamiliesModel>,
    var status: FamiliesStatus,
    var mode: FamiliesMode?
) : MVIBaseState {

    companion object {
        /**
         * Default state of the Families Screen.
         *
         * @return [FamiliesState]: Default state of the Families Screen.
         */
        fun default() = FamiliesState(
            isLoading = false,
            numFamily = 0,
            actualFamily = 0,
            points = 0,
            correctAnswers = 0,
            lives = 3,
            families = emptyList(),
            status = FamiliesStatus.SELECTION_MODE,
            mode = null
        )
    }
}

/**
 * Sealed interface that represents the possible intents of the Families Screen.
 *
 *
 * @see MVIBaseIntent
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface FamiliesIntent : MVIBaseIntent {

    data class GenerateGame(
        val category: Category,
        val difficulty: Difficulty,
        val numFamilies: Int,
    ) : FamiliesIntent

    data class ResetGame(
        val points: Int,
    ) : FamiliesIntent

    data class SelectMode(val familiesMode: FamiliesMode) : FamiliesIntent

    data class Response(
        val response: String,
        val familiesState: FamiliesState
    ) : FamiliesIntent

    data class OutGame(
        val points: Int,
        val onSuccess: () -> Unit
    ) : FamiliesIntent
}

enum class FamiliesStatus {
    SELECTION_MODE,
    CUSTOM,
    RUNNING,
    FINISHED
}

enum class FamiliesMode(
    val strRes: Int,
    val icon: ImageVector,
    val numFamilies: Int,
    val multiPoints: Double
) {
    SURVIVAL(R.string.survival, Icons.Filled.LocalFireDepartment, 100, 1.5),
    TIME_ATTACK(R.string.time_attack, Icons.Filled.HourglassBottom, 20, 1.2),
    ALEATORY(R.string.aleatory, Icons.Filled.Casino, 10, 1.0),
    CUSTOM(R.string.custom, Icons.Filled.DashboardCustomize, 0, 0.75)
}
