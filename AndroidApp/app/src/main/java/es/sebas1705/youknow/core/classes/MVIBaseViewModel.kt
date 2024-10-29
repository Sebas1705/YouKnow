package es.sebas1705.youknow.core.classes
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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Base class for the MVIBaseViewModel
 *
 * @param S [MVIBaseState]: State of the viewModel
 * @param I [MVIBaseIntent]: Intent of the viewModel
 *
 * @property initialState [S]: Initial state of the viewModel
 * @property uiState [MutableStateFlow]<[S]>: UiState for the composable
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
abstract class MVIBaseViewModel<S : MVIBaseState, I : MVIBaseIntent> : ViewModel() {

    private val initialState: S by lazy { initState() }
    private val _uiState: MutableStateFlow<S> by lazy { MutableStateFlow(initialState) }
    val uiState get() = _uiState
        .onStart{ onViewModelInit() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            initialState
        )

    /**
     * Initial state of the viewModel
     *
     * @return [S]
     */
    protected abstract fun initState(): S

    /**
     * Handle the intents from the composable
     */
    protected abstract fun intentHandler(intent: I)

    /**
     * Actions to be executed when the viewModel is initialized
     */
    protected open fun onViewModelInit() = Unit

    /**
     * Receive the intents from the composable
     */
    fun eventHandler(intent: I) {
        intentHandler(intent)
    }

    /**
     * Update the uiState
     *
     * @param handler [suspend] (intent: S) -> S: handler to update the uiState
     */
    protected fun updateUi(
        handler: suspend (intent: S) -> S,
    ) = execute {
        _uiState.update { handler(it) }
    }

    /**
     * Execute the actions in the viewModelScope
     *
     * @param dispatcher [CoroutineDispatcher]: dispatcher to execute the action
     * @param action [suspend] (): Unit: action to be executed
     */
    protected fun execute(
        dispatcher: CoroutineDispatcher = Dispatchers.Main,
        action: suspend () -> Unit
    ) = viewModelScope.launch(dispatcher) { action() }

}

