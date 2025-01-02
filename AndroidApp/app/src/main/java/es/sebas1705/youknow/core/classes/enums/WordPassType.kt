package es.sebas1705.youknow.core.classes.enums

import es.sebas1705.youknow.R

enum class WordPassType(val strRes: Int, val multiPoints: Double) {
    ANY(R.string.any, 0.0),
    FIRST_LETTER(R.string.first_letter, 1.0),
    BETWEEN_LETTER(R.string.between_letter, 1.25),
    LAST_LETTER(R.string.last_letter, 1.5),
}