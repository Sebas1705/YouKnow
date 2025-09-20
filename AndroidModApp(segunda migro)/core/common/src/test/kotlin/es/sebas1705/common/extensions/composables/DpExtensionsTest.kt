package es.sebas1705.common.extensions.composables

import android.content.Context
import androidx.compose.ui.unit.Dp
import es.sebas1705.common.utlis.extensions.composables.toPx
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class DpExtensionsTest {

    @Test
    fun `toPx converts dp to px correctly`() {
        val mockContext = mock(Context::class.java)
        val mockDisplayMetrics = mockContext.resources.displayMetrics
        `when`(mockDisplayMetrics.density).thenReturn(2.0f)
        val dpValue = Dp(10f)
        val pxValue = dpValue.toPx(mockContext)
        assertEquals(20, pxValue)
    }

    @Test
    fun `toPx handles zero dp value`() {
        val mockContext = mock(Context::class.java)
        val mockDisplayMetrics = mockContext.resources.displayMetrics
        `when`(mockDisplayMetrics.density).thenReturn(2.0f)
        val dpValue = Dp(0f)
        val pxValue = dpValue.toPx(mockContext)
        assertEquals(0, pxValue)
    }

    @Test
    fun `toPx handles negative dp value`() {
        val mockContext = mock(Context::class.java)
        val mockDisplayMetrics = mockContext.resources.displayMetrics
        `when`(mockDisplayMetrics.density).thenReturn(2.0f)
        val dpValue = Dp(-5f)
        val pxValue = dpValue.toPx(mockContext)
        assertEquals(-10, pxValue)
    }

    @Test
    fun `toPx handles density of one`() {
        val mockContext = mock(Context::class.java)
        val mockDisplayMetrics = mockContext.resources.displayMetrics
        `when`(mockDisplayMetrics.density).thenReturn(1.0f)
        val dpValue = Dp(15f)
        val pxValue = dpValue.toPx(mockContext)
        assertEquals(15, pxValue)
    }
}