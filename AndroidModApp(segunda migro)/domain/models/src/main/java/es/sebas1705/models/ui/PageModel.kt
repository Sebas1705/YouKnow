package es.sebas1705.youknow.domain.model.ui


/**
 * Model to represent a page
 *
 * @property title [String]: Title of the page
 * @property introduction [String]: Introduction of the page
 * @property imagesAndDescription [List<Pair<Int, String>]: List of images and descriptions
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class PageModel(
    val title: String,
    val introduction: String,
    val imagesAndDescription: List<Pair<Int, String>>
)