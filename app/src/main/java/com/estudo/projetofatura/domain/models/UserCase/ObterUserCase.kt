package com.estudo.projetofatura.domain.models.UserCase

import com.estudo.projetofatura.Data.remote.Invoice
import com.estudo.projetofatura.Utils.jsonParaFaturas
import com.estudo.projetofatura.domain.models.ApplyKey
import com.estudo.projetofatura.domain.models.Repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObterUserCase constructor(
    private val repository: PreferencesRepository
) {
    suspend fun executar(): Flow<List<Invoice>>{
        return repository.get(ApplyKey.SALVAR_FATURAS).map { jsonString ->
            jsonParaFaturas(jsonString)
        }
    }
}