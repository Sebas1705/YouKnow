package es.sebas1705.common.utlis.extensions.composables

import android.content.Context
import android.media.MediaPlayer
import android.widget.Toast

/**
 * Print a text generating a [Toast]
 *
 * @receiver [Context]: context of the app
 *
 * @param message [String]: the message to print
 *
 * @see Toast
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
fun Context.printTextInToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.playSound(soundRes: Int) {
    val mp = MediaPlayer.create(this, soundRes)
    mp.start()
}