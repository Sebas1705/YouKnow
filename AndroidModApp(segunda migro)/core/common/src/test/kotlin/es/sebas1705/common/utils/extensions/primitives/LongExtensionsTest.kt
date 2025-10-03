package es.sebas1705.common.utils.extensions.primitives

import es.sebas1705.common.utlis.extensions.primitives.millisToFormatDate
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class LongExtensionsTest {

    @Test fun `millisToFormatDate formats epoch millis correctly`() {
        val epochMillis = 0L
        val formattedDate = epochMillis.millisToFormatDate()
        assertEquals("00:00 - 01/01/1970", formattedDate)
    }

    @Test fun `millisToFormatDate formats current time correctly`() {
        val currentMillis = System.currentTimeMillis()
        val formattedDate = currentMillis.millisToFormatDate()
        val expectedDate = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy")
            .withZone(ZoneId.of("UTC"))
            .format(java.time.Instant.ofEpochMilli(currentMillis))
        assertEquals(expectedDate, formattedDate)
    }

    @Test fun `millisToFormatDate handles large epoch millis`() {
        val largeMillis = 32503680000000L // Year 3000
        val formattedDate = largeMillis.millisToFormatDate()
        assertEquals("00:00 - 01/01/3000", formattedDate)
    }

    @Test fun `millisToFormatDate handles negative epoch millis`() {
        val negativeMillis = -1L
        val formattedDate = negativeMillis.millisToFormatDate()
        assertEquals("23:59 - 31/12/1969", formattedDate)
    }
}