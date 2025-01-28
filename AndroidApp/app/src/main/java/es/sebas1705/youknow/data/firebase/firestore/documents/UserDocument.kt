package es.sebas1705.youknow.data.firebase.firestore.documents
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
 * Data class to represent the user document in Firestore
 *
 * @property email [String]: Email
 * @property provider [Int]: Provider
 * @property nickName [String]: Nickname
 * @property photoUrl [String]: Photo url
 * @property groupId [String]: Group id
 * @property points [Int]: Points
 * @property credits [Int]: Credits
 * @property friends [List]<[String]>: Friends
 * @property logged [Boolean]: Logged
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class UserDocument(
    val email: String = "",
    val provider: Int = 0,
    val nickName: String = "",
    val photoUrl: String? = null,
    val groupId: String? = null,
    val points: Int = 0,
    val credits: Int = 0,
    val logged: Boolean = false
)