package es.sebas1705.youknow.presentation.features.game.features.wordpass.viewmodel
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
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.enums.Letter
import es.sebas1705.youknow.core.classes.enums.WordPassType
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.core.utlis.extensions.primitives.normalizeString
import es.sebas1705.youknow.domain.model.games.WordModel
import es.sebas1705.youknow.domain.usecases.DatastoreUsesCases
import es.sebas1705.youknow.domain.usecases.games.WordPassUsesCases
import es.sebas1705.youknow.domain.usecases.user.AuthUsesCases
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for WordPass Screen that will decide the start destination of the app
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
class WordPassViewModel @Inject constructor(
    private val wordPassUsesCases: WordPassUsesCases,
    private val userUsesCases: UserUsesCases,
    private val authUsesCases: AuthUsesCases,
    private val application: Application
) : MVIBaseViewModel<WordPassState, WordPassIntent>() {

    private val ctx: Context = application.applicationContext

    override fun initState(): WordPassState = WordPassState.default()

    override fun intentHandler(intent: WordPassIntent) {
        when (intent) {
            is WordPassIntent.GenerateGame -> generateGame(intent)
            is WordPassIntent.ResetGame -> resetGame(intent)
            is WordPassIntent.SelectMode -> selectMode(intent)
            is WordPassIntent.Response -> response(intent)
            is WordPassIntent.OutGame -> outGame(intent)
        }
    }


    //Actions:
    private fun generateGame(
        intent: WordPassIntent.GenerateGame
    ) = execute(Dispatchers.IO) {
        wordPassUsesCases.generateWordPass(
            intent.numWords,
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
                stopAndError(error, ctx::printTextInToast)
            }
        )
    }


    private fun resetGame(intent: WordPassIntent.ResetGame) = addPointsAndCredits(intent.points) {
        updateUi {
            WordPassState.default()
        }
    }

    private fun selectMode(
        intent: WordPassIntent.SelectMode
    ) {
        updateUi {
            it.copy(
                mode = intent.wordPassMode,
                numWords = intent.wordPassMode.numWords
            )
        }
    }

    private fun response(
        intent: WordPassIntent.Response
    ) {
        val word = intent.wordPassState.words[intent.wordPassState.actualWord]
        Log.i(
            "WordPassViewModel",
            "Word: ${word.word.normalizeString()} - Response: ${intent.response.normalizeString()}"
        )
        val correct = intent.response.normalizeString() == word.word.normalizeString()
        val last =
            (intent.wordPassState.actualWord + 1 == intent.wordPassState.words.size) or (!correct && intent.wordPassState.mode == WordPassMode.SURVIVAL && intent.wordPassState.lives - 1 <= 0)
        val pointsToAdd =
            (word.difficulty.points * word.wordPassType.multiPoints * (intent.wordPassState.mode?.multiPoints
                ?: 1.0)).toInt()
        val buff =
            intent.wordPassState.correctAnswers / intent.wordPassState.words.size.toFloat()
        if (!correct)
            ctx.printTextInToast("${ctx.getString(R.string.correct_response)}${word.word}")
        updateUi {
            it.copy(
                status = if (last) WordPassStatus.FINISHED else WordPassStatus.RUNNING,
                points = if (correct) it.points + (pointsToAdd * (if (last) buff else 1f)).toInt() else it.points,
                actualWord = it.actualWord + 1,
                correctAnswers = if (correct) it.correctAnswers + 1 else it.correctAnswers,
                lives = if (correct) it.lives else it.lives - 1
            )
        }
    }

    private fun outGame(
        intent: WordPassIntent.OutGame
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
 * Data class that represents the state of the WordPass Screen.
 *
 * @param isLoading [Boolean]: Flag that indicates if the screen is loading.
 *
 * @see MVIBaseState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class WordPassState(
    var isLoading: Boolean,
    var numWords: Int,
    var actualWord: Int,
    var points: Int,
    var correctAnswers: Int,
    var lives: Int,
    var words: List<WordModel>,
    var status: WordPassStatus,
    var mode: WordPassMode?
) : MVIBaseState {

    companion object {
        /**
         * Default state of the WordPass Screen.
         *
         * @return [WordPassState]: Default state of the WordPass Screen.
         */
        fun default() = WordPassState(
            isLoading = false,
            numWords = 0,
            actualWord = 0,
            points = 0,
            correctAnswers = 0,
            lives = 3,
            words = emptyList(),
            status = WordPassStatus.SELECTION_MODE,
            mode = null
        )
    }
}

/**
 * Sealed interface that represents the possible intents of the WordPass Screen.
 *
 *
 * @see MVIBaseIntent
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface WordPassIntent : MVIBaseIntent {

    data class GenerateGame(
        val difficulty: Difficulty,
        val wordPassType: WordPassType,
        val wordPassMode: WordPassMode,
        val numWords: Int,
    ) : WordPassIntent

    data class ResetGame(
        val points: Int,
    ) : WordPassIntent

    data class SelectMode(
        val wordPassMode: WordPassMode
    ) : WordPassIntent

    data class Response(
        val response: String,
        val wordPassState: WordPassState
    ) : WordPassIntent

    data class OutGame(
        val points: Int,
        val onSuccess: () -> Unit
    ) : WordPassIntent
}

enum class WordPassStatus {
    SELECTION_MODE,
    RUNNING,
    FINISHED
}

enum class WordPassMode(
    val strRes: Int,
    val icon: ImageVector,
    val numWords: Int,
    val multiPoints: Double
) {
    SURVIVAL(R.string.survival, Icons.Filled.LocalFireDepartment, 100, 1.5),
    FIRE_WHEEL(R.string.fire_wheel, Icons.Filled.Settings, Letter.entries.size, 5.0),
    ALEATORY(R.string.aleatory, Icons.Filled.Casino, 20, 1.0)
}


