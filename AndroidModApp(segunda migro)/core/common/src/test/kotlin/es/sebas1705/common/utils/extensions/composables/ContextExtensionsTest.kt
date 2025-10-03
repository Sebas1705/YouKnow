package es.sebas1705.common.utils.extensions.composables

import android.content.Context
import android.media.MediaPlayer
import android.widget.Toast
import es.sebas1705.common.utlis.extensions.composables.playSound
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ContextExtensionsTest {

    private lateinit var mockContext: Context
    private lateinit var mediaPlayerMockedStatic: MockedStatic<MediaPlayer>

    @Before
    fun setUp() {
        // Create a mock context once for all tests
        mockContext = mock()
        // Mock the static MediaPlayer.create method
        mediaPlayerMockedStatic = Mockito.mockStatic(MediaPlayer::class.java)
    }

    @After
    fun tearDown() {
        // Close the static mock after each test to avoid conflicts
        mediaPlayerMockedStatic.close()
    }

    @Test
    fun `printTextInToast displays correct message`() {
        val message = "Hello, World!"
        val mockToast = mock<Toast>()

        // Use a static mock for Toast as well, but only for this test
        Mockito.mockStatic(Toast::class.java).use { toastMockedStatic ->
            toastMockedStatic.`when`<Any> { Toast.makeText(mockContext, message, Toast.LENGTH_SHORT) }.thenReturn(mockToast)

            // When: The extension function is called
            mockContext.printTextInToast(message)

            // Then: Verify that the show() method was called on the Toast
            verify(mockToast).show()
        }
    }

    @Test
    fun `playSound starts media player and sets completion listener`() {
        val soundRes = 123
        val mockMediaPlayer = mock<MediaPlayer>()

        // Given: MediaPlayer.create returns our mock player
        whenever(MediaPlayer.create(mockContext, soundRes)).thenReturn(mockMediaPlayer)

        // When: The extension function is called
        mockContext.playSound(soundRes)

        // Then: Verify the player starts
        verify(mockMediaPlayer).start()

        // And also verify that a completion listener was set
        verify(mockMediaPlayer).setOnCompletionListener(any())
    }

    @Test
    fun `playSound releases player on completion`() {
        val soundRes = 123
        val mockMediaPlayer = mock<MediaPlayer>()
        val listenerCaptor = ArgumentCaptor.forClass(MediaPlayer.OnCompletionListener::class.java)

        // Given: MediaPlayer.create returns our mock player
        whenever(MediaPlayer.create(mockContext, soundRes)).thenReturn(mockMediaPlayer)

        // When: The extension function is called
        mockContext.playSound(soundRes)

        // Then: Capture the listener that was set
        verify(mockMediaPlayer).setOnCompletionListener(listenerCaptor.capture())

        // And when the captured listener is triggered, verify release() is called
        listenerCaptor.value.onCompletion(mockMediaPlayer)
        verify(mockMediaPlayer).release()
    }

    @Test
    fun `playSound does not crash with invalid resource`() {
        val soundRes = -1

        // Given: MediaPlayer.create returns null
        whenever(MediaPlayer.create(mockContext, soundRes)).thenReturn(null)

        // When: The function is called with a resource that results in a null player
        mockContext.playSound(soundRes)

        // Then: No exception is thrown and nothing is verified, as no player should be created.
        // This test passes if it completes without crashing.
    }
}
