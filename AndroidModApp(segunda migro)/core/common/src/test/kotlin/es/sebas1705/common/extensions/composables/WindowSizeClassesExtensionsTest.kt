package es.sebas1705.common.extensions.composables

import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.window.core.layout.WindowSizeClass
import es.sebas1705.common.utlis.extensions.composables.calcLayoutType
import es.sebas1705.common.utlis.extensions.composables.filter
import es.sebas1705.common.utlis.extensions.composables.isCompact
import es.sebas1705.common.utlis.extensions.composables.isExpanded
import es.sebas1705.common.utlis.extensions.composables.isMedium
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class WindowSizeClassesExtensionsTest {

    @Test
    fun `isCompact returns true for width below medium breakpoint`() {
        val mockWindowSizeClass = mock(WindowSizeClass::class.java)
        `when`(mockWindowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)).thenReturn(false)
        assertEquals(true, mockWindowSizeClass.isCompact())
    }

    @Test
    fun `isMedium returns true for width below expanded breakpoint`() {
        val mockWindowSizeClass = mock(WindowSizeClass::class.java)
        `when`(mockWindowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)).thenReturn(false)
        assertEquals(true, mockWindowSizeClass.isMedium())
    }

    @Test
    fun `isExpanded returns true for width at least expanded breakpoint`() {
        val mockWindowSizeClass = mock(WindowSizeClass::class.java)
        `when`(mockWindowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)).thenReturn(true)
        assertEquals(true, mockWindowSizeClass.isExpanded())
    }

    @Test
    fun `filter returns compact value for compact size class`() {
        val mockWindowSizeClass = mock(WindowSizeClass::class.java)
        `when`(mockWindowSizeClass.isCompact()).thenReturn(true)
        assertEquals("Compact", mockWindowSizeClass.filter("Compact", "Medium", "Expanded"))
    }

    @Test
    fun `filter returns medium value for medium size class`() {
        val mockWindowSizeClass = mock(WindowSizeClass::class.java)
        `when`(mockWindowSizeClass.isMedium()).thenReturn(true)
        assertEquals("Medium", mockWindowSizeClass.filter("Compact", "Medium", "Expanded"))
    }

    @Test
    fun `filter returns expanded value for expanded size class`() {
        val mockWindowSizeClass = mock(WindowSizeClass::class.java)
        `when`(mockWindowSizeClass.isExpanded()).thenReturn(true)
        assertEquals("Expanded", mockWindowSizeClass.filter("Compact", "Medium", "Expanded"))
    }

    @Test
    fun `calcLayoutType returns NavigationDrawer for expanded size class`() {
        val mockWindowAdaptiveInfo = mock(WindowAdaptiveInfo::class.java)
        val mockWindowSizeClass = mock(WindowSizeClass::class.java)
        `when`(mockWindowAdaptiveInfo.windowSizeClass).thenReturn(mockWindowSizeClass)
        `when`(mockWindowSizeClass.isExpanded()).thenReturn(true)
        assertEquals(NavigationSuiteType.NavigationDrawer, mockWindowAdaptiveInfo.calcLayoutType())
    }

    @Test
    fun `calcLayoutType returns NavigationBar for non-expanded size class`() {
        val mockWindowAdaptiveInfo = mock(WindowAdaptiveInfo::class.java)
        val mockWindowSizeClass = mock(WindowSizeClass::class.java)
        `when`(mockWindowAdaptiveInfo.windowSizeClass).thenReturn(mockWindowSizeClass)
        `when`(mockWindowSizeClass.isExpanded()).thenReturn(false)
        assertEquals(NavigationSuiteType.NavigationBar, mockWindowAdaptiveInfo.calcLayoutType())
    }
}