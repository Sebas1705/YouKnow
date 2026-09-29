package es.sebas1705.opendbusescases.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.opendbusescases.DownloadQuestions
import es.sebas1705.opendbusescases.GetTriviaTenQuestionsUseCase
import es.sebas1705.opendbusescases.OpendbUsesCases
import es.sebas1705.repositories.interfaces.IOpendbRepository
import es.sebas1705.room.repository.DatabaseRepository
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebas1705 30/09/2026
 * @since 1.3.1
 */
@Module
@InstallIn(SingletonComponent::class)
object OpendbModule {

    /**
     * Function to provide OpenTDB use cases
     *
     * @param opendbRepository [IOpendbRepository]: Repository to fetch questions from OpenTDB
     * @param databaseRepository [DatabaseRepository]: Repository to store the downloaded questions
     *
     * @return [OpendbUsesCases]: Use cases of OpenTDB
     *
     * @since 1.3.1
     * @author Sebas1705 30/09/2026
     */
    @Provides
    @Singleton
    fun provideOpendbUsesCases(
        opendbRepository: IOpendbRepository,
        databaseRepository: DatabaseRepository
    ): OpendbUsesCases = OpendbUsesCases(
        downloadQuestions = DownloadQuestions(
            GetTriviaTenQuestionsUseCase(opendbRepository),
            databaseRepository
        )
    )

}
