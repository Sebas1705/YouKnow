package es.sebas1705.youknow.core.classes.enums

import es.sebas1705.youknow.R

enum class QuizType(val id: String?, val strRes: Int, val multiPoints: Double) {
    ANY(null, R.string.any, 0.0),
    BOOLEAN("boolean", R.string.type_boolean, 0.5),
    MULTIPLE("multiple", R.string.type_multiple, 1.0);

    companion object {
        fun getType(id: String): QuizType = entries.find { it.id == id } ?: ANY
    }
}