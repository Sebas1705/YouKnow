package es.sebas1705.youknow.data.local.database.typeconverter
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

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.QuizType
import es.sebas1705.youknow.core.classes.enums.games.wordpass.Letter

/**
 * Class to convert the objects needed to save in the database
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class Converter {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromCategory(value: Category): Int {
        return value.ordinal
    }

    @TypeConverter
    fun toCategory(value: Int): Category {
        return Category.entries[value]
    }

    @TypeConverter
    fun fromDifficulty(value: Difficulty): Int {
        return value.ordinal
    }

    @TypeConverter
    fun toDifficulty(value: Int): Difficulty {
        return Difficulty.entries[value]
    }

    @TypeConverter
    fun fromLanguage(value: Languages): Int {
        return value.ordinal
    }

    @TypeConverter
    fun toLanguage(value: Int): Languages {
        return Languages.entries[value]
    }

    @TypeConverter
    fun fromLetter(value: Letter): Int {
        return value.ordinal
    }

    @TypeConverter
    fun toLetter(value: Int): Letter {
        return Letter.entries[value]
    }

    @TypeConverter
    fun fromQuizType(value: QuizType): Int {
        return value.ordinal
    }

    @TypeConverter
    fun toQuizType(value: Int): QuizType {
        return QuizType.entries[value]
    }
}
