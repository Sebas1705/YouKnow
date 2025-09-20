package es.sebas1705.quizusescases.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.quizusescases.QuizUsesCases
import es.sebas1705.quizusescases.usescases.GenerateQuestionList
import es.sebas1705.quizusescases.usescases.InsertQuestionList
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
object QuizModule {
    /**
     * Function to provide quiz use cases
     *
     * @param databaseRepository [DatabaseRepository]: Repository to access to the database
     *
     * @return [QuizUsesCases]: Use cases of the quiz
     *
     * @since 1.0.0
     * @author Sebas1705 12/09/2025
     */
    @Provides
    @Singleton
    fun provideQuizUsesCases(
        databaseRepository: DatabaseRepository
    ): QuizUsesCases = QuizUsesCases(
        generateQuestionList = GenerateQuestionList(databaseRepository),
        insertQuestionList = InsertQuestionList(databaseRepository)
    )

}