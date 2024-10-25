package es.sebas1705.youknow.data.local.database.entities
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

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.sebas1705.youknow.data.local.database.config.SettingsDB

/**
 * Data class to represent the user data and use as entity in the database
 *
 * @property firebaseId [String]: Firebase id
 * @property email [String]: Email
 * @property provider [Int]: Provider
 * @property nickName [String]: Nickname
 * @property photoUrl [String]: Photo url
 * @property groupId [String]: Group id
 * @property points [Int]: Points
 * @property credits [Int]: Credits
 * @property friends [List]<[String]>: Friends
 *
 * @constructor Create empty User data
 */
@Entity(tableName = SettingsDB.USERS_TABLE)
data class UserEntity(
    @PrimaryKey val firebaseId: String,
    val email: String,
    val provider: Int,
    val nickName: String,
    val photoUrl: String?,
    val groupId: String?,
    val points: Int,
    val credits: Int,
    val friends: List<String>
)