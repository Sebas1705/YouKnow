package es.sebas1705.common.utlis.extensions.composables
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
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun Context.printTextInToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.playSound(soundRes: Int) {
    val mp = MediaPlayer.create(this, soundRes)
    mp.start()
}