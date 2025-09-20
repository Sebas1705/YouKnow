package es.sebas1705.core.resources.games.quiz


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Enum class that represents the possible modes of the Quiz game.
 *
 * @param strRes [Int]: The string resources of the mode.
 * @param icon [ImageVector]: The icon of the mode.
 * @param numQuestions [Int]: The number of questions that the mode will have.
 * @param multiPoints [Double]: The multiplier of the points of the mode.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
enum class QuizMode(
    val strRes: Int,
    val icon: ImageVector,
    val numQuestions: Int,
    val multiPoints: Double
) {
    SURVIVAL(es.sebas1705.core.resources.R.string.core_resources_survival, Icons.Filled.LocalFireDepartment, 100, 1.5),
    TIME_ATTACK(es.sebas1705.core.resources.R.string.core_resources_time_attack, Icons.Filled.HourglassBottom, 20, 1.2),
    ALEATORY(es.sebas1705.core.resources.R.string.core_resources_aleatory, Icons.Filled.Casino, 10, 1.0),
    CUSTOM(es.sebas1705.core.resources.R.string.core_resources_custom, Icons.Filled.DashboardCustomize, 0, 0.75)
}