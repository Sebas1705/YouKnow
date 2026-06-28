package es.sebas1705.wordpassusescases.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.room.repository.DatabaseRepository
import es.sebas1705.wordpassusescases.WordPassUsesCases
import es.sebas1705.wordpassusescases.usescases.GenerateWheelWordPass
import es.sebas1705.wordpassusescases.usescases.GenerateWordPass
import es.sebas1705.wordpassusescases.usescases.InsertWordPassList
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object WordPassModule {

    /**
     * Function to provide word pass use cases
     *
     * @param databaseRepository [DatabaseRepository]: Repository to access to the database
     *
     * @return [WordPassUsesCases]: Use cases of the word pass
     *
     * @since 1.0.0
     * @author Sebas1705 12/09/2025
     */
    @Provides
    @Singleton
    fun provideWordPassUsesCases(
        databaseRepository: DatabaseRepository
    ): WordPassUsesCases = WordPassUsesCases(
        generateWordPass = GenerateWordPass(databaseRepository),
        generateWheelWordPass = GenerateWheelWordPass(databaseRepository),
        insertWordPassList = InsertWordPassList(databaseRepository)
    )

}