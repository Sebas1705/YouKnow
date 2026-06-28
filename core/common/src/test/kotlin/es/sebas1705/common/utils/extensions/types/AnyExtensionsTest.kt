package es.sebas1705.common.utils.extensions.types

import android.util.Log
import es.sebas1705.common.utlis.extensions.types.logD
import es.sebas1705.common.utlis.extensions.types.logE
import es.sebas1705.common.utlis.extensions.types.logI
import es.sebas1705.common.utlis.extensions.types.logV
import es.sebas1705.common.utlis.extensions.types.logW
import es.sebas1705.common.utlis.extensions.types.logWTF
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.MockedStatic
import org.mockito.Mockito

class AnyExtensionsTest {

    // 1. Declare a variable to hold the static mock
    private lateinit var logMock: MockedStatic<Log>

    private val instance = Any()
    private val tag = instance.javaClass.simpleName

    @Before
    fun setUp() {
        // 2. Create the static mock before each test
        logMock = Mockito.mockStatic(Log::class.java)
    }

    @After
    fun tearDown() {
        // 3. Close the mock after each test to avoid side effects
        logMock.close()
    }

    @Test
    fun `logI logs message with INFO level`() {
        val message = "Info message"
        instance.logI(message)
        // 4. Verify on the logMock object, NOT on Mockito
        logMock.verify { Log.i(tag, message) }
    }

    @Test
    fun `logE logs message with ERROR level`() {
        val message = "Error message"
        instance.logE(message)
        logMock.verify { Log.e(tag, message) }
    }

    @Test
    fun `logD logs message with DEBUG level`() {
        val message = "Debug message"
        instance.logD(message)
        logMock.verify { Log.d(tag, message) }
    }

    @Test
    fun `logV logs message with VERBOSE level`() {
        val message = "Verbose message"
        instance.logV(message)
        logMock.verify { Log.v(tag, message) }
    }

    @Test
    fun `logW logs message with WARN level`() {
        val message = "Warn message"
        instance.logW(message)
        logMock.verify { Log.w(tag, message) }
    }

    @Test
    fun `logWTF logs message with WTF level`() {
        val message = "WTF message"
        instance.logWTF(message)
        logMock.verify { Log.wtf(tag, message) }
    }
}
