package es.sebas1705.user.usescases
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

import es.sebas1705.mappers.toUserModel
import es.sebas1705.models.social.UserModel
import es.sebas1705.firestore.repository.FirestoreRepository

/**
 * Use case to set a listener to get user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to get the user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class SetUserListener(
    private val firestoreRepository: FirestoreRepository
) {
    operator fun invoke(
        firebaseId: String,
        onDataChange: (UserModel) -> Unit
    ) = firestoreRepository.setUserListener(firebaseId, {
        onDataChange(it.toUserModel(firebaseId))
    })
}