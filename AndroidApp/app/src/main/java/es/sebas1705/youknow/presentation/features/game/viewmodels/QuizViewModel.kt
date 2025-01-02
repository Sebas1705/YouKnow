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
import es.sebas1705.youknow.core.classes.enums.QuizType
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.domain.model.games.QuestionModel
import es.sebas1705.youknow.domain.usecases.DatastoreUsesCases
import es.sebas1705.youknow.domain.usecases.games.QuizUsesCases
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
class QuizViewModel @Inject constructor(
    private val quizUsesCases: QuizUsesCases,
    private val userUsesCases: UserUsesCases,
    private val authUsesCases: AuthUsesCases,
    private val application: Application
) : MVIBaseViewModel<QuizState, QuizIntent>() {

    private val ctx: Context = application.applicationContext

    override fun initState(): QuizState = QuizState.default()

    override fun intentHandler(intent: QuizIntent) {
        when (intent) {
            is QuizIntent.GenerateGame -> generateGame(intent)
            is QuizIntent.ResetGame -> resetGame(intent)
            is QuizIntent.SelectMode -> selectMode(intent)
            is QuizIntent.ResponseQuestion -> responseQuestion(intent)
            is QuizIntent.OutGame -> outGame(intent)
        }
    }


    //Actions:
    private fun generateGame(
        intent: QuizIntent.GenerateGame
    ) = execute(Dispatchers.IO) {
        quizUsesCases.generateQuestionList(
            intent.numQuestions,
            intent.category,
            intent.difficulty,
            intent.quizType,
            onLoading = { startLoading() },
            onSuccess = { questions ->
                val questionsShuffled = questions.map { question ->
                    question.copy(
                        answers = question.answers.shuffled()
                    )
                }
                stopLoading()
                updateUi {
                    it.copy(
                        questions = questionsShuffled,
                        status = QuizStatus.RUNNING
                    )
                }
            },
            onError = { error ->
                stopAndError(error, ctx::printTextInToast)
            }
        )
    }


    private fun resetGame(intent: QuizIntent.ResetGame) = addPointsAndCredits(intent.points) {
        updateUi {
            QuizState.default()
        }
    }

    private fun selectMode(
        intent: QuizIntent.SelectMode
    ) {
        updateUi {
            it.copy(
                mode = intent.quizMode,
                status = if (intent.quizMode == QuizMode.CUSTOM)
                    QuizStatus.CUSTOM
                else
                    QuizStatus.SELECTION_MODE,
                numQuestions = intent.quizMode.numQuestions
            )
        }
    }

    private fun responseQuestion(
        intent: QuizIntent.ResponseQuestion
    ) {
        val question = intent.quizState.questions[intent.quizState.actualQuestion]
        val correct = intent.response == question.correctAnswer
        val last =
            (intent.quizState.actualQuestion + 1 == intent.quizState.questions.size) or (!correct && intent.quizState.mode == QuizMode.SURVIVAL && intent.quizState.lives - 1 == 0)
        val pointsToAdd =
            (question.difficulty.points * question.quizType.multiPoints * (intent.quizState.mode?.multiPoints
                ?: 1.0)).toInt()
        val buff = intent.quizState.correctAnswers / intent.quizState.questions.size.toFloat()
        updateUi {
            it.copy(
                status = if (last) QuizStatus.FINISHED else QuizStatus.RUNNING,
                points = if (correct) it.points + (pointsToAdd * (if (last) buff else 1f)).toInt() else it.points,
                actualQuestion = it.actualQuestion + 1,
                correctAnswers = if (correct) it.correctAnswers + 1 else it.correctAnswers,
                lives = if (correct) it.lives else it.lives - 1
            )
        }
    }

    private fun outGame(
        intent: QuizIntent.OutGame
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
                Log.i("QuizViewModel", "User: $user")
                execute(Dispatchers.IO) {
                    userUsesCases.addPointsToUser(
                        user = user,
                        pointsToAdd = points,
                        onSuccess = {
                            Log.i("QuizViewModel", "Points added")
                            execute(Dispatchers.IO) {
                                userUsesCases.addCreditsToUser(
                                    user = user,
                                    creditsToAdd = points / (10..100).random(),
                                    onSuccess = {
                                        Log.i("QuizViewModel", "Credits added")
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
 * Data class that represents the state of the Quiz Screen.
 *
 * @param isLoading [Boolean]: Flag that indicates if the screen is loading.
 *
 * @see MVIBaseState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class QuizState(
    var isLoading: Boolean,
    var numQuestions: Int,
    var actualQuestion: Int,
    var points: Int,
    var correctAnswers: Int,
    var lives: Int,
    var questions: List<QuestionModel>,
    var status: QuizStatus,
    var mode: QuizMode?
) : MVIBaseState {

    companion object {
        /**
         * Default state of the Quiz Screen.
         *
         * @return [QuizState]: Default state of the Quiz Screen.
         */
        fun default() = QuizState(
            isLoading = false,
            numQuestions = 0,
            actualQuestion = 0,
            points = 0,
            correctAnswers = 0,
            lives = 3,
            questions = emptyList(),
            status = QuizStatus.SELECTION_MODE,
            mode = null
        )
    }
}

/**
 * Sealed interface that represents the possible intents of the Quiz Screen.
 *
 *
 * @see MVIBaseIntent
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface QuizIntent : MVIBaseIntent {

    data class GenerateGame(
        val category: Category,
        val difficulty: Difficulty,
        val quizType: QuizType,
        val numQuestions: Int,
    ) : QuizIntent

    data class ResetGame(
        val points: Int,
    ) : QuizIntent

    data class SelectMode(val quizMode: QuizMode) : QuizIntent

    data class ResponseQuestion(
        val response: String,
        val quizState: QuizState
    ) : QuizIntent

    data class OutGame(
        val points: Int,
        val onSuccess: () -> Unit
    ) : QuizIntent
}

enum class QuizStatus {
    SELECTION_MODE,
    CUSTOM,
    RUNNING,
    FINISHED
}

enum class QuizMode(
    val strRes: Int,
    val icon: ImageVector,
    val numQuestions: Int,
    val multiPoints: Double
) {
    SURVIVAL(R.string.survival, Icons.Filled.LocalFireDepartment, 100, 1.5),
    TIME_ATTACK(R.string.time_attack, Icons.Filled.HourglassBottom, 20, 1.2),
    ALEATORY(R.string.aleatory, Icons.Filled.Casino, 10, 1.0),
    CUSTOM(R.string.custom, Icons.Filled.DashboardCustomize, 0, 0.75)
}

