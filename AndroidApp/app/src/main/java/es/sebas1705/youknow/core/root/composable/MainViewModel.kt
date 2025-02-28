package es.sebas1705.youknow.core.root.composable
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
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.enums.games.LogType
import es.sebas1705.youknow.core.classes.enums.theme.ThemeContrast
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.root.composable.MainIntent.ChargeData
import es.sebas1705.youknow.core.root.composable.MainIntent.FinishSplashScreen
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.core.utlis.extensions.types.log
import es.sebas1705.youknow.data.firebase.analytics.config.EventLog
import es.sebas1705.youknow.data.local.datastore.config.DefaultValuesDS
import es.sebas1705.youknow.domain.usecases.logs.AnalyticsUsesCases
import es.sebas1705.youknow.domain.usecases.ui.DatastoreUsesCases
import es.sebas1705.youknow.domain.usecases.user.AuthUsesCases
import es.sebas1705.youknow.presentation.navigation.AppGraph
import es.sebas1705.youknow.presentation.navigation.AppGraph.AuthNavigation
import es.sebas1705.youknow.presentation.navigation.AppGraph.GuideScreen
import es.sebas1705.youknow.presentation.navigation.AppGraph.HomeNavigation
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel of the Main Screen.
 *
 * @param authUsesCases [AuthUsesCases]: Use cases of the authentication.
 * @param datastoreUsesCases [DatastoreUsesCases]: Use cases of the Datastore.
 * @param analyticsUsesCases [AnalyticsUsesCases]: Use cases of the Analytics.
 * @param application [Application]: Application
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val authUsesCases: AuthUsesCases,
    private val datastoreUsesCases: DatastoreUsesCases,
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
                datastoreUsesCases.readMusicVolume().collect { data ->
                    updateUi {
                        it.copy(
                            musicVolume = data
                        )
                    }
                }
            }
            execute(Dispatchers.IO) {
                datastoreUsesCases.readSoundVolume().collect { data ->
                    updateUi {
                        it.copy(
                            soundVolume = data
                        )
                    }
                }
            }
            execute(Dispatchers.IO) {
                datastoreUsesCases.readAppContrast().collect { data ->
                    updateUi {
                        it.copy(
                            themeContrast = data
                        )
                    }
                }
            }
            execute(Dispatchers.IO) {
                datastoreUsesCases.readFirstTime().collect { data ->
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
                                GuideScreen
                            else if (authUsesCases.getFirebaseUser() != null)
                                HomeNavigation
                            else
                                AuthNavigation,
                            isSplashVisible = false
                        )
                    }
                }
            }
        } catch (e: Exception) {
            application.printTextInToast(
                application.getString(R.string.error_loading_data)
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
 * @author Sebastián Ramiro Entrerrios García
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
            startDestination = AuthNavigation,
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
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface MainIntent : MVIBaseIntent {

    data object ChargeData : MainIntent

    data object FinishSplashScreen : MainIntent
}

