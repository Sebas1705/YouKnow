package es.sebas1705.domain.providers

import android.media.SoundPool
import es.sebas1705.core.resources.Sounds

/**
 * Extension function to play a sound from a SoundPool with a given volume.
 *
 * @param sound [es.sebas1705.core.resources.Sounds]: Sound to play.
 * @param volume [Float]: Volume to play the sound at (0.0 to 1.0).
 *
 * @since 0.1.0
 * @author Sebas1705 03/07/2025
 */
fun Pair<SoundPool, Float>.play(
    sound: Sounds,
    volume: Float
) {
    this.first.play(
        SoundPoolProvider.mapId[sound] ?: return,
        volume,
        volume,
        SoundPoolProvider.PRIORITY_SOUND,
        SoundPoolProvider.LOOP_N,
        SoundPoolProvider.RATE
    )
}