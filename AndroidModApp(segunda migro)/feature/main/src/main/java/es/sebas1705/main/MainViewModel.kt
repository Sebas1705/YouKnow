package es.sebas1705.main


import android.app.Application
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.analytics.AnalyticsUsesCases
import es.sebas1705.analytics.config.EventLog
import es.sebas1705.common.managers.LogType
import es.sebas1705.common.classes.mvi.MVIBaseIntent
import es.sebas1705.common.classes.mvi.MVIBaseState
import es.sebas1705.common.classes.mvi.MVIBaseViewModel
import es.sebas1705.common.theme.ThemeContrast
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.common.utlis.extensions.types.log
import es.sebas1705.main.MainIntent.ChargeData
import es.sebas1705.main.MainIntent.FinishSplashScreen
import es.sebas1705.settings.SettingUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel of the Main Screen.
 *
 * @param authUsesCases [AuthUsesCases]: Use cases of the authentication.
 * @param settingUsesCases [SettingUsesCases]: Use cases of the Settings.
 * @param analyticsUsesCases [AnalyticsUsesCases]: Use cases of the Analytics.
 * @param application [Application]: Application
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val authUsesCases: AuthUsesCases,
    private val settingUsesCases: SettingUsesCases,
    private val analyticsUsesCases: AnalyticsUsesCases,
    private val application: Application
) : MVIBaseViewModel<MainState, MainIntent>() {

    override fun initState(): MainState = MainState.default()

    override fun intentHandler(intent: MainIntent) {
        when (intent) {
            is ChargeData -> chargeData()
            is FinishSplashScreen -> finishSlashScreen()
        }
    }


    //Actions:
    /**
     * Action associated with [MainIntent.ChargeData] that will charge the data of the Main Screen.
     *
     * @see [MainIntent.ChargeData]
     */
    private fun chargeData() = execute(Dispatchers.IO) {
        try {
            log("Start chargeData", LogType.INFO)
            val time = System.currentTimeMillis()
            setConnectivityCallback()
            execute(Dispatchers.IO) {
                settingUsesCases.readMusicVolume().collect { data ->
                    updateUi {
                        it.copy(
                            musicVolume = data
                        )
                    }
                }
            }
            execute(Dispatchers.IO) {
                settingUsesCases.readSoundVolume().collect { data ->
                    updateUi {
                        it.copy(
                            soundVolume = data
                        )
                    }
                }
            }
            execute(Dispatchers.IO) {
                settingUsesCases.readAppContrast().collect { data ->
                    updateUi {
                        it.copy(
                            themeContrast = data
                        )
                    }
                }
            }
            execute(Dispatchers.IO) {
                settingUsesCases.readFirstTime().collect { data ->
                    val millis = System.currentTimeMillis() - time
                    val millisLog = System.currentTimeMillis()
                    log("End chargeData", LogType.INFO)
                    analyticsUsesCases.logEvent(
                        EventLog.ChargeTime,
                        Bundle().apply {
                            putLong("Time (millis)", millis)
                            putLong("TimeLog (millis)", System.currentTimeMillis() - millisLog)
                        }
                    )
                    updateUi {
                        it.copy(
                            startDestination =
                            if (!data)
                                AppGraph.GuideScreen
                            else if (authUsesCases.getFirebaseUser() != null)
                                AppGraph.HomeNavigation
                            else
                                AppGraph.AuthNavigation,
                            isSplashVisible = false
                        )
                    }
                }
            }
        } catch (e: Exception) {
            application.printTextInToast(
                application.getString(es.sebas1705.core.resources.R.string.core_resources_error_loading_data)
                        + e.message.toString()
            )
        }
    }


    /**
     * Action associated with [MainIntent.FinishSplashScreen] that will hide the Main Screen.
     *
     * @see [MainIntent.FinishSplashScreen]
     */
    private fun finishSlashScreen() {
        updateUi {
            it.copy(isSplashVisible = false)
        }
    }

    //Privates:
    /**
     * Set the connectivity callback to check the network status.
     *
     * @see [ConnectivityManager.NetworkCallback]
     */
    private fun setConnectivityCallback() {
        val connectivityManager = application.getSystemService(ConnectivityManager::class.java)
        val activeNetwork = connectivityManager.activeNetwork
        val isConnected = activeNetwork != null

        updateUi {
            it.copy(isNetworkAvailable = isConnected)
        }
        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                updateUi {
                    it.copy(isNetworkAvailable = true)
                }
            }

            override fun onLost(network: Network) {
                updateUi {
                    it.copy(isNetworkAvailable = false)
                }
                //Log.i("Network", "The application no longer has a default network. The last default network was $network")
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                //Log.i("Network", "The default network changed capabilities: $networkCapabilities")
            }

            override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
                //Log.i("Network", "The default network changed link properties: $linkProperties")
            }
        })

    }

}

/**
 * Data class that represents the state of the Main Screen.
 *
 * @param startDestination [Any]: Start destination of the app.
 * @param isSplashVisible [Boolean]: Indicates if the splash screen is visible.
 * @param isNetworkAvailable [Boolean]: Indicates if the network is available.
 * @param themeContrast [ThemeContrast]: Theme contrast of the app.
 * @param musicVolume [Float]: Music volume of the app.
 * @param soundVolume [Float]: Sound volume of the app.
 *
 * @see MVIBaseState
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class MainState(
    var startDestination: AppGraph,
    var isSplashVisible: Boolean,
    var isNetworkAvailable: Boolean,
    var themeContrast: ThemeContrast,
    var musicVolume: Float,
    var soundVolume: Float
) : MVIBaseState {
    companion object {

        /**
         * Default state of the Main Screen.
         *
         * @return [MainState]: Default state of the Main Screen.
         */
        fun default() = MainState(
            startDestination = AppGraph.AuthNavigation,
            isSplashVisible = true,
            isNetworkAvailable = true,
            themeContrast = DefaultValuesDS.APP_UI_CONTRAST,
            musicVolume = DefaultValuesDS.MUSIC_VOLUME,
            soundVolume = DefaultValuesDS.SOUND_VOLUME
        )
    }
}

/**
 * Sealed interface that represents the possible intents of the Main Screen.
 *
 * @property ChargeData [MainIntent.ChargeData]: Intent to charge the data of the Main Screen.
 * @property FinishSplashScreen [MainIntent.FinishSplashScreen]: Intent to hide the Main Screen.
 *
 * @see MVIBaseIntent
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
sealed interface MainIntent : MVIBaseIntent {

    data object ChargeData : MainIntent

    data object FinishSplashScreen : MainIntent
}

