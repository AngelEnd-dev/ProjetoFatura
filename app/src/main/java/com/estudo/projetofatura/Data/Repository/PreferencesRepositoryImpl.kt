package com.estudo.projetofatura.Data.Repository

import androidx.datastore.preferences.core.Preferences
import com.estudo.projetofatura.Data.DataStore.DataStoreManager
import com.estudo.projetofatura.Data.Mapper.toPreferencesKey
import com.estudo.projetofatura.domain.models.ApplyKey
import com.estudo.projetofatura.domain.models.PreferencesRepository
import kotlinx.coroutines.flow.Flow


class PreferencesRepositoryImpl (
    private val dataStoreManager: DataStoreManager
): PreferencesRepository {

    override suspend fun put(applyKey: ApplyKey, value: String) {
        dataStoreManager.editPreferencesKey(applyKey.toPreferencesKey(), value)
    }

    override suspend fun get(
        applyKey: ApplyKey,
        toPreferencesKey: String.() -> Preferences.Key<String>
    ): Flow<String?> {
        return dataStoreManager.readPreferenceKey(applyKey.toPreferencesKey())
    }

    override suspend fun remove(applyKey: ApplyKey) {

    }
    override suspend fun clear() {
        dataStoreManager.clearAllPreferences()
    }
}