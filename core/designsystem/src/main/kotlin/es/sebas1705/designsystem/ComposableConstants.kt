package es.sebas1705.designsystem

object ComposableConstants {
    const val MAX_GROUP = 50
    const val MAX_SOUNDS_SIMULTANEITY = 5

    // SoundPool playback parameters:
    const val PRIORITY_SOUND = 1
    const val LOOP_N = 0
    const val RATE = 1.0f

    // Raw resources for button sounds. They were replaced by 0 during the migration to make it
    // compile, and SoundPool.load(context, 0, …) throws Resources.NotFoundException (home crashed).
    val NAV_BUTTON_SOUND = es.sebas1705.core.resources.R.raw.click_tap
    val GAME_BUTTON_SOUND = es.sebas1705.core.resources.R.raw.click_arcade
}
