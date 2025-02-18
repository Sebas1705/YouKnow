package es.sebas1705.settings.usescases
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

import es.sebas1705.datastore.repository.DatastoreRepository

/**
 * Use case to save that the app is not being opened for the first time
 *
 * @param datastoreRepository [DatastoreRepository]: repository to save the preferences
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class SaveFirstTime(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke() {
        return datastoreRepository.saveFirstTime()
    }
}