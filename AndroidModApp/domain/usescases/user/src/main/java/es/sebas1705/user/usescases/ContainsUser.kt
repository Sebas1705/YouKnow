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

import es.sebas1705.common.utlis.extensions.types.catcher
import es.sebas1705.firestore.repository.FirestoreRepository

/**
 * Use case to check if user exists
 *
 * @property firestoreRepository [FirestoreRepository]: repository to check if user exists
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class ContainsUser(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        firebaseId: String,
        onLoading: () -> Unit = {},
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.containsUser(firebaseId).catcher(
        onLoading,
        onSuccess,
        onError
    )
}