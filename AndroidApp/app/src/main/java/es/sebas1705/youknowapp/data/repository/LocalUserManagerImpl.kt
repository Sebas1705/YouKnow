package es.sebas1705.youknowapp.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import es.sebas1705.youknowapp.data.local.manager.PreferencesKeys
import es.sebas1705.youknowapp.data.local.manager.dataStore
import es.sebas1705.youknowapp.domain.repository.LocalUserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context
) : LocalUserManager {

    override suspend fun saveAppEntry() {
        context.dataStore.edit {
            it[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map{
            it[PreferencesKeys.APP_ENTRY] ?: false
        }
    }

}