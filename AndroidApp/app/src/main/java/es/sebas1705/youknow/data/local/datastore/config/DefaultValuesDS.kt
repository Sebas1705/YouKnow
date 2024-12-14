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

import es.sebas1705.youknow.core.classes.theme.ThemeContrast

/**
 * Default values for the data store
 *
 * @property FIRST_TIME [Boolean]: First time
 * @property APP_VOLUME [Float]: App volume
 * @property APP_Ui_CONTRAST [ThemeContrast]: App contrast
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
object DefaultValuesDS {
    const val FIRST_TIME = false
    const val APP_VOLUME = 1.0f
    val APP_UI_CONTRAST = ThemeContrast.Low
}
