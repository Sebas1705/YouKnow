package es.sebas1705.youknow.core.root
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

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import es.sebas1705.youknow.presentation.features.splash.screens.SplashScreen


/**
 * Main activity of the app
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Create the activity and set the content to the SplashScreen
     * before the app is ready enable the edge to edge and set on the
     * decor view the listener to hide the system bars
     *
     * @param savedInstanceState [Bundle]: the saved instance state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.apply {
            setOnApplyWindowInsetsListener { _, insets ->
                hideBarsAfterDelay()
                insets
            }
            windowInsetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            windowInsetsController?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        setContent {
            SplashScreen()
        }
    }

    /**
     * Handler to hide the system bars after a delay
     */
    private val hideHandler = Handler(Looper.getMainLooper())

    /**
     * Hide the system bars
     */
    private fun hideSystemBars(){
        window.decorView.windowInsetsController?.apply{
            hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    /**
     * Hide the system bars after a delay
     */
    private fun hideBarsAfterDelay() {
        hideHandler.postDelayed(
            {hideSystemBars()},
            2000
        )
    }
}
