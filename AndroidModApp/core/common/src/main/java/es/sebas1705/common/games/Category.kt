package es.sebas1705.common.games
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

import es.iberext.youknow.core.common.R

/**
 * Enum class that will manage the categories
 *
 * @property id [Int?]: Category id
 * @property strRes [Int]: Category string resource
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
enum class Category(val id: Int?, val strRes: Int) {
    ANY(null, es.iberext.youknow.core.resources.R.string.core_resources_any),
    GENERAL_KNOWLEDGE(9, R.string.core_common_cat_general_knowledge),
    BOOKS(10, R.string.core_common_cat_entertainment_books),
    FILMS(11, R.string.core_common_cat_entertainment_film),
    MUSIC(12, R.string.core_common_cat_entertainment_music),
    MUSICALS_AND_THEATRES(13, R.string.core_common_cat_entertainment_musicals_and_theatres),
    TELEVISION(14, R.string.core_common_cat_entertainment_television),
    VIDEO_GAMES(15, R.string.core_common_cat_entertainment_video_games),
    BOARD_GAMES(16, R.string.core_common_cat_entertainment_board_games),
    SCIENCE_AND_NATURE(17, R.string.core_common_cat_science_and_nature),
    COMPUTERS(18, R.string.core_common_cat_science_computers),
    MATHEMATICS(19, R.string.core_common_cat_science_mathematics),
    MYTHOLOGY(20, R.string.core_common_cat_mythology),
    SPORTS(21, R.string.core_common_cat_sports),
    GEOGRAPHY(22, R.string.core_common_cat_geography),
    HISTORY(23, R.string.core_common_cat_history),
    POLITICS(24, R.string.core_common_cat_politics),
    ART(25, R.string.core_common_cat_art),
    CELEBRITIES(26, R.string.core_common_cat_celebrities),
    ANIMALS(27, R.string.core_common_cat_animals),
    VEHICLES(28, R.string.core_common_cat_vehicles),
    COMICS(29, R.string.core_common_cat_entertainment_comics),
    GADGETS(30, R.string.core_common_cat_science_gadgets),
    ANIME_AND_MANGA(31, R.string.core_common_cat_entertainment_japanese_anime_and_manga),
    CARTOON_AND_ANIMATIONS(32, R.string.core_common_cat_entertainment_cartoon_and_animations);

    companion object {
        fun getCategory(id: Int): Category = entries.find { it.id == id } ?: ANY
    }
}
