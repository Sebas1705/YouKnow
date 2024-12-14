package es.sebas1705.youknow.presentation.features.app.viewmodels
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

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.domain.usecases.DatastoreUsesCases
import es.sebas1705.youknow.domain.usecases.user.AuthUsesCases
import es.sebas1705.youknow.domain.usecases.user.UserUsesCases
import es.sebas1705.youknow.presentation.features.app.navigation.AuthNavigation
import es.sebas1705.youknow.presentation.features.app.navigation.GuideScreen
import es.sebas1705.youknow.presentation.features.app.navigation.HomeNavigation
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for Splash Screen that will decide the start destination of the app
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
class SplashViewModel @Inject constructor(
    private val authUsesCases: AuthUsesCases,
    private val datastoreUsesCases: DatastoreUsesCases
) : MVIBaseViewModel<SplashState, SplashIntent>() {

    override fun initState(): SplashState = SplashState.default()

    override fun intentHandler(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.ChargeCloudData -> chargeStartDestination()
            is SplashIntent.FinishSplashScreen -> finishSplashScreen()
            is SplashIntent.SetConnectivityCallback -> setConnectivityCallback(intent)
        }
    }


    //Actions:
    /**
     * Action associated with [SplashIntent.ChargeCloudData] that will decide the start destination of the app.
     * Execute the [DatastoreUsesCases.readFirstTime] UseCase to check if the app is opened for the first time
     * using a [Dispatchers.IO] context.
     *
     * @see [SplashIntent.ChargeCloudData]
     */
    private fun chargeStartDestination() {
        execute(Dispatchers.IO) {
            datastoreUsesCases.readFirstTime().collect { data ->
                updateUi {
                    it.copy(
                        startDestination =
                        if (!data) GuideScreen
                        else if (authUsesCases.getFirebaseUser() != null) HomeNavigation else AuthNavigation
                    )
                }
            }
        }
    }

    /**
     * Action associated with [SplashIntent.FinishSplashScreen] that will hide the Splash Screen.
     *
     * @see [SplashIntent.FinishSplashScreen]
     */
    private fun finishSplashScreen() {
        updateUi {
            it.copy(isSplashScreenVisible = false)
        }
    }

    /**
     * Set the connectivity callback to check the network status.
     *
     * @see [ConnectivityManager.NetworkCallback]
     */
    private fun setConnectivityCallback(intent: SplashIntent.SetConnectivityCallback) {
        val connectivityManager = intent.context.getSystemService(ConnectivityManager::class.java)
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
                //Log.i("Network", "The default network is now: $network")
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
 * Data class that represents the state of the Splash Screen.
 *
 * @param startDestination [Any]: Start destination of the app.
 * @param isSplashScreenVisible [Boolean]: Visibility of the Splash Screen.
 *
 * @see MVIBaseState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class SplashState(
    var startDestination: Any,
    var isSplashScreenVisible: Boolean,
    var isNetworkAvailable: Boolean
) : MVIBaseState {
    companion object {

        /**
         * Default state of the Splash Screen.
         *
         * @return [SplashState]: Default state of the Splash Screen.
         */
        fun default() = SplashState(
            startDestination = GuideScreen,
            isSplashScreenVisible = true,
            isNetworkAvailable = true
        )
    }
}

/**
 * Sealed interface that represents the possible intents of the Splash Screen.
 *
 * @property ChargeCloudData [SplashIntent.ChargeCloudData]: Intent to charge the start destination of the app.
 * @property FinishSplashScreen [SplashIntent.FinishSplashScreen]: Intent to hide the Splash Screen.
 *
 * @see MVIBaseIntent
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface SplashIntent : MVIBaseIntent {
    data object ChargeCloudData : SplashIntent
    data object FinishSplashScreen : SplashIntent
    data class SetConnectivityCallback(val context: Context) : SplashIntent
}

