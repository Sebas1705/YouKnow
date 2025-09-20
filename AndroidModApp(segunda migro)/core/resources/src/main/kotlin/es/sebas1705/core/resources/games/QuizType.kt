package es.sebas1705.resources.games

import es.sebas1705.core.resources.R



/**
 * Enum class for quiz types
 *
 * @property id the id of the quiz type
 * @property strRes String resources
 * @property multiPoints the multiplier points
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
enum class QuizType(val id: String?, val strRes: Int, val multiPoints: Double) {
    ANY(null, es.sebas1705.core.resources.R.string.core_resources_any, 0.0),
    BOOLEAN("boolean", R.string.core_resources_type_boolean, 0.5),
    MULTIPLE("multiple", R.string.core_resources_type_multiple, 1.0);

    companion object {
        fun getType(id: String): QuizType = entries.find { it.id == id } ?: ANY
    }
}