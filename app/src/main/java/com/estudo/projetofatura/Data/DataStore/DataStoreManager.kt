package com.estudo.projetofatura.Data.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


private val Context.preferencesDataStoreConfig: DataStore<Preferences> by preferencesDataStore(name = "dados_preferences")

class DataStoreManager(private val context: Context) {


    //funções genéricas - PreferencesKey (objeto)

    //leitura
    fun readPreferenceKey(key: Preferences.Key<String>): Flow<String?> {
        return context.preferencesDataStoreConfig.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[key]
            }
    }

    //edit
    suspend fun editPreferencesKey(key: Preferences.Key<String>, value: String) {
        context.preferencesDataStoreConfig.edit { preferences ->
            preferences[key] = value
        }
    }

    //clear/remove
    suspend fun clearAllPreferences() {
        context.preferencesDataStoreConfig.edit { preferences ->
            preferences.clear()
        }
    }

}
