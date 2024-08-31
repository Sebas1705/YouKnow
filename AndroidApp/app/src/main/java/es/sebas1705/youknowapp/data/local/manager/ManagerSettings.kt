package es.sebas1705.youknowapp.data.local.manager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import es.sebas1705.youknowapp.domain.utils.Constants
import es.sebas1705.youknowapp.domain.utils.Constants.USER_SETTINGS

val Context.dataStore by preferencesDataStore(name = USER_SETTINGS)

object PreferencesKeys{
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}
