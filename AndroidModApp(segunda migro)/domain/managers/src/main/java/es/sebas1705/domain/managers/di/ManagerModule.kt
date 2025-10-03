package es.sebas1705.domain.managers.di

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.sebas1705.domain.managers.R
import jakarta.inject.Singleton

/**
 * Module to provide managers dependencies
 *
 * @since 0.1.0
 * @author Sebas1705 03/07/2025
 */
@Module
@InstallIn(SingletonComponent::class)
object ManagerModule {

    @Provides
    @Singleton
    fun provideMediaPlayer(
        @ApplicationContext context: Context
    ): MediaPlayer = MediaPlayer.create(
        context,
        es.sebas1705.core.resources.R.raw.music_background
    )

    @Provides
    @Singleton
    fun provideConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityManager = context.getSystemService(ConnectivityManager::class.java)

}