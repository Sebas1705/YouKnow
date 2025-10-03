package es.sebas1705.realtime.jsons

/**
 * Data class to represent the group in Realtime Database
 *
 * @property description [String]: Description
 * @property members [List]<[String]>: Members
 *
 * @since 1.0.0
 * @author Sebas1705 22/09/2025
 */
data class GroupJson(
    val description: String = "",
    val members: List<String> = emptyList(),
)