package es.sebas1705.core.resources

/**
 * Enum class to define the different sounds used in the app.
 *
 * @since 0.1.0
 * @author Sebas1705 03/07/2025
 */
enum class Sounds(val soundRes: Int) {
    BUTTON(R.raw.click_casual),
    ICON_BUTTON(R.raw.click_instant),
    FAB_BUTTON(R.raw.sound_bowing),
    RADIO_BUTTON(R.raw.click_clock),
    NAV_BUTTON(R.raw.click_tap),
    GAME_BUTTON(R.raw.click_arcade)
}