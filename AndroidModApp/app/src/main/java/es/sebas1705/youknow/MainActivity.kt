package es.sebas1705.youknow
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

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import es.sebas1705.common.managers.LogType
import es.sebas1705.common.utlis.extensions.types.log
import es.sebas1705.main.MainScreen
import es.sebas1705.services.BackgroundMusicService


/**
 * Main activity of the app
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var musicService: BackgroundMusicService? = null
    private var isBound = false

    /**
     * Create the activity and set the content to the SplashScreen
     * before the app is ready enable the edge to edge and set on the
     * decor view the listener to hide the system bars
     *
     * @param savedInstanceState [Bundle]: the saved instance state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, BackgroundMusicService::class.java)
        startService(intent)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)

        enableEdgeToEdge()
        window.decorView.apply {
            setOnApplyWindowInsetsListener { _, insets ->
                hideBarsAfterDelay()
                insets
            }
            windowInsetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            windowInsetsController?.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        setContent {
            MainScreen(
                onVolumeChange = { musicService?.setVolume(it) },
                onMusicChange = { song, volume -> musicService?.playSong(song, volume) }
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        musicService?.pauseMusic()
        if (isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
    }

    override fun onPause() {
        super.onPause()
        musicService?.pauseMusic()
        log("onPause", LogType.WARNING)
    }

    override fun onResume() {
        super.onResume()
        musicService?.resumeMusic()
        log("onResume", LogType.WARNING)
    }

    override fun onStop() {
        super.onStop()
        musicService?.pauseMusic()
        log("onStop", LogType.WARNING)
    }

    override fun onRestart() {
        super.onRestart()
        musicService?.resumeMusic()
        log("onRestart", LogType.WARNING)
    }

    /**
     * Handler to hide the system bars after a delay
     */
    private val hideHandler = Handler(Looper.getMainLooper())

    /**
     * Hide the system bars
     */
    private fun hideSystemBars() {
        window.decorView.windowInsetsController?.apply {
            log("Hiding system bars", LogType.INFO)
            hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    /**
     * Hide the system bars after a delay
     */
    private fun hideBarsAfterDelay() {
        hideHandler.postDelayed(
            { hideSystemBars() },
            2000
        )
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BackgroundMusicService.LocalBinder
            musicService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            musicService = null
            isBound = false
        }
    }

}
