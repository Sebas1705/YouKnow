package es.sebas1705.youknow.presentation.features.game.features.quiz.viewmodel
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
import es.sebas1705.youknow.core.classes.enums.games.Category
import es.sebas1705.youknow.core.classes.enums.games.Difficulty
import es.sebas1705.youknow.core.classes.enums.games.quiz.QuizMode
import es.sebas1705.youknow.core.classes.enums.games.quiz.QuizStatus
import es.sebas1705.youknow.core.classes.enums.games.quiz.QuizType
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
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
 * @param quizUsesCases [QuizUsesCases]: UseCases for the Quiz.
 * @param userUsesCases [UserUsesCases]: UseCases for the user.
 * @param authUsesCases [AuthUsesCases]: UseCases for the Auth.
 * @param datastoreUsesCases [DatastoreUsesCases]: UseCases for the Datastore.
 * @param application [Application]: Application context.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizUsesCases: QuizUsesCases,
    private val userUsesCases: UserUsesCases,
    private val authUsesCases: AuthUsesCases,
    private val datastoreUsesCases: DatastoreUsesCases,
    private val application: Application
) : MVIBaseViewModel<QuizState, QuizIntent>() {

    override fun initState(): QuizState = QuizState.default()

    override fun intentHandler(intent: QuizIntent) {
        when (intent) {
            is QuizIntent.ReadLanguages -> readLanguages()
            is QuizIntent.GenerateGame -> generateGame(intent)
            is QuizIntent.ResetGame -> resetGame()
            is QuizIntent.SelectMode -> selectMode(intent)
            is QuizIntent.Response -> response(intent)
            is QuizIntent.OutGame -> outGame(intent)
        }
    }

    //Actions:
    private fun readLanguages() = execute(Dispatchers.IO) {
        datastoreUsesCases.readGameLanguage().collect {
            updateUi {
                it.copy(languages = it.languages)
            }
        }
    }

    private fun generateGame(
        intent: QuizIntent.GenerateGame
    ) = execute(Dispatchers.IO) {
        quizUsesCases.generateQuestionList(
            intent.numQuestions,
            intent.category,
            intent.difficulty,
            _uiState.value.languages,
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
                        status = QuizStatus.RUNNING,
                        mode = QuizMode.CUSTOM,
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
            QuizState.default()
        }
    }

    private fun selectMode(
        intent: QuizIntent.SelectMode
    ) {
        if (intent.quizMode != QuizMode.CUSTOM)
            execute(Dispatchers.IO) {
                quizUsesCases.generateQuestionList(
                    intent.quizMode.numQuestions,
                    Category.ANY,
                    Difficulty.ANY,
                    _uiState.value.languages,
                    QuizType.ANY,
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
                                mode = intent.quizMode,
                                status = QuizStatus.RUNNING,
                                questions = questionsShuffled,
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
                mode = intent.quizMode,
                status = QuizStatus.CUSTOM
            )
        }
    }

    private fun response(
        intent: QuizIntent.Response
    ) {
        val state = _uiState.value
        val question = state.questions[state.actualQuestion]
        val correct = intent.response == question.correctAnswer
        //Last: last question or no lives left
        val last =
            (state.actualQuestion + 1 == state.questions.size) or (!correct && state.mode == QuizMode.SURVIVAL && state.lives - 1 <= 0)
        //Points: difficulty * quizType * mode
        val quizPoints = (if (correct) 1 else 0) *
                (question.difficulty.points * question.quizType.multiPoints * (state.mode?.multiPoints
                    ?: 1.0)).toInt()
        //Buff: correct answers / total questions
        val buff = state.correctAnswers / state.questions.size.toFloat()
        updateUi {
            it.copy(
                status = if (last) QuizStatus.FINISHED else QuizStatus.RUNNING,
                points = it.points + quizPoints + ((it.points + quizPoints) * (if (last) buff else 0f)).toInt(),
                actualQuestion = it.actualQuestion + 1,
                correctAnswers = it.correctAnswers + (if (correct) 1 else 0),
                lives = it.lives - (if (correct) 0 else 1)
            )
        }
    }

    private fun outGame(
        intent: QuizIntent.OutGame
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