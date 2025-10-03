package es.sebas1705.common.utils.extensions.primitives

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import androidx.compose.ui.unit.Dp
import es.sebas1705.common.utlis.extensions.primitives.toDp
import es.sebas1705.common.utlis.extensions.primitives.toReducedString
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class IntExtensionsTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockResources: Resources

    private lateinit var displayMetrics: DisplayMetrics

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        displayMetrics = DisplayMetrics()
        `when`(mockContext.resources).thenReturn(mockResources)
        `when`(mockResources.displayMetrics).thenReturn(displayMetrics)
    }

    @Test fun `toDp converts px to dp correctly`() {
        displayMetrics.density = 2.0f
        val pxValue = 20
        val dpValue = pxValue.toDp(mockContext)
        assertEquals(Dp(10f), dpValue)
    }

    @Test fun `toDp handles zero px value`() {
        displayMetrics.density = 2.0f
        val pxValue = 0
        val dpValue = pxValue.toDp(mockContext)
        assertEquals(Dp(0f), dpValue)
    }

    @Test fun `toDp handles negative px value`() {
        displayMetrics.density = 2.0f
        val pxValue = -20
        val dpValue = pxValue.toDp(mockContext)
        assertEquals(Dp(-10f), dpValue)
    }

    @Test fun `toReducedString formats numbers below 1000 correctly`() {
        val value = 999
        val reducedString = value.toReducedString()
        assertEquals("999", reducedString)
    }

    @Test fun `toReducedString formats numbers in thousands correctly`() {
        val value = 1500
        val reducedString = value.toReducedString()
        assertEquals("1.5k", reducedString)
    }

    @Test fun `toReducedString formats numbers in millions correctly`() {
        val value = 2500000
        val reducedString = value.toReducedString()
        assertEquals("2.5M", reducedString)
    }

    @Test fun `toReducedString handles edge case of exactly 1000`() {
        val value = 1000
        val reducedString = value.toReducedString()
        assertEquals("1.0k", reducedString)
    }

    @Test fun `toReducedString handles edge case of exactly 1000000`() {
        val value = 1000000
        val reducedString = value.toReducedString()
        assertEquals("1.0M", reducedString)
    }
}