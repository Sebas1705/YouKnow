package es.sebas1705.designsystem

import es.iberext.youknow.core.designsystem.R

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
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
object ComposableConstants {
    const val MAX_GROUP = 50

    //Sounds:
    const val MAX_SOUNDS_SIMULTANEITY = 100
    const val PRIORITY_SOUND = 1
    const val LOOP_N = 0
    const val RATE = 1f
    val BUTTON_SOUND = R.raw.click_casual
    val ICON_BUTTON_SOUND = R.raw.click_instant
    val FAB_BUTTON_SOUND = R.raw.sound_boing
    val RADIO_BUTTON_SOUND = R.raw.click_clock
    val NAV_BUTTON_SOUND = R.raw.click_tap
    val GAME_BUTTON_SOUND = R.raw.click_arcade
    val WIN_SOUND = R.raw.sound_win
    val LOSE_SOUND = R.raw.sound_lose
}