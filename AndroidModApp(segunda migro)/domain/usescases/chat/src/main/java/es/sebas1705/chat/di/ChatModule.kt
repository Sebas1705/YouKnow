package es.sebas1705.chat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.chat.ChatUsesCases
import es.sebas1705.chat.RemoveMessagesListener
import es.sebas1705.chat.SendMessage
import es.sebas1705.chat.SetMessagesListener
import es.sebas1705.realtime.repository.RealtimeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

    @Provides
    @Singleton
    fun provideChatUsesCases(realtimeRepository: RealtimeRepository): ChatUsesCases =
        ChatUsesCases(
            sendMessage = SendMessage(realtimeRepository),
            setMessagesListener = SetMessagesListener(realtimeRepository),
            removeMessagesListener = RemoveMessagesListener(realtimeRepository)
        )
}
