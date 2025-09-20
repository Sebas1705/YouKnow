package es.sebas1705.common.extensions.types

import android.util.Log
import org.junit.Test
import org.mockito.Mockito.*

class AnyExtensionsTest {

    @Test fun `logI logs message with INFO level`() {
        val mockLog = mock(Log::class.java)
        val instance = Any()
        instance.logI("Info message")
        verify(mockLog).i(instance::class.java.simpleName, "Info message")
    }

    @Test fun `logE logs message with ERROR level`() {
        val mockLog = mock(Log::class.java)
        val instance = Any()
        instance.logE("Error message")
        verify(mockLog).e(instance::class.java.simpleName, "Error message")
    }

    @Test fun `logD logs message with DEBUG level`() {
        val mockLog = mock(Log::class.java)
        val instance = Any()
        instance.logD("Debug message")
        verify(mockLog).d(instance::class.java.simpleName, "Debug message")
    }

    @Test fun `logV logs message with VERBOSE level`() {
        val mockLog = mock(Log::class.java)
        val instance = Any()
        instance.logV("Verbose message")
        verify(mockLog).v(instance::class.java.simpleName, "Verbose message")
    }

    @Test fun `logW logs message with WARN level`() {
        val mockLog = mock(Log::class.java)
        val instance = Any()
        instance.logW("Warn message")
        verify(mockLog).w(instance::class.java.simpleName, "Warn message")
    }

    @Test fun `logWTF logs message with WTF level`() {
        val mockLog = mock(Log::class.java)
        val instance = Any()
        instance.logWTF("WTF message")
        verify(mockLog).wtf(instance::class.java.simpleName, "WTF message")
    }
}