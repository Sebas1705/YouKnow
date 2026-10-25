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

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    // Posted to Main instead of invoked here: this block runs inside the superclass constructor,
    // before Kotlin has assigned the subclass's constructor properties. Calling onInit() directly let
    // subclasses launch work on Dispatchers.IO that could read a not-yet-injected dependency (null)
    // when the IO thread won the race against the constructor (flaky NPE on 2-vCPU CI runners).
    // Dispatchers.Main (not .immediate) always dispatches, so onInit() runs once construction is done.
    init { execute { onInit() } }

    /**
     * Hook for the first load of the ViewModel. It runs on the main dispatcher right after the
     * ViewModel has been fully constructed, so every injected dependency is available here.
     */
    open fun onInit() {}

    abstract fun initState(): S
    abstract fun intentHandler(intent: I)

    fun sendIntent(intent: I) = intentHandler(intent)
    fun eventHandler(intent: I) = intentHandler(intent)

    protected fun updateUi(transform: (S) -> S) { _uiState.update(transform) }

    protected fun setLoading(value: Boolean) { _loading.value = value }

    protected fun execute(dispatcher: CoroutineDispatcher = Dispatchers.Main, action: suspend () -> Unit) {
        viewModelScope.launch(dispatcher) { action() }
    }
}
