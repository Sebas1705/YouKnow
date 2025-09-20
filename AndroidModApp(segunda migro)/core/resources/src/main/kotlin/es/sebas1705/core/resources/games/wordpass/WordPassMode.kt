package es.sebas1705.resources.games.wordpass


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Enum class that represents the possible modes of the WordPass game.
 *
 * @param strRes [Int]: The string resources of the mode.
 * @param icon [ImageVector]: The icon of the mode.
 * @param numWords [Int]: The number of words that the mode will have.
 * @param multiPoints [Double]: The multiplier of the points of the mode.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
enum class WordPassMode(
    val strRes: Int,
    val icon: ImageVector,
    val numWords: Int,
    val multiPoints: Double
) {
    SURVIVAL(es.sebas1705.core.resources.R.string.core_resources_survival, Icons.Filled.LocalFireDepartment, 100, 1.5),
    FIRE_WHEEL(es.sebas1705.core.resources.R.string.core_resources_fire_wheel, Icons.Filled.Settings, Letter.entries.size, 5.0),
    ALEATORY(es.sebas1705.core.resources.R.string.core_resources_aleatory, Icons.Filled.Casino, 20, 1.0)
}