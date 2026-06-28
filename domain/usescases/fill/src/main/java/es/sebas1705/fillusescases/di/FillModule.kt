package es.sebas1705.fillusescases.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.files.repository.FileRepository
import es.sebas1705.fillusescases.FillUsesCases
import es.sebas1705.fillusescases.usescases.FillByDefaultFamilies
import es.sebas1705.fillusescases.usescases.FillByDefaultQuestions
import es.sebas1705.fillusescases.usescases.FillByDefaultWords
import es.sebas1705.room.repository.DatabaseRepository
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object FillModule {

    /**
     * Function to provide fill use cases
     *
     * @param fileRepository [FileRepository]: Repository to access to the files
     * @param databaseRepository [DatabaseRepository]: Repository to access to the database
     *
     * @return [FillUsesCases]: Use cases of the fill
     *
     * @since 1.0.0
     * @author Sebas1705 12/09/2025
     */
    @Provides
    @Singleton
    fun provideFillUsesCases(
        fileRepository: FileRepository,
        databaseRepository: DatabaseRepository
    ): FillUsesCases = FillUsesCases(
        fillByDefaultFamilies = FillByDefaultFamilies(fileRepository, databaseRepository),
        fillByDefaultQuestions = FillByDefaultQuestions(fileRepository, databaseRepository),
        fillByDefaultWords = FillByDefaultWords(fileRepository, databaseRepository)
    )

}