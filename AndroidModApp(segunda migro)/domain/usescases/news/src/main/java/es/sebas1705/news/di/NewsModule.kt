package es.sebas1705.news.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.news.NewsUsesCases
import es.sebas1705.news.usescases.GetNews
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
object NewsModule {

    /**
     * Function to provide news use cases
     *
     * @param firestoreRepository [FirestoreRepository]: Repository to access to the firestore
     *
     * @return [NewsUsesCases]: Use cases of the news
     *
     * @since 1.0.0
     * @author Sebas1705 12/09/2025
     */
    @Provides
    @Singleton
    fun provideNewUsesCases(
        firestoreRepository: FirestoreRepository
    ): NewsUsesCases = NewsUsesCases(
        getNews = GetNews(firestoreRepository)
    )

}