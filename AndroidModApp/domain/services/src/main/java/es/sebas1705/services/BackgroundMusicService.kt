package es.sebas1705.services
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

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import es.sebas1705.youknow.domain.services.R

/**
 * Service to handle the background music
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class BackgroundMusicService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    private var volume: Float = 1f
    private val songs = R.raw.music_background to R.raw.music_game

    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): BackgroundMusicService = this@BackgroundMusicService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer = MediaPlayer.create(this, R.raw.music_background)
        mediaPlayer?.isLooping = true
        mediaPlayer?.setVolume(volume, volume)
        mediaPlayer?.start()

        return START_STICKY
    }

    fun setVolume(newVolume: Float) {
        volume = newVolume.coerceIn(0f, 1f)
        mediaPlayer?.setVolume(volume, volume)
    }

    fun pauseMusic() {
        mediaPlayer?.pause()
    }

    fun resumeMusic() {
        mediaPlayer?.start()
    }

    fun playSong(back: Boolean, newVolume: Float) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(this, if (back) songs.first else songs.second).apply {
            isLooping = true
            volume = newVolume.coerceIn(0f, 1f)
            setVolume(volume, volume)
            start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}