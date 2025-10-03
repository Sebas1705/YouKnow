package es.sebas1705.domain.providers

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import es.sebas1705.core.resources.Sounds

/**
 * Object to provide a SoundPool instance and manage its volume.
 *
 * @since 0.1.0
 * @author Sebas1705 03/07/2025
 */
object SoundPoolProvider {

    val mapId = mutableMapOf<Sounds, Int>()

    const val MAX_SOUNDS_SIMULTANEITY = 5
    const val PRIORITY_SOUND = 1
    const val LOOP_N = 0
    const val RATE = 1f

    private var soundPool: SoundPool? = null
    private var volume: Float = 1.0f

    fun getSoundPool(context: Context): Pair<SoundPool, Float> {
        if (soundPool == null) {
            soundPool = SoundPool
                .Builder()
                .setMaxStreams(MAX_SOUNDS_SIMULTANEITY)
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
                .build()
            // Load sounds:
            Sounds.entries.forEach {
                mapId[it] = soundPool!!.load(context, it.soundRes, PRIORITY_SOUND)
            }
        }
        return Pair(soundPool!!, volume)
    }

    fun setVolume(newVolume: Float) {
        volume = newVolume
    }
}

