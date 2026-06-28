package es.sebas1705.repositories.repos

import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.common.managers.ClassLogData
import es.sebas1705.repositories.interfaces.IDatabaseRepository
import es.sebas1705.room.Database
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val roomDatabase: Database,
    private val logEventDataSource: LogEventDataSource
) : IDatabaseRepository, ClassLogData()
