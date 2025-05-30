package es.sebas1705.datastore.config
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
import androidx.datastore.preferences.preferencesDataStore
import es.sebas1705.youknow.data.local.datastore.config.SettingsDS

/**
 * Data store initialization by a context
 *
 * @receiver [Context]
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
val Context.dataStore by preferencesDataStore(name = SettingsDS.USER_SETTINGS_DATASTORE)