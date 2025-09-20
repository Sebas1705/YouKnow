package es.sebas1705.core.resources.games.families


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.ui.graphics.vector.ImageVector
import es.sebas1705.core.resources.R

/**
 * Enum class that represents the modes of the Families game.
 *
 * @param strRes [Int]: String resources of the mode.
 * @param icon [ImageVector]: Icon of the mode.
 * @param numFamilies [Int]: Number of families in the mode.
 * @param multiPoints [Double]: Multiplier of the points in the mode.
 *
 * @since 1.0.0
 * @Author Sebas1705 12/09/2025
 */
enum class FamiliesMode(
    val strRes: Int,
    val icon: ImageVector,
    val numFamilies: Int,
    val multiPoints: Double
) {
    SURVIVAL(R.string.core_resources_survival, Icons.Filled.LocalFireDepartment, 100, 1.5),
    TIME_ATTACK(R.string.core_resources_survival, Icons.Filled.HourglassBottom, 20, 1.2),
    ALEATORY(R.string.core_resources_survival, Icons.Filled.Casino, 10, 1.0),
    CUSTOM(R.string.core_resources_survival, Icons.Filled.DashboardCustomize, 0, 0.75)
}