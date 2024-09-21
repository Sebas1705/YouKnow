package es.sebas1705.youknowapp.data.source.local.localUserManager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import es.sebas1705.youknowapp.common.Constants
import es.sebas1705.youknowapp.ui.Contrast

val Context.dataStore by preferencesDataStore(name = Constants.USER_SETTINGS)

object PreferencesKeys{
    val FIRST_TIME = booleanPreferencesKey(name = Constants.FIRST_TIME)
    val APP_VOLUME = floatPreferencesKey(name = Constants.APP_VOLUME)
    val APP_CONTRAST = intPreferencesKey(name = Constants.APP_CONTRAST)
}

object DefaultValues{
    const val FIRST_TIME = false
    const val APP_VOLUME = 1.0f
    val APP_CONTRAST = Contrast.Low
}
