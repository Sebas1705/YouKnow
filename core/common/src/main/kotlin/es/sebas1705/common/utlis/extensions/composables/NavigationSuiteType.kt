package es.sebas1705.common.utlis.extensions.composables

import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass




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