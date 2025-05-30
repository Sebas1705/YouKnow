package es.sebas1705.groups.di
/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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
 * @author Sebastián Ramiro Entrerrios García
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
     * @author Sebastián Ramiro Entrerrios García
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