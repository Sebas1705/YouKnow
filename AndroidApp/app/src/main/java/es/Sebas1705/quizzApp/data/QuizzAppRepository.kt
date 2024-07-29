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
 */

package es.Sebas1705.quizzApp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import es.Sebas1705.quizzApp.data.local.database.QuizzApp
import es.Sebas1705.quizzApp.data.local.database.QuizzAppDao
import javax.inject.Inject

interface QuizzAppRepository {
    val quizzApps: Flow<List<String>>

    suspend fun add(name: String)
}

class DefaultQuizzAppRepository @Inject constructor(
    private val quizzAppDao: QuizzAppDao
) : QuizzAppRepository {

    override val quizzApps: Flow<List<String>> =
        quizzAppDao.getQuizzApps().map { items -> items.map { it.name } }

    override suspend fun add(name: String) {
        quizzAppDao.insertQuizzApp(QuizzApp(name = name))
    }
}
