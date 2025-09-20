package es.sebas1705.common.extensions.composables

import android.content.Context
import android.media.MediaPlayer
import es.sebas1705.common.utlis.extensions.composables.playSound
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class ContextExtensionsTest {

    @Test
    fun `printTextInToast displays correct message`() {
        val mockContext = mock(Context::class.java)
        val message = "Hello, World!"
        mockContext.printTextInToast(message)
        verify(mockContext).printTextInToast(message)
    }

    @Test
    fun `playSound starts media player with valid resource`() {
        val mockContext = mock(Context::class.java)
        val mockMediaPlayer = mock(MediaPlayer::class.java)
        val soundRes = 123
        `when`(MediaPlayer.create(mockContext, soundRes)).thenReturn(mockMediaPlayer)
        mockContext.playSound(soundRes)
        verify(mockMediaPlayer).start()
    }

    @Test
    fun `playSound does not crash with invalid resource`() {
        val mockContext = mock(Context::class.java)
        val soundRes = -1
        `when`(MediaPlayer.create(mockContext, soundRes)).thenReturn(null)
        mockContext.playSound(soundRes)
        // No exception should be thrown
    }
}
