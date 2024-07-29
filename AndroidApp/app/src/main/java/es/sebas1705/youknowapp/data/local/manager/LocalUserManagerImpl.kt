package es.sebas1705.youknowapp.data.local.manager

import android.content.Context
import es.sebas1705.youknowapp.domain.manager.LocalUserManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import es.sebas1705.youknowapp.domain.Constants
import es.sebas1705.youknowapp.domain.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

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

private val Context.dataStore by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKeys{
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}
