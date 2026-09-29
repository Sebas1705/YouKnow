package es.sebas1705.dailychallengeusescases.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.dailychallengeusescases.DailyChallengeUsesCases
import es.sebas1705.dailychallengeusescases.usescases.GetOrCreateDailyChallenge
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.quizusescases.usescases.GenerateQuestionList
import es.sebas1705.room.repository.DatabaseRepository
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebas1705 30/09/2026
 * @since 1.3.0
 */
@Module
@InstallIn(SingletonComponent::class)
object DailyChallengeModule {

    /**
     * Function to provide daily challenge use cases
     *
     * @param firestoreRepository [FirestoreRepository]: Repository to access to the firestore
     * @param databaseRepository [DatabaseRepository]: Repository to access to the local questions database
     *
     * @return [DailyChallengeUsesCases]: Use cases of the daily challenge
     *
     * @since 1.3.0
     * @author Sebas1705 30/09/2026
     */
    @Provides
    @Singleton
    fun provideDailyChallengeUsesCases(
        firestoreRepository: FirestoreRepository,
        databaseRepository: DatabaseRepository
    ): DailyChallengeUsesCases = DailyChallengeUsesCases(
        getOrCreateDailyChallenge = GetOrCreateDailyChallenge(
            firestoreRepository,
            GenerateQuestionList(databaseRepository)
        )
    )

}
