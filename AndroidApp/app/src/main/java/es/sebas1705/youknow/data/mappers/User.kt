package es.sebas1705.youknow.data.mappers

import es.sebas1705.youknow.data.firebase.authentication.config.ProviderAuth
import es.sebas1705.youknow.data.firebase.firestore.documents.UserDocument
import es.sebas1705.youknow.domain.model.social.UserModel

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

// MODEL

/**
 * Mapper function
 *
 * @receiver [UserModel]: The object to convert
 *
 * @return [UserDocument]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun UserModel.toUserDocument() = UserDocument(
    email,
    provider.ordinal,
    nickName,
    photoUrl,
    groupId,
    points,
    credits
)

// DOCUMENT

/**
 * Mapper function
 *
 * @receiver [UserDocument]: The object to convert
 * @param firebaseId [String]: The firebase id
 *
 * @return [UserModel]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun UserDocument.toUserModel(firebaseId: String) = UserModel(
    firebaseId = firebaseId,
    email = email,
    provider = ProviderAuth.entries[provider],
    nickName = nickName,
    photoUrl = photoUrl,
    groupId = groupId,
    points = points,
    credits = credits
)
