package es.sebas1705.youknow.data.firebase.realtime.config
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

import kotlin.reflect.full.memberProperties

/**
 * Extension function that check if an object is savable in the Realtime Database
 *
 * @receiver [T]: Object to check
 *
 * @return [Boolean]: True if the object is savable, false otherwise
 *
 * @see SettingsRT
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun <T : Any> T.isRealtimeSavable(): Boolean {
    return when (this) {
        is Boolean, is Long, is Double, is String, is Map<*, *>, is List<*> -> true             // Primitive types
        else -> return this::class.isData && this::class.memberProperties.all { property ->     // Data classes
            val value = property.call(this)!!
            value.isRealtimeSavable()
        }
    }
}