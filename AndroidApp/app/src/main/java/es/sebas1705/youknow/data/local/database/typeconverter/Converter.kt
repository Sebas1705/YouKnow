package es.sebas1705.youknow.data.local.database.typeconverter

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import es.sebas1705.youknow.core.classes.enums.Category
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.enums.Languages
import es.sebas1705.youknow.core.classes.enums.Letter
import es.sebas1705.youknow.core.classes.enums.QuizType

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
