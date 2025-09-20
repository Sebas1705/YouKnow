package es.sebas1705.familiesusescases.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.familiesusescases.FamiliesUsesCases
import es.sebas1705.familiesusescases.usescases.GenerateFamilies
import es.sebas1705.familiesusescases.usescases.InsertFamiliesList
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
object FamiliesModule {

    /**
     * Function to provide families use cases
     *
     * @param databaseRepository [DatabaseRepository]: Repository to access to the database
     *
     * @return [FamiliesUsesCases]: Use cases of the families
     *
     * @since 1.0.0
     * @author Sebas1705 12/09/2025
     */
    @Provides
    @Singleton
    fun provideFamiliesUsesCases(
        databaseRepository: DatabaseRepository
    ): FamiliesUsesCases = FamiliesUsesCases(
        generateFamilies = GenerateFamilies(databaseRepository),
        insertFamiliesList = InsertFamiliesList(databaseRepository)
    )


}