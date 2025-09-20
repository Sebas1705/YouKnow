package es.sebas1705.models.social
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

import com.google.firebase.auth.FirebaseUser
import es.sebas1705.common.ProviderAuth

/**
 * Model to represent a user
 *
 * @property firebaseId [String]: Firebase ID of the user
 * @property email [String]: Email of the user
 * @property provider [ProviderAuth]: Provider of the user
 * @property nickName [String]: Nickname of the user
 * @property photoUrl [String]: Photo URL of the user
 * @property groupId [String]: Group ID of the user
 * @property points [Int]: Points of the user
 * @property credits [Int]: Credits of the user
 * @property friends [List]<[String]>: List of friends of the user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class UserModel(
    val firebaseId: String,
    val email: String,
    val provider: ProviderAuth,
    val nickName: String,
    val photoUrl: String?,
    val groupId: String?,
    val points: Int,
    val credits: Int
) {

    companion object {
        fun newGoogleUser(firebaseUser: FirebaseUser) = UserModel(
            firebaseId = firebaseUser.uid,
            email = firebaseUser.email!!,
            provider = ProviderAuth.GOOGLE,
            nickName = firebaseUser.displayName!!,
            photoUrl = firebaseUser.photoUrl!!.toString(),
            groupId = null,
            points = 0,
            credits = 1000
        )

        fun newEmailUser(nickName: String, firebaseUser: FirebaseUser) = UserModel(
            firebaseId = firebaseUser.uid,
            email = firebaseUser.email!!,
            provider = ProviderAuth.EMAIL,
            nickName = nickName,
            photoUrl = null,
            groupId = null,
            points = 0,
            credits = 1000
        )

        fun default(): UserModel {
            return UserModel(
                firebaseId = "firebaseId",
                email = "email",
                nickName = "nickName",
                photoUrl = "photoUrl",
                credits = 0,
                points = 0,
                groupId = "groupId",
                provider = ProviderAuth.EMAIL
            )
        }
    }
}
