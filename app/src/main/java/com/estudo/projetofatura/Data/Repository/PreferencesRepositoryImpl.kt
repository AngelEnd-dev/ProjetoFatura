package com.estudo.projetofatura.Data.Repository

import com.estudo.projetofatura.Data.DataStore.DataStoreManager
import com.estudo.projetofatura.Data.Mapper.toPreferencesKey
import com.estudo.projetofatura.domain.models.ApplyKey
import com.estudo.projetofatura.domain.models.Repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow


class PreferencesRepositoryImpl (
    private val dataStoreManager: DataStoreManager
): PreferencesRepository {

    override suspend fun put(applyKey: ApplyKey, value: String) {
        dataStoreManager.editPreferencesKey(applyKey.toPreferencesKey(), value)
    }

    override suspend fun get(
        applyKey: ApplyKey
    ): Flow<String?> {
        return dataStoreManager.readPreferenceKey(applyKey.toPreferencesKey())
    }

    override suspend fun remove(applyKey: ApplyKey) {
        dataStoreManager.removePreferenceKey(applyKey.toPreferencesKey() )

    }
    override suspend fun clear() {
        dataStoreManager.clearAllPreferences()
    }

    override suspend fun salvar() {

    }

    override suspend fun obter() {

    }

}
//Implementação da Interface do Domain (PreferencesRepository)
//Tem haver com a persistenci de dados do DataStore(ler, apagar, gravar)
