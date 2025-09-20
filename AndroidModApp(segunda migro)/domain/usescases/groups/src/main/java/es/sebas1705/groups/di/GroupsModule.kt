package es.sebas1705.groups.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.groups.GroupUsesCases
import es.sebas1705.groups.usescases.CreateGroup
import es.sebas1705.groups.usescases.RemoveGroup
import es.sebas1705.groups.usescases.RemoveGroupsListener
import es.sebas1705.groups.usescases.SetGroupsListener
import es.sebas1705.realtime.repository.RealtimeRepository
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object GroupsModule {

    /**
     * Function to provide group use cases
     *
     * @param realtimeRepository [RealtimeRepository]: Repository to access to the realtime database
     *
     * @return [GroupUsesCases]: Use cases of the group
     *
     * @since 1.0.0
     * @author Sebas1705 12/09/2025
     */
    @Provides
    @Singleton
    fun provideGroupUsesCases(
        realtimeRepository: RealtimeRepository
    ): GroupUsesCases = GroupUsesCases(
        createGroup = CreateGroup(realtimeRepository),
        removeGroup = RemoveGroup(realtimeRepository),
        setGroupsListener = SetGroupsListener(realtimeRepository),
        removeGroupsListener = RemoveGroupsListener(realtimeRepository)
    )

}