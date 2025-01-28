package es.sebas1705.youknow.core.utlis.extensions.primitives
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

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.text.Normalizer

/**
 * Check if a string is an image url
 *
 * @receiver [String]: the string to check
 *
 * @return [Boolean]: true if the string is an image url, false otherwise
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun String.isImageUrl(): Boolean {
    var isImage = false
    try {
        val client = OkHttpClient()
        val request = Request.Builder().url(this).build()
        val response: Response = client.newCall(request).execute()
        val contentType = response.header("Content-Type")
        Log.i("isImageUrl", "Content-Type: $contentType")
        isImage = contentType != null && contentType.startsWith("image/")
    } catch (e: Exception) {
        Log.e("isImageUrl", e.message.toString())
        isImage = false
    }
    Log.i("isImageUrl", "Is image: $isImage")
    return isImage
}

/**
 * Normalize a string
 *
 * @receiver [String]: the string to normalize
 *
 * @return [String]: the string normalized
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun String.normalizeString(): String {
    val normalized = Normalizer.normalize(this, Normalizer.Form.NFD)
    return normalized.replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "").lowercase()
}

/**
 * Decode a string from URL format using [URLDecoder.decode]
 * and standard charset [StandardCharsets.UTF_8]
 *
 * @receiver [String] to decode
 *
 * @return [String] decoded
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun String.decodeUrl(): String {
    return URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
}
