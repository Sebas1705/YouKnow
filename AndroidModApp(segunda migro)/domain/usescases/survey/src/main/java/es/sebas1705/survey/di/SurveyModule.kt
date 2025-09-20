package es.sebas1705.survey.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.room.repository.DatabaseRepository
import es.sebas1705.survey.SurveyUsesCases
import es.sebas1705.survey.usescases.GetActualSurvey
import es.sebas1705.survey.usescases.GetAllSurveys
import es.sebas1705.survey.usescases.PublicSurvey
import es.sebas1705.firestore.repository.FirestoreRepository
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object SurveyModule {

    /**
     * Function to provide survey use cases
     *
     * @param firestoreRepository [FirestoreRepository]: Repository to access to the firestore
     * @param databaseRepository [DatabaseRepository]: Repository to access to the database
     *
     * @return [SurveyUsesCases]: Use cases of the survey
     *
     * @since 1.0.0
     * @author Sebas1705 12/09/2025
     */
    @Provides
    @Singleton
    fun provideSurveyUsesCases(
        firestoreRepository: FirestoreRepository,
        databaseRepository: DatabaseRepository
    ): SurveyUsesCases = SurveyUsesCases(
        publicSurvey = PublicSurvey(firestoreRepository, databaseRepository),
        getActualSurvey = GetActualSurvey(firestoreRepository, databaseRepository),
        getAllSurveys = GetAllSurveys(firestoreRepository),
    )
}