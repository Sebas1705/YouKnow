package es.sebas1705.youknowapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknowapp.data.source.local.localUserManager.DefaultValues
import es.sebas1705.youknowapp.domain.usecases.AnalyticsUsesCases
import es.sebas1705.youknowapp.domain.usecases.LocalUsesCases
import es.sebas1705.youknowapp.ui.Contrast
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val localUsesCases: LocalUsesCases,
    private val analyticsUsesCases: AnalyticsUsesCases
) : ViewModel() {

    var contrastApp by mutableStateOf(DefaultValues.APP_CONTRAST)
        private set

    var volumeApp by mutableFloatStateOf(DefaultValues.APP_VOLUME)
        private set

    fun chargeSettings(
        percentage: Float,
        onChargingInc: (Float) -> Unit,
        onTimeMeasured: (Long) -> Unit
    ) {
        viewModelScope.launch {
            val time = measureNanoTime {
                val job1 = async {
                    localUsesCases.readAppContrast().onEach {
                        contrastApp = it

                    }
                }
                onChargingInc(0.25f * percentage)
                val job2 = async {
                    localUsesCases.readAppVolume().onEach {
                        volumeApp = it
                        onChargingInc(1.0f)
                    }
                }
                onChargingInc(0.25f * percentage)
                awaitAll(job1, job2)
                onChargingInc(0.5f * percentage)
            }
            onTimeMeasured(time)
        }
    }

    fun changeContrast(contrast: Contrast) {
        contrastApp = contrast
        viewModelScope.launch {
            localUsesCases.saveAppContrast(contrast)
        }
    }

    fun changeVolume(volume: Float) {
        volumeApp = volume
        viewModelScope.launch {
            localUsesCases.saveAppVolume(volume)
        }
    }


}