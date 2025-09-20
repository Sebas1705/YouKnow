package es.sebas1705.mysterynumberusescases.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.mysterynumberusescases.MysteryNumberUsesCases
import es.sebas1705.mysterynumberusescases.usescases.GenerateRandomNumber
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object MysteryNumberModule {

    /**
     * Function to provide mystery number use cases
     *
     * @return [MysteryNumberUsesCases]: Use cases of the mystery number
     *
     * @since 1.0.0
     * @author Sebas1705 12/09/2025
     */
    @Provides
    @Singleton
    fun provideMysteryNumberUsesCases(
    ): MysteryNumberUsesCases = MysteryNumberUsesCases(
        generateRandomNumber = GenerateRandomNumber()
    )

}