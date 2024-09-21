package es.sebas1705.youknowapp.presentation.viewmodel

import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknowapp.data.source.remote.analyticsManager.EventLog
import es.sebas1705.youknowapp.domain.usecases.AnalyticsUsesCases
import es.sebas1705.youknowapp.domain.usecases.AuthUsesCases
import es.sebas1705.youknowapp.domain.usecases.LocalUsesCases
import es.sebas1705.youknowapp.presentation.navigation.AuthNavigation
import es.sebas1705.youknowapp.presentation.navigation.GuideScreen
import es.sebas1705.youknowapp.presentation.navigation.HomeNavigation
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authUsesCases: AuthUsesCases,
    private val localUsesCases: LocalUsesCases,
    private val analyticsUsesCases: AnalyticsUsesCases
) : ViewModel() {

    var startDestination by mutableStateOf<Any>(GuideScreen)
        private set

    var isSplashScreenVisible by mutableStateOf(true)

    fun chargeCloudData(
        percentage: Float,
        onChargingInc: (Float) -> Unit,
        onTimeMeasured: (Long) -> Unit
    ) {
        viewModelScope.launch {
            val time = measureNanoTime {
                val job = async {
                    localUsesCases.readFirstTime().onEach {
                        startDestination =
                            if (!it) GuideScreen
                            else
                                if (authUsesCases.isUserLogged()) HomeNavigation
                                else AuthNavigation
                    }
                }
                onChargingInc(0.25f * percentage)
                awaitAll(job)
                onChargingInc(0.75f * percentage)
            }
            onTimeMeasured(time)
        }
    }

    fun logTime(nano: Long) {
        val bundle = Bundle().apply {
            putLong("Seconds", nano.div(1_000_000_000))
            putLong("Millis", nano.div(1_000_000))
            putLong("Nano", nano)
        }
        analyticsUsesCases.logEvent(EventLog.CHARGE_TIME,bundle)
    }

}