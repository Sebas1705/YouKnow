package es.sebas1705.youknow.data.local.database
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

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.sebas1705.room.daos.FamiliesDao
import es.sebas1705.room.daos.QuestionDao
import es.sebas1705.room.daos.SurveyDao
import es.sebas1705.room.daos.WordDao
import es.sebas1705.room.entities.FamiliesEntity
import es.sebas1705.room.entities.QuestionEntity
import es.sebas1705.room.entities.SurveyEntity
import es.sebas1705.room.entities.WordEntity
import es.sebas1705.youknow.data.local.database.typeconverter.Converter

/**
 * Local database
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Database(
    entities = [
        QuestionEntity::class,
        FamiliesEntity::class,
        WordEntity::class,
        SurveyEntity::class
    ],
    version = 1
)
@TypeConverters(Converter::class)
abstract class Database : RoomDatabase() {

    //DAOs:
    abstract fun questionDao(): QuestionDao

    abstract fun familiesDao(): FamiliesDao

    abstract fun wordDao(): WordDao

    abstract fun surveyDao(): SurveyDao

}