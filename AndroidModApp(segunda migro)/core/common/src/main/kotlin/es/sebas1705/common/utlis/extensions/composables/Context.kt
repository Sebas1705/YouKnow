package es.sebas1705.common.utlis.extensions.composables

import android.content.Context
import android.media.MediaPlayer
import android.widget.Toast

/**
 * Extension function to display a toast message.
 *
 * @receiver [Context]: The context in which to display the toast.
 * @param message [String]: The message to display.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
fun Context.printTextInToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * Extension function to play a sound.
 *
 * @receiver [Context]: The context from which to play the sound.
 * @param soundRes [Int]: The resource ID of the sound to play.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
fun Context.playSound(soundRes: Int) {
    val mediaPlayer = MediaPlayer.create(this, soundRes)
    mediaPlayer?.start()
    // Set a listener to release the player once playback is complete
    mediaPlayer?.setOnCompletionListener {
        it.release()
    }
}
