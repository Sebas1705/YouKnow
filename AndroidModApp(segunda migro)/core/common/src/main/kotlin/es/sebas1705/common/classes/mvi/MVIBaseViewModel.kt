package es.sebas1705.common.classes.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class MVIBaseViewModel<S : MVIBaseState, I : MVIBaseIntent> : ViewModel() {
    protected val _uiState: MutableStateFlow<S> by lazy { MutableStateFlow(initState()) }
    val uiState: StateFlow<S> by lazy { _uiState.asStateFlow() }

    abstract fun initState(): S
    abstract fun intentHandler(intent: I)

    fun sendIntent(intent: I) = intentHandler(intent)
    fun eventHandler(intent: I) = intentHandler(intent)

    protected fun updateUi(transform: (S) -> S) { _uiState.update(transform) }

    protected fun execute(dispatcher: CoroutineDispatcher = Dispatchers.Main, action: suspend () -> Unit) {
        viewModelScope.launch(dispatcher) { action() }
    }
}
