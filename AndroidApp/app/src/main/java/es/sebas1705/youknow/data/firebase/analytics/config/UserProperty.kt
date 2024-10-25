package es.sebas1705.youknow.data.firebase.analytics.config
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
 * Sealed class to represent the user properties
 *
 * @param tag [String]: Tag of the user property
 *
 * @property FavoriteCategory [UserProperty]: Favorite category user property
 * @property FavoriteDifficulty [UserProperty]: Favorite difficulty user property
 * @property FavoriteType [UserProperty]: Favorite type user property
 * @property PlayerLevel [UserProperty]: Player level user property
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed class UserProperty(val tag: String) {
    object FavoriteCategory: UserProperty("favorite_category")
    object FavoriteDifficulty: UserProperty("favorite_difficulty")
    object FavoriteType: UserProperty("favorite_type")
    object PlayerLevel: UserProperty("player_level")
}