package es.sebas1705.common.managers
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
 * Sealed class that represents the repositories of the application.
 *
 * @param tag [String]: the tag of the repository.
 *
 * @property Opendb [Repository]: the OpenDB repository.
 * @property Analytics [Repository]: the Analytics repository.
 * @property Authentication [Repository]: the Authentication repository.
 * @property Firestore [Repository]: the Firestore repository.
 * @property Realtime [Repository]: the Realtime repository.
 * @property Database [Repository]: the Database repository.
 * @property Datastore [Repository]: the Datastore repository.
 * @property Files [Repository]: the Files repository.
 * @property None [Repository]: the None repository.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed class Repository(val tag: String) {
    data object Opendb : Repository("OpenDB")
    data object Analytics : Repository("Analytics")
    data object Authentication : Repository("Authentication")
    data object Firestore : Repository("Firestore")
    data object Realtime : Repository("Realtime")
    data object Database : Repository("Database")
    data object Datastore : Repository("Datastore")
    data object Files : Repository("Files")
    data object None : Repository("")
}