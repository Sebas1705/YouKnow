package es.sebas1705.designsystem

import es.sebas1705.core.designsystem.R



/**
 * Constants of the composable package
 *
 * @property MAX_GROUP [Int]: Max number of groups that can be created
 * @property MAX_SOUNDS_SIMULTANEITY [Int]: Max number of sounds that can be played simultaneously
 * @property BUTTON_SOUND [Int]: Button sound
 * @property ICON_BUTTON_SOUND [Int]: Icon button sound
 * @property FAB_BUTTON_SOUND [Int]: FAB button sound
 * @property RADIO_BUTTON_SOUND [Int]: Radio button sound
 * @property NAV_BUTTON_SOUND [Int]: Navigation button sound
 * @property GAME_BUTTON_SOUND [Int]: Game button sound
 * @property WIN_SOUND [Int]: Win sound
 * @property LOSE_SOUND [Int]: Lose sound
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
object ComposableConstants {
    const val MAX_GROUP = 50

    //Sounds:
    const val MAX_SOUNDS_SIMULTANEITY = 100
    const val PRIORITY_SOUND = 1
    const val LOOP_N = 0
    const val RATE = 1f
    val BUTTON_SOUND = es.sebas1705.core.resources.R.raw.click_casual
    val ICON_BUTTON_SOUND = es.sebas1705.core.resources.R.raw.click_instant
    val FAB_BUTTON_SOUND = es.sebas1705.core.resources.R.raw.sound_bowing
    val RADIO_BUTTON_SOUND = es.sebas1705.core.resources.R.raw.click_clock
    val NAV_BUTTON_SOUND = es.sebas1705.core.resources.R.raw.click_tap
    val GAME_BUTTON_SOUND = es.sebas1705.core.resources.R.raw.click_arcade
    val WIN_SOUND = es.sebas1705.core.resources.R.raw.sound_win
    val LOSE_SOUND = es.sebas1705.core.resources.R.raw.sound_lose
}