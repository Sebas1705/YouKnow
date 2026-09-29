package es.sebas1705.domain.managers

import android.content.Context
import android.media.MediaPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import es.sebas1705.core.resources.Musics
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manager to handle MediaPlayer operations
 *
 * This [mediaPlayer] is a Hilt singleton, so it outlives any single Activity instance — nothing
 * about a plain [MediaPlayer] knows whether the app is actually visible. Every call that would
 * start audible playback is gated behind [isForeground] (kept in sync by
 * [es.sebas1705.app.MainActivity]'s `onResume`/`onPause` through [setForeground]), so the shared
 * player can never start or resume playback while the app is backgrounded. Before this guard
 * existed, that surfaced as music playing after leaving/closing the app, and music starting on
 * its own (a composable's `DisposableEffect` calling [changeSong] while the app was hidden, e.g.
 * a screen entered or left during backgrounding).
 *
 * @property mediaPlayer The MediaPlayer instance used to play audio
 * @property context The application context used to access resources
 *
 * @since 0.1.0
 * @author Sebas1705 05/07/2025
 */
@Singleton
class MediaPlayerManager @Inject constructor(
    private val mediaPlayer: MediaPlayer,
    @param:ApplicationContext private val context: Context
){

    private var isForeground = false

    /** The track [changeSong] was asked to play while backgrounded, resumed once visible again. */
    private var pendingSong: Musics? = null

    /**
     * Keeps the player in sync with the app's visibility: pauses immediately when leaving the
     * foreground, and resumes (or starts whatever track was requested meanwhile) on returning.
     */
    fun setForeground(foreground: Boolean) {
        isForeground = foreground
        if (foreground) {
            pendingSong?.let {
                pendingSong = null
                changeSong(it)
            } ?: play()
        } else {
            pause()
        }
    }

    fun play() {
        if (isForeground && !mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }

    fun pause() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    fun stop() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.prepare() // Prepare the MediaPlayer for future use
        }
    }

    fun setVolume(leftVolume: Float, rightVolume: Float) {
        mediaPlayer.setVolume(leftVolume, rightVolume)
    }

    fun setVolume(volume: Float) {
        mediaPlayer.setVolume(volume, volume)
    }

    fun changeSong(music: Musics) {
        mediaPlayer.reset()
        val afd = context.resources.openRawResourceFd(music.resourceId)
        mediaPlayer.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
        mediaPlayer.prepare()
        if (isForeground) {
            mediaPlayer.start()
        } else {
            // Remembered, not played: switching tracks while hidden must stay silent.
            pendingSong = music
        }
    }
}
