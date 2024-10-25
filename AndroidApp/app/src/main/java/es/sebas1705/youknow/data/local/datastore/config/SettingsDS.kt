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

/**
 * Datastore settings
 *
 * @property USER_SETTINGS_DATASTORE [String]: User settings
 * @property FIRST_TIME_KEY [String]: First time
 * @property APP_VOLUME_KEY [String]: App volume
 * @property APP_CONTRAST_KEY [String]: App contrast
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
object SettingsDS{
    const val USER_SETTINGS_DATASTORE = "userSettings"
    const val FIRST_TIME_KEY = "firstTime"
    const val APP_VOLUME_KEY = "appVolume"
    const val APP_CONTRAST_KEY = "appContrast"
}