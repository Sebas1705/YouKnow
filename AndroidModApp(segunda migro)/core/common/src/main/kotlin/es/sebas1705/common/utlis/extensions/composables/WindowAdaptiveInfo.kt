package es.sebas1705.common.utlis.extensions.composables

import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

/**
 * Filter function for WindowAdaptiveInfo to return different values based on the width
 *
 * @receiver [WindowAdaptiveInfo]: The window adaptive info to filter.
 * @param compact [T]: Value to return if the size class is compact.
 * @param medium [T]: Value to return if the size class is medium.
 * @param expanded [T]: Value to return if the size class is expanded.
 *
 * @return [T]: The value corresponding to the current size class.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
fun <T> WindowAdaptiveInfo.filterWidth(
    compact: T,
    medium: T,
    expanded: T
): T = when {
    windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> expanded
    windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> medium
    else -> compact
}

/**
 * Filter function for WindowAdaptiveInfo to return different values based on the height
 *
 * @receiver [WindowAdaptiveInfo]: The window adaptive info to filter.
 *
 * @param compact [T]: Value to return if the size class is compact.
 * @param medium [T]: Value to return if the size class is medium.
 * @param expanded [T]: Value to return if the size class is expanded.
 *
 * @return [T]: The value corresponding to the current size class.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
fun <T> WindowAdaptiveInfo.filterHeight(
    compact: T,
    medium: T,
    expanded: T
): T = when {
    windowSizeClass.isHeightAtLeastBreakpoint(WindowSizeClass.HEIGHT_DP_EXPANDED_LOWER_BOUND) -> expanded
    windowSizeClass.isHeightAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> medium
    else -> compact
}

/**
 * Determines the appropriate navigation suite type based on the window's width size class.
 *
 * This extension property follows Material Design's adaptive layout guidelines:
 * - **Compact width:** Suggests a bottom NavigationBar.
 * - **Medium width:** Suggests a side NavigationRail.
 * - **Expanded width:** Suggests a permanent NavigationDrawer.
 *
 * @receiver The [WindowAdaptiveInfo] from which to derive the navigation type.
 * @return The calculated [NavigationSuiteType] for the current window size.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025 // Updated on 02/10/2025
 */
val WindowAdaptiveInfo.navigationSuiteType: NavigationSuiteType
    get() = this.filterWidth(
        compact = NavigationSuiteType.NavigationBar,
        medium = NavigationSuiteType.NavigationRail,
        expanded = NavigationSuiteType.NavigationDrawer
    )