package es.sebas1705.room.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.QuizType
import es.sebas1705.resources.games.wordpass.Letter

/**
 * Class to convert the objects needed to save in the database
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
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
