package es.sebas1705.common.utils.extensions.composables

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import androidx.compose.ui.unit.dp
import es.sebas1705.common.utlis.extensions.composables.toPx
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DpExtensionsTest {

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

    @Test
    fun `toPx converts dp to px correctly`() {
        displayMetrics.density = 2.0f
        val dpValue = 10.dp
        val pxValue = dpValue.toPx(mockContext)
        assertEquals(20, pxValue)
    }

    @Test
    fun `toPx handles zero dp value`() {
        displayMetrics.density = 2.0f
        val dpValue = 0.dp
        val pxValue = dpValue.toPx(mockContext)
        assertEquals(0, pxValue)
    }

    @Test
    fun `toPx handles negative dp value`() {
        displayMetrics.density = 2.0f
        val dpValue = (-5).dp
        val pxValue = dpValue.toPx(mockContext)
        assertEquals(-10, pxValue)
    }

    @Test
    fun `toPx handles density of one`() {
        displayMetrics.density = 1.0f
        val dpValue = 15.dp
        val pxValue = dpValue.toPx(mockContext)
        assertEquals(15, pxValue)
    }
}