package com.estudo.projetofatura.domain.models.UserCase

import com.estudo.projetofatura.Data.remote.Invoice
import com.estudo.projetofatura.Utils.faturasParaJson
import com.estudo.projetofatura.domain.models.ApplyKey
import com.estudo.projetofatura.domain.models.Repository.PreferencesRepository

class SalveUserCase(
    private val repository: PreferencesRepository

) {
    suspend fun executar(faturas: List<Invoice>){
        val faturaJson = faturasParaJson(faturas)
        repository.put(ApplyKey.SALVAR_FATURAS, faturaJson)
    }
}

