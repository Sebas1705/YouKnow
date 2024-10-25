package es.sebas1705.youknow.data.local.datastore.config
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

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey

/**
 * Datastore keys for the preferences
 *
 * @property FIRST_TIME [booleanPreferencesKey]: First time
 * @property APP_VOLUME [floatPreferencesKey]: App volume
 * @property APP_CONTRAST [intPreferencesKey]: App contrast
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
object KeysDS{
    val FIRST_TIME = booleanPreferencesKey(name = SettingsDS.FIRST_TIME_KEY)
    val APP_VOLUME = floatPreferencesKey(name = SettingsDS.APP_VOLUME_KEY)
    val APP_CONTRAST = intPreferencesKey(name = SettingsDS.APP_CONTRAST_KEY)
}