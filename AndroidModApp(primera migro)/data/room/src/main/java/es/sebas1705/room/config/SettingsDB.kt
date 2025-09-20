package es.sebas1705.room.config
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
 * Object with the settings of the database
 *
 * @property DATABASE_NAME [String]: Database name
 * @property QUESTION_TABLE [String]: Question table name
 * @property FAMILIES_TABLE [String]: Families table name
 * @property WORD_TABLE [String]: Word table name
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
object SettingsDB {
    const val DATABASE_NAME = "YouKnow_database"
    const val QUESTION_TABLE = "questions_table"
    const val FAMILIES_TABLE = "families_table"
    const val WORD_TABLE = "word_table"
    const val SURVEY_TABLE = "survey_table"
}