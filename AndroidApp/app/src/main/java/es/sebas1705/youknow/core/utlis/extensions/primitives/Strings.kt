package es.sebas1705.youknow.core.utlis.extensions.primitives

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.text.Normalizer
import kotlin.text.startsWith

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

fun String.normalizeString(): String {
    val normalized = Normalizer.normalize(this, Normalizer.Form.NFD)
    return normalized.replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "").lowercase()
}
