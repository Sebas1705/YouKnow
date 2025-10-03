package es.sebas1705.common.utils.extensions.composables

import androidx.compose.material3.adaptive.Posture
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import es.sebas1705.common.utlis.extensions.composables.filterHeight
import es.sebas1705.common.utlis.extensions.composables.filterWidth
import es.sebas1705.common.utlis.extensions.composables.navigationSuiteType
import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Tests for extension functions in [WindowAdaptiveInfo.kt].
 *
 * @since 0.1.0
 * @author Sebas1705 02/10/2025
 */
class WindowAdaptiveInfoExtensionsTest {

    // Helper to create a WindowAdaptiveInfo for a given size.
    private fun createAdaptiveInfo(width: Int, height: Int): WindowAdaptiveInfo {
        val size = DpSize(width.dp, height.dp)
        val windowSizeClass = WindowSizeClass(size.width.value, size.height.value)
        return WindowAdaptiveInfo(windowSizeClass, windowPosture = Posture()) // Posture is not needed for these tests
    }

    // --- filterWidth Tests ---

    @Test
    fun `filterWidth returns compact value for compact width`() {
        val adaptiveInfo = createAdaptiveInfo(width = 400, height = 800)
        val result = adaptiveInfo.filterWidth(compact = "Compact", medium = "Medium", expanded = "Expanded")
        assertEquals(result,"Compact")
    }

    @Test
    fun `filterWidth returns medium value for medium width`() {
        val adaptiveInfo = createAdaptiveInfo(width = 700, height = 800)
        val result = adaptiveInfo.filterWidth(compact = "Compact", medium = "Medium", expanded = "Expanded")
        assertEquals(result,"Medium")
    }

    @Test
    fun `filterWidth returns expanded value for expanded width`() {
        val adaptiveInfo = createAdaptiveInfo(width = 900, height = 800)
        val result = adaptiveInfo.filterWidth(compact = "Compact", medium = "Medium", expanded = "Expanded")
        assertEquals(result,"Expanded")
    }

    // --- filterHeight Tests ---

    @Test
    fun `filterHeight returns compact value for compact height`() {
        val adaptiveInfo = createAdaptiveInfo(width = 800, height = 400)
        val result = adaptiveInfo.filterHeight(compact = "Compact", medium = "Medium", expanded = "Expanded")
        assertEquals(result,"Compact")
    }

    @Test
    fun `filterHeight returns medium value for medium height`() {
        val adaptiveInfo = createAdaptiveInfo(width = 800, height = 600)
        val result = adaptiveInfo.filterHeight(compact = "Compact", medium = "Medium", expanded = "Expanded")
        assertEquals(result,"Medium")
    }

    @Test
    fun `filterHeight returns expanded value for expanded height`() {
        val adaptiveInfo = createAdaptiveInfo(width = 800, height = 1000)
        val result = adaptiveInfo.filterHeight(compact = "Compact", medium = "Medium", expanded = "Expanded")
        assertEquals(result,"Expanded")
    }

    // --- navigationSuiteType Tests ---

    @Test
    fun `navigationSuiteType returns NavigationBar for compact width`() {
        val adaptiveInfo = createAdaptiveInfo(width = 400, height = 800)
        assertEquals(adaptiveInfo.navigationSuiteType,NavigationSuiteType.Companion.NavigationBar)
    }

    @Test
    fun `navigationSuiteType returns NavigationRail for medium width`() {
        val adaptiveInfo = createAdaptiveInfo(width = 700, height = 800)
        assertEquals(adaptiveInfo.navigationSuiteType,NavigationSuiteType.Companion.NavigationRail)
    }

    @Test
    fun `navigationSuiteType returns NavigationDrawer for expanded width`() {
        val adaptiveInfo = createAdaptiveInfo(width = 900, height = 800)
        assertEquals(adaptiveInfo.navigationSuiteType,NavigationSuiteType.Companion.NavigationDrawer)
    }
}