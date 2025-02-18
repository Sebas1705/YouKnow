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

import es.sebas1705.common.games.Languages
import es.sebas1705.common.theme.ThemeContrast
import es.sebas1705.datastore.config.DefaultValuesDS.APP_UI_CONTRAST
import es.sebas1705.datastore.config.DefaultValuesDS.FIRST_TIME
import es.sebas1705.datastore.config.DefaultValuesDS.GAME_LANGUAGE
import es.sebas1705.datastore.config.DefaultValuesDS.MUSIC_VOLUME
import es.sebas1705.datastore.config.DefaultValuesDS.SOUND_VOLUME

/**
 * Default values for the data store
 *
 * @property FIRST_TIME [Boolean]: First time
 * @property MUSIC_VOLUME [Float]: App music volume
 * @property SOUND_VOLUME [Float]: App sound volume
 * @property APP_UI_CONTRAST [ThemeContrast]: App contrast
 * @property GAME_LANGUAGE [Languages]: Game language
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
object DefaultValuesDS {
    const val FIRST_TIME = false
    const val MUSIC_VOLUME = 1.0f
    const val SOUND_VOLUME = 1.0f
    val APP_UI_CONTRAST = ThemeContrast.Low
    val GAME_LANGUAGE = Languages.EN
}
