package es.sebas1705.chat.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.chat.ChatUsesCases
import es.sebas1705.chat.usescases.RemoveMessagesListener
import es.sebas1705.chat.usescases.SendMessage
import es.sebas1705.chat.usescases.SetMessagesListener
import es.sebas1705.realtime.repository.RealtimeRepository
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

    /**
     * Function to provide chat use cases
     *
     * @param realtimeRepository [RealtimeRepository]: Repository to access to the realtime database
     *
     * @return [ChatUsesCases]: Use cases of the chat
     *
     * @since 1.0.0
     * @author Sebas1705 12/09/2025
     */
    @Provides
    @Singleton
    fun provideChatUsesCases(
        realtimeRepository: RealtimeRepository
    ): ChatUsesCases = ChatUsesCases(
        sendMessage = SendMessage(realtimeRepository),
        setMessagesListener = SetMessagesListener(realtimeRepository),
        removeMessagesListener = RemoveMessagesListener(realtimeRepository)
    )

}