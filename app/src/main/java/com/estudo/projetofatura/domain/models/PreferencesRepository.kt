package com.estudo.projetofatura.domain.models

import androidx.datastore.preferences.core.Preferences
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
}