package es.sebas1705.main.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.common.classes.mvi.MVIBaseViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : MVIBaseViewModel<MainState, MainIntent>() {

    override fun initState(): MainState = MainState()

    override fun intentHandler(intent: MainIntent) {
        when (intent) {
            is MainIntent.ChargeData -> chargeData()
        }
    }

    private fun chargeData() = execute(Dispatchers.IO) {
        updateUi {
            it.copy(splashFinished = true)
        }
    }
}
