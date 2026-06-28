package es.sebas1705.datastore.config


import es.sebas1705.common.theme.ThemeContrast
import es.sebas1705.datastore.config.DefaultValuesDS.APP_UI_CONTRAST
import es.sebas1705.datastore.config.DefaultValuesDS.FIRST_TIME
import es.sebas1705.datastore.config.DefaultValuesDS.GAME_LANGUAGE
import es.sebas1705.datastore.config.DefaultValuesDS.MUSIC_VOLUME
import es.sebas1705.datastore.config.DefaultValuesDS.SOUND_VOLUME
import es.sebas1705.resources.games.Languages

/**
 * Default values for the data store
 *
 * @property FIRST_TIME [Boolean]: First time
 * @property MUSIC_VOLUME [Float]: App music volume
 * @property SOUND_VOLUME [Float]: App sound volume
 * @property APP_UI_CONTRAST [ThemeContrast]: App contrast
 * @property GAME_LANGUAGE [Languages]: Game language
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
object DefaultValuesDS {
    const val FIRST_TIME: Boolean = false
    const val MUSIC_VOLUME = 1.0f
    const val SOUND_VOLUME = 1.0f
    val APP_UI_CONTRAST = ThemeContrast.Low
    val GAME_LANGUAGE = Languages.EN
}
