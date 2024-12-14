package es.sebas1705.youknow.data.firebase.firestore.config
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
 * Settings for Firestore
 *
 * @property USERS_COLLECTION_NAME [String]: Users collection reference
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
object SettingsFS {
    const val USERS_COLLECTION_NAME = "users"
    const val USERS_LOGGED_FIELD = "logged"
    const val USERS_CREDITS_FIELD = "credits"
    const val USERS_POINTS_FIELD = "points"
    const val USERS_GROUP_FIELD = "groupId"
    const val USERS_PHOTO_FIELD = "photoUrl"
    const val USERS_NICKNAME_FIELD = "nickName"

    const val ERROR_GENERIC_MESSAGE_EX = "An error occurred on firestore by an exception"
    const val ERROR_GENERIC_MESSAGE_FAIL = "An error occurred on firestore by failure listener"
    const val ERROR_CREDITS_NEGATIVE = "The credits can't be negative"
    const val USER_NOT_FOUND = "User not found"
}