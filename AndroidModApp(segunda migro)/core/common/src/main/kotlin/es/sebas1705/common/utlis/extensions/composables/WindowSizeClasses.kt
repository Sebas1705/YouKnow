package es.sebas1705.common.utlis.extensions.composables

import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable

/**
 * Filter function for WindowSizeClass to return different values based on the width
 *
 * @receiver [WindowSizeClass]: The window size class to filter.
 * @param compact [T]: Value to return if the size class is compact.
 * @param medium [T]: Value to return if the size class is medium.
 * @param expanded [T]: Value to return if the size class is expanded.
 *
 * @return [T]: The value corresponding to the current size class.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
fun<T> WindowSizeClass.filterWidth(
    compact: T,
    medium: T,
    expanded: T
) = when(this.widthSizeClass) {
    WindowWidthSizeClass.Compact -> compact
    WindowWidthSizeClass.Medium -> medium
    else -> expanded
}

/**
 * Filter function for WindowSizeClass to return different values based on the height
 *
 * @receiver [WindowSizeClass]: The window size class to filter.
 * @param compact [T]: Value to return if the size class is compact.
 * @param medium [T]: Value to return if the size class is medium.
 * @param expanded [T]: Value to return if the size class is expanded.
 *
 * @return [T]: The value corresponding to the current size class.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
fun<T> WindowSizeClass.filterHeight(
    compact: T,
    medium: T,
    expanded: T
) = when(this.heightSizeClass) {
    WindowHeightSizeClass.Compact -> compact
    WindowHeightSizeClass.Medium -> medium
    else -> expanded
}

/**
 * Extension function to determine the layout type based on the WindowAdaptiveInfo.
 *
 * @receiver [WindowAdaptiveInfo]: The adaptive info to check.
 *
 * @return [NavigationSuiteType]: The type of navigation suite.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
fun WindowAdaptiveInfo.calcLayoutType(): NavigationSuiteType = when {
    this.windowSizeClass -> NavigationSuiteType.NavigationDrawer
    else -> NavigationSuiteType.NavigationBar
}

/**
 * Composable function to handle different navigation suite types.
 *
 * @receiver [NavigationSuiteType]: The type of navigation suite.
 * @param onBar [@Composable] (() -> Unit): Composable for NavigationBar.
 * @param onDrawer [@Composable] (() -> Unit): Composable for NavigationDrawer.
 * @param onRail [@Composable] (() -> Unit): Composable for NavigationRail.
 * @param onNone [@Composable] (() -> Unit): Composable for no navigation.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
@Composable
fun NavigationSuiteType.compound(
    onBar: @Composable (() -> Unit) = {},
    onDrawer: @Composable (() -> Unit) = {},
    onRail: @Composable (() -> Unit) = {},
    onNone: @Composable (() -> Unit) = {}
) = when (this) {
    NavigationSuiteType.NavigationBar -> onBar()
    NavigationSuiteType.NavigationDrawer -> onDrawer()
    NavigationSuiteType.NavigationRail -> onRail()
    else -> onNone()
}

/**
 * Extension functions to check the type of navigation suite.
 *
 * @receiver [NavigationSuiteType]: The type of navigation suite.
 *
 * @return [Boolean]: True if the type matches, false otherwise.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
fun NavigationSuiteType.isNavigationBar(): Boolean = this == NavigationSuiteType.NavigationBar

/**
 * Check if the navigation suite type is NavigationDrawer.
 *
 * @receiver [NavigationSuiteType]: The type of navigation suite.
 *
 * @return [Boolean]: True if the type is NavigationDrawer, false otherwise.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
fun NavigationSuiteType.isNavigationDrawer(): Boolean = this == NavigationSuiteType.NavigationDrawer

/**
 * Check if the navigation suite type is NavigationRail.
 *
 * @receiver [NavigationSuiteType]: The type of navigation suite.
 *
 * @return [Boolean]: True if the type is NavigationRail, false otherwise.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
fun NavigationSuiteType.isNavigationRail(): Boolean = this == NavigationSuiteType.NavigationRail