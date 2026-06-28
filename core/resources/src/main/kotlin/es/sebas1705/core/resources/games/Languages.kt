package es.sebas1705.resources.games


import es.sebas1705.core.resources.R

/**
 * Enum class for languages
 *
 * @property strRes String resources
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
enum class Languages(val strRes: Int) {
    ANY(es.sebas1705.core.resources.R.string.core_resources_any),
    EN(R.string.core_resources_lan_en),
    ES(R.string.core_resources_lan_es),
}