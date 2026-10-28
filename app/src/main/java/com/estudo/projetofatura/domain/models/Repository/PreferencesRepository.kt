package com.estudo.projetofatura.domain.models.Repository

import com.estudo.projetofatura.domain.models.ApplyKey
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    suspend fun put(
        applyKey: ApplyKey,
        value: String,
    )
    suspend fun get( //ler
        applyKey: ApplyKey,
    ): Flow<String?>

    suspend fun remove(
        applyKey: ApplyKey,
    )

    suspend fun clear()

    suspend fun salvar()

    suspend fun obter()


}