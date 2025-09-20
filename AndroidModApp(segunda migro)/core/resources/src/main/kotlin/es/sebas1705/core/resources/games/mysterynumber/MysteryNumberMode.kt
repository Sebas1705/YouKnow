package es.sebas1705.resources.games.mysterynumber


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Enum class that represents the modes of the Mystery Number game.
 *
 * @param strRes [Int]: String resources of the mode.
 * @param icon [ImageVector]: Icon of the mode.
 * @param lives [Int]: Lives of the mode.
 * @param multiPoints [Double]: Multiplier of the points.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
enum class MysteryNumberMode(
    val strRes: Int,
    val icon: ImageVector,
    val lives: Int,
    val multiPoints: Double
) {
    TIME_ATTACK(es.sebas1705.core.resources.R.string.core_resources_time_attack, Icons.Filled.HourglassBottom, 100, 1.2),
    ALEATORY(es.sebas1705.core.resources.R.string.core_resources_aleatory, Icons.Filled.Casino, 20, 1.0),
    CUSTOM(es.sebas1705.core.resources.R.string.core_resources_custom, Icons.Filled.DashboardCustomize, 20, 1.0),
}